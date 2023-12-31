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
public class BannerCarouselModel {
    /**
     * Taking the Primary Navigation data in the below list.
     */

    @Inject
    private List <BannerCarouselBeanModel>items;

    public List<BannerCarouselBeanModel> getItems() {
        return items != null ? new ArrayList<>(items) : Collections.emptyList();
    }

}
