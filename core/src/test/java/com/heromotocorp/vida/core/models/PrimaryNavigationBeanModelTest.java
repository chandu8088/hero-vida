/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.wcm.api.Page;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for PrimaryNavigationBeanModel.
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class PrimaryNavigationBeanModelTest {

	private final AemContext context = new AemContext();

	private PrimaryNavigationBeanModel primaryNavigationBeanModel = new PrimaryNavigationBeanModel();

	private Page page;

	private Resource resource;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/testvida");
		resource = context.create().resource(page, "test vida", "sling:resourceType",
				"vida/components/primarynavigation", "primarynavtext", "primarynavtext", "primarynavtextlink",
				"primarynavtextlink", "profilelinkcheckbox", "profilelinkcheckbox", "loginregisterlinkcheckbox",
				"loginregisterlinkcheckbox");

		primaryNavigationBeanModel = resource.adaptTo(PrimaryNavigationBeanModel.class);
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.models.PrimaryNavigationBeanModel#getPrimarynavtext()}.
	 */
	@Test
	void testGetPrimarynavtext() {
		assertEquals("primarynavtext", primaryNavigationBeanModel.getPrimarynavtext());
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.models.PrimaryNavigationBeanModel#getPrimarynavtextlink()}.
	 */
	@Test
	void testGetPrimarynavtextlink() {
		assertEquals("primarynavtextlink", primaryNavigationBeanModel.getPrimarynavtextlink());
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.models.PrimaryNavigationBeanModel#getProfilelinkcheckbox()}.
	 */
	@Test
	void testGetProfilelinkcheckbox() {
		assertEquals("profilelinkcheckbox", primaryNavigationBeanModel.getProfilelinkcheckbox());
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.models.PrimaryNavigationBeanModel#getLoginregisterlinkcheckbox()}.
	 */
	@Test
	void testGetLoginregisterlinkcheckbox() {
		assertEquals("loginregisterlinkcheckbox", primaryNavigationBeanModel.getLoginregisterlinkcheckbox());
	}

}
