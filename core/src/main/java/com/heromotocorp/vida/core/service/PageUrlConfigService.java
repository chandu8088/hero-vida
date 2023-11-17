package com.heromotocorp.vida.core.service;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * Interface Service to get URLs from PageURLConfig and activate it .
 *
 */
@ObjectClassDefinition(
		name = "Page Activate Scheduler Configuration",
		description = "Page Activate Scheduler Configuration")
public @interface PageUrlConfigService {

	@AttributeDefinition(
			name = "Page Activating Scheduler Config",
			description = "This is Page Publishing Scheduler Config", 
			type = AttributeType.STRING)
	public String schedulername();

	@AttributeDefinition(
			name = "Cron Expression", 
			description = "Cron Expression used by Scheduler", 
			type = AttributeType.STRING)
	public String cronExpression();

	/**
	 * Gets the page url.
	 *
	 * @return the page url
	 */
	@AttributeDefinition(
			name = "Page URLs",
			description = "This is Config Page Path in Language Master", 
			type = AttributeType.STRING)
	public String[] pageURLs();

	/**
	 * Enables Scheduler.
	 *
	 * @return the boolean value
	 */
	@AttributeDefinition(
			name = "Enable Page URL Config Scheduler",
			description = "Will enable Page URL Config Scheduler",
			type = AttributeType.BOOLEAN)
	public boolean isPageUrlConfigSchedulerEnabled();
}
