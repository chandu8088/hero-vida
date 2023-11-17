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
 * JUnit test class for ProductDetailsModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class ProductDetailsModelTest {

	private final AemContext context = new AemContext();

	private ProductDetailsModel productDetailsModel = new ProductDetailsModel();

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
		resource = context.create().resource(page, "sample vida", "sling:resourceType", "vida/components/productdetails",
				"pdprimarybtnurl", "pdprimarybtnurl", "pdsecondarybtnurl", "pdsecondarybtnurl");

		context.registerService(SlingHttpServletRequest.class, requestMock);
        context.registerService(Resource.class, resource);
        productDetailsModel = context.request().adaptTo(ProductDetailsModel.class);

	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductDetailsModel#getPdprimarybtnurl()}.
	 */
	@Test
	void testGetPdprimarybtnurl() {
		assertEquals("", productDetailsModel.getPdprimarybtnurl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductDetailsModel#getPdsecondarybtnurl()}.
	 */
	@Test
	void testGetPdsecondarybtnurl() {
		assertEquals("", productDetailsModel.getPdsecondarybtnurl());
	}

}
