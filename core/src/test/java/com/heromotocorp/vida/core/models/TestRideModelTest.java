/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.wcm.api.Page;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for TestRideModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class TestRideModelTest {

	private final AemContext context = new AemContext();

	private TestRideModel testRideModel = new TestRideModel();

	private Page page;

	private Resource resource;

	@Mock
	private SlingHttpServletRequest requestMock;

	@Mock
	private ResourceResolver resolver;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/samplevida");
		resource = context.create().resource(page, "sample vida", "sling:resourceType", "vida/components/loginmodel",
				"countrycode", "countrycode", "profilepageurl", "profilepageurl", "shortTermRedirectURL",
				"shortTermRedirectURL", "longTermRedirectURL", "longTermRedirectURL");

		context.registerService(SlingHttpServletRequest.class, requestMock);
		context.registerService(Resource.class, resource);
		testRideModel = context.request().adaptTo(TestRideModel.class);

		PrivateAccessor.setField(testRideModel, "resolver", resolver);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.TestRideModel#init()}.
	 */
	@Test
	void testInit() {
		testRideModel.init();
		assertNotNull(testRideModel);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.TestRideModel#getCountryCodes()}.
	 */
	@Test
	void testGetCountryCodes() {
		testRideModel.getCountryCodes();
		assertNotNull(testRideModel);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.TestRideModel#getProfilepageurl()}.
	 */
	@Test
	void testGetProfilepageurl() {
		assertEquals(null, testRideModel.getProfilepageurl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.TestRideModel#getShortTermRedirectURL()}.
	 */
	@Test
	void testGetShortTermRedirectURL() {
		Mockito.when(testRideModel.getShortTermRedirectURL()).thenReturn("shortTermRedirectURL");
		assertEquals("shortTermRedirectURL", testRideModel.getShortTermRedirectURL());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.TestRideModel#getLongTermRedirectURL()}.
	 */
	@Test
	void testGetLongTermRedirectURL() {
		assertEquals(null, testRideModel.getLongTermRedirectURL());
	}

}
