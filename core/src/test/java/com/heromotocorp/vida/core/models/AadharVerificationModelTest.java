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
 * JUnit test class for AadharVerificationModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class AadharVerificationModelTest {

	private final AemContext context = new AemContext();

	private AadharVerificationModel aadharVerificationModel = new AadharVerificationModel();

	private Page page;

	private Resource resource;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/vida");
		resource = context.create().resource(page, "vida", "sling:resourceType", "vida/components/aadharverification",
				"subtext", "Sample Text");

		aadharVerificationModel = resource.adaptTo(AadharVerificationModel.class);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.AadharVerificationModel#getSubtext()}.
	 */
	@Test
	void testGetSubtext() {
		aadharVerificationModel.getSubtext();

		assertEquals("Sample Text", aadharVerificationModel.getSubtext());
	}

}
