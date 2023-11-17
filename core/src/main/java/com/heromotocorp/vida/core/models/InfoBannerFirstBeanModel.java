package com.heromotocorp.vida.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;


/**
 * The type Info Banner First Bean Model.
 */
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class InfoBannerFirstBeanModel {

	@Inject
    private String labeltext;

    @Inject
    private String labelvalue;
    


    /**
     * Gets label text
     *
     * @return the label text
     */
    public String getLabeltext() {
        return labeltext;
    }

    /**
     * Gets label value
     *
     * @return the label value
     */
    public String getLabelvalue() {
        return labelvalue;
    }
}
