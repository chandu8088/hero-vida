package com.heromotocorp.vida.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Product info bean model.
 */
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductInfoBeanModel {

    /**
     * The Item name.
     */
    String item_name;

    String item_image;
    
    String varSku;

    /**
     * The Product variant info list.
     */
    List<ProductVariantInfoBeanModdel> productVariantInfoList= new ArrayList<>();

    /**
     * Gets item name.
     *
     * @return the item name
     */
    public String getItem_name() {
        return item_name;
    }

    /**
     * Sets item name.
     *
     * @param item_name the item name
     */
    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }

    /**
     * Gets product variant info list.
     *
     * @return the product variant info list
     */
    public List<ProductVariantInfoBeanModdel> getProductVariantInfoList() {
        return productVariantInfoList;
    }

    /**
     * Sets product variant info list.
     *
     * @param productVariantInfoList the product variant info list
     */
    public void setProductVariantInfoList(List<ProductVariantInfoBeanModdel> productVariantInfoList) {
        this.productVariantInfoList = productVariantInfoList;
    }

	public String getVarSku() {
		return varSku;
	}

	public void setVarSku(String varSku) {
		this.varSku = varSku;
	}
}
