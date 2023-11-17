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
 * JUnit test class for FullScreenVideoModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class FullScreenVideoModelTest {

	private final AemContext context = new AemContext();

	private FullScreenVideoModel fullScreenVideoModel = new FullScreenVideoModel();

	private Page page;

	private Resource resource;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/samplevida");
		resource = context.create().resource(page, "sample vida", "sling:resourceType",
				"vida/components/fullscreenvideo", "mobScreenVideo", "mobScreenVideo");

		fullScreenVideoModel = resource.adaptTo(FullScreenVideoModel.class);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.FullScreenVideoModel#getMobScreenVideo()}.
	 */
	@Test
	void testGetMobScreenVideo() {
		fullScreenVideoModel.getMobScreenVideo();
		assertEquals("mobScreenVideo", fullScreenVideoModel.getMobScreenVideo());
	}

}
