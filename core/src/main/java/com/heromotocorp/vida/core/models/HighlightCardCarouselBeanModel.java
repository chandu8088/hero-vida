package com.heromotocorp.vida.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;


/**
 * The type Banner carousel bean model.
 */
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HighlightCardCarouselBeanModel {

    @Inject
    private String heading;

    @Inject
    private String description;

    @Inject
    private String imagepath;

    @Inject
    private String alttext;

    /**
     * Gets heading.
     *
     * @return the heading
     */
    public String getHeading() {
        return heading;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets imagepath.
     *
     * @return the imagepath
     */
    public String getImagepath() {
        return imagepath;
    }

    /**
     * Gets alttext.
     *
     * @return the alttext
     */
    public String getAlttext() {
        return alttext;
    }
}
