package com.heromotocorp.vida.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;


/**
 * This model is to get the text and text redirecting link
 * of the secondary navigation segment.
 */
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SecondaryNavigationBeanModel {

    @Inject
    private String secondarynavtext;

    @Inject
    private String secondarynavtextlink;

    /**
     * Gets secondarynavtext.
     *
     * @return the secondarynavtext
     */
    public String getSecondarynavtext() {
        return secondarynavtext;
    }

    /**
     * Gets secondarynavtextlink.
     *
     * @return the secondarynavtextlink
     */
    public String getSecondarynavtextlink() {
        return secondarynavtextlink;
    }


}
