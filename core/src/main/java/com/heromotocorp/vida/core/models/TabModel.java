package com.heromotocorp.vida.core.models;


import java.util.Collections;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;


/**
 * Model to get the multifield values of tabs.
 *
 */
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TabModel {


    /**
     * To get all the child nodes of Tabs field.
     *
     */
    @ChildResource
    private List<TabBeanModel> tabs;

    public List<TabBeanModel> getTabs() {
		return Collections.unmodifiableList(tabs);
	}

	
}
