package com.heromotocorp.vida.core.schedulers;

import java.util.Objects;

import javax.jcr.Session;

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
import com.heromotocorp.vida.core.service.PageUrlConfigService;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;

/**
 * Service to get List of URLS
 *
 */
@Component(immediate = true, service = Runnable.class, configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = PageUrlConfigService.class)
public class PageUrlConfigServiceScheduler implements Runnable {

	/** The Constant log. */
	private Logger log = LoggerFactory.getLogger(this.getClass());

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

	private String[] pageURLs;
	
	boolean isEnabled;

	/**
	 * Activate Method.
	 * 
	 * @param config
	 */
	@Activate
	protected void activate(PageUrlConfigService config) {
		log.info("Parameters for activate method in PageActivationScheduler-\t");
		schedulerId = config.schedulername().hashCode();
		addScheduler(config);
	}

	/**
	 * Deactivate Method.
	 * 
	 * @param config
	 */
	@Deactivate
	protected void deactivate(PageUrlConfigService config) {
		log.info("Parameters for deactivate method in PageActivationScheduler-\t");
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
	private void addScheduler(PageUrlConfigService config) {
		log.info("Parameters for addScheduler method in PageActivationScheduler-\t");

		ScheduleOptions scheduleOptions = scheduler.EXPR(config.cronExpression());
		scheduleOptions.name(String.valueOf(schedulerId));
		scheduleOptions.canRunConcurrently(true);
		scheduler.schedule(this, scheduleOptions);
		ScheduleOptions scheduleOptionsNow = scheduler.NOW();
		scheduler.schedule(this, scheduleOptionsNow);
		pageURLs = config.pageURLs();
		isEnabled = config.isPageUrlConfigSchedulerEnabled();
		log.info("\n ---------Page Activating Scheduler added----------");
	}

	/**
	 * Method to Run Scheduler.
	 */
	@Override
	public void run() {
		if (isEnabled) {
			log.info("\n ====>Page Activating Scheduler RUN METHOD  ");
			ResourceResolver resolver = CommonUtils.getResourceResolver(resolverFactory, Constants.WRITERSERVICEUSER);
			PageManager pageManager = resolver.adaptTo(PageManager.class);
			Session session = resolver.adaptTo(Session.class);
			for (int i = 0; i < pageURLs.length; i++) {
				if (Objects.nonNull(pageManager.getPage(pageURLs[i]))) {
					CommonUtils.replicateContent(session, pageURLs[i], replicator);
				}
			}
		}
	}
}
