package com.heromotocorp.vida.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

/**
 * This is the main uttonTabs model.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ButtonTabsModel {

	/**
	 * Taking the tabs data in the below list.
	 */
	@Inject
	private List<ButtonTabsBeanModel> tabdetail;

	/**
	 * Gets tabdetail.
	 *
	 * @return the tabdetail
	 */
	public List<ButtonTabsBeanModel> getTabdetail() {
		return tabdetail != null ? new ArrayList<>(tabdetail) : Collections.emptyList();
	}

}
