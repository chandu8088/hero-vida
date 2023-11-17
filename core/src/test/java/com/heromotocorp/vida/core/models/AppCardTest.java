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
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.wcm.api.Page;


import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for AppCard
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class AppCardTest {

	private final AemContext context = new AemContext();

	private AppCard appCard = new AppCard();

	private Page page;

	private Resource resource;

	private Resource resource1;

	private List<Resource> listRes = new ArrayList<>();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		listRes.add(resource1);
		page = context.create().page("/content/samplevida");
		resource = context.create().resource(page, "sample vida", "sling:resourceType", "vida/components/appcards",
				"basictab", listRes);

		appCard = resource.adaptTo(AppCard.class);
	}

	@Test
	void test() {
		assertNotNull(appCard);
	}

}
