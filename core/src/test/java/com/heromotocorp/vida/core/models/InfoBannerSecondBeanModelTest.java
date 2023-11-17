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
 * JUnit test class for InfoBannerSecondBeanModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class InfoBannerSecondBeanModelTest {

	private final AemContext context = new AemContext();

	private InfoBannerSecondBeanModel infoBannerSecondBeanModel = new InfoBannerSecondBeanModel();

	private Page page;

	private Resource resource;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/samplevida");
		resource = context.create().resource(page, "sample vida", "sling:resourceType",
				"vida/components/infobannersecond", "accordiontitle", "accordiontitle", "accordiondescription",
				"accordiondescription");

		infoBannerSecondBeanModel = resource.adaptTo(InfoBannerSecondBeanModel.class);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.InfoBannerSecondBeanModel#getDelaytime()}.
	 */
	@Test
	void testGetDelaytime() {
		infoBannerSecondBeanModel.setDelaytime(10.00);
		infoBannerSecondBeanModel.getDelaytime();

		assertEquals(10.00, infoBannerSecondBeanModel.getDelaytime());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.InfoBannerSecondBeanModel#setDelaytime(double)}.
	 */
	@Test
	void testSetDelaytime() {
		infoBannerSecondBeanModel.setDelaytime(10.00);

		assertEquals(10.00, infoBannerSecondBeanModel.getDelaytime());

	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.InfoBannerSecondBeanModel#getAccordiontitle()}.
	 */
	@Test
	void testGetAccordiontitle() {
		assertEquals("accordiontitle", infoBannerSecondBeanModel.getAccordiontitle());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.InfoBannerSecondBeanModel#getAccordiondescription()}.
	 */
	@Test
	void testGetAccordiondescription() {
		assertEquals("accordiondescription", infoBannerSecondBeanModel.getAccordiondescription());
	}

}
