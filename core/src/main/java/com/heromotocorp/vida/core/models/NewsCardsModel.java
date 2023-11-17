package com.heromotocorp.vida.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is the main navigationmenu model
 */
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NewsCardsModel {
    /**
     * Taking the Primary Navigation data in the below list.
     */
    @Inject
    private String heading;

    @Inject
    private List <NewsCardsBeanModel>items;

    /**
     * Gets heading.
     *
     * @return the heading
     */
    public String getHeading() {
        return heading;
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public List<NewsCardsBeanModel> getItems() {
        return items != null ? new ArrayList<>(items) : Collections.emptyList();
    }

}
