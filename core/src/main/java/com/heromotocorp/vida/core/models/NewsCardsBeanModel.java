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
public class NewsCardsBeanModel {

    @Inject
    private String imagepath;

    @Inject
    private String imagealttext;

    @Inject
    private String newsheading;

    @Inject
    private String source;

    @Inject
    private String newsdate;

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
     * Gets newsheading.
     *
     * @return the newsheading
     */
    public String getNewsheading() {
        return newsheading;
    }

    /**
     * Gets source.
     *
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * Gets newsdate.
     *
     * @return the newsdate
     */
    public String getNewsdate() {
        return newsdate;
    }
}
