package com.heromotocorp.vida.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import javax.inject.Inject;
import java.util.List;

/**
 * The type Feature Banner.
 */
@Model(adaptables = Resource.class, resourceType = "vida/components/featurebanner", defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FeatureBanner {

    @ChildResource
    public List<Resource> featurebanner;

    @Inject
    public String variations;

    public String getVariations() {
        return variations;
    }
}
