package com.heromotocorp.vida.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Source;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is the main navigationmenu model.
 */
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NavigationModel {
    /**
     * Taking the Primary Navigation data in the below list.
     */

    @Inject
    private List <PrimaryNavigationBeanModel>primarynavigation;

    /**
     * Taking the Secondary Navigation data in the below list.
     */
    @Inject
    private List<SecondaryNavigationBeanModel> secondarynavigation;
    
    /** The maintenance page url. */
    @ValueMapValue
    private String maintenancePageUrl;
    
	/** The resolver. */
    @Inject
    @Source("sling-object")
	private ResourceResolver resolver;


    /**
     * Gets primarynavigation.
     *
     * @return the primarynavigation
     */
    public List<PrimaryNavigationBeanModel> getPrimarynavigation() {
        return primarynavigation != null ? new ArrayList<>(primarynavigation) : Collections.emptyList();
    }

    /**
     * Gets secondarynavigation.
     *
     * @return the secondarynavigation
     */
    public List<SecondaryNavigationBeanModel> getSecondarynavigation() {
        return secondarynavigation != null ? new ArrayList<>(secondarynavigation) : Collections.emptyList();
    }

	/**
	 * Gets the maintenance page url.
	 *
	 * @return the maintenance page url
	 */
	public String getMaintenancePageUrl() {
		return resolver.map(maintenancePageUrl);
	}

}
