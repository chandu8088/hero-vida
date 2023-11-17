package com.heromotocorp.vida.core.config;

/**
 * Interface for Global Configuration.
 *
 */
public interface GlobalConfig {

	/**
	 * Geo data url.
	 *
	 * @return the string
	 */
	String geoDataUrl();
	
	/**
	 * Serviceable cities url.
	 *
	 * @return the string
	 */
	String serviceableCitiesUrl();
	
	/**
	 * Serviceable branches url.
	 *
	 * @return the string
	 */
	String serviceableBranchesUrl();
	
	/**
	 * available Test Ride Cities.
	 *
	 * @return the string
	 */
	String availableTestRideCities();
	
	/**
	 * LTTR State Cities.
	 *
	 * @return the string
	 */
	String lttrStateCities();
	
	/**
	 * available Dealer Cities.
	 *
	 * @return the string
	 */
	String availableDealerCities();
	
	/**
	 * KYC Document URL
	 *
	 * @return the string
	 */
	String addressTypesUrl();
	
	/**
	 * Pincode city url.
	 *
	 * @return the string
	 */
	String pincodeCityUrl();
	
	/**
	 * Short term serviceable cities url.
	 *
	 * @return the string
	 */
	String shortTermServiceableCitiesUrl();
	
	/**
	 * Long term serviceable cities url.
	 *
	 * @return the string
	 */
	String longTermServiceableCitiesUrl();

	/**
	 * Exchange vehicle master url.
	 *
	 * @return the string
	 */
	String exchangeVehicleMasterUrl();
	
	/**
	 * Lttr vehicle master url.
	 *
	 * @return the string
	 */
	String lttrVehicleMasterUrl();
	
	/**
	 * Lttr city master url.
	 *
	 * @return the string
	 */
	String lttrCityMasterUrl();
	
	/**
	 * Lttr relation master url.
	 *
	 * @return the string
	 */
	String lttrRelationMasterUrl();
	
	/**
	 * Lttr package master url.
	 *
	 * @return the string
	 */
	String lttrPackageMasterUrl();
	
	/**
	 * Server name.
	 *
	 * @return the string
	 */
	String serverName();
	
	/**
	 * SFDC Partner url.
	 *
	 * @return the string
	 */
	String sfdcPartnerUrl();
	
	/**
	 * Post request token.
	 *
	 * @return the string
	 */
	String postRequestToken();
	
	/**
	 * Grant type.
	 *
	 * @return the string
	 */
	String grantType();
	
	/**
	 * Client id.
	 *
	 * @return the string
	 */
	String clientId();
	
	/**
	 * Client secret.
	 *
	 * @return the string
	 */
	String clientSecret();
	
	/**
	 * Username.
	 *
	 * @return the string
	 */
	String username();
	
	/**
	 * Password.
	 *
	 * @return the string
	 */
	String password();
	
	/**
	 * Content type.
	 *
	 * @return the string
	 */
	String contentType();
	
	/**
	 * Location data url.
	 *
	 * @return the string
	 */
	String locationDataUrl();
	
	/**
	 * Near by request url.
	 *
	 * @return the string
	 */
	String nearByRequestUrl();
	
	/**
	 * Dealersbranches url.
	 *
	 * @return the string
	 */
	String dealersbranchesUrl();
	
	/**
	 * Leads url.
	 *
	 * @return the string
	 */
	String leadsUrl();
	
	/**
	 * Case url.
	 *
	 * @return the string
	 */
	String caseUrl();

	/**
	 * Token url.
	 *
	 * @return the string
	 */
	String tokencreateUrl();

	/**
	 * System url.
	 *
	 * @return the string
	 */
	String systemUrl();
	
	/**
	 * Category url.
	 *
	 * @return the string
	 */
	String categoryUrl();

	/**
	 * Subcategory url.
	 *
	 * @return the string
	 */
	String subcategoryUrl();

	/**
	 * Internalcomplain url.
	 *
	 * @return the string
	 */
	String internalComplainUrl();
	/**
	 * City master CSV location path.
	 *
	 * @return the string
	 */
	String cityMasterCSVLocationPath();
	
	/**
	 * Sfdc query endpoint.
	 *
	 * @return the string
	 */
	String sfdcQueryEndpoint();
	
	/**
	 * Gets the item price.
	 *
	 * @return the item price
	 */
	String getItemPrice();
	
	/**
	 * autovert Min EMI EndPoint .
	 *
	 * @return the string
	 */
	String autovertMinEmiEndPoint();
	
	/**
	 * autovert Offer URL EndPoint.
	 *
	 * @return the string
	 */
	String autovertOfferUrlEndPoint();
	
	/**
	 * Pincode city req url.
	 *
	 * @return the string
	 */
	String pincodeCityReqUrl();
	
	/**
	 * Exchange vehicle CSV location req path.
	 *
	 * @return the string
	 */
	String exchangeVehicleCSVLocationReqPath();
	
	/**
	 * Gets the magento url.
	 *
	 * @return the magento url
	 */
	public String magentoUrl();
	
	/**
	 * Gets the magento bearer token.
	 *
	 * @return the magento bearer token
	 */
	public String magentoBearerToken();
	
	/**
	 * Gets the magento price API Method.
	 *
	 * @return the magento price API Method
	 */
	public String magentoPriceAPIMethod();
	
	/**
	 * Gets the login page url.
	 *
	 * @return the login page url
	 */
	public String loginPageUrl();
	
	/**
	 * Gets the profile page url.
	 *
	 * @return the profile page url
	 */
	public String profilePageUrl();

	/**
	 * Gets the test drive page url.
	 *
	 * @return the test drive page url
	 */
	public String testDriveUrl();

	/**
	 * Gets the test drive selector page url.
	 *
	 * @return the test drive selector page url
	 */
	public String getTestDriveSelectorUrl();

	/**
	 * Gets the test drive login page url.
	 *
	 * @return the test drive login page url
	 */
	public String getTestDriveLoginUrl();
	
	/**
	 * Gets the long term test drive page url.
	 *
	 * @return the long term test drive page url
	 */
	public String getLongTermTestDriveUrl();
	
	/**
	 * Gets the short term test drive page url.
	 *
	 * @return the short term test drive page url
	 */
	public String getShortTermTestDriveUrl();
	
	
	/**
	 * Gets the purchase config url.
	 *
	 * @return the purchase config url
	 */
	public String purchaseConfigUrl();
	/**
	 * Gets the home page url.
	 *
	 * @return the home page url
	 */
	public String homePageUrl();

	/**
	 * Gets the product image folder path.
	 *
	 * @return the product image page path
	 */
	public String productImageFolderPath();

	/**
	 * Gets the google map url.
	 *
	 * @return the google map url
	 */
	public String googleMapUrl();

	/**
	 * Gets the resource path.
	 *
	 * @return the resource path
	 */
	public String resourcePath();
	
	/**
	 * Pre booking url.
	 *
	 * @return the string
	 */
	public String preBookingUrl();
	
	/**
	 * Quick reserve url.
	 *
	 * @return the string
	 */
	public String quickReserveUrl();
	
	/**
	 * Billing shipping url.
	 *
	 * @return the string
	 */
	public String billingShippingUrl();

	/**
	 * Billing pricing url.
	 *
	 * @return the string
	 */
	public String billingPricingUrl();

	/**
	 * purchase summary url.
	 *
	 * @return the string
	 */
	String purchaseSummaryUrl();

	/**
	 * booking status url.
	 *
	 * @return the string
	 */
	public String bookingStatusUrl();
	 /**
	 * nominee details url.
	 *
	 * @return the string
	 */
	public String nomineeDetailsUrl();
	/**
	 * Pre booking status url.
	 *
	 * @return the string
	 */
	public String preBookingStatusUrl();
	
	/**
	 * Faq url.
	 *
	 * @return the string
	 */
	public String faqUrl();
	
	/**
	 * Get In Touch Url.
	 *
	 * @return the string
	 */
	public String getInTouchUrl();
	
	/**
	 * Analytics url.
	 *
	 * @return the string
	 */
	public String analyticsUrl();

	/**
	 * Store Detail url.
	 *
	 * @return the string
	 */
	public String storeDetailsUrl();
	
	/**
	 * Store product details url.
	 *
	 * @return the string
	 */
	public String productListUrl();

	/**
	 * Store product price url.
	 *
	 * @return the string
	 */
	public String productPriceUrl();

	/**
	 * Store product branch url.
	 *
	 * @return the string
	 */
	public String productBranchesUrl();

	/**
	 * Contact Us url.
	 *
	 * @return the string
	 */
	public String contactUsUrl();

	/**
	 * Internalcomplain url.
	 *
	 * @return the string
	 */
	public String internalUserUrl();
	
	/**
	 * Upload Document url.
	 *
	 * @return the uploadDocumentsUrl
	 */
	public String uploadDocumentsUrl();

	/**
	 * Upload Aadhar Validation Url.
	 *
	 * @return the aadharValidationUrl
	 */
	public String aadharValidationUrl();

	/**
	 * Upload Aadhar Validation Status Url.
	 *
	 * @return the aadharValidationStatusUrl
	 */
	public String aadharValidationStatusUrl();

	/**
	 * delivery tracker Url.
	 *
	 * @return the deliveryTrackerUrl
	 */
	public String deliveryTrackerUrl();
	
	/**
	 * Exchange vehicle CSV location path.
	 *
	 * @return the string
	 */
	public String exchangeVehicleCSVLocationPath();

	/**
	 * configuration Url.
	 *
	 * @return the configurationUrl
	 */
	public String configurationUrl();

	/**
	 * Aadhar Verification Url.
	 *
	 * @return the aadharVerificationUrl
	 */
	public String aadharVerificationUrl();
	
	/**
	 * Gets the Long term test drive upload document page url.
	 *
	 * @return the short term test drive upload document page url
	 */
	public String getlttrTestDriveUploadDocumentsUrl();
	
	/**
	 * Gets the Long term test drive summary page url.
	 *
	 * @return the short term test drive summary page url
	 */
	public String getlttrTestDriveSummaryUrl();
	
	/**
	 * Gets the Long term test drive acknowledge page url.
	 *
	 * @return the short term test drive acknowledge page url
	 */
	public String lttrAcknowledgeUrl();

	/**
	 * Gets the Long term test drive status page url.
	 *
	 * @return the short term test drive status page url
	 */
	String getlttrTestDriveStatusUrl();
	
	String lttrApiKey();
	
	String lttrBaseEndPoint();

	String pickupLocationUrl();	

	/**
	 * Gets the electrify End Point url.
	 *
	 * @return the electrify End Point url
	 */
	String electrifyEndPoint();

	/**
	 * Gets the electrify Token.
	 *
	 * @return the electrify Token
	 */
	String electrifyToken();

	/**
	 * Gets the Long term test drive status page url.
	 *
	 * @return the short term test drive status page url
	 */
	int freedoBookingLimit();

	boolean encryptionSupportRequired();

	/**
	 * Gets the slow term test drive Enabled.
	 *
	 * @return the slow term test drive Enabled
	 */
	boolean isShortTermTestRideEnabled();

	/**
	 * Gets the Long term test drive Enabled.
	 *
	 * @return the Long term test drive Enabled
	 */

	boolean isLongTermTestRideEnabled();

	/**
	 * Gets the recent tab Enabled.
	 *
	 * @return the recent tab Enabled.
	 */
	boolean isRecentEnabled();

	public String country();

	String lttrAllCityEndPoint();

	String lttrRelationsEndPoint();

	String lttrPackageMasterLocation();

	String lttrRelationsMasterLocation();

	String lttrCityMasterLocation();

	/**
	 * Gets the scooter config Enabled.
	 *
	 * @return the scooter config Enabled
	 */
	boolean isScooterEnabled();

	/**
	 * Gets the organization Id.
	 *
	 * @return the organization Id.
	 */
	String organizationId();

	/**
	 * Gets the default Radius.
	 *
	 * @return the default Radius.
	 */
	String defaultRadius();
		
	/**
	 * Gets the advantage Page URL.
	 *
	 * @return the advantage Page URL.
	 */
	String advantagePageUrl();

	/**
	 * Gets the EMI Calculator URL.
	 *
	 * @return the EMI Calculator URL.
	 */
	String emiCalculatorUrl();

	/**
	 * Gets the Partial Payment URL.
	 *
	 * @return the Partial Payment URL.
	 */
	String partialPayUrl();

	/**
	 * Gets the Dealer Locator URL.
	 *
	 * @return the Dealer Locator URL.
	 */
	String dealerLocatorUrl();

	/**
	 * Gets the Google Tag.
	 *
	 * @return the Google Tag.
	 */
	String googleTag();

	/**
	 * Gets the Network Code URL.
	 *
	 * @return the Network Code URL.
	 */
	String networkCodesUrl();

	/**
	 * Gets the Leads Creation URL.
	 *
	 * @return the Leads Creation URL.
	 */
	String leadCreationUrl();

	/**
	 * Gets the Leads Campaign ID.
	 *
	 * @return the Leads Campaign ID.
	 */
	String leadCampaignId();

	/**
	 * Gets the Battery URL.
	 *
	 * @return the Battery URL.
	 */
	String batteryUrl();
	
	/**
	 * Quick purchase url.
	 *
	 * @return the string
	 */
	public String quickPurchaseUrl();
	
}
