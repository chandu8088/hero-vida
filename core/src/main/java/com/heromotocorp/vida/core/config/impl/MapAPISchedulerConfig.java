package com.heromotocorp.vida.core.config.impl;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * Configuration for Scheduler.
 *
 */
@ObjectClassDefinition(
		name = "Map Scheduler Configuration",
		description = "Map Scheduler Configuration")
public @interface MapAPISchedulerConfig {
	
	@AttributeDefinition(
			name = "Map Scheduler",
			description = "This is Map Scheduler",
			type = AttributeType.STRING)
	public String schedulerName();
	
	@AttributeDefinition(
			name = "Cron Expression",
			description = "Cron Expression used by Scheduler",
			type = AttributeType.STRING)
	public String cronExpression();
	
	@AttributeDefinition(
			name = "Enable Map API Scheduler",
			description = "Will enable Map API Scheduler",
			type = AttributeType.BOOLEAN)
	public boolean isMapAPISchedulerEnabled();
}
