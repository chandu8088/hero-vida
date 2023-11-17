package com.heromotocorp.vida.core.schedulers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import javax.jcr.Session;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import com.day.cq.replication.Replicator;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.heromotocorp.vida.core.config.impl.PagePublishingSchedulerConfig;

import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for PagePublishingScheduler
 */
@ExtendWith(AemContextExtension.class)
class PagePublishingSchedulerTest {

	private final AemContext context = new AemContext();

	private PagePublishingScheduler pagePublishingScheduler;

	private PagePublishingSchedulerConfig pagePublishingSchedulerConfig;

	private Scheduler scheduler;

	private ScheduleOptions scheduleOptions;

	private ScheduleOptions scheduleOptionsNow;

	private ResourceResolverFactory resolverFactory;

	private ResourceResolver resolver;

	private PageManager pageManager;

	private Session session;
	
	private Page page;
	
	private Replicator replicator;

	@BeforeEach
	public void setUpBeforeClass() throws Exception {
		pagePublishingScheduler = context.registerService(new PagePublishingScheduler());
		pagePublishingSchedulerConfig = Mockito.mock(PagePublishingSchedulerConfig.class);
		scheduler = context.getService(Scheduler.class);
		scheduler = mock(Scheduler.class);
		PrivateAccessor.setField(pagePublishingScheduler, "scheduler", scheduler);
		scheduleOptions = Mockito.mock(ScheduleOptions.class);
		scheduleOptionsNow = Mockito.mock(ScheduleOptions.class);
		resolver = Mockito.mock(ResourceResolver.class);
		pageManager = Mockito.mock(PageManager.class);
		session = Mockito.mock(Session.class);
		page = Mockito.mock(Page.class);

		resolverFactory = context.getService(ResourceResolverFactory.class);
		resolverFactory = mock(ResourceResolverFactory.class);
		PrivateAccessor.setField(pagePublishingScheduler, "resolverFactory", resolverFactory);
		
		replicator = context.getService(Replicator.class);
		replicator = mock(Replicator.class);
		PrivateAccessor.setField(pagePublishingScheduler, "replicator", replicator);
	}

	@Test
	void testActivate() {
		Mockito.when(pagePublishingSchedulerConfig.schedulername()).thenReturn("Page Publishing Scheduler");
		Mockito.when(pagePublishingSchedulerConfig.cronExpression()).thenReturn("");
		Mockito.when(pagePublishingSchedulerConfig.isPagePublishingSchedulerEnabled()).thenReturn(true);
		Mockito.when(scheduler.EXPR("")).thenReturn(scheduleOptions);
		Mockito.when(scheduler.NOW()).thenReturn(scheduleOptionsNow);
		Mockito.when(pagePublishingSchedulerConfig.configPagePath()).thenReturn("configPagePath");
		Mockito.when(pagePublishingSchedulerConfig.sfdcmasterPagePath()).thenReturn("sfdcmasterPagePath");
		pagePublishingScheduler.activate(pagePublishingSchedulerConfig);
		
		assertNotNull(pagePublishingScheduler);
	}

	@Test
	void testDeactivate() {
		pagePublishingScheduler.deactivate(pagePublishingSchedulerConfig);
		
		assertNotNull(pagePublishingScheduler);
	}

	@Test
	void testRun() {
		pagePublishingScheduler.run();
		
		testActivate();
		Mockito.when(CommonUtils.getResourceResolver(resolverFactory, Constants.WRITERSERVICEUSER))
		.thenReturn(resolver);
		Mockito.when(resolver.adaptTo(PageManager.class)).thenReturn(pageManager);
		Mockito.when(resolver.adaptTo(Session.class)).thenReturn(session);
		pagePublishingScheduler.run();
		
		Mockito.when(pageManager.getPage("configPagePath")).thenReturn(page);
		Mockito.when(pageManager.getPage("sfdcmasterPagePath")).thenReturn(page);
		pagePublishingScheduler.run();
	}

}
