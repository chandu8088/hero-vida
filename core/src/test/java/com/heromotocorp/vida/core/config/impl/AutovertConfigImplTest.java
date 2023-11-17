/**
 * 
 */
package com.heromotocorp.vida.core.config.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;



import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for AutovertConfigImpl
 */
@ExtendWith(AemContextExtension.class)
class AutovertConfigImplTest {

	private final AemContext context = new AemContext();

	private AutovertConfigImpl autovertConfigImpl = new AutovertConfigImpl();

	private AutovertConfigImpl.ServiceConfig serviceConfig;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		autovertConfigImpl = context.registerService(new AutovertConfigImpl());
		serviceConfig = Mockito.mock(AutovertConfigImpl.ServiceConfig.class);

		Mockito.when(serviceConfig.authHeader()).thenReturn("authHeader");
		Mockito.when(serviceConfig.autovertClientSecret()).thenReturn("autovertClientSecret");
		autovertConfigImpl.activate(serviceConfig);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.AutovertConfigImpl#activate(com.heromotocorp.vida.core.config.impl.AutovertConfigImpl.ServiceConfig)}.
	 */
	@Test
	void testActivate() {
		autovertConfigImpl.activate(serviceConfig);
		assertNotNull(autovertConfigImpl);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.AutovertConfigImpl#authHeader()}.
	 */
	@Test
	void testAuthHeader() {
		assertEquals("authHeader", autovertConfigImpl.authHeader());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.AutovertConfigImpl#autovertClientSecret()}.
	 */
	@Test
	void testAutovertClientSecret() {
		assertEquals("autovertClientSecret", autovertConfigImpl.autovertClientSecret());
	}

}
