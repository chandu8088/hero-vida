package com.heromotocorp.vida.core.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import javax.jcr.Session;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.servlets.post.JSONResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.day.cq.replication.Replicator;
import com.google.common.net.UrlEscapers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.heromotocorp.vida.core.config.AutovertConfig;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.service.PricesConfigService;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;
import com.heromotocorp.vida.core.vo.CityMasterVO;
import com.heromotocorp.vida.core.vo.PriceVO;
import com.heromotocorp.vida.core.vo.VidaCenter;

/**
 * Implementation Service that pulls Product Price data, Lease/Loan offer Price   from SFDC  and Caches in AEM at DAM.
 *
 */
@Component(service = PricesConfigService.class, immediate = true)
public class PricesConfigServiceImpl implements PricesConfigService {

	private static final String SKU = "sku";

	private static final String NAME = "name";

	private static final String SALE_PRICE = "Sale Price";

	private static final String EX_SHOWROOM_PRICE = "ex_showroom_price";

	private static final String FAME_SUBSIDY_AMOUNT = "fame_subsidy_amount";

	private static final String STATE_SUBSIDY = "state_subsidy";

	private static final String PORTABLE_CHARGER = "portable_charger";

	private static final String EFFECTIVE_PRICE = "effective_price";

	private static final String PRICE = "price";

	/** The Constant log. */
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** The Resolver Factory. */
	@Reference
	private ResourceResolverFactory resolverFactory;

	/** The Replicator. */
	@Reference
	private Replicator replicator;

	/** The auth header config. */
	@Reference
	private transient AutovertConfig autovertConfig;

	@Reference
	private ClientConfig clientConfig;

	@Reference
	private transient GlobalConfig globalConfig;



	/* (non-Javadoc)
	 * @see com.heromotocorp.vida.core.service.PricesConfigService#processCityBasedPriceAndOffer()
	 *
	 *  Step -1- Read  City Master CSV fle to load in CityMasterVO
	 *  Step -2- Read  Product Master json file to load in priceVoList
	 *  Step -3- Read  Above 1 and2 to get Price based on SKU and City (actually Branch) 
	 *  
	 */
	
	@Override
	public void  processCityBasedPriceAndOffer() {

		String location = globalConfig.cityMasterCSVLocationPath() == null
				? Constants.CONTENT_DAM_VIDA_CONFIG_CITY_MASTER_CSV
				: globalConfig.cityMasterCSVLocationPath();
		List<CityMasterVO> cities = new ArrayList<>();
		ResourceResolver resolver = CommonUtils.getResourceResolver(resolverFactory, Constants.WRITERSERVICEUSER);
		Resource assetResource = resolver.getResource(location);
		Asset asset = assetResource.adaptTo(Asset.class);
		Rendition original = asset.getOriginal();
		if (original != null) {

			InputStream stream = original.getStream();
			List<String[]> records = new ArrayList<>();
			try (java.util.Scanner scanner = new java.util.Scanner(stream);) {
				while (scanner.hasNextLine()) {
					records.add(getRecordFromLine(scanner.nextLine()));
				}
				records.remove(0);
				List<PriceVO> priceVoList = new ArrayList<>();
				for (String[] record : records) {
					CityMasterVO vo = new CityMasterVO();

					vo.setCityName(record[0]);
					vo.setStateName(record[1]);
					vo.setCountryName(record[2]);
					vo.setLatitde(record[3]);
					vo.setLongitude(record[4]);
					vo.setDefaultPartnerIdCity(record[5]);
					vo.setDefaultBranchIdCity(record[6]);
					vo.setId();
					List<VidaCenter> serviceCenters = new ArrayList<>();

					vo.setServiceCenter(serviceCenters);
					cities.add(vo);
					getProductMaster(vo.getId(), vo.getDefaultBranchIdCity(), vo.getDefaultPartnerIdCity(),
							vo.getCityName(), priceVoList);

				}
				String jsonString =  new GsonBuilder().setPrettyPrinting().create().toJson(priceVoList);
				log.info(jsonString);
				if (!jsonString.isEmpty()) {
					CommonUtils.createDamAsset(jsonString, resolver, globalConfig.productPriceUrl());
					Session session = resolver.adaptTo(Session.class);
					CommonUtils.replicateContent(session, globalConfig.productPriceUrl(), replicator);

					session.logout();
				}
			}

		}
	
	}

	/**
	 * @param id
	 * @param branchId
	 * @param partnerId
	 * @param cityName
	 * @param prices
	 */
	private void getProductMaster(String id, String branchId, String partnerId, String cityName, List<PriceVO> prices) {
		log.info("Parameters for getProductMaster method-\t" + id + "\t" + branchId + "\t" + partnerId + "\t" + cityName
				+ "\t" + prices);
		JsonElement productMasterElement = readJson(globalConfig.productListUrl());
		if (Objects.nonNull(productMasterElement)) {
			JsonObject itemsObject = productMasterElement.getAsJsonObject();

			JsonArray itemsArray = itemsObject.get(Constants.ITEMS).getAsJsonArray();

			for (JsonElement jsonElement : itemsArray) {

				JsonObject jsonObject = jsonElement.getAsJsonObject();
				String itemSfId = jsonObject.get(Constants.SF_ID).getAsString();
				String itemName= jsonObject.get(NAME).getAsString();
				String itemSKU= jsonObject.get(SKU).getAsString();
				
				

				JsonArray variantArray = jsonObject.get(Constants.VARIANTS).getAsJsonArray();
				for (JsonElement variantElement : variantArray) {

					JsonObject variantObject = variantElement.getAsJsonObject();
					String variantSfId = variantObject.get(Constants.SF_ID).getAsString();

					String variantSku = variantObject.get(Constants.SKU).getAsString();
					String variantName = variantObject.get(Constants.NAMEFILED).getAsString();
					
					PriceVO priceVO = new PriceVO();
					priceVO.setItem_sf_id(itemSfId);
					priceVO.setItem_name(itemName);
					priceVO.setItem_sku(itemSKU);
					priceVO.setVariant_sf_id(variantSfId);
					priceVO.setVariant_name(variantName);
					priceVO.setCity_state_id(id);
					priceVO.setVariant_sku(variantSku);

					getPrice(cityName, branchId, partnerId, itemSfId, variantSfId, variantSku, priceVO);

					minLeaseDetails(cityName, variantSku, priceVO);
					minLoanDetails(cityName, variantSku, priceVO);
					getLeaseOfferUrl(cityName, variantSku, variantName, priceVO);
					getLoanOfferUrl(cityName, variantSku, variantName, priceVO);
					prices.add(priceVO);

				}

			}
		}

	}

	/**
	 * @param id
	 * @param branchId
	 * @param partnerId
	 * @param itemSfId
	 * @param variantSfId
	 * @param variantSku
	 * @param priceVO
	 * @return
	 */
	@SuppressWarnings("squid:S2095")
	private void getPrice(String cityName, String branchId, String partnerId, String itemSfId, String variantSfId,
			String variantSku, PriceVO priceVO) {
		log.info("Parameters for getPrice method-\t" + cityName + "\t" + branchId + "\t" + partnerId + "\t" + itemSfId + "\t"
				+ variantSfId + "\t" + variantSku + "\t" + priceVO);
		//CloseableHttpClient httpClient = HttpClients.createDefault();
		String price = StringUtils.EMPTY;
	
		String token = globalConfig.magentoBearerToken();

		String url = new StringBuilder(globalConfig.magentoUrl()).append(globalConfig.magentoPriceAPIMethod()).append("?city=").append(UrlEscapers.urlFragmentEscaper().escape(cityName))
					.append("&itemSku=").append(itemSfId).append("&itemSkuId=").append(variantSfId).toString();
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader(Constants.AUTHORIZATION,Constants.BEARER+" " +token);

		log.info("Calling Price API..."+httpGet.getURI().toString());
		
		
		CloseableHttpResponse httpResponse = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		try {
			httpResponse = httpClient.execute(httpGet);
		} catch (IOException e1) {
			log.error("IOException  occured method:", e1);

		}
		try {
			httpResponse = httpClient.execute(httpGet);

			if (Objects.nonNull(httpResponse)) {
				price = EntityUtils.toString(httpResponse.getEntity());
				log.info("Prices Entity" + price);
				Gson gson = new Gson();
				JsonElement element = gson.fromJson(price, JsonElement.class);
				JsonObject jsonObject = element.getAsJsonObject();
				if (Objects.nonNull(jsonObject.get(EX_SHOWROOM_PRICE))
						&& !jsonObject.get(EX_SHOWROOM_PRICE).isJsonNull()) {
					priceVO.setExShowRoomPrice(jsonObject.get(EX_SHOWROOM_PRICE).getAsString());
					priceVO.setOnRoadPrice(jsonObject.get(EX_SHOWROOM_PRICE).getAsString());
				}
				if (Objects.nonNull(jsonObject.get(PRICE)) && !jsonObject.get(PRICE).isJsonNull()) {
					priceVO.setPrice(jsonObject.get(PRICE).getAsString());
				}
				if (Objects.nonNull(jsonObject.get(FAME_SUBSIDY_AMOUNT))
						&& !jsonObject.get(FAME_SUBSIDY_AMOUNT).isJsonNull()) {
					priceVO.setFame2IncentivePrice(jsonObject.get(FAME_SUBSIDY_AMOUNT).getAsString());
				}
				if (Objects.nonNull(jsonObject.get(STATE_SUBSIDY)) && !jsonObject.get(STATE_SUBSIDY).isJsonNull()) {
					priceVO.setStateSubsidyPrice(jsonObject.get(STATE_SUBSIDY).getAsString());
				}
				if (Objects.nonNull(jsonObject.get(PORTABLE_CHARGER))
						&& !jsonObject.get(PORTABLE_CHARGER).isJsonNull()) {
					priceVO.setPortablechargerPrice(jsonObject.get(PORTABLE_CHARGER).getAsString());
				}
				if (Objects.nonNull(jsonObject.get(EFFECTIVE_PRICE)) && !jsonObject.get(EFFECTIVE_PRICE).isJsonNull()) {
					priceVO.setEffectivePrice(jsonObject.get(EFFECTIVE_PRICE).getAsString());
				}
				log.info("Variant Name"  + variantSfId) ;	
				log.info("Price-For" + url +"==>" +price );		
			}
		} catch (IOException e) {
			log.error("IOException  occured method: getPrice cause : %s", e);

		} finally {
			try {
				
				httpResponse.close();
			} catch (IOException e) {
				log.error("IOException  occured Closing Connection", e);

			}
		}

	}

	/**
	 * @param line
	 * @return
	 */
	private static String[] getRecordFromLine(String line) {
		List<String> values = new ArrayList<>();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(Constants.COMA);
			while (rowScanner.hasNext()) {
				values.add(rowScanner.next());
			}
		}
		return Arrays.copyOf(values.toArray(), values.toArray().length, String[].class);
	}

	/**
	 * @param cityName
	 * @param variantSku
	 * @param priceVO
	 */
	private void minLeaseDetails(String cityName, String variantSku, PriceVO priceVO) {
		log.info("Parameters for minLeaseDetails method-\t" + cityName + "\t" + variantSku + "\t" + priceVO);

		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		cityName = UrlEscapers.urlFragmentEscaper().escape(cityName.trim());// "Bangalore"; //
		variantSku = UrlEscapers.urlFragmentEscaper().escape(variantSku.trim());
		
		String url = new StringBuilder(globalConfig.autovertMinEmiEndPoint()).append("/application/lease/").append(variantSku).append("/get-offers/")
				.append(cityName.trim()).toString();
		HttpGet httpGet = new HttpGet(url);
		
		log.info("Calling Lease API \n"+ httpGet.toString());
		
		CloseableHttpResponse httpResponse = null;
		try {

			//Setting default value 
			httpResponse = httpClient.execute(httpGet);
			
			int statuscode = httpResponse.getStatusLine().getStatusCode();
						
			String jsonString = EntityUtils.toString(httpResponse.getEntity());
			

			if (Objects.nonNull(httpResponse)) {
				
				log.info(" Lease Response API ->= "+ statuscode + httpResponse.getStatusLine().getReasonPhrase()  );
				log.info(" Lease Response API String ->= "+ jsonString);
				
				
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					
					Gson gson = new Gson();

					JsonElement element = gson.fromJson(jsonString, JsonElement.class);
					JsonObject jsonObject = element.getAsJsonObject();
					if (Objects.nonNull(jsonObject)) {
						JsonArray dataArray = jsonObject.get("data").getAsJsonArray();
						List<Integer> minLeaseEmiList = new ArrayList<Integer>();
						List<Integer> minLeaseDownPaymentList = new ArrayList<Integer>();
						for (JsonElement jsonElement : dataArray) {
							JsonObject dataObject = jsonElement.getAsJsonObject();
							if (Objects.nonNull(dataObject)) {
								Integer minEmi = dataObject.get("min_emi").getAsInt();
								Integer minLeaseDownPayment = dataObject.get("min_downpayment").getAsInt();
								minLeaseEmiList.add(minEmi);

								minLeaseDownPaymentList.add(minLeaseDownPayment);
							}

						}
						
						
						priceVO.setMinLeaseEMI(Collections.min(minLeaseEmiList).toString());
						priceVO.setMinLeaseDownPayment(Collections.min(minLeaseDownPaymentList).toString());
					}
				}

			}
		} catch (IOException e1) {
			log.error("IOException  occured method:", e1);

		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				log.error("IOException  occured method: getLocationData cause ", e);

			}
		}

	}

	/**
	 * @param cityName
	 * @param variantSku
	 * @param priceVO
	 */
	private void minLoanDetails(String cityName, String variantSku, PriceVO priceVO) {
		log.info("Parameters for minLoanDetails method-\t" + cityName + "\t" + variantSku + "\t" + priceVO);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		cityName = UrlEscapers.urlFragmentEscaper().escape(cityName);// "Bangalore";//
		variantSku =  UrlEscapers.urlFragmentEscaper().escape(variantSku);// "variantSku";//
		String url = new StringBuilder(globalConfig.autovertMinEmiEndPoint()).append("/application/loan/").append(variantSku).append("/get-offers/")
				.append(cityName.trim()).toString();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse httpResponse = null;
		try {

			log.info("Calling Loan API..\n"+ httpGet.toString());	
			httpResponse = httpClient.execute(httpGet);
			
			int statuscode = httpResponse.getStatusLine().getStatusCode();
			String jsonString = EntityUtils.toString(httpResponse.getEntity());
			
			log.info(" Loan API Response ->= "+ statuscode + httpResponse.getStatusLine().getReasonPhrase()  );
			log.info("Loan API Response String ->= "+ jsonString);
			 
			
			
			
			if (Objects.nonNull(httpResponse)) {
				if (statuscode == 200) {
					
					Gson gson = new Gson();

					JsonElement element = gson.fromJson(jsonString, JsonElement.class);
					JsonObject jsonObject = element.getAsJsonObject();
					if (Objects.nonNull(jsonObject)) {
						JsonArray dataArray = jsonObject.get("data").getAsJsonArray();
						List<Integer> minLoanEmiList = new ArrayList<Integer>();
						List<Integer> minLoanDownPaymentList = new ArrayList<Integer>();
						for (JsonElement jsonElement : dataArray) {
							JsonObject dataObject = jsonElement.getAsJsonObject();
							if (Objects.nonNull(dataObject)) {
								Integer minEmi = dataObject.get("min_emi").getAsInt();
								Integer minLeaseDownPayment = dataObject.get("min_downpayment").getAsInt();
								minLoanEmiList.add(minEmi);
								minLoanDownPaymentList.add(minLeaseDownPayment);

							}

						}
						if (Collections.min(minLoanEmiList) != null){
							priceVO.setMinLoanEMI(Collections.min(minLoanEmiList).toString());
						}
						//priceVO.setMinLoanEMI(Collections.min(minLoanEmiList));
						if (Collections.min(minLoanDownPaymentList) != null){
							priceVO.setMinLoanEMI(Collections.min(minLoanDownPaymentList).toString());
						}
						
						//priceVO.setMinLoanDonpayment(Collections.min(minLoanDownPaymentList));
					}
				}

			}
		} catch (IOException e1) {
			log.error("IOException  occured method:", e1);

		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				log.error("IOException  occured method: getLocationData cause ", e);

			}
		}

	}

	/**
	 * @param cityName
	 * @param variantSku
	 * @param variantName
	 * @param price
	 * @param priceVO
	 */
	private void getLeaseOfferUrl(String cityName, String variantSku, String variantName,
			PriceVO priceVO) {
		log.info("Parameters for getLeaseOfferUrl method-\t" + cityName + "\t" + variantSku + "\t" + variantName + "\t"
				 + priceVO);
		String baseUrl = new StringBuilder(globalConfig.autovertOfferUrlEndPoint()+"/lease/get-offers").toString();// "https://sandboxms.autovert.in/lease/get-offers";
		JsonObject jsonObject = new JsonObject();
		int fameSubsidyPrice = !priceVO.getFame2IncentivePrice().isEmpty() ? Math.abs(Integer.parseInt(priceVO.getFame2IncentivePrice())) : 0;
		int stateSubsidyPrice = !priceVO.getStateSubsidyPrice().isEmpty() ? Math.abs(Integer.parseInt(priceVO.getStateSubsidyPrice())) : 0;
		jsonObject.addProperty("city", cityName.toUpperCase());
		jsonObject.addProperty("shipto_city", cityName.toUpperCase());
		jsonObject.addProperty("application_type", "LEASE");
		JsonArray jsonArray = new JsonArray();
		JsonObject assetObject = new JsonObject();
		assetObject.addProperty(SKU, variantSku);
		assetObject.addProperty("display_name", "Vida");
		assetObject.addProperty("ex_showroom_price", priceVO.getExShowRoomPrice());// price);
		assetObject.addProperty("on_road_price",priceVO.getOnRoadPrice());// price);
		assetObject.addProperty("road_tax", "0");
		assetObject.addProperty("fame_subsidy", fameSubsidyPrice);
		assetObject.addProperty("state_subsidy", stateSubsidyPrice);
		assetObject.addProperty("smart_card_fee", "0");
		assetObject.addProperty("rto_registration_fee", "0");
		assetObject.addProperty("discount", "0");
		assetObject.addProperty("comprehensive_insurance_price", "0");
		assetObject.addProperty("addon_insurance_price", "0");
		assetObject.addProperty("non_vehicle_amount", "0");
		JsonArray accessoriesArray = new JsonArray();
		/*JsonObject accessoriesObject = new JsonObject();
		accessoriesObject.addProperty("accessory_sku", "HMA_001");
		accessoriesObject.addProperty("accessory_name", "Stickers");
		accessoriesObject.addProperty("accessory_price", "200");
		accessoriesArray.add(accessoriesObject);*/
		assetObject.add("accessories", accessoriesArray);
		assetObject.addProperty("pricing_notes", "Innuagral Offers");
		jsonArray.add(assetObject);
		jsonObject.add("assets", jsonArray);

		HttpPost postRequst = new HttpPost(baseUrl);
		
		log.info("Calling Lease Offer API"+ postRequst.toString());
		log.info("Post Data "+ jsonObject.toString());
		
		setAuthHeader(postRequst);
		postRequst.setHeader(new BasicHeader(Constants.CONTENTTYPE, JSONResponse.RESPONSE_CONTENT_TYPE));
		postRequst.setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8));

		if (Objects.nonNull(clientConfig.client())) {
			try {
				CloseableHttpResponse response = clientConfig.client().execute(postRequst);
				int statuscode = response.getStatusLine().getStatusCode();
				String jsonString = EntityUtils.toString(response.getEntity());
				
				
				log.info("Loan Offer Response ->= "+ statuscode + response.getStatusLine().getReasonPhrase()  );
				log.info("Loan Offer Response String ->= "+  jsonString);
				
				
				
				if (statuscode == 200) {
					Gson gson = new Gson();

					JsonElement element = gson.fromJson(jsonString, JsonElement.class);
					JsonObject leaseOfferbject = element.getAsJsonObject();
					JsonObject dataObject = leaseOfferbject.get("data").getAsJsonObject();
					String leaseOfferRL = dataObject.get("application_link").getAsString();
					priceVO.setLeaseOfferRL(leaseOfferRL);
				}

			} catch (ParseException | IOException e) {
				log.error("Exception  occured method: postData cause : %s", e);
			}
		}
	}

	/**
	 * @param cityName
	 * @param variantSku
	 * @param variantName
	 * @param price
	 * @param priceVO
	 */
	private void getLoanOfferUrl(String cityName, String variantSku, String variantName,
			PriceVO priceVO) {
		log.info("Parameters for getLeaseOfferUrl method-\t" + cityName + "\t" + variantSku + "\t" + variantName
				+ "\t" + priceVO);
		String baseUrl = globalConfig.autovertOfferUrlEndPoint()+"/loan/get-offers";
		int fameSubsidyPrice = !priceVO.getFame2IncentivePrice().isEmpty() ? Math.abs(Integer.parseInt(priceVO.getFame2IncentivePrice())) : 0;
		int stateSubsidyPrice = !priceVO.getStateSubsidyPrice().isEmpty() ? Math.abs(Integer.parseInt(priceVO.getStateSubsidyPrice())) : 0;
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("city", cityName.toUpperCase());
		jsonObject.addProperty("shipto_city", cityName.toUpperCase());
		jsonObject.addProperty("application_type", "LOAN");
		JsonArray jsonArray = new JsonArray();
		JsonObject assetObject = new JsonObject();
		assetObject.addProperty(SKU, variantSku);
		assetObject.addProperty("display_name", priceVO.getItem_name());// variantName);
		assetObject.addProperty("ex_showroom_price", priceVO.getExShowRoomPrice());// price);
		assetObject.addProperty("on_road_price", priceVO.getExShowRoomPrice());// price);
		assetObject.addProperty("road_tax", "0");
		assetObject.addProperty("fame_subsidy", fameSubsidyPrice);
		assetObject.addProperty("state_subsidy", stateSubsidyPrice);
		assetObject.addProperty("smart_card_fee", "0");
		assetObject.addProperty("rto_registration_fee", "0");
		assetObject.addProperty("discount", "0");
		assetObject.addProperty("comprehensive_insurance_price", "0");
		assetObject.addProperty("addon_insurance_price", "0");
		assetObject.addProperty("non_vehicle_amount", "0");
		JsonArray accessoriesArray = new JsonArray();
		JsonObject accessoriesObject = new JsonObject();
		/*accessoriesObject.addProperty("accessory_sku", "HMA_001");
		accessoriesObject.addProperty("accessory_name", "Stickers");
		accessoriesObject.addProperty("accessory_price", "200");*/
		accessoriesArray.add(accessoriesObject);
		assetObject.add("accessories", accessoriesArray);
		assetObject.addProperty("pricing_notes", "Innuagral Offers");
		jsonArray.add(assetObject);
		jsonObject.add("assets", jsonArray);

		HttpPost postRequst = new HttpPost(baseUrl);
		
		log.info("Calling Loan Offer API"+ postRequst.toString());
	
		log.info("Post Data "+ jsonObject.toString());

		setAuthHeader(postRequst);
		postRequst.setHeader(new BasicHeader(Constants.CONTENTTYPE, JSONResponse.RESPONSE_CONTENT_TYPE));
		postRequst.setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8));

		if (Objects.nonNull(clientConfig.client())) {
			
			try(CloseableHttpResponse response = clientConfig.client().execute(postRequst)) {
			
				
				int statuscode = response.getStatusLine().getStatusCode();
				String jsonString = EntityUtils.toString(response.getEntity());
				
				
				log.info("Loan Offer Response ->= "+ statuscode + response.getStatusLine().getReasonPhrase()  );
				log.info("Loan Offer Response String ->= "+ jsonString);
				
				
						
				
				if (statuscode == 200) {
					//String jsonString = EntityUtils.toString(response.getEntity());
					Gson gson = new Gson();

					JsonElement element = gson.fromJson(jsonString, JsonElement.class);
					JsonObject leaseOfferbject = element.getAsJsonObject();
					JsonObject dataObject = leaseOfferbject.get("data").getAsJsonObject();
					String loanOfferURL = dataObject.get("application_link").getAsString();
					priceVO.setLoanOfferURL(loanOfferURL);
				}
			} catch (ParseException | IOException e) {
				log.error("Exception  occured method: postData cause : %s", e);
			}
		}
	}

	/**
	 * @param postRequst
	 */
	private void setAuthHeader(HttpPost postRequst) {
		String authHeader = new StringBuilder(autovertConfig.authHeader()).append(Constants.COLON)
				.append(autovertConfig.autovertClientSecret()).toString();
		String base64Credentials = new String(Base64.getEncoder().encode(authHeader.getBytes()));
		authHeader = new StringBuilder(Constants.BASIC).append(" ").append(base64Credentials).toString();
		postRequst.setHeader(Constants.AUTHORIZATION, authHeader);
	}

	/**
	 * Method that reads JSON from DAM Asset.
	 */
	private JsonElement readJson(String location) {
		log.info("Parameters for readJson method-\t" + location);
		ResourceResolver resolver = CommonUtils.getResourceResolver(resolverFactory, Constants.WRITERSERVICEUSER);
		Resource assetResource = resolver.getResource(location);
		Asset asset = assetResource.adaptTo(Asset.class);
		Rendition original = asset.getOriginal();
		try {
			if (original != null) {
				InputStream content = original.adaptTo(InputStream.class);

				StringBuilder sb = new StringBuilder();

				String line;
				BufferedReader br = new BufferedReader(new InputStreamReader(content, StandardCharsets.UTF_8));

				while ((line = br.readLine()) != null) {
					sb.append(line);
				}

				Gson gson = new Gson();
				return gson.fromJson(sb.toString(), JsonElement.class);
			}
		} catch (IOException e) {
			log.error("IOException occured method: readJson cause : %s", e);
		}
		return null;

	}
	
	
}
