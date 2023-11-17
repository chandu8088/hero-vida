/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.sling.api.SlingHttpServletRequest;
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
 * JUnit test class for LoginModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class LoginModelTest {

	private final AemContext context = new AemContext();

	private LoginModel loginModel = new LoginModel();

	private Page page;

	private Resource resource;
	
    @Mock
    private SlingHttpServletRequest requestMock;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/samplevida");
		resource = context.create().resource(page, "sample vida", "sling:resourceType", "vida/components/loginmodel",
				"redirectionUrl", "redirectionUrl", "termsAndConditionUrl", "termsAndConditionUrl");

		context.registerService(SlingHttpServletRequest.class, requestMock);
        context.registerService(Resource.class, resource);
		loginModel = context.request().adaptTo(LoginModel.class);

	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.LoginModel#getRedirectionUrl()}.
	 */
	@Test
	void testGetRedirectionUrl() {
		assertEquals("", loginModel.getRedirectionUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.LoginModel#getTermsAndConditionUrl()}.
	 */
	@Test
	void testGetTermsAndConditionUrl() {
		assertEquals("", loginModel.getTermsAndConditionUrl());
	}

}
