package com.heromotocorp.vida.core.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.jcr.Session;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.servlets.post.JSONResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.replication.Replicator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.service.ProductMasterConfigService;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;
import com.heromotocorp.vida.core.vo.Product;
import com.heromotocorp.vida.core.vo.ProductItemColourVariant;
import com.heromotocorp.vida.core.vo.ProductItemVariant;

/**
 * Service to get Product Master data from SFDC
 *
 */
@Component(service = ProductMasterConfigService.class, immediate = true)
public class ProductMasterConfigServiceImpl implements ProductMasterConfigService {

	/** The Constant log. */
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** The Resolver Factory. */
	@Reference
	private ResourceResolverFactory resolverFactory;

	/** The Replicator. */
	@Reference
	private Replicator replicator;

	/** The global config. */
	@Reference
	private transient GlobalConfig globalConfig;

	/** The client config. */
	@Reference
	private transient ClientConfig clientConfig;

	static String masterDataQueryForProductVariant = "?q=SELECT+Id,Name,dmpl__Description__c,dmpl__ItemCode__c,+"
			+ "(+SELECT+Name,Range__c,TopSpeed__c,ChargingTime__c,HorsePowerKWH__c,HorsePower_Display_Name__c,Battery__c,BatteryType__c,BKTYPE__c,"
			+ "CC__c,ClassofVehicle__c,CreatedById,CreatedDate,dmpl__IsDefault__c,dmpl__QuantityUnitOfMeasure__c,FuelUsed__c,GrossWeight__c,Speedin3Sec__c,RidingModes__c,SeatingType__c,"
			+ "dmpl__ProductColor__c,SKUDescription__c,dmpl__SKUCode__c,dmpl__ItemId__c,"
			+ "Id+FROM+dmpl__SKUs__r+WHERE+dmpl__ProductColor__c!=''+)+FROM+dmpl__Item__c+WHERE+dmpl__ItemType__c+=+'Product'+AND+dmpl__IsActive__c=true+order+by+dmpl__ItemCode__c+desc";
	
	@Override
	public void processProductMasterJson() {
		String token = CommonUtils.getToken(globalConfig, clientConfig);
		getProductData(token);
		
	}

	public void getProductData(String token) {
		log.info("Parameters for getProductData method-\t" + token);

		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {

			HttpGet httpGet = new HttpGet(new StringBuilder(globalConfig.sfdcQueryEndpoint())
					.append(masterDataQueryForProductVariant).toString());
			
			
			httpGet.addHeader("Authorization", token);
			httpGet.addHeader("Content-Type", JSONResponse.RESPONSE_CONTENT_TYPE);
			CloseableHttpResponse httpResponse = null;
			try {
				httpResponse = httpClient.execute(httpGet);
			} catch (IOException e1) {
				log.error("IOException  occured method:", e1);

			}

			Product product = new Product();
			if (Objects.nonNull(httpResponse)) {
				String jsonString = EntityUtils.toString(httpResponse.getEntity());
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				JsonElement element = gson.fromJson(jsonString, JsonElement.class);
				JsonObject jsonObject = element.getAsJsonObject();
				JsonArray rootRecordArray = jsonObject.get(Constants.RECORDS).getAsJsonArray();

				List<ProductItemVariant> items = product.getItems();
				for (JsonElement jsonElement : rootRecordArray) {

					ProductItemVariant item = new ProductItemVariant();
					JsonObject recordsObject = poplateProdctItem(jsonElement, item);

					JsonElement colorSKUObj = recordsObject.get(Constants.SF_ITEM_ATTRIBUTE_SKU);
					if (colorSKUObj != null && !colorSKUObj.isJsonNull()) {
						JsonArray variantArray = colorSKUObj.getAsJsonObject().get(Constants.RECORDS).getAsJsonArray();

						List<ProductItemColourVariant> variants = item.getVariants();

						for (JsonElement variantElement : variantArray) {
							poplateVariant(variants, variantElement);

						}
					}
					if (item.getVariants().size() > 0)
						items.add(item);

				}
			}

			String jsonString = new GsonBuilder().setPrettyPrinting().create().toJson(product);
			log.info(jsonString);
			if (!jsonString.isEmpty()) {
				ResourceResolver resolver = CommonUtils.getResourceResolver(resolverFactory,
						Constants.WRITERSERVICEUSER);
				CommonUtils.createDamAsset(jsonString, resolver, globalConfig.productListUrl());
				Session session = resolver.adaptTo(Session.class);
				CommonUtils.replicateContent(session, globalConfig.productListUrl(), replicator);
			}
		} catch (Exception e1) {
			log.error("Exception  occured method:", e1.getMessage());

		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				log.error("IOException  occured method: getLocationData cause ", e);

			}
		}

	}

	/**
	 * @param jsonElement
	 * @param item
	 * @return
	 */
	private static JsonObject poplateProdctItem(JsonElement jsonElement, ProductItemVariant item) {
		JsonObject recordsObject = jsonElement.getAsJsonObject();
		item.setSf_id(recordsObject.get(Constants.ID).getAsString());
		item.setName(recordsObject.get(Constants.NAME).getAsString());
		item.setSku(recordsObject.get(Constants.DMPL_ITEM_CODE_C) == null
				|| recordsObject.get(Constants.DMPL_ITEM_CODE_C).isJsonNull() ? StringUtils.EMPTY
						: recordsObject.get(Constants.DMPL_ITEM_CODE_C).getAsString());
		item.set__typename("ConfigurableProduct");
		return recordsObject;
	}

	/**
	 * @param variants
	 * @param variantElement
	 */
	private static void poplateVariant(List<ProductItemColourVariant> variants, JsonElement variantElement) {
		ProductItemColourVariant variant = new ProductItemColourVariant();
		JsonObject obj = variantElement.getAsJsonObject();
		variant.set_typename("SimpleProduct");
		variant.setSf_id(obj.get(Constants.ID).getAsString());
		variant.setSku(obj.get(Constants.DMPL_SKU_CODE_C).getAsString());
		variant.setCharging_time(
				obj.get(Constants.CHARGING_TIME_C) == null || obj.get(Constants.CHARGING_TIME_C).isJsonNull()
						? StringUtils.EMPTY
						: obj.get(Constants.CHARGING_TIME_C).getAsString());

		variant.setRange(
				obj.get(Constants.RANGE_C) == null || obj.get(Constants.RANGE_C).isJsonNull() ? StringUtils.EMPTY
						: obj.get(Constants.RANGE_C).getAsString());
		variant.setTop_speed(obj.get(Constants.TOP_SPEED_C) == null || obj.get(Constants.TOP_SPEED_C).isJsonNull()
				? StringUtils.EMPTY
				: obj.get(Constants.TOP_SPEED_C).getAsString());
		variant.setAccelerator(
				obj.get(Constants.HORSE_POWER_KWH_C) == null || obj.get(Constants.HORSE_POWER_KWH_C).isJsonNull()
						? StringUtils.EMPTY
						: obj.get(Constants.HORSE_POWER_KWH_C).getAsString());

		variant.setColor(
				obj.get(Constants.DMPL_PRODUCT_COLOR_C) == null || obj.get(Constants.DMPL_PRODUCT_COLOR_C).isJsonNull()
						? StringUtils.EMPTY
						: obj.get(Constants.DMPL_PRODUCT_COLOR_C).getAsString());
		variant.setDescription(
				obj.get(Constants.DMPL_PRODUCT_COLOR_C) == null || obj.get(Constants.SKU_DESCRIPTION_C).isJsonNull()
						? StringUtils.EMPTY
						: obj.get(Constants.SKU_DESCRIPTION_C).getAsString());
		variant.setWeight(obj.get(Constants.GROSS_WEIGHT_C) == null || obj.get(Constants.GROSS_WEIGHT_C).isJsonNull()
				? StringUtils.EMPTY
				: obj.get(Constants.GROSS_WEIGHT_C).getAsString());

		variant.setName(obj.get(Constants.NAME).getAsString());
		
		
		variant.setRidingModes(
				obj.get(Constants.RidingModes__c) == null || obj.get(Constants.RidingModes__c).isJsonNull()
						? StringUtils.EMPTY
						: obj.get(Constants.RidingModes__c).getAsString());
		
		variant.setSeatingType(
				obj.get(Constants.SeatingType__c) == null || obj.get(Constants.SeatingType__c).isJsonNull()
						? StringUtils.EMPTY
						: obj.get(Constants.SeatingType__c).getAsString());
		
		
		
		
		variants.add(variant);
	}

}
