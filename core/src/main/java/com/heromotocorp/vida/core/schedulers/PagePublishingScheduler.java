package com.heromotocorp.vida.core.schedulers;

import java.util.Objects;

import javax.jcr.Session;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.replication.Replicator;
import com.day.cq.wcm.api.PageManager;
import com.heromotocorp.vida.core.config.impl.PagePublishingSchedulerConfig;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;

/**
 * This scheduler replicates config and sfdc master page.
 *
 */
@Component(immediate = true, service = Runnable.class, configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = PagePublishingSchedulerConfig.class)
public class PagePublishingScheduler implements Runnable {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory.getLogger(PagePublishingScheduler.class);

	/** The Scheduler ID. */
	private int schedulerId;

	/** The Scheduler Service */
	@Reference
	private Scheduler scheduler;

	/** The ResourceResolverFactory. */
	@Reference
	private ResourceResolverFactory resolverFactory;

	/** The Replicator. */
	@Reference
	private Replicator replicator;

	/** The Config Page Path. */
	private String configPagePath = StringUtils.EMPTY;

	/** The Sfdc-Master Page Path. */
	private String sfdcmasterPagePath = StringUtils.EMPTY;
	
	boolean isEnabled;

	/**
	 * Activate Method.
	 * 
	 * @param config
	 */
	@Activate
	protected void activate(PagePublishingSchedulerConfig config) {
		LOG.info("Parameters for activate method in PagePublishingScheduler-\t" + config);
		schedulerId = config.schedulername().hashCode();
		addScheduler(config);
	}

	/**
	 * Deactivate Method.
	 * 
	 * @param config
	 */
	@Deactivate
	protected void deactivate(PagePublishingSchedulerConfig config) {
		LOG.info("Parameters for deactivate method in PagePublishingScheduler-\t" + config);
		removeScheduler();
	}

	/**
	 * Method to Remove Scheduler.
	 */
	private void removeScheduler() {
		scheduler.unschedule(String.valueOf(schedulerId));

	}

	/**
	 * Method to Add Scheduler.
	 * 
	 * @param config
	 */
	private void addScheduler(PagePublishingSchedulerConfig config) {
		LOG.info("Parameters for addScheduler method in PagePublishingScheduler-\t" + config);
		
		ScheduleOptions scheduleOptions = scheduler.EXPR("");
		scheduleOptions.name(String.valueOf(schedulerId));
		scheduleOptions.canRunConcurrently(true);
		scheduler.schedule(this, scheduleOptions);
		ScheduleOptions scheduleOptionsNow = scheduler.NOW();
		scheduler.schedule(this, scheduleOptionsNow);
		configPagePath = config.configPagePath();
		sfdcmasterPagePath = config.sfdcmasterPagePath();
		isEnabled = config.isPagePublishingSchedulerEnabled();
		LOG.info("\n ---------Page Publishing Scheduler added----------");
	}

	/**
	 * Method to Run Scheduler.
	 */
	@Override
	public void run() {
		if (isEnabled) {
			LOG.info("\n ====>Page Publishing Scheduler RUN METHOD  ");
			ResourceResolver resolver = CommonUtils.getResourceResolver(resolverFactory, Constants.WRITERSERVICEUSER);
			PageManager pageManager = resolver.adaptTo(PageManager.class);
			Session session = resolver.adaptTo(Session.class);
			if (Objects.nonNull(pageManager.getPage(configPagePath))) {
				CommonUtils.replicateContent(session, configPagePath, replicator);
			}
			if (Objects.nonNull(pageManager.getPage(sfdcmasterPagePath))) {
				CommonUtils.replicateContent(session, sfdcmasterPagePath, replicator);
			}
		}
	}

}
