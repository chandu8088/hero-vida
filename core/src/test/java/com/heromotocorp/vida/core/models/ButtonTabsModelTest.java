/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.wcm.api.Page;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for ButtonTabsModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class ButtonTabsModelTest {

	private final AemContext context = new AemContext();

	@Mock
	private ButtonTabsBeanModel buttonTabsBeanModel;

	private ButtonTabsModel buttonTabsModel = new ButtonTabsModel();

	private ButtonTabsModel buttonTabsModel1 = new ButtonTabsModel();

	private List<ButtonTabsBeanModel> tabdetail = new ArrayList<>();

	private List<ButtonTabsBeanModel> tabdetail1 = new ArrayList<>();

	private Page page;

	private Resource resource;

	private Page page1;

	private Resource resource1;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		tabdetail.add(buttonTabsBeanModel);
		page = context.create().page("/content/vida");
		resource = context.create().resource(page, "vida", "sling:resourceType", "vida/components/page", "tabdetail",
				tabdetail);

		buttonTabsModel = resource.adaptTo(ButtonTabsModel.class);

		page1 = context.create().page("/content/testvida");
		resource1 = context.create().resource(page1, "test vida", "sling:resourceType",
				"vida/components/primarynavigation", "tabdetail", tabdetail1);

		buttonTabsModel1 = resource1.adaptTo(ButtonTabsModel.class);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ButtonTabsModel#getTabdetail()}.
	 */
	@Test
	void testGetTabdetail() {
		assertNotNull(buttonTabsModel.getTabdetail());

		buttonTabsModel1.getTabdetail();
	}

}
