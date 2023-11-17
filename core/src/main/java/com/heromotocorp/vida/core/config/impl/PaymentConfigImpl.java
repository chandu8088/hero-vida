package com.heromotocorp.vida.core.config.impl;


import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import com.heromotocorp.vida.core.config.PaymentConfig;

/**
 * Osgi configuration to Payment Configuration.
 *
 */
@Component(service = PaymentConfig.class, immediate = true)
@Designate(ocd = PaymentConfigImpl.ServiceConfig.class)
public class PaymentConfigImpl implements PaymentConfig {
	
	/**
	 * The Interface ServiceConfig.
	 */
	@ObjectClassDefinition(name = "Payment Configuration", description = "Payment Configuration")
		 public @interface ServiceConfig {
    	
    	/**
	     * Working key.
	     *
	     * @return the string
	     */
	    @AttributeDefinition(name = "Working Key", description = "This is Working Key",
    			type = AttributeType.STRING)
		String workingKey();
    	
    	/**
	     * Successful page path.
	     *
	     * @return the string
	     */
	    @AttributeDefinition(name = "Successful Page Path", description = "Successful Page Path",
    			type = AttributeType.STRING)
		String successfulPagePath();

            
	
	
	 @AttributeDefinition(name = "Booking Repspose Page Path", description = "Booking Repspose Page Path",
 			type = AttributeType.STRING)
		String bookingResponseFwdPagePath();
 	
		/**
		 * Long Term Test Ride Booking Response Forward page path.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Long Term Test Ride Booking Response Forward Page Path", description = "Long Term Test Ride Booking Response Forward Page Path", type = AttributeType.STRING)
		String lttrBookingResponseFwdPagePath();

        }
	

    /**
     * Working Key.
     */
    private String workingKey;
    
    /** The successful page path. */
    private String  successfulPagePath;
    
    
    private String  bookingResponseFwdPagePath;
    
    /** The Long Term Test Ride Booking Response Forward page path. */
    private String  lttrBookingResponseFwdPagePath;
   
    /**
     * This is activate method.
     *
     * @param serviceConfig the service config
     */
    @Activate
    protected void activate(ServiceConfig serviceConfig) {
    	
    	workingKey = serviceConfig.workingKey();
    	successfulPagePath = serviceConfig.successfulPagePath();
    	bookingResponseFwdPagePath = serviceConfig.bookingResponseFwdPagePath();
    	lttrBookingResponseFwdPagePath = serviceConfig.lttrBookingResponseFwdPagePath();
    	
    }

	/**
	 * This is working key.
	 *
	 * @return the string
	 */
	@Override
	public String workingKey() {
		return workingKey;
	}

	/**
	 * Successful page path.
	 *
	 * @return the string
	 */
	@Override
	public String successfulPagePath() {
		return successfulPagePath;
	}

	@Override
	public String bookingResponseFwdPagePath() {
		return bookingResponseFwdPagePath;
	}

	@Override
	public String lttrBookingResponseFwdPagePath() {
		return lttrBookingResponseFwdPagePath;
	}
	
}
