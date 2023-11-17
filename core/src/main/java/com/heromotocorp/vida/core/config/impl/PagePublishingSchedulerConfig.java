package com.heromotocorp.vida.core.config.impl;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * Configuration for Scheduler.
 *
 */
@ObjectClassDefinition(
		name = "Scheduler Configuration",
		description = "Scheduler Configuration")
public @interface PagePublishingSchedulerConfig {
	
	@AttributeDefinition(
			name = "Page Publishing Scheduler Config",
			description = "This is Page Publishing Scheduler Config",
			type = AttributeType.STRING)
	public String schedulername();
	
	@AttributeDefinition(
			name = "Cron Expression",
			description = "Cron Expression used by Scheduler",
			type = AttributeType.STRING)
	public String cronExpression();
	

	@AttributeDefinition(
			name = "Config Page Path",
			description = "This is Config Page Path in Language Master",
			type = AttributeType.STRING)
	public String configPagePath();
	

	@AttributeDefinition(
			name = "Sfdc Master Page Path",
			description = "This is Sfdc Master Page Path in Language Master",
			type = AttributeType.STRING)
	public String sfdcmasterPagePath();
	
	@AttributeDefinition(
			name = "Enable Page Publishing Scheduler",
			description = "Will enable Page Publishing Scheduler",
			type = AttributeType.BOOLEAN)
	public boolean isPagePublishingSchedulerEnabled();
}
