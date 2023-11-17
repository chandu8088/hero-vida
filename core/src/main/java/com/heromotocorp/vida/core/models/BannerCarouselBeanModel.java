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
public class BannerCarouselBeanModel {

    @Inject
    private String heading;

    @Inject
    private String subheading;

    @Inject
    private String description;

    @Inject
    private String itemType;

    @Inject
    private String imagepath;

    @Inject
    private String imagealttext;

    @Inject
    private String videopath;

    /**
     * Gets heading.
     *
     * @return the heading
     */
    public String getHeading() {
        return heading;
    }

    /**
     * Gets subheading.
     *
     * @return the subheading
     */
    public String getSubheading() {
        return subheading;
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
     * Gets item type.
     *
     * @return the item type
     */
    public String getItemType() {
        return itemType;
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
     * Gets imagealttext.
     *
     * @return the imagealttext
     */
    public String getImagealttext() {
        return imagealttext;
    }

    /**
     * Gets videopath.
     *
     * @return the videopath
     */
    public String getVideopath() {
        return videopath;
    }
}
