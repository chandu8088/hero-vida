package com.heromotocorp.vida.core.models;

import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.wcm.api.Page;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.service.InstanceTypeService;
import com.heromotocorp.vida.core.utils.CommonUtils;

/**
 * The Class PageModel.
 */
@Model(adaptables = { Resource.class, SlingHttpServletRequest.class })
public class PageModel {

	/** The magento url. */
	String magentoUrl;

	/** The login page url. */
	String loginPageUrl;

	/** The profile page url. */
	String profilePageUrl;

	/** The home page url. */
	String homePageUrl;

	/** The test drive page url. */
	String testDriveUrl;

	/** The test drive selector page url. */
	String testDriveSelectorUrl;

	/** The test drive login page url. */
	String testDriveLoginUrl;
	
	/** The Long Term Test Drive URL */
	String longTermTestDriveUrl;
	
	/** The Short Term Test Drive URL */
	String shortTermTestDriveUrl;
	
	/** The Long Term Test Drive document upload URL */
	String lttrTestDriveUploadDocumentsUrl;
	
	/** The Long Term Test Drive summary URL */
	String lttrTestDriveSummaryUrl;
	
	/** The Long Term Test Drive Acknowledge URL */
	String lttrAcknowledgeUrl;
	
	/** The Long Term Test Drive status URL */
	String lttrTestDriveStatusUrl;
	
	/** The Advantage Page URL */
	String advantagePageUrl;
	
	/** The EMI Calculator Page URL */
	String emiCalculatorUrl;
	
	/** The Partial Payment Page URL */
	String partialPaymentUrl;
	
	/** The Dealer Locator Page URL */
	String dealerLocatorUrl;

	/** The purcahse config url. */
	String purchaseConfigUrl;

	/** The product image folder path. */
	String productImageFolderPath;

	/** The Geo Data Url. */
	String geoDataUrl;

	/** The Serviceable Cities Url. */
	String serviceableCitiesUrl;
	
	/** The available test ride cities url. */
	String availableTestRideCities;
	
	/** The available LTTR State cities url. */
	String availableLTTRStateCities;
	
	/** The available dealer cities url. */
	String availableDealerCities;
	
	/** The KYC Document url. */
	String addressTypesUrl;

	/** The Serviceable Branches Url. */
	String serviceableBranchesUrl;

	/** The pincode city url. */
	String pincodeCityUrl;


	/** The Short Term Serviceable Cities Url. */
	String shortTermServiceableCitiesUrl;

	/** The Long Term Serviceable Cities Url. */
	String longTermServiceableCitiesUrl;

	/** The exchange Vehicle Master url. */
	String exchangeVehicleMasterUrl;
	
	/** The long term test ride Vehicle Master url. */
	String lttrVehicleMasterUrl;
	
	/** The long term test ride City Master url. */
	String lttrCityMasterUrl;
	
	/** The long term test ride Relation Master url. */
	String lttrRelationMasterUrl;
	
	/** The long term test ride Package Master url. */
	String lttrPackageMasterUrl;


	/** The SFDC url. */
	String serverName;
	
	/** The SFDC Partner url. */
	String sfdcPartnerUrl;
	
	/** The google map url. */
	String googleMapUrl;

	/** The resource path. */
	String resourcePath;

	/** The pre booking url. */
	String preBookingUrl;

	/** The quick reserveg url. */
	String quickReserveUrl;

	/** The quick purchase url. */
	String quickPurchaseUrl;

	/** The billing shipping url. */
	String billingShippingUrl;

	/** The billing shipping url. */
	String billingPricingUrl;

	/** The purchase summary url. */
	private String purchaseSummaryUrl;

	/** The booking status url. */
	String bookingStatusUrl;

	/** The nominee details url. */
	String nomineeDetailsUrl;

	/** The pre booking status url. */
	String preBookingStatusUrl;

	/** The faq url. */
	String faqUrl;

	/** The Get In Touch url. */
	String getInTouchUrl;

	/** The analytics url. */
	String storeDetailsUrl;

	/** The product list url. */
	String productListUrl;

	/** The product list url. */
	String productPriceUrl;

	/** The product branch url. */
	String productBranchesUrl;

	/** The analytics url. */
	String analyticsUrl;

	/** The contactUs url. */
	String contactUsUrl;

	/** The internalurl url. */
	String internalUserUrl;

	/** The uploadDocumentsUrl url. */
	String uploadDocumentsUrl;

	/** The aadhar validation url. */
	String aadharValidationUrl;

	/** The aadhar validation status url. */
	String aadharValidationStatusUrl;

	/** The delivery Tracker url. */
	String deliveryTrackerUrl;

	/** The configuration url. */
	String configurationUrl;

	/** The Aadhar Verification url. */
	String aadharVerificationUrl;
	
	String pickupLocationUrl;
	
	/** The environment. */
	String environment;
	
	/** The instance. */
	String instance;
	
	/** The freedoBookingLimit. */
	int freedoBookingLimit;
	
	/** The encryptionSupportRequired. */
	boolean encryptionSupportRequired;
	
	/** The isShortTermTestRideEnabled. */
	boolean isShortTermTestRideEnabled;
	
	/** The isLongTermTestRideEnabled. */
	boolean isLongTermTestRideEnabled;
	
	/** The isScooterEnabled. */
	boolean isScooterEnabled;

	/** The isRecentEnabled. */
	boolean isRecentEnabled;

	/** The global config. */
	@OSGiService
	private GlobalConfig globalConfig;

	/** The sling http servlet request. */
	@Self
	private SlingHttpServletRequest slingHttpServletRequest;

	/** The resolver. */
	@ScriptVariable
	private ResourceResolver resolver;

	/** The resource. */
	@SlingObject
	private Resource resource;

	/** The current page. */
	@Inject
	private Page currentPage;
	
	/** The instance type service. */
	@OSGiService
	InstanceTypeService instanceTypeService;

	/** The page notification. */
	Boolean pageNotification = false;

	/** The is login page. */
	Boolean isLoginPage = false;

	/** The is login protected. */
	Boolean isLoginProtected;

	/** The is login protected. */
	String loginouttitle;

	/** The sessionexpirytitle. */
	String sessionexpirytitle;

	/** The primarybtnlabel. */
	String primarybtnlabel;

	/** The primarybtnlink. */
	String primarybtnlink;

	/** The secondarybtnlabel. */
	String secondarybtnlabel;

	/** The secondarybtnlink. */
	String secondarybtnlink;

	/** The networkCodesUrl. */
	String networkCodesUrl;

	/** The batteryUrl. */
	String batteryUrl;

	/** The leadCreationUrl. */
	String leadCreationUrl;
	
	String seoSchema;
	
	String country;

	/** The googleTag. */
	String googleTag;

	/**
	 * Inits the.
	 */
	@PostConstruct
	protected void init() {
		magentoUrl = globalConfig.magentoUrl();
		loginPageUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.loginPageUrl()));
		profilePageUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.profilePageUrl()));
		testDriveUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.testDriveUrl()));
		testDriveSelectorUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.getTestDriveSelectorUrl()));
		testDriveLoginUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.getTestDriveLoginUrl()));
		longTermTestDriveUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.getLongTermTestDriveUrl()));
		shortTermTestDriveUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.getShortTermTestDriveUrl()));
		lttrTestDriveUploadDocumentsUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.getlttrTestDriveUploadDocumentsUrl()));
		lttrTestDriveSummaryUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.getlttrTestDriveSummaryUrl()));
		lttrAcknowledgeUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.lttrAcknowledgeUrl()));
		lttrTestDriveStatusUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.getlttrTestDriveStatusUrl()));
		purchaseConfigUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.purchaseConfigUrl()));
		homePageUrl = CommonUtils.updateUrl(resource, globalConfig.homePageUrl());
		productImageFolderPath = globalConfig.productImageFolderPath();
		googleMapUrl = globalConfig.googleMapUrl();
		resourcePath = globalConfig.resourcePath();
		preBookingUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.preBookingUrl()));
		quickReserveUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.quickReserveUrl()));
		billingShippingUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.billingShippingUrl()));
		billingPricingUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.billingPricingUrl()));
		purchaseSummaryUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.purchaseSummaryUrl()));
		bookingStatusUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.bookingStatusUrl()));
		nomineeDetailsUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.nomineeDetailsUrl()));
		preBookingStatusUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.preBookingStatusUrl()));
		faqUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.faqUrl()));
		getInTouchUrl = CommonUtils.updateUrl(resource, globalConfig.getInTouchUrl());
		analyticsUrl = globalConfig.analyticsUrl();
		storeDetailsUrl = globalConfig.storeDetailsUrl();
		productListUrl = globalConfig.productListUrl();
		productPriceUrl = globalConfig.productPriceUrl();
		productBranchesUrl = globalConfig.productBranchesUrl();
		contactUsUrl = CommonUtils.updateUrl(resource, globalConfig.contactUsUrl());
		internalUserUrl= globalConfig.internalUserUrl();
		uploadDocumentsUrl = resolver.map(globalConfig.uploadDocumentsUrl());
		aadharValidationUrl = resolver.map(globalConfig.aadharValidationUrl());
		aadharValidationStatusUrl = resolver.map(globalConfig.aadharValidationStatusUrl());
		deliveryTrackerUrl = resolver.map(globalConfig.deliveryTrackerUrl());
		configurationUrl = resolver.map(globalConfig.configurationUrl());
		aadharVerificationUrl = resolver.map(globalConfig.aadharVerificationUrl());
		freedoBookingLimit = globalConfig.freedoBookingLimit();
		encryptionSupportRequired = globalConfig.encryptionSupportRequired();
		serverName = globalConfig.serverName();
		sfdcPartnerUrl = globalConfig.sfdcPartnerUrl();
		advantagePageUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.advantagePageUrl()));
		emiCalculatorUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.emiCalculatorUrl()));
		partialPaymentUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.partialPayUrl()));
		dealerLocatorUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.dealerLocatorUrl()));
		
		if (Objects.nonNull(currentPage.getProperties().get("pageNotification"))) {
			pageNotification = currentPage.getProperties().get("pageNotification", Boolean.class);
		}
		if (Objects.nonNull(currentPage.getProperties().get("isLoginPage"))) {
			isLoginPage = currentPage.getProperties().get("isLoginPage", Boolean.class);
		}
		if (Objects.nonNull(currentPage.getProperties().get("isLoginProtected"))) {
			isLoginProtected = currentPage.getProperties().get("isLoginProtected", Boolean.class);
		}
		
		if (Objects.nonNull(currentPage.getProperties().get("seoSchema"))) {
			seoSchema = currentPage.getProperties().get("seoSchema", String.class);
			}
		
		if (Objects.nonNull(currentPage.getProperties().get("loginouttitle"))) {
			loginouttitle = currentPage.getProperties().get("loginouttitle", String.class);
		}

		if (Objects.nonNull(currentPage.getProperties().get("sessionexpirytitle"))) {
			sessionexpirytitle = currentPage.getProperties().get("sessionexpirytitle", String.class);
		}

		if (Objects.nonNull(currentPage.getProperties().get("primarybtnlabel"))) {
			primarybtnlabel = currentPage.getProperties().get("primarybtnlabel", String.class);
		}

		if (Objects.nonNull(currentPage.getProperties().get("primarybtnlink"))) {
			primarybtnlink = resolver.map(CommonUtils.updateUrl(resource, currentPage.getProperties().get("primarybtnlink", String.class)));
		}

		if (Objects.nonNull(currentPage.getProperties().get("secondarybtnlabel"))) {
			secondarybtnlabel = currentPage.getProperties().get("secondarybtnlabel", String.class);
		}

		if (Objects.nonNull(currentPage.getProperties().get("secondarybtnlink"))) {
			secondarybtnlink = resolver.map(CommonUtils.updateUrl(resource, currentPage.getProperties().get("secondarybtnlink", String.class)));
		}
		country = globalConfig.country();
		isShortTermTestRideEnabled = globalConfig.isShortTermTestRideEnabled();
		isLongTermTestRideEnabled = globalConfig.isLongTermTestRideEnabled();
		isScooterEnabled = globalConfig.isScooterEnabled();
		isRecentEnabled = globalConfig.isRecentEnabled();
		googleTag = globalConfig.googleTag();
		networkCodesUrl = globalConfig.networkCodesUrl();
		leadCreationUrl = globalConfig.leadCreationUrl();
		batteryUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.batteryUrl()));
		quickPurchaseUrl = resolver.map(CommonUtils.updateUrl(resource, globalConfig.quickPurchaseUrl()));
	}

	/**
	 * Gets the global service.
	 *
	 * @return the global service
	 */
	public GlobalConfig getGlobalService() {
		return globalConfig;
	}

	/**
	 * Gets the magento url.
	 *
	 * @return the magento url
	 */
	public String getMagentoUrl() {
		return magentoUrl;
	}

	/**
	 * Gets the magento graphql url.
	 *
	 * @return the magento graphql url
	 */
	public String getMagentoGrapghqlUrl() {
		return magentoUrl+"/graphql";
	}

	/**
	 * Gets the login page url.
	 *
	 * @return the login page url
	 */
	public String getLoginPageUrl() {
		return loginPageUrl;
	}

	/**
	 * Gets the profile page url.
	 *
	 * @return the profile page url
	 */
	public String getProfilePageUrl() {
		return profilePageUrl;
	}

	/**
	 * Gets the test dirve page url.
	 *
	 * @return the test drive page url
	 */
	public String getTestDriveUrl() {
		return testDriveUrl;
	}
	
	public String getTestDriveSelectorUrl() {
		return testDriveSelectorUrl;
	}
	
	public String getTestDriveLoginUrl() {
		return testDriveLoginUrl;
	}

	/**
	 * Gets the long term test dirve page url.
	 *
	 * @return the long term test drive page url
	 */
	public String getLongTermTestDriveUrl() {
		return longTermTestDriveUrl;
	}
    
	/**
	 * Gets the short term test dirve page url.
	 *
	 * @return the short term test drive page url
	 */
	public String getShortTermTestDriveUrl() {
		return shortTermTestDriveUrl;
	}

	

	/**
	 * Gets the purchase config url.
	 *
	 * @return the purchase config url
	 */
	public String getPurchaseConfigUrl() {
		return purchaseConfigUrl;
	}

	/**
	 * Gets the home page url.
	 *
	 * @return the home page url
	 */
	public String getHomePageUrl() {
		return homePageUrl;
	}

	/**
	 * Gets the Google Map url.
	 * 
	 * @return googleMapUrl.
	 */
	public String getGoogleMapUrl() {
		return googleMapUrl;
	}

	/**
	 * Gets the Resource Path.
	 * 
	 * @return resourcePath.
	 */
	public String getResourcePath() {
		return resourcePath;
	}

	/**
	 * Gets the product image folder path.
	 *
	 * @return the product image page path
	 */
	public String getProductImageFolderPath() {
		return productImageFolderPath;
	}

	/**
	 * Gets the Geo Data url.
	 * 
	 * @return geoDataUrl.
	 */
	public String getGeoDataUrl() {
		geoDataUrl = globalConfig.geoDataUrl();
		return geoDataUrl;
	}

	/**
	 * Gets the Serviceable Cities url.
	 * 
	 * @return serviceableCitiesUrl.
	 */
	public String getServiceableCitiesUrl() {
		serviceableCitiesUrl = globalConfig.serviceableCitiesUrl();
		return serviceableCitiesUrl;
	}

	/**
	 * Gets the Available test ride Cities Url.
	 *
	 * @return the Available test ride Cities
	 */
	public String availableTestRideCities() {
		availableTestRideCities = globalConfig.availableTestRideCities();
		return availableTestRideCities;
	}

	/**
	 * Gets the Available LTTR State Cities Url.
	 *
	 * @return the Available LTTR State Cities
	 */
	public String getLttrStateCities() {
		availableLTTRStateCities = globalConfig.lttrStateCities();
		return availableLTTRStateCities;
	}

	/**
	 * Gets the Available Dealer Cities Url.
	 *
	 * @return the Available Dealer Cities
	 */
	public String getAvailableDealerCities() {
		availableDealerCities = globalConfig.availableDealerCities();
		return availableDealerCities;
	}

	
	/**
	 * Gets the KYC Document url.
	 *
	 * @return the KYC Document Type url
	 */
	public String addressTypesUrl() {
		addressTypesUrl = globalConfig.addressTypesUrl();
		return addressTypesUrl;
	}
	
	/**
	 * Gets the Serviceable Branch url.
	 * 
	 * @return serviceableCitiesUrl.
	 */
	public String getServiceableBranchesUrl() {
		serviceableBranchesUrl = CommonUtils.updateUrl(resource, globalConfig.serviceableBranchesUrl());
		return serviceableBranchesUrl;
	}

	/**
	 * Gets the pincode city url.
	 *
	 * @return the pincode city url
	 */
	public String getPincodeCityUrl() {
		pincodeCityUrl = CommonUtils.updateUrl(resource, globalConfig.pincodeCityUrl());
		return pincodeCityUrl;
	}
	
	/**
	 * Exchange Vehicle Master url.
	 *
	 * @return the string
	 */
	public String exchangeVehicleMasterUrl() {
		exchangeVehicleMasterUrl = globalConfig.exchangeVehicleMasterUrl();
		return exchangeVehicleMasterUrl;
	}

	/**
	 * Long Term Test Ride Vehicle Master url.
	 *
	 * @return the string
	 */
	public String lttrVehicleMasterUrl() {
		lttrVehicleMasterUrl = CommonUtils.updateUrl(resource, globalConfig.lttrVehicleMasterUrl());
		return lttrVehicleMasterUrl;
	}

	/**
	 * Long Term Test Ride City Master url.
	 *
	 * @return the string
	 */
	public String lttrCityMasterUrl() {
		lttrCityMasterUrl = globalConfig.lttrCityMasterUrl();
		return lttrCityMasterUrl;
	}

	/**
	 * Long Term Test Ride Relation Master url.
	 *
	 * @return the string
	 */
	public String lttrRelationMasterUrl() {
		lttrRelationMasterUrl = globalConfig.lttrRelationMasterUrl();
		return lttrRelationMasterUrl;
	}

	/**
	 * Long Term Test Ride Package Master url.
	 *
	 * @return the string
	 */
	public String lttrPackageMasterUrl() {
		lttrPackageMasterUrl = globalConfig.lttrPackageMasterUrl();
		return lttrPackageMasterUrl;
	}
	
	/**
	 * SFDC url
	 *
	 * @return the string
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * SFDC Partner url
	 *
	 * @return the string
	 */
	public String getSfdcPartnerUrl() {
		return sfdcPartnerUrl;
	}

	/**
	 * Gets the Short Term Serviceable Cities url.
	 * 
	 * @return shortTermserviceableCitiesUrl.
	 */
	public String getShortTermServiceableCitiesUrl() {
		shortTermServiceableCitiesUrl = globalConfig.shortTermServiceableCitiesUrl();
		return shortTermServiceableCitiesUrl;
	}

	/**
	 * Gets the Long Term Serviceable Cities url.
	 * 
	 * @return longTermserviceableCitiesUrl.
	 */
	public String getLongTermServiceableCitiesUrl() {
		longTermServiceableCitiesUrl =globalConfig.longTermServiceableCitiesUrl();
		return longTermServiceableCitiesUrl;
	}

	/**
	 * Gets the pre booking url.
	 *
	 * @return the pre booking url
	 */
	public String getPreBookingUrl() {
		return preBookingUrl;
	}

	/**
	 * Gets the quick Reserve url.
	 *
	 * @return the quick Reserve url
	 */
	public String getQuickReserveUrl() {
		return quickReserveUrl;
	}

	/**
	 * Gets the pbilling shipping url.
	 *
	 * @return the billing shipping url
	 */
	public String getBillingShippingUrl() {
		return billingShippingUrl;
	}

	/**
	 * Gets the pbilling pricing url.
	 *
	 * @return the billing pricing url
	 */
	public String getBillingPricingUrl() {
		return billingPricingUrl;
	}

	/**
	 * Gets the purchase summary url.
	 *
	 * @return the purchase summary url
	 */
	public String purchaseSummaryUrl() {
		return purchaseSummaryUrl;
	}

	/**
	 * Gets the pbilling pricing url.
	 *
	 * @return the billing pricing url
	 */
	public String getBookingStatusUrl() {
		return bookingStatusUrl;
	}

	/**
	 * Gets the nominee details url.
	 *
	 * @return the nominee details url
	 */
	public String getNomineeDetailsUrl() {
		return nomineeDetailsUrl;
	}

	/**
	 * Gets the pre booking status url.
	 *
	 * @return the pre booking status url
	 */
	public String getPreBookingStatusUrl() {
		return preBookingStatusUrl;
	}

	/**
	 * Gets the faq url.
	 *
	 * @return the faq url
	 */
	public String getFaqUrl() {
		return faqUrl;
	}

	/**
	 * Gets the Get In Touch url.
	 *
	 * @return the getInTouchUrl
	 */
	public String getGetInTouchUrl() {
		return getInTouchUrl;
	}

	/**
	 * Gets the analytics url.
	 *
	 * @return the analytics url
	 */
	public String getAnalyticsUrl() {
		return analyticsUrl;
	}

	/**
	 * Gets the Store Detail url.
	 *
	 * @return the analytics url
	 */
	public String getStoreDetailsUrl() {
		return storeDetailsUrl;
	}

	/**
	 * Gets the product lsit url.
	 *
	 * @return the product list url
	 */
	public String getProductListUrl() {
		return productListUrl;
	}

	/**
	 * Gets the product price url.
	 *
	 * @return the product price url
	 */
	public String getProductPriceUrl() {
		return productPriceUrl;
	}

	/**
	 * Gets the Product branch url.
	 *
	 * @return the product branch url
	 */
	public String getProductBranchesUrl() {
		return productBranchesUrl;
	}

	/**
	 * Gets the contact us url.
	 *
	 * @return the contact us url
	 */
	public String getContactUsUrl() {
		return contactUsUrl;
	}

	/**
	 * Gets the internalUser us url.
	 *
	 * @return the contact us url
	 */
	public String getInternalUserUrl() {
		return internalUserUrl;
	}

	/**
	 * Gets the Upload Document url.
	 *
	 * @return the uploadDocumentsUrl
	 */
	public String getUploadDocumentsUrl() {
		return uploadDocumentsUrl;
	}

	/**
	 * Gets the Aadhar Validation Url.
	 *
	 * @return the aadharValidationUrl
	 */
	public String getAadharValidationUrl() {
		return aadharValidationUrl;
	}

	/**
	 * Gets the aadhar validation status url.
	 *
	 * @return the aadhar validation status url
	 */
	public String getAadharValidationStatusUrl() {
		return aadharValidationStatusUrl;
	}

	/**
	 * Gets the delivery tracker Url.
	 *
	 * @return the deliveryTrackerUrl
	 */
	public String getDeliveryTrackerUrl() {
		return deliveryTrackerUrl;
	}

	/**
	 * Gets the configuration Url.
	 *
	 * @return the configurationUrl
	 */
	public String getConfigurationUrl() {
		return configurationUrl;
	}

	/**
	 * Aadhar Verification Url.
	 *
	 * @return the Aadhar Verification Url
	 */
	public String getAadharVerificationUrl() {
		return aadharVerificationUrl;
	}

	/**
	 * Gets the Page Notification.
	 *
	 * @return the pageNotification
	 */
	public Boolean getPageNotification() {
		return pageNotification;
	}

	/**
	 * Gets the is Login Page.
	 *
	 * @return the isLoginPage
	 */
	public Boolean getIsLoginPage() {
		return isLoginPage;
	}

	/**
	 * Gets the is Login Protected.
	 *
	 * @return the isLoginProtected
	 */
	public Boolean getIsLoginProtected() {
		return isLoginProtected;
	}
	
	public String getSeoSchema() {
		return seoSchema;
	}

	/**
	 * Gets the environment.
	 *
	 * @return the environment
	 */
	public String getEnvironment() {
		return instanceTypeService.getEnvironment();
	}
	
	

	public String getLttrTestDriveUploadDocumentsUrl() {
		return lttrTestDriveUploadDocumentsUrl;
	}

	public String getLttrTestDriveSummaryUrl() {
		return lttrTestDriveSummaryUrl;
	}

	public String lttrAcknowledgeUrl() {
		return lttrAcknowledgeUrl;
	}

	public String getLttrTestDriveStatusUrl() {
		return lttrTestDriveStatusUrl;
	}

	/**
	 * Gets the single instance of PageModel.
	 *
	 * @return single instance of PageModel
	 */
	public String getInstance() {
		return instanceTypeService.getInstance();
	}
	
	public String getDefaultCountryCode() {
		return "+91";
	}

	public String getPickupLocationUrl() {
		pickupLocationUrl = CommonUtils.updateUrl(resource, globalConfig.pickupLocationUrl());
		return pickupLocationUrl;
	}

	public int getFreedoBookingLimit() {
		return freedoBookingLimit;
	}

	public boolean getEncryptionSupportRequired() {
		return encryptionSupportRequired;
	}

	public boolean getIsShortTermTestRideEnabled() {
		return isShortTermTestRideEnabled;
	}

	public boolean getIsLongTermTestRideEnabled() {
		return isLongTermTestRideEnabled;
	}

	public boolean getIsScooterEnabled() {
		return isScooterEnabled;
	}

	public boolean getIsRecentEnabled() {
		return isRecentEnabled;
	}

	public String getCountry() {
		return country;
	}
	
	public String getAdvantagePageUrl() {
		return advantagePageUrl;
	}
	
	public String getEmiCalculatorUrl() {
		return emiCalculatorUrl;
	}
	
	public String getPartialPaymentUrl() {
		return partialPaymentUrl;
	}
	
	public String getDealerLocatorUrl() {
		return dealerLocatorUrl;
	}
	
	public String getGoogleTag() {
		return googleTag;
	}
	
	public String getLoginouttitle() {
		return loginouttitle;
	}
	
	public String getSessionexpirytitle() {
		return sessionexpirytitle;
	}
	
	public String getPrimarybtnlabel() {
		return primarybtnlabel;
	}
	
	public String getPrimarybtnlink() {
		return primarybtnlink;
	}
	
	public String getSecondarybtnlabel() {
		return secondarybtnlabel;
	}
	
	public String getSecondarybtnlink() {
		return secondarybtnlink;
	}
	
	public String getLeadCreationUrl() {
		return leadCreationUrl;
	}
	
	public String getNetworkCodesUrl() {
		return networkCodesUrl;
	}
	
	public String getBatteryUrl() {
		return batteryUrl;
	}

	/**
	 * Gets the quick purchase url.
	 *
	 * @return the quick Purchase url
	 */
	public String getQuickPurchaseUrl() {
		return quickPurchaseUrl;
	}	
}
