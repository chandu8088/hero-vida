package com.heromotocorp.vida.core.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;
import com.heromotocorp.vida.core.vo.ProductInfoVO;

/**
 * The type Product info model.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductInfoModel extends Constants{

	/** The Constant log. */
	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	/** The resource resolver. */
	@SlingObject
	private ResourceResolver resourceResolver;

	/** The pbvariantsku. */
	@ValueMapValue
	private String pbvariantsku;

	/** The city. */
	@ValueMapValue
	private String city;

	/**
	 * The Product master.
	 */
	List<ProductInfoBeanModel> productMaster = new ArrayList<>();
	
	/** The price master. */
	List<ProductVariantPriceMasterInfoBeanModdel> priceMaster = new ArrayList<>();
	
	/** The price master map. */
	Map<String, ProductInfoVO> priceMasterMap = new HashMap<>();
	
	/** The cityprice map. */
	Map<String, String> citypriceMap = new HashMap<>();
	
	/** The product info banner obj. */
	ProductInfoBeanModel productInfoBannerObj = new ProductInfoBeanModel();

	/**
	 * Init.
	 */
	@PostConstruct
	protected void init() {
		readProductInfoJson();
		productBannerInfoBanner();
	}

	/**
	 * Product banner info banner.
	 */
	private void productBannerInfoBanner() {

		String location = "/content/dam/vida/config/product-master.json";
		Resource assetResource = resourceResolver.getResource(location);
		assert assetResource != null;
		Asset asset = assetResource.adaptTo(Asset.class);
		assert asset != null;
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
				JsonElement element = gson.fromJson(sb.toString(), JsonElement.class);
				JsonObject jsonObject = element.getAsJsonObject();
				if (Objects.nonNull(jsonObject) && Objects.nonNull(jsonObject.get(Constants.ITEMS))) {
					JsonArray jsonArray = jsonObject.get(Constants.ITEMS).getAsJsonArray();
					if (Objects.nonNull(jsonArray)) {
						for (JsonElement jsonElement : jsonArray) {
							JsonObject jsonItemsObject = jsonElement.getAsJsonObject();
							if (Objects.nonNull(jsonItemsObject)) {
								JsonArray itemsVarinatArray = jsonItemsObject.get(Constants.VARIANTS).getAsJsonArray();
								List<ProductVariantInfoBeanModdel> temp = new ArrayList<>();
								if (Objects.nonNull(itemsVarinatArray)) {
									for (JsonElement VarinatDataElement : itemsVarinatArray) {
										JsonObject itemsVarinatObject = VarinatDataElement.getAsJsonObject();
										ProductVariantInfoBeanModdel productVariantInfoBeanModdel = new ProductVariantInfoBeanModdel();
										String sku = itemsVarinatObject.get(Constants.SKU) != null
												? itemsVarinatObject.get(Constants.SKU).getAsString()
												: StringUtils.EMPTY;
										if (StringUtils.equals(sku, pbvariantsku)) {
											if (!Objects.equals(sku, StringUtils.EMPTY)) {
												productVariantInfoBeanModdel.setVariant_sku(sku);
												readProductVariantPriceJson(sku);
											}

											String color = itemsVarinatObject.get(Constants.COLOR) != null
													? itemsVarinatObject.get(Constants.COLOR).getAsString()
													: StringUtils.EMPTY;
											if (!Objects.equals(color, StringUtils.EMPTY)) {
												productVariantInfoBeanModdel.setVarinat_color(color);
												productVariantInfoBeanModdel.setVariant_color_id(color);
											}

											for (String colorName : Constants.COLORGRADIANT.keySet()) {
												if (Objects.equals(colorName, color)) {
													String varinatColorGradiant = Constants.COLORGRADIANT
															.get(colorName);
													productVariantInfoBeanModdel
															.setVarinatColorGradiant(varinatColorGradiant);
												}
											}

											String range = itemsVarinatObject.get(Constants.RANGE) != null
													? itemsVarinatObject.get(Constants.RANGE).getAsString()
													: StringUtils.EMPTY;
											if (!range.equals(StringUtils.EMPTY)) {
												String rangeValue = "";
												String rangeUnit = StringUtils.EMPTY;
												if (range.contains(Constants.SPACE)) {
													String[] rangeValues = range.split(Constants.SPACE);

													rangeValue = rangeValues[0];
													rangeUnit = rangeValues[1];
												} else {
													rangeValue = range;
												}
												productVariantInfoBeanModdel.setVariant_range_value(rangeValue);
												productVariantInfoBeanModdel.setVariant_range_unit(rangeUnit);

											}
											String chargingTime = itemsVarinatObject.get(Constants.CHARGINGTIME) != null
													? itemsVarinatObject.get(Constants.CHARGINGTIME).getAsString()
													: StringUtils.EMPTY;
											if (!Objects.equals(chargingTime, StringUtils.EMPTY)) {
												String chargingTimeValue = "";
												String chargingTimeUnit = StringUtils.EMPTY;
												if (chargingTime.contains(Constants.SPACE)) {
													String[] chargingTimes = chargingTime.split(Constants.SPACE);
													chargingTimeValue = chargingTimes[0];
													chargingTimeUnit = chargingTimes[1];
												} else {
													chargingTimeValue = chargingTime;
												}

												productVariantInfoBeanModdel
														.setVariant_charging_time_value(chargingTimeValue);
												productVariantInfoBeanModdel
														.setVariant_charging_time_unit(chargingTimeUnit);
											}
											String acceleration = itemsVarinatObject.get(Constants.ACCELERATOR)
													.getAsString();
											if (!acceleration.equals(StringUtils.EMPTY)) {
												String accelerationsValue = "";
												String accelerationsUnit = StringUtils.EMPTY;
												if (acceleration.contains(Constants.SPACE)) {
													String[] accelerations = acceleration.split(Constants.SPACE);
													accelerationsValue = accelerations[0];
													accelerationsUnit = accelerations[1];
												} else {
													accelerationsValue = acceleration;
												}
												productVariantInfoBeanModdel
														.setVariant_accelerator_value(accelerationsValue);
												productVariantInfoBeanModdel
														.setVariant_accelerator_unit(accelerationsUnit);

											}
											String topSpeed = itemsVarinatObject.get(Constants.TOPSPEED).getAsString();
											if (!topSpeed.equals(StringUtils.EMPTY)) {
												String topSpeedsValue = "";
												String topSpeedsUnit = StringUtils.EMPTY;
												if (topSpeed.contains(Constants.SPACE)) {
													String[] topSpeeds = topSpeed.split(Constants.SPACE);
													topSpeedsValue = topSpeeds[0];
													topSpeedsUnit = topSpeeds[1];
												} else {
													topSpeedsValue = topSpeed;
												}
												productVariantInfoBeanModdel.setVariant_top_speed_value(topSpeedsValue);
												productVariantInfoBeanModdel.setVariant_top_speed_unit(topSpeedsUnit);
											}

											temp.add(productVariantInfoBeanModdel);
											productInfoBannerObj.setProductVariantInfoList(temp);
											productInfoBannerObj.setVarSku(pbvariantsku);

											String nameObject = jsonItemsObject.get(Constants.NAMEFILED) != null
													? jsonItemsObject.get(Constants.NAMEFILED).getAsString()
													: StringUtils.EMPTY;
											if (!Objects.equals(nameObject, StringUtils.EMPTY)) {
												productInfoBannerObj.setItem_name(nameObject);
											}

											StringBuilder imagePathValue = new StringBuilder(
													Constants.PRODUCTIMAGEPATH);
											imagePathValue.append(jsonItemsObject.get(Constants.SKU).getAsString());
											imagePathValue.append(Constants.PNGEXTENSION);

											String imagePath = imagePathValue.toString();
											if (!Objects.equals(imagePath, StringUtils.EMPTY)) {
												productInfoBannerObj.setItem_image(imagePath);
											}
										}
									}
								}
							}

						}
					}
				}
			}
		} catch (IOException e) {
			LOG.error("IOException  occured method: productBannerInfoBanner cause : %s", e);
		}

	}

	/**
	 * Read product info json.
	 */
	private void readProductInfoJson() {
		String location = "/content/dam/vida/config/product-master.json";
		Resource assetResource = resourceResolver.getResource(location);
		assert assetResource != null;
		Asset asset = assetResource.adaptTo(Asset.class);
		assert asset != null;
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
				JsonElement element = gson.fromJson(sb.toString(), JsonElement.class);
				JsonObject jsonObject = element.getAsJsonObject();
				if (Objects.nonNull(jsonObject) && Objects.nonNull(jsonObject.get(Constants.ITEMS))) {
					JsonArray jsonArray = jsonObject.get(Constants.ITEMS).getAsJsonArray();
					if (Objects.nonNull(jsonArray)) {
						for (JsonElement jsonElement : jsonArray) {
							JsonObject jsonItemsObject = jsonElement.getAsJsonObject();
							if (Objects.nonNull(jsonItemsObject)) {
								String nameObject = jsonItemsObject.get(Constants.NAMEFILED) != null
										? jsonItemsObject.get(Constants.NAMEFILED).getAsString()
										: StringUtils.EMPTY;
								ProductInfoBeanModel productInfoBeanModel = new ProductInfoBeanModel();
								if (!Objects.equals(nameObject, StringUtils.EMPTY)) {
									productInfoBeanModel.setItem_name(nameObject);
								}

								StringBuilder imagePathValue = new StringBuilder(Constants.PRODUCTIMAGEPATH);
								imagePathValue.append(jsonItemsObject.get(Constants.SKU).getAsString());
								imagePathValue.append(Constants.PNGEXTENSION);

								String imagePath = imagePathValue.toString();
								if (!Objects.equals(imagePath, StringUtils.EMPTY)) {
									productInfoBeanModel.setItem_image(imagePath);
								}

								JsonArray itemsVarinatArray = jsonItemsObject.get(Constants.VARIANTS).getAsJsonArray();
								List<ProductVariantInfoBeanModdel> temp = new ArrayList<>();
								if (Objects.nonNull(itemsVarinatArray)) {
									for (JsonElement VarinatDataElement : itemsVarinatArray) {
										JsonObject itemsVarinatObject = VarinatDataElement.getAsJsonObject();
										ProductVariantInfoBeanModdel productVariantInfoBeanModdel = new ProductVariantInfoBeanModdel();

										String sku = itemsVarinatObject.get(Constants.SKU) != null
												? itemsVarinatObject.get(Constants.SKU).getAsString()
												: StringUtils.EMPTY;
										if (!Objects.equals(sku, StringUtils.EMPTY)) {
											productVariantInfoBeanModdel.setVariant_sku(sku);
											readProductVariantPriceJson(sku);
										}

										String color = itemsVarinatObject.get(Constants.COLOR) != null
												? itemsVarinatObject.get(Constants.COLOR).getAsString()
												: StringUtils.EMPTY;
										if (!Objects.equals(color, StringUtils.EMPTY)) {
											productVariantInfoBeanModdel.setVarinat_color(color);
											productVariantInfoBeanModdel.setVariant_color_id(color);
										}

										for (String colorName : Constants.COLORGRADIANT.keySet()) {
											if (Objects.equals(colorName, color)) {
												String varinatColorGradiant = Constants.COLORGRADIANT.get(colorName);
												productVariantInfoBeanModdel
														.setVarinatColorGradiant(varinatColorGradiant);
											}
										}

										String range = itemsVarinatObject.get(Constants.RANGE) != null
												? itemsVarinatObject.get(Constants.RANGE).getAsString()
												: StringUtils.EMPTY;
										if (!range.equals(StringUtils.EMPTY)) {
											String rangeValue = "";
											String rangeUnit = StringUtils.EMPTY;
											if (range.contains(Constants.SPACE)) {
												String[] rangeValues = range.split(Constants.SPACE);

												rangeValue = rangeValues[0];
												rangeUnit = rangeValues[1];
											} else {
												rangeValue = range;
											}
											productVariantInfoBeanModdel.setVariant_range_value(rangeValue);
											productVariantInfoBeanModdel.setVariant_range_unit(rangeUnit);

										}
										String chargingTime = itemsVarinatObject.get(Constants.CHARGINGTIME) != null
												? itemsVarinatObject.get(Constants.CHARGINGTIME).getAsString()
												: StringUtils.EMPTY;
										if (!Objects.equals(chargingTime, StringUtils.EMPTY)) {
											String chargingTimeValue = "";
											String chargingTimeUnit = StringUtils.EMPTY;
											if (chargingTime.contains(Constants.SPACE)) {
												String[] chargingTimes = chargingTime.split(Constants.SPACE);
												chargingTimeValue = chargingTimes[0];
												chargingTimeUnit = chargingTimes[1];
											} else {
												chargingTimeValue = chargingTime;
											}

											productVariantInfoBeanModdel
													.setVariant_charging_time_value(chargingTimeValue);
											productVariantInfoBeanModdel
													.setVariant_charging_time_unit(chargingTimeUnit);
										}
										String acceleration = itemsVarinatObject.get(Constants.ACCELERATOR)
												.getAsString();
										if (!acceleration.equals(StringUtils.EMPTY)) {
											String accelerationsValue = "";
											String accelerationsUnit = StringUtils.EMPTY;
											if (acceleration.contains(Constants.SPACE)) {
												String[] accelerations = acceleration.split(Constants.SPACE);
												accelerationsValue = accelerations[0];
												accelerationsUnit = accelerations[1];
											} else {
												accelerationsValue = acceleration;
											}
											productVariantInfoBeanModdel
													.setVariant_accelerator_value(accelerationsValue);
											productVariantInfoBeanModdel.setVariant_accelerator_unit(accelerationsUnit);

										}
										String topSpeed = itemsVarinatObject.get(Constants.TOPSPEED).getAsString();
										if (!topSpeed.equals(StringUtils.EMPTY)) {
											String topSpeedsValue = "";
											String topSpeedsUnit = StringUtils.EMPTY;
											if (topSpeed.contains(Constants.SPACE)) {
												String[] topSpeeds = topSpeed.split(Constants.SPACE);
												topSpeedsValue = topSpeeds[0];
												topSpeedsUnit = topSpeeds[1];
											} else {
												topSpeedsValue = topSpeed;
											}
											productVariantInfoBeanModdel.setVariant_top_speed_value(topSpeedsValue);
											productVariantInfoBeanModdel.setVariant_top_speed_unit(topSpeedsUnit);
										}

										temp.add(productVariantInfoBeanModdel);
										productInfoBeanModel.setProductVariantInfoList(temp);
									}
									productMaster.add(productInfoBeanModel);
								}
							}
						}
					}
				}
			}
		} catch (IOException e) {
			LOG.error("IOException  occured method: readProductInfoJson cause : %s", e);
		}
	}

	/**
	 * Read product variant price json.
	 *
	 * @param skuParam the sku param
	 */
	private void readProductVariantPriceJson(String skuParam) {
		String location = "/content/dam/vida/config/price-master.json";
		ProductInfoVO product = new ProductInfoVO();
		Resource assetResource = resourceResolver.getResource(location);
		assert assetResource != null;
		Asset asset = assetResource.adaptTo(Asset.class);
		assert asset != null;
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
				JsonElement element = gson.fromJson(sb.toString(), JsonElement.class);
				if (Objects.nonNull(element)) {
					JsonArray jsonArray = element.getAsJsonArray();
					if (Objects.nonNull(jsonArray)) {
						for (JsonElement jsonElement : jsonArray) {
							JsonObject jsonObject = jsonElement.getAsJsonObject();
							ProductVariantPriceMasterInfoBeanModdel productVariantPriceMasterInfoBeanModdel = new ProductVariantPriceMasterInfoBeanModdel();
							if (Objects.nonNull(jsonObject)) {
								String varian_sku = jsonObject.get(Constants.VARIANTSKU) != null
										? jsonObject.get(Constants.VARIANTSKU).getAsString()
										: StringUtils.EMPTY;
								productVariantPriceMasterInfoBeanModdel.setVarinatSku(varian_sku);

								String effectivePrice = jsonObject.get(Constants.EFFECTIVEPRICE) != null
										? jsonObject.get(Constants.EFFECTIVEPRICE).getAsString()
										: StringUtils.EMPTY;
								String city = jsonObject.get(Constants.CITY_PRODUCT) != null
										? jsonObject.get(Constants.CITY_PRODUCT).getAsString()
										: StringUtils.EMPTY;
								String cityValue = StringUtils.substringBefore(city, "~").toLowerCase();
								city = CommonUtils.formatCityName(cityValue);
								effectivePrice = !Objects.equals(effectivePrice, StringUtils.EMPTY)
										? CommonUtils.rupeeFormat(effectivePrice) : StringUtils.EMPTY;
								productVariantPriceMasterInfoBeanModdel.setEffectivePrice(effectivePrice);
								priceMaster.add(productVariantPriceMasterInfoBeanModdel);
								if (Objects.equals(varian_sku, skuParam)) {
									product.setCity(city);
									product.setPrice(effectivePrice);
									priceMasterMap.put(varian_sku, product);
									if(!Objects.equals(effectivePrice, StringUtils.EMPTY))
										citypriceMap.put(city, effectivePrice);
								}
							}
						}
					}
				}

			}
		} catch (IOException e) {
			LOG.error("IOException  occured method: readProductVariantPriceJson cause : %s", e);
		}
	}

	/**
	 * Gets the product master.
	 *
	 * @return the product master
	 */
	public List<ProductInfoBeanModel> getProductMaster() {
		return productMaster;
	}

	/**
	 * Gets the price master.
	 *
	 * @return the price master
	 */
	public List<ProductVariantPriceMasterInfoBeanModdel> getPriceMaster() {
		return priceMaster;
	}

	/**
	 * Gets the price master map.
	 *
	 * @return the price master map
	 */
	public Map<String, ProductInfoVO> getPriceMasterMap() {
		return priceMasterMap;
	}

	/**
	 * Gets the product info banner obj.
	 *
	 * @return the product info banner obj
	 */
	public ProductInfoBeanModel getProductInfoBannerObj() {
		return productInfoBannerObj;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		String cityValue = StringUtils.substringBefore(city, "~").toLowerCase();
		return CommonUtils.formatCityName(cityValue);
	}

	/**
	 * Gets the cityprice map.
	 *
	 * @return the cityprice map
	 */
	public Map<String, String> getCitypriceMap() {
		return citypriceMap;
	}

	/**
	 * displays the cityprice map.
	 *
	 * @return the cityprice map
	 */
	public SortedMap<String, String> getDisplayCitypriceMap() {
		TreeMap<String, String> cityPriceSet = new TreeMap<>(); 
		cityPriceSet.putAll(citypriceMap);
		return cityPriceSet;
	}
}
