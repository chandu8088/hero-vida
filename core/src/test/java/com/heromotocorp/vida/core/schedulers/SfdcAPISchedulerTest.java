package com.heromotocorp.vida.core.schedulers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import com.heromotocorp.vida.core.config.impl.SfdcAPISchedulerConfig;
import com.heromotocorp.vida.core.service.AvailableTestRideCityService;
import com.heromotocorp.vida.core.service.CityMasterConfigService;
import com.heromotocorp.vida.core.service.ExtendedVehicleConfigService;
import com.heromotocorp.vida.core.service.KYCDocumentTypeService;
import com.heromotocorp.vida.core.service.LTTRStateCityService;
import com.heromotocorp.vida.core.service.LongTermTestRideFreedoConfigService;
import com.heromotocorp.vida.core.service.LongTermTestRidePackageService;
import com.heromotocorp.vida.core.service.MasterGeoConfigService;
import com.heromotocorp.vida.core.service.PricesConfigService;
import com.heromotocorp.vida.core.service.ProductMasterConfigService;


import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for SfdcAPIScheduler
 */
@ExtendWith(AemContextExtension.class)
class SfdcAPISchedulerTest {

	private final AemContext context = new AemContext();

	private SfdcAPIScheduler sfdcAPIScheduler;

	private SfdcAPISchedulerConfig sfdcAPISchedulerConfig;

	private Scheduler scheduler;

	private ScheduleOptions scheduleOptions;

	private CityMasterConfigService cityMasterConfigService;

	private ProductMasterConfigService productMasterConfigService;

	private PricesConfigService pricesConfigService;

	private ExtendedVehicleConfigService extendedVehicleConfigService;

	private LongTermTestRideFreedoConfigService lttrRideFreedoConfigService;

	private LongTermTestRidePackageService longTermTestRidePackageService;

	private AvailableTestRideCityService availableTestRideCityService;

	private MasterGeoConfigService masterGeoConfigService;

	private KYCDocumentTypeService kycDocumentTypeService;

	private LTTRStateCityService lttrStateCityService;

	@BeforeEach
	void setUp() throws Exception {
		sfdcAPIScheduler = context.registerService(new SfdcAPIScheduler());
		sfdcAPISchedulerConfig = Mockito.mock(SfdcAPISchedulerConfig.class);
		scheduler = context.getService(Scheduler.class);
		scheduler = mock(Scheduler.class);
		PrivateAccessor.setField(sfdcAPIScheduler, "scheduler", scheduler);

		cityMasterConfigService = context.getService(CityMasterConfigService.class);
		cityMasterConfigService = mock(CityMasterConfigService.class);
		PrivateAccessor.setField(sfdcAPIScheduler, "cityMasterConfigService", cityMasterConfigService);

		scheduleOptions = Mockito.mock(ScheduleOptions.class);

		productMasterConfigService = context.getService(ProductMasterConfigService.class);
		productMasterConfigService = mock(ProductMasterConfigService.class);
		PrivateAccessor.setField(sfdcAPIScheduler, "productMasterConfigService", productMasterConfigService);

		pricesConfigService = context.getService(PricesConfigService.class);
		pricesConfigService = mock(PricesConfigService.class);
		PrivateAccessor.setField(sfdcAPIScheduler, "pricesConfigService", pricesConfigService);

		extendedVehicleConfigService = context.getService(ExtendedVehicleConfigService.class);
		extendedVehicleConfigService = mock(ExtendedVehicleConfigService.class);
		PrivateAccessor.setField(sfdcAPIScheduler, "extendedVehicleConfigService", extendedVehicleConfigService);

		lttrRideFreedoConfigService = context.getService(LongTermTestRideFreedoConfigService.class);
		lttrRideFreedoConfigService = mock(LongTermTestRideFreedoConfigService.class);
		PrivateAccessor.setField(sfdcAPIScheduler, "lttrRideFreedoConfigService", lttrRideFreedoConfigService);

		longTermTestRidePackageService = context.getService(LongTermTestRidePackageService.class);
		longTermTestRidePackageService = mock(LongTermTestRidePackageService.class);
		PrivateAccessor.setField(sfdcAPIScheduler, "longTermTestRidePackageService", longTermTestRidePackageService);

		availableTestRideCityService = context.getService(AvailableTestRideCityService.class);
		availableTestRideCityService = mock(AvailableTestRideCityService.class);
		PrivateAccessor.setField(sfdcAPIScheduler, "availableTestRideCityService", availableTestRideCityService);

		masterGeoConfigService = context.getService(MasterGeoConfigService.class);
		masterGeoConfigService = mock(MasterGeoConfigService.class);
		PrivateAccessor.setField(sfdcAPIScheduler, "masterGeoConfigService", masterGeoConfigService);

		kycDocumentTypeService = context.getService(KYCDocumentTypeService.class);
		kycDocumentTypeService = mock(KYCDocumentTypeService.class);
		PrivateAccessor.setField(sfdcAPIScheduler, "kycDocumentTypeService", kycDocumentTypeService);

		lttrStateCityService = context.getService(LTTRStateCityService.class);
		lttrStateCityService = mock(LTTRStateCityService.class);
		PrivateAccessor.setField(sfdcAPIScheduler, "lttrStateCityService", lttrStateCityService);

	}

	@Test
	void testActivate() {
		Mockito.when(sfdcAPISchedulerConfig.schedulername()).thenReturn("SFDC API Scheduler");
		Mockito.when(sfdcAPISchedulerConfig.isSfdcAPISchedulerEnabled()).thenReturn(true);
		Mockito.when(sfdcAPISchedulerConfig.cronExpression()).thenReturn("0 0/5 * 1/1 * ? *");
		Mockito.when(scheduler.EXPR("0 0/5 * 1/1 * ? *")).thenReturn(scheduleOptions);
		sfdcAPIScheduler.activate(sfdcAPISchedulerConfig);
		assertNotNull(sfdcAPIScheduler);
	}

	@Test
	void testDeactivate() {
		sfdcAPIScheduler.deactivate(sfdcAPISchedulerConfig);
		assertNotNull(sfdcAPIScheduler);
	}

	@Test
	void testRun() {
		sfdcAPIScheduler.run();

		testActivate();
		sfdcAPIScheduler.run();
	}

}
