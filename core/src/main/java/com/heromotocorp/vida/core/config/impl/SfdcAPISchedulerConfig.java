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
public @interface SfdcAPISchedulerConfig {
	
	@AttributeDefinition(
			name = "Social Media Scheduler",
			description = "This is SFDC API Scheduler",
			type = AttributeType.STRING)
	public String schedulername();
	
	@AttributeDefinition(
			name = "Cron Expression",
			description = "Cron Expression used by Scheduler",
			type = AttributeType.STRING)
	public String cronExpression();
	
	@AttributeDefinition(
			name = "Enable SFDC API Scheduler",
			description = "Will enable SFDC API Scheduler",
			type = AttributeType.BOOLEAN)
	public boolean isSfdcAPISchedulerEnabled();
}
