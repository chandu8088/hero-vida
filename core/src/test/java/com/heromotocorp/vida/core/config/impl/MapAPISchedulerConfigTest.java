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
 * JUnit test class for MapAPISchedulerConfig
 */
@ExtendWith(AemContextExtension.class)
class MapAPISchedulerConfigTest {

	private MapAPISchedulerConfig mapAPISchedulerConfig;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		mapAPISchedulerConfig = Mockito.mock(MapAPISchedulerConfig.class);

		Mockito.when(mapAPISchedulerConfig.schedulerName()).thenReturn("MapAPISchedulerConfig");
		Mockito.when(mapAPISchedulerConfig.cronExpression()).thenReturn("Cron Expression");
		Mockito.when(mapAPISchedulerConfig.isMapAPISchedulerEnabled()).thenReturn(true);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.MapAPISchedulerConfig#schedulerName()}.
	 */
	@Test
	void testSchedulerName() {
		assertEquals("MapAPISchedulerConfig", mapAPISchedulerConfig.schedulerName());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.MapAPISchedulerConfig#cronExpression()}.
	 */
	@Test
	void testCronExpression() {
		assertEquals("Cron Expression", mapAPISchedulerConfig.cronExpression());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.MapAPISchedulerConfig#isMapAPISchedulerEnabled()}.
	 */
	@Test
	void testIsMapAPISchedulerEnabled() {
		assertEquals(true, mapAPISchedulerConfig.isMapAPISchedulerEnabled());
	}

}
