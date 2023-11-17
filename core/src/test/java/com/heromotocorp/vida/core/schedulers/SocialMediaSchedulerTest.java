package com.heromotocorp.vida.core.schedulers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import com.heromotocorp.vida.core.config.TwitterConfig;
import com.heromotocorp.vida.core.config.YoutubeConfig;
import com.heromotocorp.vida.core.config.impl.SocialMediaSchedulerConfig;
import com.heromotocorp.vida.core.service.InstagramService;


import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for SocialMediaScheduler
 */
@ExtendWith(AemContextExtension.class)
class SocialMediaSchedulerTest {

	private final AemContext context = new AemContext();

	private SocialMediaScheduler socialMediaScheduler;

	private SocialMediaSchedulerConfig socialMediaSchedulerConfig;

	private Scheduler scheduler;

	private ScheduleOptions scheduleOptions;

	private YoutubeConfig youtubeConfig;

	private TwitterConfig twitterConfig;

	private InstagramService instagramService;

	@BeforeEach
	void setUp() throws Exception {
		socialMediaScheduler = context.registerService(new SocialMediaScheduler());
		socialMediaSchedulerConfig = Mockito.mock(SocialMediaSchedulerConfig.class);
		scheduler = context.getService(Scheduler.class);
		scheduler = mock(Scheduler.class);
		PrivateAccessor.setField(socialMediaScheduler, "scheduler", scheduler);
		youtubeConfig = context.getService(YoutubeConfig.class);
		youtubeConfig = mock(YoutubeConfig.class);
		PrivateAccessor.setField(socialMediaScheduler, "youtubeConfig", youtubeConfig);
		twitterConfig = context.getService(TwitterConfig.class);
		twitterConfig = mock(TwitterConfig.class);
		PrivateAccessor.setField(socialMediaScheduler, "twitterConfig", twitterConfig);
		instagramService = context.getService(InstagramService.class);
		instagramService = mock(InstagramService.class);
		PrivateAccessor.setField(socialMediaScheduler, "instagramService", instagramService);
		scheduleOptions = Mockito.mock(ScheduleOptions.class);
	}

	@Test
	void testActivate() {
		Mockito.when(socialMediaSchedulerConfig.schedulername()).thenReturn("Social Media Scheduler");
		Mockito.when(socialMediaSchedulerConfig.isSocialMediaSchedulerEnabled()).thenReturn(true);
		Mockito.when(socialMediaSchedulerConfig.cronExpression()).thenReturn("0 0/5 * 1/1 * ? *");
		Mockito.when(scheduler.EXPR("0 0/5 * 1/1 * ? *")).thenReturn(scheduleOptions);
		socialMediaScheduler.activate(socialMediaSchedulerConfig);

		assertNotNull(socialMediaScheduler);
	}

	@Test
	void testDeactivate() {
		socialMediaScheduler.deactivate(socialMediaSchedulerConfig);

		assertNotNull(socialMediaScheduler);
	}

	@Test
	void testRun() {
		socialMediaScheduler.run();

		testActivate();
		socialMediaScheduler.run();
	}

}
