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
 * JUnit test class for PaymentConfigImpl
 */
@ExtendWith(AemContextExtension.class)
class PaymentConfigImplTest {

	private final AemContext context = new AemContext();

	private PaymentConfigImpl paymentConfigImpl = new PaymentConfigImpl();

	private PaymentConfigImpl.ServiceConfig serviceConfig;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		paymentConfigImpl = context.registerService(new PaymentConfigImpl());
		serviceConfig = Mockito.mock(PaymentConfigImpl.ServiceConfig.class);

		Mockito.when(serviceConfig.workingKey()).thenReturn("workingKey");
		Mockito.when(serviceConfig.successfulPagePath()).thenReturn("successfulPagePath");
		Mockito.when(serviceConfig.bookingResponseFwdPagePath()).thenReturn("bookingResponseFwdPagePath");
		Mockito.when(serviceConfig.lttrBookingResponseFwdPagePath()).thenReturn("lttrBookingResponseFwdPagePath");

		paymentConfigImpl.activate(serviceConfig);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.PaymentConfigImpl#activate(com.heromotocorp.vida.core.config.impl.PaymentConfigImpl.ServiceConfig)}.
	 */
	@Test
	void testActivate() {
		paymentConfigImpl.activate(serviceConfig);
		assertNotNull(paymentConfigImpl);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.PaymentConfigImpl#workingKey()}.
	 */
	@Test
	void testWorkingKey() {
		assertEquals("workingKey", paymentConfigImpl.workingKey());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.PaymentConfigImpl#successfulPagePath()}.
	 */
	@Test
	void testSuccessfulPagePath() {
		assertEquals("successfulPagePath", paymentConfigImpl.successfulPagePath());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.PaymentConfigImpl#bookingResponseFwdPagePath()}.
	 */
	@Test
	void testBookingResponseFwdPagePath() {
		assertEquals("bookingResponseFwdPagePath", paymentConfigImpl.bookingResponseFwdPagePath());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.PaymentConfigImpl#lttrBookingResponseFwdPagePath()}.
	 */
	@Test
	void testLttrBookingResponseFwdPagePath() {
		assertEquals("lttrBookingResponseFwdPagePath", paymentConfigImpl.lttrBookingResponseFwdPagePath());
	}

}
