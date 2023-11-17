package com.heromotocorp.vida.core.models;


import java.util.Collections;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import com.heromotocorp.vida.core.vo.HotspotBeanModel;


/**
 * Model to get the multifield values of Hotspot.
 *
 */
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HotspotModel {


    /**
     * To get all the child nodes of Tabs field.
     *
     */
    @ChildResource
    private List<HotspotBeanModel> items;

    public List<HotspotBeanModel> getItems() {
		return Collections.unmodifiableList(items);
	}

	
}
