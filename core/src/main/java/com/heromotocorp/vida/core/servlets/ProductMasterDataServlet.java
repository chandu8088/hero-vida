package com.heromotocorp.vida.core.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.apache.sling.servlets.post.JSONResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.PathInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.vo.Product;
import com.heromotocorp.vida.core.vo.ProductItemColourVariant;
import com.heromotocorp.vida.core.vo.ProductItemVariant;

/**
 * Servlet that Co all the States and Cities into the response based on the
 * Country . Servlet can be accessed using via any page using selector
 * mastergeodata.
 *
 */
@Component(service = { Servlet.class })
@SlingServletResourceTypes(resourceTypes = "vida/components/page", methods = HttpConstants.METHOD_GET, selectors = "product-master", extensions = "json")
@ServiceDescription("Master Product Info Servlet")
public class ProductMasterDataServlet extends SlingSafeMethodsServlet {

	private static final String GROSS_WEIGHT_C = "GrossWeight__c";

	private static final String RECORDS = "records";

	private static final String EMPTY_STRING = "";

	private static final String ID = "Id";

	private static final String NAME = "Name";

	private static final String DMPL_ITEM_CODE_C = "dmpl__ItemCode__c";

	private static final String DMPL_SKU_CODE_C = "dmpl__SKUCode__c";

	private static final String CHARGING_TIME_C = "ChargingTime__c";

	private static final String RANGE_C = "Range__c";

	private static final String TOP_SPEED_C = "TopSpeed__c";

	private static final String HORSE_POWER_KWH_C = "HorsePowerKWH__c";

	private static final String DMPL_PRODUCT_COLOR_C = "dmpl__ProductColor__c";

	private static final String SKU_DESCRIPTION_C = "SKUDescription__c";

	private static final String SF_ITEM_ATTRIBUTE_SKU = "dmpl__SKUs__r";

	//public static String host = "https://hvida.my.salesforce.com/services/data/v53.0/query/";

	//public static String masterDataQueryForProductVariant = "?q=SELECT+Id,Name,dmpl__Description__c,dmpl__ItemCode__c,+(+SELECT+Name,Range__c,TopSpeed__c,ChargingTime__c,HorsePowerKWH__c,Battery__c,BatteryType__c,BKTYPE__c,CC__c,ClassofVehicle__c,CreatedById,CreatedDate,dmpl__IsDefault__c,dmpl__QuantityUnitOfMeasure__c,FuelUsed__c,GrossWeight__c,Speedin3Sec__c,dmpl__ProductColor__c,SKUDescription__c,dmpl__SKUCode__c,dmpl__ItemId__c,Id+FROM+dmpl__SKUs__r+WHERE+dmpl__ProductColor__c!=''+)+FROM+dmpl__Item__c+WHERE+dmpl__ItemType__c+=+'Product' and dmpl__IsActive__c=true";

	static String masterDataQueryForProductVariant = "?q=SELECT+Id,Name,dmpl__Description__c,dmpl__ItemCode__c,+(+SELECT+Name,Range__c,TopSpeed__c,ChargingTime__c,HorsePowerKWH__c,Battery__c,BatteryType__c,BKTYPE__c,CC__c,ClassofVehicle__c,CreatedById,CreatedDate,dmpl__IsDefault__c,dmpl__QuantityUnitOfMeasure__c,FuelUsed__c,GrossWeight__c,Speedin3Sec__c,dmpl__ProductColor__c,SKUDescription__c,dmpl__SKUCode__c,dmpl__ItemId__c,Id+FROM+dmpl__SKUs__r+WHERE+dmpl__ProductColor__c!=''+)+FROM+dmpl__Item__c+WHERE+dmpl__ItemType__c+=+'Product'+and+dmpl__IsActive__c=true";

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3592470025905082741L;

	/** The Constant log. */
	private static final Logger log = LoggerFactory
			.getLogger(ProductMasterDataServlet.class);

	/** The global config. */
	@Reference
	private transient GlobalConfig globalConfig;

	/** The client config. */
	@Reference
	private transient ClientConfig clientConfig;

	/**
	 * Do get.
	 *
	 * @param req
	 *            the req
	 * @param resp
	 *            the resp
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	protected void doGet(final SlingHttpServletRequest req,
			final SlingHttpServletResponse resp) throws ServletException,
			IOException {
		PathInfo pathinf = new PathInfo(req.getPathInfo());
		String[] array = pathinf.getSelectors();
		HttpPost postRequst = new HttpPost(
				globalConfig.postRequestToken());

		List<org.apache.http.NameValuePair> nvps = new ArrayList<>();

		nvps.add(new BasicNameValuePair("grant_type", globalConfig
				.grantType()));
		nvps.add(new BasicNameValuePair("client_id", globalConfig
				.clientId()));
		nvps.add(new BasicNameValuePair("client_secret", globalConfig
				.clientSecret()));

		nvps.add(new BasicNameValuePair("username", globalConfig
				.username()));
		nvps.add(new BasicNameValuePair("password", globalConfig
				.password()));

		postRequst.setHeader(new BasicHeader("Content-Type",
				globalConfig.contentType()));

		postRequst.setEntity(new UrlEncodedFormEntity(nvps));
		if (Objects.nonNull(clientConfig.client())) {
			CloseableHttpResponse response = clientConfig.client().execute(
					postRequst);
			String jsonString = EntityUtils.toString(response.getEntity());

			Gson gson = new Gson();
			JsonElement element = gson.fromJson(jsonString, JsonElement.class);
			JsonObject jsonObject = element.getAsJsonObject();
			String token = "Bearer "
					+ jsonObject.get("access_token").getAsString();

			getProductData(token, array[0], resp);

		}
	}

	/**
	 * Gets the countries.
	 *
	 * @param token
	 *            the token
	 * @param countrySelector
	 *            the country selector
	 * @param resp
	 *            the resp
	 * @return the location data
	 */
	private void getProductData(String token, String countrySelector,
			SlingHttpServletResponse resp) {
		log.debug("Parameters for getProductData method-\t" + token + "\t" + countrySelector + "\t" + resp);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {

			HttpGet httpGet = new HttpGet(new StringBuilder(globalConfig.sfdcQueryEndpoint()).append(
					masterDataQueryForProductVariant).toString());
			httpGet.addHeader("Authorization", token);
			httpGet.addHeader("Content-Type",
					JSONResponse.RESPONSE_CONTENT_TYPE);
			CloseableHttpResponse httpResponse = null;
			httpResponse = httpClient.execute(httpGet);

			Product product = new Product();
			if (Objects.nonNull(httpResponse)) {
				String jsonString = EntityUtils.toString(httpResponse
						.getEntity());
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				JsonElement element = gson.fromJson(jsonString,
						JsonElement.class);
				JsonObject jsonObject = element.getAsJsonObject();
				JsonArray rootRecordArray = jsonObject.get(RECORDS)
						.getAsJsonArray();

				List<ProductItemVariant> items = product.getItems();
				for (JsonElement jsonElement : rootRecordArray) {

					ProductItemVariant item = new ProductItemVariant();
					JsonObject recordsObject = poplateProdctItem(jsonElement,
							item);

					JsonElement colorSKUObj = recordsObject
							.get(SF_ITEM_ATTRIBUTE_SKU);
					if (colorSKUObj != null && !colorSKUObj.isJsonNull()) {
						JsonArray variantArray = colorSKUObj.getAsJsonObject()
								.get(RECORDS).getAsJsonArray();

						List<ProductItemColourVariant> variants = item
								.getVariants();

						for (JsonElement variantElement : variantArray) {
							poplateVariant(variants, variantElement);

						}
					}
					if (item.getVariants().size() > 0)
						items.add(item);

				}
			}

			String jsonString = new Gson().toJson(product);
			resp.getWriter().write(jsonString);

		} catch (ParseException | IOException e1) {
			log.error(
					"IOException or ParseException  occured method:",e1);

		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				log.error(
						"IOException  occured method: getLocationData cause ",	e);

			}
		}
	}

	private static JsonObject poplateProdctItem(JsonElement jsonElement,
			ProductItemVariant item) {
		log.debug("Parameters for poplateProdctItem method-\t" + jsonElement + "\t" + item);
		JsonObject recordsObject = jsonElement.getAsJsonObject();
		item.setSf_id(recordsObject.get(ID).getAsString());
		item.setName(recordsObject.get(NAME).getAsString());
		item.setSku(recordsObject.get(DMPL_ITEM_CODE_C) == null
				|| recordsObject.get(DMPL_ITEM_CODE_C).isJsonNull() ? EMPTY_STRING
				: recordsObject.get(DMPL_ITEM_CODE_C).getAsString());
		return recordsObject;
	}

	private static void poplateVariant(List<ProductItemColourVariant> variants,
			JsonElement variantElement) {
		log.debug("Parameters for poplateVariant method-\t" + variants + "\t" + variantElement);
		ProductItemColourVariant variant = new ProductItemColourVariant();
		JsonObject obj = variantElement.getAsJsonObject();

		variant.setSf_id(obj.get(ID).getAsString());
		variant.setSku(obj.get(DMPL_SKU_CODE_C).getAsString());
		variant.setCharging_time(obj.get(CHARGING_TIME_C) == null
				|| obj.get(CHARGING_TIME_C).isJsonNull() ? EMPTY_STRING : obj.get(
				CHARGING_TIME_C).getAsString());

		variant.setRange(obj.get(RANGE_C) == null
				|| obj.get(RANGE_C).isJsonNull() ? EMPTY_STRING : obj.get(RANGE_C)
				.getAsString());
		variant.setTop_speed(obj.get(TOP_SPEED_C) == null
				|| obj.get(TOP_SPEED_C).isJsonNull() ? EMPTY_STRING : obj
				.get(TOP_SPEED_C).getAsString());
		variant.setAccelerator(obj.get(HORSE_POWER_KWH_C) == null
				|| obj.get(HORSE_POWER_KWH_C).isJsonNull() ? EMPTY_STRING : obj.get(
				HORSE_POWER_KWH_C).getAsString());

		variant.setColor(obj.get(DMPL_PRODUCT_COLOR_C) == null
				|| obj.get(DMPL_PRODUCT_COLOR_C).isJsonNull() ? EMPTY_STRING : obj.get(
				DMPL_PRODUCT_COLOR_C).getAsString());
		variant.setDescription(obj.get(DMPL_PRODUCT_COLOR_C) == null
				|| obj.get(SKU_DESCRIPTION_C).isJsonNull() ? EMPTY_STRING : obj.get(
				SKU_DESCRIPTION_C).getAsString());
		variant.setWeight(obj.get(GROSS_WEIGHT_C) == null
				|| obj.get(GROSS_WEIGHT_C).isJsonNull() ? EMPTY_STRING : obj.get(
						GROSS_WEIGHT_C).getAsString());
		

		variant.setName(obj.get(NAME).getAsString());
		variants.add(variant);
	}
}
