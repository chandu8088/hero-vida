/**
 * 
 */
package com.heromotocorp.vida.core.config.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for PagePublishingSchedulerConfig
 */
@ExtendWith(AemContextExtension.class)
class PagePublishingSchedulerConfigTest {

	private PagePublishingSchedulerConfig pagePublishingSchedulerConfig;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		pagePublishingSchedulerConfig = Mockito.mock(PagePublishingSchedulerConfig.class);

		Mockito.when(pagePublishingSchedulerConfig.schedulername()).thenReturn("pagePublishingSchedulerConfig");
		Mockito.when(pagePublishingSchedulerConfig.cronExpression()).thenReturn("Cron Expression");
		Mockito.when(pagePublishingSchedulerConfig.isPagePublishingSchedulerEnabled()).thenReturn(true);
		Mockito.when(pagePublishingSchedulerConfig.configPagePath()).thenReturn("ConfigPagePath");
		Mockito.when(pagePublishingSchedulerConfig.sfdcmasterPagePath()).thenReturn("SfdcmasterPagePath");
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.PagePublishingSchedulerConfig#schedulername()}.
	 */
	@Test
	void testSchedulername() {
		assertEquals("pagePublishingSchedulerConfig", pagePublishingSchedulerConfig.schedulername());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.PagePublishingSchedulerConfig#cronExpression()}.
	 */
	@Test
	void testCronExpression() {
		assertEquals("Cron Expression", pagePublishingSchedulerConfig.cronExpression());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.PagePublishingSchedulerConfig#configPagePath()}.
	 */
	@Test
	void testConfigPagePath() {
		assertEquals("ConfigPagePath", pagePublishingSchedulerConfig.configPagePath());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.PagePublishingSchedulerConfig#sfdcmasterPagePath()}.
	 */
	@Test
	void testSfdcmasterPagePath() {
		assertEquals("SfdcmasterPagePath", pagePublishingSchedulerConfig.sfdcmasterPagePath());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.PagePublishingSchedulerConfig#isPagePublishingSchedulerEnabled()}.
	 */
	@Test
	void testIsPagePublishingSchedulerEnabled() {
		assertEquals(true, pagePublishingSchedulerConfig.isPagePublishingSchedulerEnabled());
	}

}
