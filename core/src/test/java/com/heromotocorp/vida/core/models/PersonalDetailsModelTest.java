/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.wcm.api.Page;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for PersonalDetailsModel.
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class PersonalDetailsModelTest {

	private final AemContext context = new AemContext();

	private PersonalDetailsModel personalDetailsModel = new PersonalDetailsModel();

	private Page page;

	private Resource resource;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/vida");
		resource = context.create().resource(page, "vida", "sling:resourceType", "vida/components/personaldetails",
				"usermanualBtnUrl", "usermanualBtnUrl");

		personalDetailsModel = resource.adaptTo(PersonalDetailsModel.class);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PersonalDetailsModel#init()}.
	 */
	@Test
	void testInit() {
		personalDetailsModel.init();
		assertNotNull(personalDetailsModel);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PersonalDetailsModel#getUsermanualBtnUrl()}.
	 */
	@Test
	void testGetUsermanualBtnUrl() {
		assertEquals("usermanualBtnUrl", personalDetailsModel.getUsermanualBtnUrl());
	}

}
