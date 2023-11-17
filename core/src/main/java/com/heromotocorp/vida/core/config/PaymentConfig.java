package com.heromotocorp.vida.core.config;

/**
 * Interface for Payment Configuration.
 *
 */
public interface PaymentConfig {

	String workingKey();
	String successfulPagePath();
	String bookingResponseFwdPagePath();
	String lttrBookingResponseFwdPagePath();
}
