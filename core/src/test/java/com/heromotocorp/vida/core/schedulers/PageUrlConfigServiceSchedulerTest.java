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
import com.heromotocorp.vida.core.service.PageUrlConfigService;

import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for PageUrlConfigServiceScheduler
 */
@ExtendWith(AemContextExtension.class)
class PageUrlConfigServiceSchedulerTest {

	private final AemContext context = new AemContext();

	private PageUrlConfigServiceScheduler pageUrlConfigServiceScheduler;

	private PageUrlConfigService pageUrlConfigService;

	private Scheduler scheduler;

	private ScheduleOptions scheduleOptions;

	private ScheduleOptions scheduleOptionsNow;

	private String[] pageURLs = { "/content/vida" };

	private ResourceResolverFactory resolverFactory;

	private ResourceResolver resolver;

	private PageManager pageManager;

	private Session session;

	private Page page;

	private Replicator replicator;

	@BeforeEach
	void setUp() throws Exception {
		pageUrlConfigServiceScheduler = context.registerService(new PageUrlConfigServiceScheduler());
		pageUrlConfigService = Mockito.mock(PageUrlConfigService.class);
		scheduler = context.getService(Scheduler.class);
		scheduler = mock(Scheduler.class);
		PrivateAccessor.setField(pageUrlConfigServiceScheduler, "scheduler", scheduler);
		scheduleOptions = Mockito.mock(ScheduleOptions.class);
		scheduleOptionsNow = Mockito.mock(ScheduleOptions.class);
		resolver = Mockito.mock(ResourceResolver.class);
		pageManager = Mockito.mock(PageManager.class);
		session = Mockito.mock(Session.class);
		page = Mockito.mock(Page.class);

		resolverFactory = context.getService(ResourceResolverFactory.class);
		resolverFactory = mock(ResourceResolverFactory.class);
		PrivateAccessor.setField(pageUrlConfigServiceScheduler, "resolverFactory", resolverFactory);

		replicator = context.getService(Replicator.class);
		replicator = mock(Replicator.class);
		PrivateAccessor.setField(pageUrlConfigServiceScheduler, "replicator", replicator);
	}

	@Test
	void testActivate() {
		Mockito.when(pageUrlConfigService.schedulername()).thenReturn("Page URL Config Service Scheduler");
		Mockito.when(pageUrlConfigService.cronExpression()).thenReturn("0 0/5 * 1/1 * ? *");
		Mockito.when(pageUrlConfigService.isPageUrlConfigSchedulerEnabled()).thenReturn(true);
		Mockito.when(scheduler.EXPR("0 0/5 * 1/1 * ? *")).thenReturn(scheduleOptions);
		Mockito.when(scheduler.NOW()).thenReturn(scheduleOptionsNow);
		Mockito.when(pageUrlConfigService.pageURLs()).thenReturn(pageURLs);
		pageUrlConfigServiceScheduler.activate(pageUrlConfigService);

		assertNotNull(pageUrlConfigServiceScheduler);
	}

	@Test
	void testDeactivate() {
		pageUrlConfigServiceScheduler.deactivate(pageUrlConfigService);

		assertNotNull(pageUrlConfigServiceScheduler);
	}

	@Test
	void testRun() {
		pageUrlConfigServiceScheduler.run();

		testActivate();
		Mockito.when(CommonUtils.getResourceResolver(resolverFactory, Constants.WRITERSERVICEUSER))
				.thenReturn(resolver);
		Mockito.when(resolver.adaptTo(PageManager.class)).thenReturn(pageManager);
		Mockito.when(resolver.adaptTo(Session.class)).thenReturn(session);
		pageUrlConfigServiceScheduler.run();

		Mockito.when(pageManager.getPage("/content/vida")).thenReturn(page);
		pageUrlConfigServiceScheduler.run();
	}

}
