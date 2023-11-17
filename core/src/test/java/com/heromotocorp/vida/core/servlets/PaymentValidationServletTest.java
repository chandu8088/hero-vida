/**
 * 
 */
package com.heromotocorp.vida.core.servlets;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.commons.Externalizer;
import com.heromotocorp.vida.core.config.PaymentConfig;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for PaymentValidationServlet
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class PaymentValidationServletTest {

	private final AemContext context = new AemContext();

	private PaymentValidationServlet paymentValidationServlet = new PaymentValidationServlet();

	private SlingHttpServletRequest req;

	private SlingHttpServletResponse resp;

	private PaymentConfig paymentConfig;

	private Externalizer externalizer;
	
	private ResourceResolver resolver;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		req = Mockito.mock(SlingHttpServletRequest.class);
		resp = Mockito.mock(SlingHttpServletResponse.class);
		resolver = Mockito.mock(ResourceResolver.class);

		paymentConfig = context.getService(PaymentConfig.class);
		paymentConfig = Mockito.mock(PaymentConfig.class);
		PrivateAccessor.setField(paymentValidationServlet, "paymentConfig", paymentConfig);

		externalizer = context.getService(Externalizer.class);
		externalizer = mock(Externalizer.class);
		PrivateAccessor.setField(paymentValidationServlet, "externalizer", externalizer);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.servlets.PaymentValidationServlet#doPost(org.apache.sling.api.SlingHttpServletRequest, org.apache.sling.api.SlingHttpServletResponse)}.
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@Test
	void testDoPostSlingHttpServletRequestSlingHttpServletResponse() throws ServletException, IOException {
		Mockito.when(req.getParameter("encResp")).thenReturn("encResp");
		Mockito.when(req.getPathInfo()).thenReturn("/content/vida.pre-booking.html");
		Mockito.when(paymentConfig.successfulPagePath()).thenReturn("successfulPagePath");
		Mockito.when(req.getResourceResolver()).thenReturn(resolver);
		Mockito.when(externalizer.publishLink(resolver, "successfulPagePath?encResp=encResp")).thenReturn("successfulPagePath");
		paymentValidationServlet.doPost(req, resp);

		Mockito.when(externalizer.publishLink(resolver, "")).thenReturn("successfulPagePath");
		Mockito.when(req.getPathInfo()).thenReturn("/content/vida.pre.html");
		paymentValidationServlet.doPost(req, resp);

		Mockito.when(paymentConfig.bookingResponseFwdPagePath()).thenReturn("successfulPagePath");
		Mockito.when(externalizer.publishLink(resolver, "successfulPagePath?encResp=encResp")).thenReturn("successfulPagePath");
		Mockito.when(req.getPathInfo()).thenReturn("/content/vida.booking.html");
		paymentValidationServlet.doPost(req, resp);

		Mockito.when(paymentConfig.lttrBookingResponseFwdPagePath()).thenReturn("successfulPagePath");
		Mockito.when(req.getPathInfo()).thenReturn("/content/vida.lttr-booking.html");
		paymentValidationServlet.doPost(req, resp);

		assertNotNull(paymentValidationServlet);
	}

}
