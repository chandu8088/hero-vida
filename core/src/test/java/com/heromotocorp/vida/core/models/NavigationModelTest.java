/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.wcm.api.Page;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for NavigationModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class NavigationModelTest {

	private final AemContext context = new AemContext();

	private NavigationModel navigationModel = new NavigationModel();

	private Page page;

	private Resource resource;

	private Page page1;

	private Resource resource1;

	private Page page2;

	private Resource resource2;

	private Page page3;

	private Resource resource3;

	private NavigationModel navigationModel1 = new NavigationModel();

	private PrimaryNavigationBeanModel primaryNavigationBeanModel = new PrimaryNavigationBeanModel();

	private SecondaryNavigationBeanModel secondaryNavigationBeanModel = new SecondaryNavigationBeanModel();

	private List<PrimaryNavigationBeanModel> primarynavigation = new ArrayList<>();

	private List<SecondaryNavigationBeanModel> secondarynavigation = new ArrayList<>();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		primarynavigation.add(primaryNavigationBeanModel);
		secondarynavigation.add(secondaryNavigationBeanModel);
		page = context.create().page("/content/vida");
		resource = context.create().resource(page, "vida", "sling:resourceType", "vida/components/navigation",
				"maintenancePageUrl", "maintenancePageUrl", "primarynavigation", primarynavigation,
				"secondarynavigation", secondarynavigation);

		navigationModel = resource.adaptTo(NavigationModel.class);

		page1 = context.create().page("/content/testvida");
		resource1 = context.create().resource(page1, "test vida", "sling:resourceType",
				"vida/components/primarynavigation", "primarynavtext", "primarynavtext", "primarynavtextlink",
				"primarynavtextlink", "profilelinkcheckbox", "profilelinkcheckbox", "loginregisterlinkcheckbox",
				"loginregisterlinkcheckbox");

		primaryNavigationBeanModel = resource1.adaptTo(PrimaryNavigationBeanModel.class);

		page2 = context.create().page("/content/testvida");
		resource2 = context.create().resource(page2, "test vida", "sling:resourceType",
				"vida/components/secondarynavigation", "secondarynavtext", "secondarynavtext", "secondarynavtextlink",
				"secondarynavtextlink");

		secondaryNavigationBeanModel = resource2.adaptTo(SecondaryNavigationBeanModel.class);

		page3 = context.create().page("/content/samplevida");
		resource3 = context.create().resource(page3, "Sample vida", "sling:resourceType",
				"vida/components/highlightcardcarousel", "maintenancePageUrl", "maintenancePageUrl",
				"primarynavigation", null, "secondarynavigation", null);

		navigationModel1 = resource3.adaptTo(NavigationModel.class);

	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.NavigationModel#getPrimarynavigation()}.
	 */
	@Test
	void testGetPrimarynavigation() {
		assertNotNull(navigationModel.getPrimarynavigation());

		navigationModel1.getPrimarynavigation();
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.NavigationModel#getSecondarynavigation()}.
	 */
	@Test
	void testGetSecondarynavigation() {
		assertNotNull(navigationModel.getSecondarynavigation());

		navigationModel1.getSecondarynavigation();
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.NavigationModel#getMaintenancePageUrl()}.
	 */
	@Test
	void testGetMaintenancePageUrl() {
		assertEquals("maintenancePageUrl", navigationModel.getMaintenancePageUrl());
	}

}
