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
 * JUnit test class for VerticalSliderModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class VerticalSliderModelTest {

	private final AemContext context = new AemContext();

	private VerticalSliderModel verticalSliderModel = new VerticalSliderModel();

	private VerticalSliderBeanModel verticalSliderBeanModel = new VerticalSliderBeanModel();

	private Page page;

	private Resource resource;

	private List<VerticalSliderBeanModel> verticalSlider = new ArrayList<>();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		verticalSlider.add(verticalSliderBeanModel);
		page = context.create().page("/content/samplevida");
		resource = context.create().resource(page, "sample vida", "sling:resourceType",
				"vida/components/verticalslider", "verticalSlider", verticalSlider);

		verticalSliderModel = resource.adaptTo(VerticalSliderModel.class);

	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.VerticalSliderModel#getVerticalSlider()}.
	 */
	@Test
	void testGetVerticalSlider() {
		verticalSliderModel.getVerticalSlider();
		assertNotNull(verticalSlider);
	}

}
