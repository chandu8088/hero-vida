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

/**
 * This SFDC scheduler fetch  Product, Price data from city Configured. After fetching it put the data in DAM 
 * 
 *
 */
@Component(immediate = true, service = Runnable.class, configurationPolicy = ConfigurationPolicy.REQUIRE )
@Designate(ocd = SfdcAPISchedulerConfig.class)


public class SfdcAPIScheduler implements Runnable {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory.getLogger(SfdcAPIScheduler.class);

	/** The Scheduler ID. */
	private int schedulerId;

	/** The Scheduler Service */
	@Reference
	private Scheduler scheduler;

	/** The ResourceResolverFactory. */
	@Reference
	private ResourceResolverFactory resolverFactory;
	
	@Reference
	private CityMasterConfigService cityMasterConfigService;
	
	@Reference
	private ProductMasterConfigService productMasterConfigService;
	
	@Reference
	private PricesConfigService pricesConfigService;
	
	@Reference
	private ExtendedVehicleConfigService extendedVehicleConfigService;
	
	@Reference
	private LongTermTestRideFreedoConfigService lttrRideFreedoConfigService;

	@Reference
	private LongTermTestRidePackageService longTermTestRidePackageService;
	
	@Reference
	private AvailableTestRideCityService availableTestRideCityService;
	
	@Reference
	private MasterGeoConfigService masterGeoConfigService;
	
	@Reference
	private KYCDocumentTypeService kycDocumentTypeService;
	
	@Reference
	private LTTRStateCityService lttrStateCityService;
	
	boolean isEnabled;
	

	/**
	 * Activate Method.
	 * 
	 * @param config
	 */
	@Activate
	protected void activate(SfdcAPISchedulerConfig config) {
		LOG.info("Parameters for addScheduler method in SfdcAPIScheduler-\t" + config);
		schedulerId = config.schedulername().hashCode();
		addScheduler(config);
	}

	/**
	 * Deactivate Method.
	 * 
	 * @param config
	 */
	@Deactivate
	protected void deactivate(SfdcAPISchedulerConfig config) {
		LOG.info("Parameters for deactivate method in SfdcAPIScheduler-\t" + config);
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
	private void addScheduler(SfdcAPISchedulerConfig config) {
		isEnabled = config.isSfdcAPISchedulerEnabled();
		LOG.info("Parameters for addScheduler method in SfdcAPIScheduler-\t" + config.cronExpression());
		ScheduleOptions options = scheduler.EXPR(config.cronExpression());
		 scheduler.schedule(this,options);
        
}

	@Override
	public void run() {
		if (isEnabled) {

			LOG.info("\n --------- SfdcAPIScheduler Started----------");

			lttrStateCityService.getAvailableLTTRStateCity();
			kycDocumentTypeService.getKYCDocumentType();
			availableTestRideCityService.getAvailableTestRideCity();
			masterGeoConfigService.StateCityMasterJson();
			cityMasterConfigService.processCityMasterJson();
			productMasterConfigService.processProductMasterJson();
			pricesConfigService.processCityBasedPriceAndOffer();
			extendedVehicleConfigService.vehicleListMasterJson();
			
			lttrRideFreedoConfigService.processLTTRMaster();
			longTermTestRidePackageService.getPackageDataJson();

			LOG.info("\n --------- SfdcAPIScheduler Completed----------");
		}

	}

}



