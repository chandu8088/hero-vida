package com.heromotocorp.vida.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

/**
 * The type Product variant info bean moddel.
 */
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductVariantPriceMasterInfoBeanModdel {

    /**
     * The Varinat sku.
     */
    String varinatSku;

    /**
     * The On road price.
     */
    String onRoadPrice; 

    /**
     * The effective price.
     */
    String effectivePrice;

    /**
     * Gets varinat sku.
     *
     * @return the varinat sku
     */
    public String getVarinatSku() {
        return varinatSku;
    }

    /**
     * Sets varinat sku.
     *
     * @param varinatSku the varinat sku
     */
    public void setVarinatSku(String varinatSku) {
        this.varinatSku = varinatSku;
    }

    /**
     * Gets on road price.
     *
     * @return the on road price
     */
    public String getOnRoadPrice() {
        return onRoadPrice;
    }

    /**
     * Sets on road price.
     *
     * @param onRoadPrice the on road price
     */
    public void setOnRoadPrice(String onRoadPrice) {
        this.onRoadPrice = onRoadPrice;
    }

    /**
     * Gets effective price.
     *
     * @return the effective price
     */
	public String getEffectivePrice() {
		return effectivePrice;
	}

	/**
     * Sets effective price.
     *
     * @param effectivePrice the effective price
     */
	public void setEffectivePrice(String effectivePrice) {
		this.effectivePrice = effectivePrice;
	}
}
