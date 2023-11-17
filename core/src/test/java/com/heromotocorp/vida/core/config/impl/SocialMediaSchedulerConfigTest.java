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
 * JUnit test class for SocialMediaSchedulerConfig
 */
@ExtendWith(AemContextExtension.class)
class SocialMediaSchedulerConfigTest {

	private SocialMediaSchedulerConfig socialMediaSchedulerConfig;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		socialMediaSchedulerConfig = Mockito.mock(SocialMediaSchedulerConfig.class);

		Mockito.when(socialMediaSchedulerConfig.schedulername()).thenReturn("SocialMediaSchedulerConfig");
		Mockito.when(socialMediaSchedulerConfig.cronExpression()).thenReturn("Cron Expression");
		Mockito.when(socialMediaSchedulerConfig.isSocialMediaSchedulerEnabled()).thenReturn(true);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.SocialMediaSchedulerConfig#schedulername()}.
	 */
	@Test
	void testSchedulername() {
		assertEquals("SocialMediaSchedulerConfig", socialMediaSchedulerConfig.schedulername());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.SocialMediaSchedulerConfig#cronExpression()}.
	 */
	@Test
	void testCronExpression() {
		assertEquals("Cron Expression", socialMediaSchedulerConfig.cronExpression());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.SocialMediaSchedulerConfig#isSocialMediaSchedulerEnabled()}.
	 */
	@Test
	void testIsSocialMediaSchedulerEnabled() {
		assertEquals(true, socialMediaSchedulerConfig.isSocialMediaSchedulerEnabled());
	}

}
