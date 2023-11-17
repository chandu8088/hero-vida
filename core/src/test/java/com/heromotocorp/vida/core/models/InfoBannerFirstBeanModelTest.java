/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.wcm.api.Page;


import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for InfoBannerFirstBeanModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class InfoBannerFirstBeanModelTest {

	private final AemContext context = new AemContext();

	private InfoBannerFirstBeanModel infoBannerFirstBeanModel = new InfoBannerFirstBeanModel();

	private Page page;

	private Resource resource;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/samplevida");
		resource = context.create().resource(page, "sample vida", "sling:resourceType",
				"vida/components/infobannerfirst", "labeltext", "labeltext", "labelvalue", "labelvalue");

		infoBannerFirstBeanModel = resource.adaptTo(InfoBannerFirstBeanModel.class);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.InfoBannerFirstBeanModel#getLabeltext()}.
	 */
	@Test
	void testGetLabeltext() {
		assertEquals("labeltext", infoBannerFirstBeanModel.getLabeltext());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.InfoBannerFirstBeanModel#getLabelvalue()}.
	 */
	@Test
	void testGetLabelvalue() {
		assertEquals("labelvalue", infoBannerFirstBeanModel.getLabelvalue());
	}

}
