package com.heromotocorp.vida.core.config.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import com.heromotocorp.vida.core.config.GlobalConfig;

/**
 * Osgi Config for Global Configuration.
 *
 */
@Component(service = GlobalConfig.class, immediate = true)
@Designate(ocd = GlobalConfigImpl.ServiceConfig.class)
public class GlobalConfigImpl implements GlobalConfig {

	/**
	 * The Interface ServiceConfig.
	 */
	@ObjectClassDefinition(name = "Global Config", description = "Global Config")
	public @interface ServiceConfig {

		/**
		 * Geo data url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Master Geo Data URL", description = "URL to get Master Geo Data", type = AttributeType.STRING)
		public String geoDataUrl();
		
		/**
		 * Serviceable cities url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Serviceable Cities URL ", description = "Serviceable Cities URL", type = AttributeType.STRING)
		public String serviceableCitiesUrl();
		
		/**
		 * Available Test ride Cities URL .
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Available Test ride Cities URL ", description = "Available Test ride Cities URL ", type = AttributeType.STRING)
		public String availableTestRideCities();
		
		/**
		 * LTTR States Cities URL .
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Long Term Test Ride States & Cities URL ", description = "Long Term Test Ride States & Cities URL ", type = AttributeType.STRING)
		public String lttrStateCities();
		
		/**
		 * Available Dealer Cities URL .
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Available Dealer Cities URL ", description = "Available Dealer Cities URL ", type = AttributeType.STRING)
		public String availableDealerCities();
		
		/**
		 * KYC Document URL .
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "KYC Document URL ", description = "KYC Document URL ", type = AttributeType.STRING)
		public String addressTypesUrl();
		
		/**
		 * Serviceable branches url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Serviceable Branches URL", description = "Serviceable Branches URL", type = AttributeType.STRING)
		public String serviceableBranchesUrl();
		
		/**
		 * Pincode city url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Pincode and City URL", description = "Pincode and City  URL", type = AttributeType.STRING)
		public String pincodeCityUrl();

		/**
		 * Short Term Serviceable cities url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Short Term Serviceable Cities URL ", description = "Short Term Serviceable Cities URL", type = AttributeType.STRING)
		public String shortTermServiceableCitiesUrl();

		/**
		 * Long Term Serviceable cities url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Long Term Serviceable Cities URL ", description = "Long Term Serviceable Cities URL", type = AttributeType.STRING)
		public String longTermServiceableCitiesUrl();
		
		/**
		 * Exchange Vehicle Master data url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Exchange Vehicle Master URL", description = "URL to get Exchange Vehicle Master Data", type = AttributeType.STRING)
		public String exchangeVehicleMasterUrl();
		
		/**
		 * Long Term Test Ride Vehicle Master url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Long Term Test Ride Vehicle Master URL ", description = "Long Term Test Ride Vehicle Master URL", type = AttributeType.STRING)
		public String lttrVehicleMasterUrl();
		
		/**
		 * Long Term Test Ride City url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Long  Term Test Ride City URL", description = "Long Term Test Ride City URL", type = AttributeType.STRING)
		public String lttrCityMasterUrl();
		
		/**
		 * Long Term Test Ride Relation Master url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Long Term Test Ride Relation Master URL", description = "Long Term Test Ride Relation Master URL", type = AttributeType.STRING)
		public String lttrRelationMasterUrl();
		
		/**
		 * Long Term Test Ride Package Master url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Long Term Test Ride Package Master URL", description = "Long Term Test Ride Package Master URL", type = AttributeType.STRING)
		public String lttrPackageMasterUrl();

		/**
		 * Gets the magento url.
		 *
		 * @return the magento url
		 */
		@AttributeDefinition(name = "Magento Url", type = AttributeType.STRING)
		String magentoUrl();

		/**
		 * Gets the magento bearer token.
		 *
		 * @return the magento bearer token
		 */
		@AttributeDefinition(name = "Magento Bearer Token", type = AttributeType.STRING)
		String magentoBearerToken();

		/**
		 * Gets the magento price API Method.
		 *
		 * @return the magento price API Method
		 */
		@AttributeDefinition(name = "Magento Price API Method", type = AttributeType.STRING)
		String magentoPriceAPIMethod();

		/**
		 * Gets the login page url.
		 *
		 * @return the login page url
		 */
		@AttributeDefinition(name = "Login Page Url", type = AttributeType.STRING)
		String loginPageUrl();

		/**
		 * Gets the profile page url.
		 *
		 * @return the profile page url
		 */
		@AttributeDefinition(name = "Profile Page Url", type = AttributeType.STRING)
		String profilePageUrl();

		/**
		 * Gets the test drive page url.
		 *
		 * @return the test drive page url
		 */
		@AttributeDefinition(name = "test-ride Page Url", type = AttributeType.STRING)
		String testDriveUrl();

		/**
		 * Gets the test drive selector page url.
		 *
		 * @return the test drive selector page url
		 */
		@AttributeDefinition(name = "Test Ride Selector Page Url", type = AttributeType.STRING)
		String testDriveSelectorUrl();

		/**
		 * Gets the test drive login page url.
		 *
		 * @return the test drive login page url
		 */
		@AttributeDefinition(name = "Test Ride Login Page Url", type = AttributeType.STRING)
		String testDriveLoginUrl();
		
		/**
		 * Gets the long term test drive page url.
		 *
		 * @return the long term test drive page url
		 */
		@AttributeDefinition(name = "Long Term Test Drive Page Url", type = AttributeType.STRING)
		String longTermTestDriveUrl();
		
		/**
		 * Gets the short term test drive page url.
		 *
		 * @return the short term test drive page url
		 */
		@AttributeDefinition(name = "Short Term Test Drive Page Url", type = AttributeType.STRING)
		String shortTermTestDriveUrl();

		/**
		 * Gets the Long term test drive document upload page url.
		 *
		 * @return the Long term test drive document upload page url
		 */
		@AttributeDefinition(name = "Long term test drive document upload page url", type = AttributeType.STRING)
		String lttrTestDriveUploadDocumentsUrl();
		
		/**
		 * Gets the Long term test drive summary page url.
		 *
		 * @return the Long term test drive summary page url
		 */
		@AttributeDefinition(name = "Long term test drive summary page url", type = AttributeType.STRING)
		String lttrTestDriveSummaryUrl();
		
		/**
		 * Gets the Long term test drive Acknowledgement page url.
		 *
		 * @return the Long term test drive Acknowledgement page url
		 */
		@AttributeDefinition(name = "Long term test drive Acknowledgement page url", type = AttributeType.STRING)
		String lttrAcknowledgeUrl();
		
		/**
		 * Gets the Long term test drive status page url.
		 *
		 * @return the Long term test drive status page url
		 */
		@AttributeDefinition(name = "Long term test drive status page url", type = AttributeType.STRING)
		String lttrTestDriveStatusUrl();
		
		/**
		 * Gets the puchase config Url.
		 *
		 * @return the puchase config Url
		 */
		@AttributeDefinition(name = "puchase config Url", type = AttributeType.STRING)
		String purchaseConfigUrl();

		/**
		 * Gets the home page url.
		 *
		 * @return the home page url
		 */
		@AttributeDefinition(name = "Home Page Url", type = AttributeType.STRING)
		String homePageUrl();

		/**
		 * Product image folder path.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Product Image Page Path", type = AttributeType.STRING)
		String productImageFolderPath();

		/**
		 * Google map url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Google Map Url", type = AttributeType.STRING)
		String googleMapUrl();

		/**
		 * Resource path.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Resource Path", type = AttributeType.STRING)
		String resourcePath();

		/**
		 * Pre booking url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Pre Booking Path", type = AttributeType.STRING)
		String preBookingUrl();

		/**
		 * Quick reserve url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Quick Reserve Path", type = AttributeType.STRING)
		String quickReserveUrl();

		/**
		 * Billing shipping url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "billing-shipping Path", type = AttributeType.STRING)
		String billingShippingUrl();

		/**
		 * Billing pricing url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "billing-pricing Path", type = AttributeType.STRING)
		String billingPricingUrl();

		/**
		 * Purchase Summary url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Purchase Summary Path", type = AttributeType.STRING)
		String purchaseSummaryUrl();

		/**
		 * booking status url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "booking-status Path", type = AttributeType.STRING)
		String bookingStatusUrl();

		/**
		 * nominee details url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "nominee-details Path", type = AttributeType.STRING)
		String nomineeDetailsUrl();

		/**
		 * Pre booking status url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Pre Booking Status  Path", type = AttributeType.STRING)
		String preBookingStatusUrl();

		/**
		 * Faq url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "faq Path", type = AttributeType.STRING)
		String faqUrl();

		/**
		 * Get In Touch url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Get In Touch Url", type = AttributeType.STRING)
		String getInTouchUrl();

		/**
		 * Analytics url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Analytics Path", type = AttributeType.STRING)
		String analyticsUrl();

		/**
		 * Store Detail url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Store Detail Url", type = AttributeType.STRING)
		String storeDetailsUrl();

		/**
		 * Product Detail url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Product details Url", type = AttributeType.STRING)
		String productListUrl();

		/**
		 * Exchange vehicle CSV location path.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Exchange Vehicle CSV Location Path", type = AttributeType.STRING)
		String exchangeVehicleCSVLocationPath();

		/**
		 * Product price url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Product price Url", type = AttributeType.STRING)
		String productPriceUrl();

		/**
		 * Product branch url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Product branch Url", type = AttributeType.STRING)
		String productBranchesUrl();

		/**
		 * Contact Us url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Contact Us Url", type = AttributeType.STRING)
		String contactUsUrl();

		/**
		 * Internal user complain url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Internal User Complain url", type = AttributeType.STRING)
		String internalUserUrl();

		/**
		 * Upload Document url.
		 *
		 * @return the uploadDocumentsUrl
		 */
		@AttributeDefinition(name = "Upload Document Url", type = AttributeType.STRING)
		String uploadDocumentsUrl();

		/**
		 * Aadhar Validation Url.
		 *
		 * @return the aadharValidationUrl
		 */
		@AttributeDefinition(name = "Adhar Validation Url", type = AttributeType.STRING)
		String aadharValidationUrl();

		/**
		 * Aadhar Validation Url.
		 *
		 * @return the aadharValidationUrl
		 */
		@AttributeDefinition(name = "Adhar Validation Url", type = AttributeType.STRING)
		String aadharValidationStatusUrl();

		/**
		 * delivery tracker url.
		 *
		 * @return the delivery tracker url
		 */
		@AttributeDefinition(name = "delivery tracker Url", type = AttributeType.STRING)
		String deliveryTrackerUrl();

		/**
		 * configuration url.
		 *
		 * @return the configuration url
		 */
		@AttributeDefinition(name = "Configuration Url", type = AttributeType.STRING)
		String configurationUrl();

		/**
		 * aadhar verification url.
		 *
		 * @return the configuration url
		 */
		@AttributeDefinition(name = "Aadhar Verification URL", type = AttributeType.STRING)
		String aadharVerificationUrl();

		/**
		 * Server name.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Server Name", description = "Server Name", type = AttributeType.STRING)
		public String serverName();
		
		/**
		 * SFDC Partner URL.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "SFDC Partner URL", description = "SFDC Partner URL", type = AttributeType.STRING)
		public String sfdcPartnerUrl();

		/**
		 * Post request token.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Token Post Request", description = "Token Post Request", type = AttributeType.STRING)
		public String postRequestToken();

		/**
		 * Grant type.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Grant Type", description = "Grant Type", type = AttributeType.STRING)
		public String grantType();

		/**
		 * Client secret.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Client Secret", description = "Client Secret", type = AttributeType.STRING)
		public String clientSecret();

		/**
		 * Username.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Username", description = "Username", type = AttributeType.STRING)
		public String username();

		/**
		 * Password.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Password", description = "password", type = AttributeType.STRING)
		public String password();

		/**
		 * Client id.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Client Id", description = "Client Id", type = AttributeType.STRING)
		public String clientId();

		/**
		 * Content type.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Content Type", description = "Content Type", type = AttributeType.STRING)
		public String contentType();

		/**
		 * Gets the location data url.
		 *
		 * @return the location data url
		 */
		@AttributeDefinition(name = "Master Geo Data Url", description = "Master Geo Data Url", type = AttributeType.STRING)
		public String locationDataUrl();

		/**
		 * Near by request url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Request URL for Near By Branches", description = "Request URL for Near By Branches", type = AttributeType.STRING)
		public String nearByRequestUrl();

		/**
		 * Dealersbranches url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Request URL for Dealers and Branches", description = "Request URL for Dealers and Branches", type = AttributeType.STRING)
		public String dealersbranchesUrl();/**
		 * Pincode city url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Request URL for pincode using city", description = "Request URL for pincode using city", type = AttributeType.STRING)
		public String pincodeCityReqUrl();

		/**
		 * Leads url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Request URL for Contact Us Leads", description = "Request URL for Contact Us Leads", type = AttributeType.STRING)
		public String leadsUrl();

		/**
		 * Leads url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Request URL for Contact Us Leads", description = "Request URL for Contact Us Leads", type = AttributeType.STRING)
		public String caseUrl();

		/**
		 * Token url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Request URL for Token Creation", description = "Request URL for Token Creation", type = AttributeType.STRING)
		public String tokencreateUrl();


		/**
		 * System list url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Request URL for System list", description = "Request URL for System list", type = AttributeType.STRING)
		public String systemUrl();

	
		/**
		 * Leads url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Request URL for Category", description = "Request URL for Category", type = AttributeType.STRING)
		public String categoryUrl();


		/**
		 * Leads url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Request URL for Subcategory", description = "Request URL for Subcategory", type = AttributeType.STRING)
		public String subcategoryUrl();

		/**
		 * Internal Complain url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Request URL For Posting Complain", description = "Request URL For Posting Complain", type = AttributeType.STRING)
		public String internalComplainUrl();

		/**
		 * City master CSV location path.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Request dam path for cityMaster csv", description = "Request dam path for cityMaster csv", type = AttributeType.STRING)
		public String cityMasterCSVLocationPath();

		/**
		 * Exchange vehicle CSV location path request.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Request dam path for exchange vehicle csv", description = "Request dam path for exchange vehicle csv", type = AttributeType.STRING)
		public String exchangeVehicleCSVLocationReqPath();

		/**
		 * Sfdc query endpoint.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Request SFDC Query Endpoint", description = "Request dam path for cityMaster csv", type = AttributeType.STRING)
		public String sfdcQueryEndpoint();

		/**
		 * Gets the item price.
		 *
		 * @return the item price
		 */
		@AttributeDefinition(name = "Item price Endpoint", description = "Request URL for Item price", type = AttributeType.STRING)
		public String getItemPrice();

		/**
		 * Minlease offers.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Min Lease Offers Endpoint", description = "Request URL for Min Lease Offers", type = AttributeType.STRING)
		public String minleaseOffers();

		/**
		 * Minloan offers.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Min Loan Offers Endpoint", description = "Request URL for Min Loan Offers", type = AttributeType.STRING)
		public String minloanOffers();

		/**
		 * autovert Min EMI EndPoint.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Autovert Min EMI EndPoint", description = "Autovert Min EMI EndPoint", type = AttributeType.STRING)
		public String autovertMinEmiEndPoint();

		/**
		 * autovert Offer Url EndPoint.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Autovert Offer URL EndPoint", description = "Autovert Offer URL EndPoint", type = AttributeType.STRING)
		public String autovertOfferUrlEndPoint();
		
		@AttributeDefinition(name = "Request dam path for stateCityMaster json", description = "Request json path for stateCityMaster", type = AttributeType.STRING)
		public String stateCityMasterJsonPath();
		
		@AttributeDefinition(name = "lttr Api Key", description = "lttr Api Key", type = AttributeType.STRING)
		public String lttrApiKey();
		
		@AttributeDefinition(name = "lttr Base EndPoint", description = "lttr Base EndPoint", type = AttributeType.STRING)
		public String lttrBaseEndPoint();
		
		@AttributeDefinition(name = "lttr All City EndPoint", description = "lttr All City EndPoint", type = AttributeType.STRING)
		public String lttrAllCityEndPoint();
		
		@AttributeDefinition(name = "lttr Relations EndPoint", description = "lttr Relations EndPoint", type = AttributeType.STRING)
		public String lttrRelationsEndPoint();
		
		@AttributeDefinition(name = "lttr Package Master Location", description = "lttr Package Master Location", type = AttributeType.STRING)
		public String lttrPackageMasterLocation();
		
		@AttributeDefinition(name = "lttr Relations Master Location", description = "lttr Relations Master Location", type = AttributeType.STRING)
		public String lttrRelationsMasterLocation();
		
		@AttributeDefinition(name = "lttr City Master Location", description = "lttr City Master Location", type = AttributeType.STRING)
		public String lttrCityMasterLocation();
		
		@AttributeDefinition(name = "Pickup Location Url", description = "Pickup Location Url", type = AttributeType.STRING)
		public String pickupLocationUrl();
		
		@AttributeDefinition(name = "Electrify EndPoint", description = "Electrify EndPoint", type = AttributeType.STRING)
		public String electrifyEndPoint();
		
		@AttributeDefinition(name = "Electrify Token", description = "Electrify Token", type = AttributeType.STRING)
		public String electrifyToken();
		
		@AttributeDefinition(name = "Freedo Booking Limit", description = "Freedo Booking Limit", type = AttributeType.INTEGER)
		public int freedoBookingLimit();
		
		@AttributeDefinition(name = "Is Encryption Support Required", description = "Is Encryption Support Required", type = AttributeType.BOOLEAN)
		public boolean encryptionSupportRequired();
		
		@AttributeDefinition(name = "Default country", description = "Default country", type = AttributeType.STRING)
		public String country();
		
		@AttributeDefinition(name = "Enable Short Term Test Ride?", description = "Enables Short Term Test Ride in profile page", type = AttributeType.BOOLEAN)
		public boolean isShortTermTestRideEnabled();
		
		@AttributeDefinition(name = "Enable Long Term Test Ride?", description = "Enables Long Term Test Ride in profile page", type = AttributeType.BOOLEAN)
		public boolean isLongTermTestRideEnabled();
		
		@AttributeDefinition(name = "Enable Scooter Config?", description = "Enables Scooter config in profile page", type = AttributeType.BOOLEAN)
		public boolean isScooterEnabled();

		@AttributeDefinition(name = "Recent tab ?", description = "Enables  Recent in profile page", type = AttributeType.BOOLEAN)
		public boolean isRecentEnabled();
		
		@AttributeDefinition(name = "Organization ID", description = "Organization ID for Map API Location", type = AttributeType.STRING)
		public String organizationId();
		
		@AttributeDefinition(name = "Default Radius", description = "Default Radius for Map API Location", type = AttributeType.STRING)
		public String defaultRadius();
		
		/**
		 * advantage Page URL.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Advantage Page URL", description = "Advantage Page URL", type = AttributeType.STRING)
		public String advantagePageUrl();
		
		/**
		 * EMI calculator Page URL.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "EMI Calculator Page URL", description = "EMI Calculator Page URL", type = AttributeType.STRING)
		public String emiCalculatorUrl();
		
		/**
		 * Partial Payment Page URL.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Partial Payment Page URL", description = "Partial Payment Page URL", type = AttributeType.STRING)
		public String partialPayUrl();
		
		/**
		 * Dealer Locator Page URL.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Dealer Locator Page URL", description = "Dealer Locator Page URL", type = AttributeType.STRING)
		public String dealerLocatorUrl();
		
		/**
		 * Google Tag.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Google Tag", description = "Google Tag", type = AttributeType.STRING)
		public String googleTag();
		
		/**
		 * Network Codes URL.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Network Codes URL", description = "Network Codes URL", type = AttributeType.STRING)
		public String networkCodesUrl();
		
		/**
		 * Battery URL.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Battery URL", description = "Battery URL", type = AttributeType.STRING)
		public String batteryUrl();
		
		/**
		 * Lead Creation URL.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Lead Creation URL", description = "Lead Creation URL", type = AttributeType.STRING)
		public String leadCreationUrl();
		
		/**
		 * Lead Campaign ID.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Lead Campaign ID", description = "Lead Campaign ID", type = AttributeType.STRING)
		public String leadCampaignId();

		/**
		 * Quick purchase url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Quick Purchase URL", type = AttributeType.STRING)
		String quickPurchaseUrl();
		
	}

	/** The geo data url. */
	private String geoDataUrl;
	
	/** The serviceable cities url. */
	private String serviceableCitiesUrl;
	
	/** The available test ride cities url. */
	private String availableTestRideCities;
	
	/** The LTTR State cities url. */
	private String lttrStateCities;
	
	/** The available dealer cities url. */
	String availableDealerCities;
	
	/** The KYC Document url. */
	private String addressTypesUrl;
	
	/** The serviceable branches url. */
	private String serviceableBranchesUrl;
	
	/** The pincode city url. */
	private String pincodeCityUrl;

	/** The exchange Vehicle Master url. */
	private String exchangeVehicleMasterUrl;
	
	/** The long term test ride Vehicle Master url. */
	private String lttrVehicleMasterUrl;
	
	/** The long term test ride City Master url. */
	private String lttrCityMasterUrl;
	
	/** The long term test ride Relation Master url. */
	private String lttrRelationMasterUrl;
	
	/** The long term test ride Package Master url. */
	private String lttrPackageMasterUrl;
	
	/** The magento url. */
	private String magentoUrl;
	
	/** The magento bearer token. */
	private String magentoBearerToken;
	
	/** The magento Price API Method. */
	private String magentoPriceAPIMethod;

	/** The login page url. */
	private String loginPageUrl;

	/** The profile page url. */
	private String profilePageUrl;

	/** The test drive url. */
	private String testDriveUrl;

	/** The test drive selector page url. */
	String testDriveSelectorUrl;

	/** The test drive login page url. */
	String testDriveLoginUrl;
	
	/** The long term test drive url. */
	private String longTermTestDriveUrl;
	
	/** The short term test drive url. */
	private String shortTermTestDriveUrl;
	
	/** The Long term test drive document upload page url. */
	private String lttrTestDriveUploadDocumentsUrl;
	
	/** The Long term test drive summary page url. */
	private String lttrTestDriveSummaryUrl;
	
	/** The Long term test drive Acknowledge page url. */
	private String lttrAcknowledgeUrl;
	
	/** The Long term test drive status page url. */
	private String lttrTestDriveStatusUrl;

	/** The purchase config url. */
	private String purchaseConfigUrl;

	/** The home page url. */
	private String homePageUrl;

	/** The product image folder path. */
	private String productImageFolderPath;

	/** The home page url. */
	private String googleMapUrl;

	/** The resource path. */
	private String resourcePath;

	/** The pre booking url. */
	private String preBookingUrl;

	/** The quick reserveg url. */
	String quickReserveUrl;

	/** The quick purchase url. */
	String quickPurchaseUrl;

	/** The billing shipping url. */
	private String billingShippingUrl;

	/** The billing pricing url. */
	private String billingPricingUrl;

	/** The purchase summary url. */
	private String purchaseSummaryUrl;

	/** The booking pricing url. */
	private String bookingStatusUrl;

	/** The nominee details url. */
	private String nomineeDetailsUrl;

	/** The pre booking status url. */
	private String preBookingStatusUrl;

	/** The faq url. */
	private String faqUrl;

	/** The get in touch url. */
	private String getInTouchUrl;

	/** The analytics url. */
	private String analyticsUrl;

	/** The Store Detail url. */
	private String storeDetailsUrl;

	/** The product Detail url. */
	private String productListUrl;

	/** The product price url. */
	private String productPriceUrl;

	/** The product branch url. */
	private String productBranchesUrl;

	/** The Store Detail url. */
	private String contactUsUrl;

	/** The Internal user url. */
	private String internalUserUrl;

	/** The upload documents url. */
	private String uploadDocumentsUrl;

	/** The aadhar validation url. */
	private String aadharValidationUrl;

	/** The aadhar validation status url. */
	private String aadharValidationStatusUrl;

	/** The delivery tracker url. */
	private String deliveryTrackerUrl;

	/** The exchange vehicle CSV location path. */
	private String exchangeVehicleCSVLocationPath;

	/** The configuration url. */
	private String configurationUrl;

	/** The Aadhar Verification url. */
	private String aadharVerificationUrl;
	
	/** The server name. */
	private String serverName;
	
	/** The SFDC Partner URL */
	private String sfdcPartnerUrl;

	/** The post request token. */
	private String postRequestToken;

	/** The grant type. */
	private String grantType;

	/** The client id. */
	private String clientId;

	/** The client secret. */
	private String clientSecret;

	/** The username. */
	private String username;

	/** The password. */
	private String password;

	/** The content type. */
	private String contentType;

	/** The get location data url. */
	private String locationDataUrl;

	/** The near by request url. */
	private String nearByRequestUrl;

	/** The dealersbranches url. */
	private String dealersbranchesUrl;

	/** The contact us leads url. */
	private String leadsUrl;

	/** The contact us case url. */
	private String caseUrl;

	/** The tokencreation url. */
	private String tokencreateUrl;
	
	/** The Systemlist url. */
	private String systemUrl;

	/** The category url. */
	private String categoryUrl;

	/** The subcategory url. */
	private String subcategoryUrl;

	/** The contact us internalComplain url. */
	private String internalComplainUrl;

	/** The City Master Path. */
	private String cityMasterCSVLocationPath;

	/** The City Master Path. */
	private String sfdcQueryEndpoint;

	/** The Get Items Price. */
	private String getItemPrice;

	/** The Autovert Offer URL EndPoint. */
	private String autovertOfferUrlEndPoint;

	/** The Autovert Min EMI EndPoint. */
	private String autovertMinEmiEndPoint;

	/** The pincode city url request. */
	private String pincodeCityReqUrl;

	/** The exchange vehicle CSV location path request. */
	private String exchangeVehicleCSVLocationReqPath;
	
	/** The short term serviceable cities url. */
	private String shortTermServiceableCitiesUrl;

	/** The long term serviceable cities url. */
	private String longTermServiceableCitiesUrl;
	
	private String lttrApiKey;
	
	private String lttrBaseEndPoint;
	
	private String lttrAllCityEndPoint;
	
	private String lttrRelationsEndPoint;
	
	private String lttrPackageMasterLocation;
	
	private String lttrRelationsMasterLocation;
	
	private String lttrCityMasterLocation;

	private String pickupLocationUrl;
	
	private String electrifyEndPoint;

	private String electrifyToken;

	private int freedoBookingLimit;

	private boolean encryptionSupportRequired;

	private boolean isShortTermTestRideEnabled;
	
	private boolean isRecentEnabled;

	private boolean isLongTermTestRideEnabled;

	private boolean isScooterEnabled;
	
	private String country;

	/** The organization Id. */
	private String organizationId;

	/** The default Radius. */
	private String defaultRadius;
	
	/** The Advantage Page URL */
	String advantagePageUrl;
	
	/** The EMI Calculator Page URL */
	String emiCalculatorUrl;
	
	/** The Partial Payment Page URL */
	String partialPayUrl;
	
	/** The Dealer Locator Page URL */
	String dealerLocatorUrl;
	
	/** The Google Tag */
	String googleTag;

	/** The networkCodesUrl. */
	String networkCodesUrl;

	/** The batteryUrl. */
	String batteryUrl;

	/** The leadCreationUrl. */
	String leadCreationUrl;

	/** The leadCampaignId. */
	String leadCampaignId;
		
	/**
	 * Activate Method.
	 *
	 * @param serviceConfig the service config
	 */
	@Activate
	protected void activate(ServiceConfig serviceConfig) {

		geoDataUrl = serviceConfig.geoDataUrl();
		serviceableCitiesUrl = serviceConfig.serviceableCitiesUrl();
		availableTestRideCities = serviceConfig.availableTestRideCities();
		lttrStateCities = serviceConfig.lttrStateCities();
		availableDealerCities = serviceConfig.availableDealerCities();
		addressTypesUrl = serviceConfig.addressTypesUrl();
		serviceableBranchesUrl = serviceConfig.serviceableBranchesUrl();
		pincodeCityUrl = serviceConfig.pincodeCityUrl();
		shortTermServiceableCitiesUrl = serviceConfig.shortTermServiceableCitiesUrl();
		longTermServiceableCitiesUrl = serviceConfig.longTermServiceableCitiesUrl();
		exchangeVehicleMasterUrl = serviceConfig.exchangeVehicleMasterUrl();
		lttrVehicleMasterUrl = serviceConfig.lttrVehicleMasterUrl();
		lttrCityMasterUrl = serviceConfig.lttrCityMasterUrl();
		lttrRelationMasterUrl = serviceConfig.lttrRelationMasterUrl();
		lttrPackageMasterUrl = serviceConfig.lttrPackageMasterUrl();
		magentoUrl = serviceConfig.magentoUrl();
		magentoBearerToken = serviceConfig.magentoBearerToken();
		magentoPriceAPIMethod = serviceConfig.magentoPriceAPIMethod();
		loginPageUrl = serviceConfig.loginPageUrl();
		profilePageUrl = serviceConfig.profilePageUrl();
		testDriveUrl = serviceConfig.testDriveUrl();
		testDriveSelectorUrl = serviceConfig.testDriveSelectorUrl();
		testDriveLoginUrl = serviceConfig.testDriveLoginUrl();
		longTermTestDriveUrl = serviceConfig.longTermTestDriveUrl();
		shortTermTestDriveUrl = serviceConfig.shortTermTestDriveUrl();
		lttrTestDriveUploadDocumentsUrl = serviceConfig.lttrTestDriveUploadDocumentsUrl();
		lttrTestDriveStatusUrl = serviceConfig.lttrTestDriveStatusUrl();
		lttrTestDriveSummaryUrl = serviceConfig.lttrTestDriveSummaryUrl();
		lttrAcknowledgeUrl = serviceConfig.lttrAcknowledgeUrl();
		purchaseConfigUrl = serviceConfig.purchaseConfigUrl();
		homePageUrl = serviceConfig.homePageUrl();
		productImageFolderPath = serviceConfig.productImageFolderPath();
		googleMapUrl = serviceConfig.googleMapUrl();
		resourcePath = serviceConfig.resourcePath();
		preBookingUrl = serviceConfig.preBookingUrl();
		quickReserveUrl = serviceConfig.quickReserveUrl();
		billingShippingUrl = serviceConfig.billingShippingUrl();
		billingPricingUrl = serviceConfig.billingPricingUrl();
		purchaseSummaryUrl = serviceConfig.purchaseSummaryUrl();
		bookingStatusUrl = serviceConfig.bookingStatusUrl();
		nomineeDetailsUrl = serviceConfig.nomineeDetailsUrl();
		preBookingStatusUrl = serviceConfig.preBookingStatusUrl();
		faqUrl = serviceConfig.faqUrl();
		getInTouchUrl = serviceConfig.getInTouchUrl();
		analyticsUrl = serviceConfig.analyticsUrl();
		storeDetailsUrl = serviceConfig.storeDetailsUrl();
		productListUrl = serviceConfig.productListUrl();
		productPriceUrl = serviceConfig.productPriceUrl();
		productBranchesUrl = serviceConfig.productBranchesUrl();
		contactUsUrl = serviceConfig.contactUsUrl();
		internalUserUrl = serviceConfig.internalUserUrl();
		uploadDocumentsUrl = serviceConfig.uploadDocumentsUrl();
		aadharValidationUrl = serviceConfig.aadharValidationUrl();
		aadharValidationStatusUrl = serviceConfig.aadharValidationStatusUrl();
		deliveryTrackerUrl = serviceConfig.deliveryTrackerUrl();
		configurationUrl = serviceConfig.configurationUrl();
		aadharVerificationUrl = serviceConfig.aadharVerificationUrl();
		exchangeVehicleCSVLocationPath = serviceConfig.exchangeVehicleCSVLocationPath();
		serverName = serviceConfig.serverName();
		sfdcPartnerUrl = serviceConfig.sfdcPartnerUrl();
		postRequestToken = serviceConfig.postRequestToken();
		grantType = serviceConfig.grantType();
		clientId = serviceConfig.clientId();
		clientSecret = serviceConfig.clientSecret();
		username = serviceConfig.username();
		contentType = serviceConfig.contentType();
		password = serviceConfig.password();
		locationDataUrl = serviceConfig.locationDataUrl();
		nearByRequestUrl = serviceConfig.nearByRequestUrl();
		dealersbranchesUrl = serviceConfig.dealersbranchesUrl();
		leadsUrl = serviceConfig.leadsUrl();
		caseUrl = serviceConfig.caseUrl();
		tokencreateUrl = serviceConfig.tokencreateUrl();
		systemUrl = serviceConfig.systemUrl();
		categoryUrl = serviceConfig.categoryUrl();
		subcategoryUrl = serviceConfig.subcategoryUrl();
		internalComplainUrl = serviceConfig.internalComplainUrl();
		sfdcQueryEndpoint = serviceConfig.sfdcQueryEndpoint();
		cityMasterCSVLocationPath = serviceConfig.cityMasterCSVLocationPath();
		getItemPrice = serviceConfig.getItemPrice();
		autovertMinEmiEndPoint = serviceConfig.autovertMinEmiEndPoint();
		autovertOfferUrlEndPoint = serviceConfig.autovertOfferUrlEndPoint();
		pincodeCityReqUrl = serviceConfig.pincodeCityReqUrl();
		exchangeVehicleCSVLocationReqPath = serviceConfig.exchangeVehicleCSVLocationReqPath();
		lttrApiKey = serviceConfig.lttrApiKey();
		lttrBaseEndPoint = serviceConfig.lttrBaseEndPoint();
		lttrRelationsEndPoint = serviceConfig.lttrRelationsEndPoint();
		lttrAllCityEndPoint = serviceConfig.lttrAllCityEndPoint();
		lttrPackageMasterLocation = serviceConfig.lttrPackageMasterLocation();
		lttrRelationsMasterLocation = serviceConfig.lttrRelationsMasterLocation();
		lttrCityMasterLocation = serviceConfig.lttrCityMasterLocation();
		pickupLocationUrl = serviceConfig.pickupLocationUrl();
		
		electrifyEndPoint = serviceConfig.electrifyEndPoint();
		electrifyToken = serviceConfig.electrifyToken();
		freedoBookingLimit = serviceConfig.freedoBookingLimit();
		encryptionSupportRequired =serviceConfig.encryptionSupportRequired();
		isShortTermTestRideEnabled =serviceConfig.isShortTermTestRideEnabled();
		isLongTermTestRideEnabled =serviceConfig.isLongTermTestRideEnabled();
		isScooterEnabled =serviceConfig.isScooterEnabled();
		isRecentEnabled = serviceConfig.isRecentEnabled();
		country = serviceConfig.country();
		organizationId = serviceConfig.organizationId();
		defaultRadius = serviceConfig.defaultRadius();
		advantagePageUrl = serviceConfig.advantagePageUrl();
		emiCalculatorUrl = serviceConfig.emiCalculatorUrl();
		partialPayUrl = serviceConfig.partialPayUrl();
		dealerLocatorUrl = serviceConfig.dealerLocatorUrl();
		googleTag = serviceConfig.googleTag();
		networkCodesUrl = serviceConfig.networkCodesUrl();
		leadCreationUrl = serviceConfig.leadCreationUrl();
		leadCampaignId = serviceConfig.leadCampaignId();
		batteryUrl = serviceConfig.batteryUrl();
		quickPurchaseUrl = serviceConfig.quickPurchaseUrl();
	}

	/**
	 * Geo Data Url.
	 *
	 * @return the string
	 */
	@Override
	public String geoDataUrl() {
		return geoDataUrl;
	}

	/**
	 * Serviceable Cities Url.
	 *
	 * @return the string
	 */
	@Override
	public String serviceableCitiesUrl() {
		return serviceableCitiesUrl;
	}

	/**
	 * Available test ride Cities Url.
	 *
	 * @return the string
	 */
	@Override
	public String availableTestRideCities() {
		return availableTestRideCities;
	}

	/**
	 * LTTR States Cities Url.
	 *
	 * @return the string
	 */
	@Override
	public String lttrStateCities() {
		return lttrStateCities;
	}

	/**
	 * Available Dealer Cities Url.
	 *
	 * @return the string
	 */
	@Override
	public String availableDealerCities() {
		return availableDealerCities;
	}
	
	/**
	 * KYC Document Url.
	 *
	 * @return the string
	 */
	@Override
	public String addressTypesUrl() {
		return addressTypesUrl;
	}

	/**
	 * Serviceable Branches Url.
	 *
	 * @return the string
	 */
	@Override
	public String serviceableBranchesUrl() {
		return serviceableBranchesUrl;
	}

	@Override
	public String shortTermServiceableCitiesUrl() {
		return shortTermServiceableCitiesUrl;
	}
	
	@Override
	public String longTermServiceableCitiesUrl() {
		return longTermServiceableCitiesUrl;
	}
	/**
	 * Pincode city url.
	 *
	 * @return the string
	 */
	@Override
	public String pincodeCityUrl() {
		return pincodeCityUrl;
	}

	/**
	 * Exchange Vehicle Master url.
	 *
	 * @return the string
	 */
	@Override
	public String exchangeVehicleMasterUrl() {
		return exchangeVehicleMasterUrl;
	}

	/**
	 * Long Term Test Ride Vehicle Master url.
	 *
	 * @return the string
	 */
	@Override
	public String lttrVehicleMasterUrl() {
		return lttrVehicleMasterUrl;
	}

	/**
	 * Long Term Test Ride City Master url.
	 *
	 * @return the string
	 */
	@Override
	public String lttrCityMasterUrl() {
		return lttrCityMasterUrl;
	}

	/**
	 * Long Term Test Ride Relation Master url.
	 *
	 * @return the string
	 */
	@Override
	public String lttrRelationMasterUrl() {
		return lttrRelationMasterUrl;
	}

	/**
	 * Long Term Test Ride Package Master url.
	 *
	 * @return the string
	 */
	@Override
	public String lttrPackageMasterUrl() {
		return lttrPackageMasterUrl;
	}
	
	/**
	 * Gets the magento url.
	 *
	 * @return the magento url
	 */
	@Override
	public String magentoUrl() {
		return magentoUrl;
	}
	
	/**
	 * Gets the magento bearer token.
	 *
	 * @return the magento bearer token
	 */
	@Override
	public String magentoBearerToken() {
		return magentoBearerToken;
	}
	
	/**
	 * Gets the magento price API method.
	 *
	 * @return the magento price API method
	 */
	@Override
	public String magentoPriceAPIMethod() {
		return magentoPriceAPIMethod;
	}

	/**
	 * Gets the login page url.
	 *
	 * @return the login page url
	 */
	@Override
	public String loginPageUrl() {
		return loginPageUrl;
	}

	/**
	 * Gets the profile page url.
	 *
	 * @return the profile page url
	 */
	@Override
	public String profilePageUrl() {
		return profilePageUrl;
	}

	/**
	 * Gets the test drive page url.
	 *
	 * @return the test drive page url
	 */
	@Override
	public String testDriveUrl() {
		return testDriveUrl;
	}
	
	/**
	 * Gets the long term test drive page url.
	 *
	 * @return the long term test drive page url
	 */
	@Override
	public String getLongTermTestDriveUrl() {
		return longTermTestDriveUrl;
	}
	
	/**
	 * Gets the short term test drive page url.
	 *
	 * @return the short term test drive page url
	 */
	@Override
	public String getShortTermTestDriveUrl() {
		return shortTermTestDriveUrl;
	}
	
	/**
	 * Gets the Long term test drive upload document page url.
	 *
	 * @return the short term test drive upload document page url
	 */
	@Override
	public String getlttrTestDriveUploadDocumentsUrl() {
		return lttrTestDriveUploadDocumentsUrl;
	}
	
	/**
	 * Gets the Long term test drive summary page url.
	 *
	 * @return the short term test drive summary page url
	 */
	@Override
	public String getlttrTestDriveSummaryUrl() {
		return lttrTestDriveSummaryUrl;
	}
	
	/**
	 * Gets the Long term test drive status page url.
	 *
	 * @return the short term test drive status page url
	 */
	@Override
	public String getlttrTestDriveStatusUrl() {
		return lttrTestDriveStatusUrl;
	}
	
	/**
	 * Gets the purchase Config Url.
	 *
	 * @return the purchase Config Url.
	 */
	@Override
	public String purchaseConfigUrl() {
		return purchaseConfigUrl;
	}

	/**
	 * Gets the home page url.
	 *
	 * @return the home page url
	 */
	@Override
	public String homePageUrl() {
		return homePageUrl;
	}

	/**
	 * Gets the product image folder path.
	 *
	 * @return the product image page path
	 */
	public String productImageFolderPath() {
		return productImageFolderPath;
	}

	/**
	 * Gets the google map url.
	 *
	 * @return the google map url
	 */
	@Override
	public String googleMapUrl() {
		return googleMapUrl;
	}

	/**
	 * Gets the resource path.
	 *
	 * @return the resource path
	 */
	@Override
	public String resourcePath() {
		return resourcePath;
	}

	/**
	 * Pre booking url.
	 *
	 * @return the string
	 */
	@Override
	public String preBookingUrl() {
		return preBookingUrl;
	}

	/**
	 * Quick reserve url.
	 *
	 * @return the string
	 */
	@Override
	public String quickReserveUrl() {
		return quickReserveUrl;
	}

	/**
	 * billing shipping url.
	 *
	 * @return the string
	 */
	@Override
	public String billingShippingUrl() {
		return billingShippingUrl;
	}

	/**
	 * billing pricing url.
	 *
	 * @return the string
	 */
	@Override
	public String billingPricingUrl() {
		return billingPricingUrl;
	}

	/**
	 * purchase summary url.
	 *
	 * @return the string
	 */
	@Override
	public String purchaseSummaryUrl() {
		return purchaseSummaryUrl;
	}

	/**
	 * booking status url.
	 *
	 * @return the string
	 */
	@Override
	public String bookingStatusUrl() {
		return bookingStatusUrl;
	}

	/**
	 * nominee details url.
	 *
	 * @return the string
	 */
	@Override
	public String nomineeDetailsUrl() {
		return nomineeDetailsUrl;
	}

	/**
	 * Pre booking status url.
	 *
	 * @return the string
	 */
	@Override
	public String preBookingStatusUrl() {
		return preBookingStatusUrl;
	}

	/**
	 * Faq url.
	 *
	 * @return the string
	 */
	@Override
	public String faqUrl() {
		return faqUrl;
	}

	/**
	 * Get In Touch Url.
	 *
	 * @return the string
	 */
	@Override
	public String getInTouchUrl() {
		return getInTouchUrl;
	}

	/**
	 * Analytics url.
	 *
	 * @return the string
	 */
	@Override
	public String analyticsUrl() {
		return analyticsUrl;
	}

	/**
	 * Store Details url.
	 *
	 * @return the string
	 */
	@Override
	public String storeDetailsUrl() {
		return storeDetailsUrl;
	}

	/**
	 * Store Details url.
	 *
	 * @return the string
	 */
	@Override
	public String productListUrl() {
		return productListUrl;
	}

	/**
	 * Store Details url.
	 *
	 * @return the string
	 */
	@Override
	public String productPriceUrl() {
		return productPriceUrl;
	}

	/**
	 * product branch url.
	 *
	 * @return the string
	 */
	@Override
	public String productBranchesUrl() {
		return productBranchesUrl;
	}

	/**
	 * Contact Us url.
	 *
	 * @return the string
	 */
	@Override
	public String contactUsUrl() {
		return contactUsUrl;
	}

	/**
	 * Internal User Complain url.
	 *
	 * @return the string
	 */
	@Override
	public String internalUserUrl() {
		return internalUserUrl;
	}
	/**
	 * Upload Documents Url.
	 *
	 * @return the uploadDocumentsUrl
	 */
	@Override
	public String uploadDocumentsUrl() {
		return uploadDocumentsUrl;
	}

	/**
	 * Aadhar Validation Url.
	 *
	 * @return the aadharValidationUrl
	 */
	@Override
	public String aadharValidationUrl() {
		return aadharValidationUrl;
	}

	/**
	 * Aadhar Validation Status Url.
	 *
	 * @return the aadharValidationStatusUrl
	 */
	@Override
	public String aadharValidationStatusUrl() {
		return aadharValidationStatusUrl;
	}

	/**
	 * Aadhar delivery Tracker Url.
	 *
	 * @return the delivery Tracker Url
	 */
	@Override
	public String deliveryTrackerUrl() {
		return deliveryTrackerUrl;
	}

	/**
	 * Exchange vehicle CSV location path.
	 *
	 * @return the string
	 */
	@Override
	public String exchangeVehicleCSVLocationPath() {
		return exchangeVehicleCSVLocationPath;
	}

	/**
	 * configuration Url.
	 *
	 * @return the configuration Url
	 */
	@Override
	public String configurationUrl() {
		return configurationUrl;
	}

	/**
	 * Aadhar Verification Url.
	 *
	 * @return the Aadhar Verification Url
	 */
	@Override
	public String aadharVerificationUrl() {
		return aadharVerificationUrl;
	}

	/**
	 * Password.
	 *
	 * @return the string
	 */
	@Override
	public String password() {
		return password;
	}

	/**
	 * Post Request URL.
	 *
	 * @return the string
	 */
	@Override
	public String postRequestToken() {
		return new StringBuffer(serverName).append(postRequestToken).toString();
	}

	/**
	 * Grant Type.
	 *
	 * @return the string
	 */
	@Override
	public String grantType() {
		return grantType;
	}

	/**
	 * Client Id.
	 *
	 * @return the string
	 */
	@Override
	public String clientId() {
		return clientId;
	}

	/**
	 * Client Secret.
	 *
	 * @return the string
	 */
	@Override
	public String clientSecret() {
		return clientSecret;
	}

	/**
	 * Content Type.
	 *
	 * @return the string
	 */
	@Override
	public String contentType() {
		return contentType;
	}

	/**
	 * Username.
	 *
	 * @return the string
	 */
	@Override
	public String username() {
		return username;
	}

	/**
	 * Near By Request URL.
	 *
	 * @return the string
	 */
	@Override
	public String nearByRequestUrl() {
		return new StringBuffer(serverName).append(nearByRequestUrl).toString();
	}

	/**
	 * Dealers and Branches URL.
	 *
	 * @return the string
	 */
	@Override
	public String dealersbranchesUrl() {
		return new StringBuffer(serverName).append(dealersbranchesUrl).toString();
	}

	/**
	 * Pincode city url request.
	 *
	 * @return the string
	 */
	@Override
	public String pincodeCityReqUrl() {
		return new StringBuffer(serverName).append(pincodeCityReqUrl).toString();
	}

	/**
	 * Location Data url.
	 *
	 * @return the string
	 */
	@Override
	public String locationDataUrl() {
		return new StringBuffer(serverName).append(locationDataUrl).toString();
	}

	/**
	 * Leads url.
	 *
	 * @return the string
	 */
	@Override
	public String leadsUrl() {
		return new StringBuffer(serverName).append(leadsUrl).toString();
	}

	/**
	 * City master CSV location path.
	 *
	 * @return the string
	 */
	public String cityMasterCSVLocationPath() {
		return cityMasterCSVLocationPath;
	}

	/**
	 * Sfdc query endpoint.
	 *
	 * @return the string
	 */
	@Override
	public String sfdcQueryEndpoint() {
		return new StringBuffer(serverName).append(sfdcQueryEndpoint).toString();
	}

	/**
	 * Case url.
	 *
	 * @return the string
	 */
	@Override
	public String caseUrl() {
		return new StringBuffer(serverName).append(caseUrl).toString();
	}

	/**
	 * Token url.
	 *
	 * @return the string
	 */
	@Override
	public String tokencreateUrl() {
		return new StringBuffer(serverName).append(tokencreateUrl).toString();
	}

	/**
	 * Systemlist url.
	 *
	 * @return the string
	 */
	@Override
	public String systemUrl() {
		return new StringBuffer(serverName).append(systemUrl).toString();
	}
	
	/**
	 * category url.
	 *
	 * @return the string
	 */
	@Override
	public String categoryUrl() {
		return new StringBuffer(serverName).append(categoryUrl).toString();
	}

	/**
	 * subcategory url.
	 *
	 * @return the string
	 */
	@Override
	public String subcategoryUrl() {
		return new StringBuffer(serverName).append(subcategoryUrl).toString();
	}

	/**
	 * internalComplain url.
	 *
	 * @return the string
	 */
	@Override
	public String internalComplainUrl() {
		return new StringBuffer(serverName).append(internalComplainUrl).toString();
	}

	/**
	 * Gets Items Price url.
	 *
	 * @return the getItemPrice
	 */
	@Override
	public String getItemPrice() {
		return new StringBuffer(serverName).append(getItemPrice).toString();
	}

	/**
	 * autovert Min EMI EndPoint.
	 *
	 * @return the autovertMinEmiEndPoint
	 */
	@Override
	public String autovertMinEmiEndPoint() {
		return autovertMinEmiEndPoint;
	}

	/**
	 * autovert Offer url EndPoint.
	 *
	 * @return the autovertMinEmiEndPoint
	 */
	@Override
	public String autovertOfferUrlEndPoint() {
		return autovertOfferUrlEndPoint;
	}

	/**
	 * Server name.
	 *
	 * @return the string
	 */
	@Override
	public String serverName() {
		return serverName;
	}

	/**
	 * Exchange vehicle CSV location path request.
	 *
	 * @return the string
	 */
	@Override
	public String exchangeVehicleCSVLocationReqPath() {
		return exchangeVehicleCSVLocationReqPath;
	}

	@Override
	public String lttrApiKey() {
		return lttrApiKey;
	}

	@Override
	public String lttrBaseEndPoint() {
		return lttrBaseEndPoint;
	}

	@Override
	public String lttrAllCityEndPoint() {
		return lttrAllCityEndPoint;
	}

	@Override
	public String lttrRelationsEndPoint() {
		return lttrRelationsEndPoint;
	}

	@Override
	public String lttrPackageMasterLocation() {
		return lttrPackageMasterLocation;
	}

	@Override
	public String lttrRelationsMasterLocation() {
		return lttrRelationsMasterLocation;
	}

	@Override
	public String lttrCityMasterLocation() {
		return lttrCityMasterLocation;
	}

	@Override
	public String pickupLocationUrl() {
		return pickupLocationUrl;
	}

	@Override
	public String electrifyEndPoint() {
		return electrifyEndPoint;
	}

	@Override
	public String electrifyToken() {
		return electrifyToken;
	}

	@Override
	public int freedoBookingLimit() {
		return freedoBookingLimit;
	}

	@Override
	public boolean encryptionSupportRequired() {
		return encryptionSupportRequired;
	}

	@Override
	public String country() {
		return country;
	}

	@Override
	public boolean isShortTermTestRideEnabled() {
		return isShortTermTestRideEnabled;
	}

	@Override
	public boolean isRecentEnabled() {
		return isRecentEnabled;
	}
	@Override
	public boolean isLongTermTestRideEnabled() {
		return isLongTermTestRideEnabled;
	}

	@Override
	public boolean isScooterEnabled() {
		return isScooterEnabled;
	}

	@Override
	public String sfdcPartnerUrl() {
		return sfdcPartnerUrl;
	}

	@Override
	public String organizationId() {
		return organizationId;
	}

	@Override
	public String defaultRadius() {
		return defaultRadius;
	}	
	
	@Override
	public String advantagePageUrl() {
		return advantagePageUrl;
	}

	@Override
	public String emiCalculatorUrl() {
		return emiCalculatorUrl;
	} 
	
	@Override
	public String partialPayUrl() {
		return partialPayUrl;
	}

	@Override
	public String getTestDriveSelectorUrl() {
		return testDriveSelectorUrl;
	} 

	@Override
	public String getTestDriveLoginUrl() {
		return testDriveLoginUrl;
	}

	@Override
	public String lttrAcknowledgeUrl() {
		return lttrAcknowledgeUrl;
	}

	@Override
	public String dealerLocatorUrl() {
		return dealerLocatorUrl;
	}

	@Override
	public String googleTag() {
		return googleTag;
	}

	@Override
	public String networkCodesUrl() {
		return networkCodesUrl;
	}

	@Override
	public String leadCreationUrl() {
		return leadCreationUrl;
	}

	@Override
	public String leadCampaignId() {
		return leadCampaignId;
	}

	@Override
	public String batteryUrl() {
		return batteryUrl;
	}

	/**
	 * Quick purchase url.
	 *
	 * @return the string
	 */
	@Override
	public String quickPurchaseUrl() {
		return quickPurchaseUrl;
	}
}