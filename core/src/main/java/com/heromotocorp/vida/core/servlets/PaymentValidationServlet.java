package com.heromotocorp.vida.core.servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.Externalizer;
import com.day.cq.commons.PathInfo;
import com.heromotocorp.vida.core.config.PaymentConfig;
import com.heromotocorp.vida.core.utils.Constants;

/**
 * Servlet that writes the response for Payment Success/Failure.
 * 
 *
 */
@Component(service = { Servlet.class })
@SlingServletResourceTypes(resourceTypes = Constants.PAGERESOURCETYPE, methods = HttpConstants.METHOD_POST,
selectors = {"pre-booking","booking","lttr-booking"},
extensions = "json")
@ServiceDescription("CCAvenue Payment Handler Servlet")
public class PaymentValidationServlet extends SlingAllMethodsServlet {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory.getLogger(PaymentValidationServlet.class);

	private static final String QUERY_STR_CHAR = "?";

	private static final String EQUAL = "=";

	private static final String AMPERSEND = "&";

	private static final String QUERYSTATUS = "?status=";

	private static final String LTTR_BOOKING = "lttr-booking";

	private static final String BOOKING = "booking";

	private static final String ENC_RESP = "encResp=";

	private static final String PRE_BOOKING = "pre-booking";

	private static final String ORDER_STATUS = "order_status";

	/** Serial Version Id. */
	private static final long serialVersionUID = 6629412121332849512L;

	/**
	 * Payment Config.
	 */
	@Reference
	transient PaymentConfig paymentConfig;

	/** The externalizer. */
	@Reference
	private transient Externalizer externalizer;

	/**
	 * Do Post.
	 *
	 * @param req  the req
	 * @param resp the resp
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	@Override
	protected void doPost(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServletException, IOException {
		String encResp = req.getParameter("encResp");
		PathInfo pathinfo = new PathInfo(req.getPathInfo());
		String[] selectorArray = pathinfo.getSelectors();
		
		String path ="";
		LOG.info("Encrypted values = " + encResp);
		LOG.info("path info = " + pathinfo);
		LOG.info("selector Array values = " + selectorArray);
		if (Arrays.asList(selectorArray).contains(PRE_BOOKING)){
			 path = new StringBuilder(paymentConfig.successfulPagePath()).append(QUERY_STR_CHAR).append(ENC_RESP).append(encResp).toString();
		}else{
			
			if (Arrays.asList(selectorArray).contains(BOOKING)){
				 path = new StringBuilder(paymentConfig.bookingResponseFwdPagePath()).append(QUERY_STR_CHAR).append(ENC_RESP).append(encResp).toString();
			
			}
			if (Arrays.asList(selectorArray).contains(LTTR_BOOKING)) {
				path = new StringBuilder(paymentConfig.lttrBookingResponseFwdPagePath()).append(QUERY_STR_CHAR).append(ENC_RESP).append(encResp).toString();

			}
		}	

		String successfulPagePath = externalizer.publishLink(req.getResourceResolver(), path);
		LOG.info("successful Page Path =" + successfulPagePath);
		resp.sendRedirect(successfulPagePath);
	}

}
