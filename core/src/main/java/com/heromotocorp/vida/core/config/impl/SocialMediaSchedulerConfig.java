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
public @interface SocialMediaSchedulerConfig {
	
	@AttributeDefinition(
			name = "Social Media Scheduler",
			description = "This is Social Media Scheduler",
			type = AttributeType.STRING)
	public String schedulername();
	
	@AttributeDefinition(
			name = "Cron Expression",
			description = "Cron Expression used by Scheduler",
			type = AttributeType.STRING)
	public String cronExpression();
	
	@AttributeDefinition(
			name = "Enable Social Media Scheduler",
			description = "Will enable Social Media Scheduler",
			type = AttributeType.BOOLEAN)
	public boolean isSocialMediaSchedulerEnabled();
}
