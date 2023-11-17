package com.heromotocorp.vida.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

/**
 * The type Product variant info bean moddel.
 */
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductVariantInfoBeanModdel {

    /**
     * The Image path.
     */
    String imagePath;

    /**
     * Gets image path.
     *
     * @return the image path
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Sets image path.
     *
     * @param imagePath the image path
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * The Variant sku.
     */
    String variant_sku;

    /**
     * The Varinat color.
     */
    String varinat_color;

    /**
     * The Variant color id.
     */
    String variant_color_id;

    /**
     * The Varinat color gradiant.
     */
    String varinatColorGradiant;

    /**
     * The Variant range.
     */
    String variant_range_value;

    /**
     * The Variant range.
     */
    String variant_range_unit;
    /**
     * The Variant charging time.
     */
    String variant_charging_time_value;
    /**
     * The Variant charging time.
     */
    String variant_charging_time_unit;
    /**
     * The Variant accelerator.
     */
    String variant_accelerator_value;
    /**
     * The Variant accelerator.
     */
    String variant_accelerator_unit;
    /**
     * The Variant top speed.
     */
    String variant_top_speed_value;
    /**
     * The Variant top speed.
     */
    String variant_top_speed_unit;

    /**
     * Gets varinat color.
     *
     * @return the varinat color
     */
    public String getVarinat_color() {
        return varinat_color;
    }

    /**
     * Sets varinat color.
     *
     * @param varinat_color the varinat color
     */
    public void setVarinat_color(String varinat_color) {
        this.varinat_color = varinat_color;
    }

    /**
     * Gets varinat color gradiant.
     *
     * @return the varinat color gradiant
     */
    public String getVarinatColorGradiant() {
        return varinatColorGradiant;
    }

    /**
     * Gets variant color id.
     *
     * @return the variant color id
     */
    public String getVariant_color_id() {
        return variant_color_id;
    }

    /**
     * Sets variant color id.
     *
     * @param variant_color_id the variant color id
     */
    public void setVariant_color_id(String variant_color_id) {
        variant_color_id = variant_color_id.replaceAll(" ","-");
        this.variant_color_id = variant_color_id;
    }

    /**
     * Sets varinat color gradiant.
     *
     * @param varinatColorGradiant the varinat color gradiant
     */
    public void setVarinatColorGradiant(String varinatColorGradiant) {
        this.varinatColorGradiant = varinatColorGradiant;
    }

    /**
     * Gets variant range value.
     *
     * @return the variant range value
     */
    public String getVariant_range_value() {
        return variant_range_value;
    }

    /**
     * Sets variant range value.
     *
     * @param variant_range_value the variant range value
     */
    public void setVariant_range_value(String variant_range_value) {
        this.variant_range_value = variant_range_value;
    }

    /**
     * Gets variant range unit.
     *
     * @return the variant range unit
     */
    public String getVariant_range_unit() {
        return variant_range_unit;
    }

    /**
     * Sets variant range unit.
     *
     * @param variant_range_unit the variant range unit
     */
    public void setVariant_range_unit(String variant_range_unit) {
        this.variant_range_unit = variant_range_unit;
    }

    /**
     * Gets variant charging time value.
     *
     * @return the variant charging time value
     */
    public String getVariant_charging_time_value() {
        return variant_charging_time_value;
    }

    /**
     * Sets variant charging time value.
     *
     * @param variant_charging_time_value the variant charging time value
     */
    public void setVariant_charging_time_value(String variant_charging_time_value) {
        this.variant_charging_time_value = variant_charging_time_value;
    }

    /**
     * Gets variant charging time unit.
     *
     * @return the variant charging time unit
     */
    public String getVariant_charging_time_unit() {
        return variant_charging_time_unit;
    }

    /**
     * Sets variant charging time unit.
     *
     * @param variant_charging_time_unit the variant charging time unit
     */
    public void setVariant_charging_time_unit(String variant_charging_time_unit) {
        this.variant_charging_time_unit = variant_charging_time_unit;
    }

    /**
     * Gets variant accelerator value.
     *
     * @return the variant accelerator value
     */
    public float getVariant_accelerator_value() {
    	return Float.parseFloat(variant_accelerator_value);
    }

    /**
     * Sets variant accelerator value.
     *
     * @param variant_accelerator_value the variant accelerator value
     */
    public void setVariant_accelerator_value(String variant_accelerator_value) {
        this.variant_accelerator_value = variant_accelerator_value;
    }

    /**
     * Gets variant accelerator unit.
     *
     * @return the variant accelerator unit
     */
    public String getVariant_accelerator_unit() {
        return variant_accelerator_unit;
    }

    /**
     * Sets variant accelerator unit.
     *
     * @param variant_accelerator_unit the variant accelerator unit
     */
    public void setVariant_accelerator_unit(String variant_accelerator_unit) {
        this.variant_accelerator_unit = variant_accelerator_unit;
    }

    /**
     * Gets variant top speed value.
     *
     * @return the variant top speed value
     */
    public String getVariant_top_speed_value() {
        return variant_top_speed_value;
    }

    /**
     * Sets variant top speed value.
     *
     * @param variant_top_speed_value the variant top speed value
     */
    public void setVariant_top_speed_value(String variant_top_speed_value) {
        this.variant_top_speed_value = variant_top_speed_value;
    }

    /**
     * Gets variant top speed unit.
     *
     * @return the variant top speed unit
     */
    public String getVariant_top_speed_unit() {
        return variant_top_speed_unit;
    }

    /**
     * Sets variant top speed unit.
     *
     * @param variant_top_speed_unit the variant top speed unit
     */
    public void setVariant_top_speed_unit(String variant_top_speed_unit) {
        this.variant_top_speed_unit = variant_top_speed_unit;
    }

    /**
     * Gets variant sku.
     *
     * @return the variant sku
     */
    public String getVariant_sku() {
        return variant_sku;
    }

    /**
     * Sets variant sku.
     *
     * @param variant_sku the variant sku
     */
    public void setVariant_sku(String variant_sku) {
        this.variant_sku = variant_sku;
    }
}
