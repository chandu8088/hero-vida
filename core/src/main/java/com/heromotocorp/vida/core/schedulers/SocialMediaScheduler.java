package com.heromotocorp.vida.core.schedulers;

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

import com.heromotocorp.vida.core.config.TwitterConfig;
import com.heromotocorp.vida.core.config.YoutubeConfig;
import com.heromotocorp.vida.core.config.impl.SocialMediaSchedulerConfig;
import com.heromotocorp.vida.core.service.CityMasterConfigService;
import com.heromotocorp.vida.core.service.InstagramService;
import com.heromotocorp.vida.core.service.PricesConfigService;
import com.heromotocorp.vida.core.service.ProductMasterConfigService;

/**
 * This scheduler fetch twitter feeds in every 60 minutes and generated JSON
 * file in DAM.
 *
 */
@Component(immediate = true, service = Runnable.class, configurationPolicy = ConfigurationPolicy.REQUIRE )
@Designate(ocd = SocialMediaSchedulerConfig.class)
public class SocialMediaScheduler implements Runnable {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory.getLogger(SocialMediaScheduler.class);

	/** The Scheduler ID. */
	private int schedulerId;

	/** The Scheduler Service */
	@Reference
	private Scheduler scheduler;

	/** The Twitter Config. */
	@Reference
	private transient TwitterConfig twitterConfig;
	
	/** The YouTube Config. */
	@Reference
	private transient YoutubeConfig youtubeConfig;
	
	/** The InstaGram Config. */
	@Reference
    private InstagramService instagramService;


	/** The ResourceResolverFactory. */
	@Reference
	private ResourceResolverFactory resolverFactory;
	
	boolean isEnabled;

	/**
	 * Activate Method.
	 * 
	 * @param config
	 */
	@Activate
	protected void activate(SocialMediaSchedulerConfig config) {
		LOG.info("Parameters for activate method in SocialMediaScheduler-\t" + config);
		schedulerId = config.schedulername().hashCode();
		addScheduler(config);
	}

	/**
	 * Deactivate Method.
	 * 
	 * @param config
	 */
	@Deactivate
	protected void deactivate(SocialMediaSchedulerConfig config) {
		LOG.info("Parameters for deactivate method in SocialMediaScheduler-\t" + config);
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
	private void addScheduler(SocialMediaSchedulerConfig config) {
		LOG.info("Parameters for addScheduler method in SocialMediaScheduler-\t" + config);
		isEnabled = config.isSocialMediaSchedulerEnabled();
	    ScheduleOptions scheduleOptions = scheduler.EXPR(config.cronExpression());
	    scheduleOptions.name(String.valueOf(schedulerId));
	    scheduleOptions.canRunConcurrently(true);
	    scheduler.schedule(this, scheduleOptions);
	    LOG.info("\n ---------Scheduler added----------");
	}

	/**
	 * Method to Run Scheduler.
	 */
	@Override
	public void run() {
		if (isEnabled) {
			LOG.info("\n ====> RUN METHOD  ");
			youtubeConfig.getYoutubeVideos();
			twitterConfig.getTweets(twitterConfig.twitterProfile(), 3);
			instagramService.getInstagramJson();
		}
	}
}
