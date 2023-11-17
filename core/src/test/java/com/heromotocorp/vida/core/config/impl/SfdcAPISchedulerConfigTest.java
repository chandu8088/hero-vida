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
 * JUnit test class for SfdcAPISchedulerConfig
 */
@ExtendWith(AemContextExtension.class)
class SfdcAPISchedulerConfigTest {

	private SfdcAPISchedulerConfig sfdcAPISchedulerConfig;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		sfdcAPISchedulerConfig = Mockito.mock(SfdcAPISchedulerConfig.class);

		Mockito.when(sfdcAPISchedulerConfig.schedulername()).thenReturn("SfdcAPISchedulerConfig");
		Mockito.when(sfdcAPISchedulerConfig.cronExpression()).thenReturn("Cron Expression");
		Mockito.when(sfdcAPISchedulerConfig.isSfdcAPISchedulerEnabled()).thenReturn(true);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.SfdcAPISchedulerConfig#schedulername()}.
	 */
	@Test
	void testSchedulername() {
		assertEquals("SfdcAPISchedulerConfig", sfdcAPISchedulerConfig.schedulername());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.SfdcAPISchedulerConfig#cronExpression()}.
	 */
	@Test
	void testCronExpression() {
		assertEquals("Cron Expression", sfdcAPISchedulerConfig.cronExpression());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.SfdcAPISchedulerConfig#isSfdcAPISchedulerEnabled()}.
	 */
	@Test
	void testIsSfdcAPISchedulerEnabled() {
		assertEquals(true, sfdcAPISchedulerConfig.isSfdcAPISchedulerEnabled());
	}

}
