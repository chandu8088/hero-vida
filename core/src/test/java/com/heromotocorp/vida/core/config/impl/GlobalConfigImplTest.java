/**
 * 
 */
package com.heromotocorp.vida.core.config.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;



import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for GlobalConfigImpl
 */
@ExtendWith(AemContextExtension.class)
class GlobalConfigImplTest {

	private final AemContext context = new AemContext();

	private GlobalConfigImpl globalConfigImpl = new GlobalConfigImpl();

	private GlobalConfigImpl.ServiceConfig serviceConfig;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		globalConfigImpl = context.registerService(new GlobalConfigImpl());
		serviceConfig = Mockito.mock(GlobalConfigImpl.ServiceConfig.class);

		Mockito.when(serviceConfig.geoDataUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.serviceableCitiesUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.availableTestRideCities()).thenReturn("SampleText");
		Mockito.when(serviceConfig.lttrStateCities()).thenReturn("SampleText");
		Mockito.when(serviceConfig.availableDealerCities()).thenReturn("SampleText");
		Mockito.when(serviceConfig.addressTypesUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.serviceableBranchesUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.pincodeCityUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.shortTermServiceableCitiesUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.longTermServiceableCitiesUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.exchangeVehicleMasterUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.lttrVehicleMasterUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.lttrCityMasterUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.lttrRelationMasterUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.lttrPackageMasterUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.magentoUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.magentoBearerToken()).thenReturn("SampleText");
		Mockito.when(serviceConfig.magentoPriceAPIMethod()).thenReturn("SampleText");
		Mockito.when(serviceConfig.loginPageUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.profilePageUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.testDriveUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.testDriveSelectorUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.testDriveLoginUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.longTermTestDriveUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.shortTermTestDriveUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.lttrTestDriveUploadDocumentsUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.lttrTestDriveStatusUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.lttrTestDriveSummaryUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.lttrAcknowledgeUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.purchaseConfigUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.homePageUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.productImageFolderPath()).thenReturn("SampleText");
		Mockito.when(serviceConfig.googleMapUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.resourcePath()).thenReturn("SampleText");
		Mockito.when(serviceConfig.preBookingUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.quickReserveUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.billingShippingUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.billingPricingUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.purchaseSummaryUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.bookingStatusUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.nomineeDetailsUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.preBookingStatusUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.faqUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.getInTouchUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.analyticsUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.storeDetailsUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.productListUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.productPriceUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.productBranchesUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.contactUsUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.internalUserUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.uploadDocumentsUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.aadharValidationUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.aadharValidationStatusUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.deliveryTrackerUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.configurationUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.aadharVerificationUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.exchangeVehicleCSVLocationPath()).thenReturn("SampleText");
		Mockito.when(serviceConfig.serverName()).thenReturn("SampleText");
		Mockito.when(serviceConfig.sfdcPartnerUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.postRequestToken()).thenReturn("SampleText");
		Mockito.when(serviceConfig.grantType()).thenReturn("SampleText");
		Mockito.when(serviceConfig.clientId()).thenReturn("SampleText");
		Mockito.when(serviceConfig.clientSecret()).thenReturn("SampleText");
		Mockito.when(serviceConfig.username()).thenReturn("SampleText");
		Mockito.when(serviceConfig.contentType()).thenReturn("SampleText");
		Mockito.when(serviceConfig.password()).thenReturn("SampleText");
		Mockito.when(serviceConfig.locationDataUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.nearByRequestUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.dealersbranchesUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.leadsUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.caseUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.tokencreateUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.systemUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.categoryUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.subcategoryUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.internalComplainUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.sfdcQueryEndpoint()).thenReturn("SampleText");
		Mockito.when(serviceConfig.cityMasterCSVLocationPath()).thenReturn("SampleText");
		Mockito.when(serviceConfig.getItemPrice()).thenReturn("SampleText");
		Mockito.when(serviceConfig.autovertMinEmiEndPoint()).thenReturn("SampleText");
		Mockito.when(serviceConfig.autovertOfferUrlEndPoint()).thenReturn("SampleText");
		Mockito.when(serviceConfig.pincodeCityReqUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.exchangeVehicleCSVLocationReqPath()).thenReturn("SampleText");
		Mockito.when(serviceConfig.lttrApiKey()).thenReturn("SampleText");
		Mockito.when(serviceConfig.lttrBaseEndPoint()).thenReturn("SampleText");
		Mockito.when(serviceConfig.lttrRelationsEndPoint()).thenReturn("SampleText");
		Mockito.when(serviceConfig.lttrAllCityEndPoint()).thenReturn("SampleText");
		Mockito.when(serviceConfig.lttrPackageMasterLocation()).thenReturn("SampleText");
		Mockito.when(serviceConfig.lttrRelationsMasterLocation()).thenReturn("SampleText");
		Mockito.when(serviceConfig.lttrCityMasterLocation()).thenReturn("SampleText");
		Mockito.when(serviceConfig.pickupLocationUrl()).thenReturn("SampleText");

		Mockito.when(serviceConfig.electrifyEndPoint()).thenReturn("SampleText");
		Mockito.when(serviceConfig.electrifyToken()).thenReturn("SampleText");
		Mockito.when(serviceConfig.freedoBookingLimit()).thenReturn(5);
		Mockito.when(serviceConfig.encryptionSupportRequired()).thenReturn(true);
		Mockito.when(serviceConfig.isShortTermTestRideEnabled()).thenReturn(false);
		Mockito.when(serviceConfig.isLongTermTestRideEnabled()).thenReturn(true);
		Mockito.when(serviceConfig.isScooterEnabled()).thenReturn(false);
		Mockito.when(serviceConfig.isRecentEnabled()).thenReturn(false);
		Mockito.when(serviceConfig.country()).thenReturn("SampleText");
		Mockito.when(serviceConfig.organizationId()).thenReturn("SampleText");
		Mockito.when(serviceConfig.defaultRadius()).thenReturn("SampleText");
		Mockito.when(serviceConfig.advantagePageUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.emiCalculatorUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.partialPayUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.dealerLocatorUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.googleTag()).thenReturn("SampleText");
		Mockito.when(serviceConfig.networkCodesUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.leadCampaignId()).thenReturn("SampleText");
		Mockito.when(serviceConfig.leadCreationUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.batteryUrl()).thenReturn("SampleText");
		Mockito.when(serviceConfig.quickPurchaseUrl()).thenReturn("SampleText");
		globalConfigImpl.activate(serviceConfig);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#activate(com.heromotocorp.vida.core.config.impl.GlobalConfigImpl.ServiceConfig)}.
	 */
	@Test
	void testActivate() {
		globalConfigImpl.activate(serviceConfig);
		assertNotNull(globalConfigImpl);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#geoDataUrl()}.
	 */
	@Test
	void testGeoDataUrl() {

		assertEquals("SampleText", globalConfigImpl.geoDataUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#serviceableCitiesUrl()}.
	 */
	@Test
	void testServiceableCitiesUrl() {

		assertEquals("SampleText", globalConfigImpl.serviceableCitiesUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#availableTestRideCities()}.
	 */
	@Test
	void testAvailableTestRideCities() {

		assertEquals("SampleText", globalConfigImpl.availableTestRideCities());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#lttrStateCities()}.
	 */
	@Test
	void testLttrStateCities() {

		assertEquals("SampleText", globalConfigImpl.lttrStateCities());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#availableDealerCities()}.
	 */
	@Test
	void testAvailableDealerCities() {

		assertEquals("SampleText", globalConfigImpl.availableDealerCities());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#addressTypesUrl()}.
	 */
	@Test
	void testAddressTypesUrl() {

		assertEquals("SampleText", globalConfigImpl.addressTypesUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#serviceableBranchesUrl()}.
	 */
	@Test
	void testServiceableBranchesUrl() {

		assertEquals("SampleText", globalConfigImpl.serviceableBranchesUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#shortTermServiceableCitiesUrl()}.
	 */
	@Test
	void testShortTermServiceableCitiesUrl() {

		assertEquals("SampleText", globalConfigImpl.shortTermServiceableCitiesUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#longTermServiceableCitiesUrl()}.
	 */
	@Test
	void testLongTermServiceableCitiesUrl() {

		assertEquals("SampleText", globalConfigImpl.longTermServiceableCitiesUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#pincodeCityUrl()}.
	 */
	@Test
	void testPincodeCityUrl() {

		assertEquals("SampleText", globalConfigImpl.pincodeCityUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#exchangeVehicleMasterUrl()}.
	 */
	@Test
	void testExchangeVehicleMasterUrl() {

		assertEquals("SampleText", globalConfigImpl.exchangeVehicleMasterUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#lttrVehicleMasterUrl()}.
	 */
	@Test
	void testLttrVehicleMasterUrl() {

		assertEquals("SampleText", globalConfigImpl.lttrVehicleMasterUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#lttrCityMasterUrl()}.
	 */
	@Test
	void testLttrCityMasterUrl() {

		assertEquals("SampleText", globalConfigImpl.lttrCityMasterUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#lttrRelationMasterUrl()}.
	 */
	@Test
	void testLttrRelationMasterUrl() {

		assertEquals("SampleText", globalConfigImpl.lttrRelationMasterUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#lttrPackageMasterUrl()}.
	 */
	@Test
	void testLttrPackageMasterUrl() {

		assertEquals("SampleText", globalConfigImpl.lttrPackageMasterUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#magentoUrl()}.
	 */
	@Test
	void testMagentoUrl() {

		assertEquals("SampleText", globalConfigImpl.magentoUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#magentoBearerToken()}.
	 */
	@Test
	void testMagentoBearerToken() {

		assertEquals("SampleText", globalConfigImpl.magentoBearerToken());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#magentoPriceAPIMethod()}.
	 */
	@Test
	void testMagentoPriceAPIMethod() {

		assertEquals("SampleText", globalConfigImpl.magentoPriceAPIMethod());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#loginPageUrl()}.
	 */
	@Test
	void testLoginPageUrl() {

		assertEquals("SampleText", globalConfigImpl.loginPageUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#profilePageUrl()}.
	 */
	@Test
	void testProfilePageUrl() {

		assertEquals("SampleText", globalConfigImpl.profilePageUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#testDriveUrl()}.
	 */
	@Test
	void testTestDriveUrl() {

		assertEquals("SampleText", globalConfigImpl.testDriveUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#getLongTermTestDriveUrl()}.
	 */
	@Test
	void testGetLongTermTestDriveUrl() {

		assertEquals("SampleText", globalConfigImpl.getLongTermTestDriveUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#getShortTermTestDriveUrl()}.
	 */
	@Test
	void testGetShortTermTestDriveUrl() {

		assertEquals("SampleText", globalConfigImpl.getShortTermTestDriveUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#getlttrTestDriveUploadDocumentsUrl()}.
	 */
	@Test
	void testGetlttrTestDriveUploadDocumentsUrl() {

		assertEquals("SampleText", globalConfigImpl.getlttrTestDriveUploadDocumentsUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#getlttrTestDriveSummaryUrl()}.
	 */
	@Test
	void testGetlttrTestDriveSummaryUrl() {

		assertEquals("SampleText", globalConfigImpl.getlttrTestDriveSummaryUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#getlttrTestDriveStatusUrl()}.
	 */
	@Test
	void testGetlttrTestDriveStatusUrl() {

		assertEquals("SampleText", globalConfigImpl.getlttrTestDriveStatusUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#purchaseConfigUrl()}.
	 */
	@Test
	void testPurchaseConfigUrl() {

		assertEquals("SampleText", globalConfigImpl.purchaseConfigUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#homePageUrl()}.
	 */
	@Test
	void testHomePageUrl() {

		assertEquals("SampleText", globalConfigImpl.homePageUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#productImageFolderPath()}.
	 */
	@Test
	void testProductImageFolderPath() {

		assertEquals("SampleText", globalConfigImpl.productImageFolderPath());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#googleMapUrl()}.
	 */
	@Test
	void testGoogleMapUrl() {

		assertEquals("SampleText", globalConfigImpl.googleMapUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#resourcePath()}.
	 */
	@Test
	void testResourcePath() {

		assertEquals("SampleText", globalConfigImpl.resourcePath());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#preBookingUrl()}.
	 */
	@Test
	void testPreBookingUrl() {

		assertEquals("SampleText", globalConfigImpl.preBookingUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#quickReserveUrl()}.
	 */
	@Test
	void testQuickReserveUrl() {

		assertEquals("SampleText", globalConfigImpl.quickReserveUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#billingShippingUrl()}.
	 */
	@Test
	void testBillingShippingUrl() {

		assertEquals("SampleText", globalConfigImpl.billingShippingUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#billingPricingUrl()}.
	 */
	@Test
	void testBillingPricingUrl() {

		assertEquals("SampleText", globalConfigImpl.billingPricingUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#purchaseSummaryUrl()}.
	 */
	@Test
	void testPurchaseSummaryUrl() {

		assertEquals("SampleText", globalConfigImpl.purchaseSummaryUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#bookingStatusUrl()}.
	 */
	@Test
	void testBookingStatusUrl() {

		assertEquals("SampleText", globalConfigImpl.bookingStatusUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#nomineeDetailsUrl()}.
	 */
	@Test
	void testNomineeDetailsUrl() {

		assertEquals("SampleText", globalConfigImpl.nomineeDetailsUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#preBookingStatusUrl()}.
	 */
	@Test
	void testPreBookingStatusUrl() {

		assertEquals("SampleText", globalConfigImpl.preBookingStatusUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#faqUrl()}.
	 */
	@Test
	void testFaqUrl() {

		assertEquals("SampleText", globalConfigImpl.faqUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#getInTouchUrl()}.
	 */
	@Test
	void testGetInTouchUrl() {

		assertEquals("SampleText", globalConfigImpl.getInTouchUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#analyticsUrl()}.
	 */
	@Test
	void testAnalyticsUrl() {

		assertEquals("SampleText", globalConfigImpl.analyticsUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#storeDetailsUrl()}.
	 */
	@Test
	void testStoreDetailsUrl() {

		assertEquals("SampleText", globalConfigImpl.storeDetailsUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#productListUrl()}.
	 */
	@Test
	void testProductListUrl() {

		assertEquals("SampleText", globalConfigImpl.productListUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#productPriceUrl()}.
	 */
	@Test
	void testProductPriceUrl() {

		assertEquals("SampleText", globalConfigImpl.productPriceUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#productBranchesUrl()}.
	 */
	@Test
	void testProductBranchesUrl() {

		assertEquals("SampleText", globalConfigImpl.productBranchesUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#contactUsUrl()}.
	 */
	@Test
	void testContactUsUrl() {

		assertEquals("SampleText", globalConfigImpl.contactUsUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#internalUserUrl()}.
	 */
	@Test
	void testInternalUserUrl() {

		assertEquals("SampleText", globalConfigImpl.internalUserUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#uploadDocumentsUrl()}.
	 */
	@Test
	void testUploadDocumentsUrl() {

		assertEquals("SampleText", globalConfigImpl.uploadDocumentsUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#aadharValidationUrl()}.
	 */
	@Test
	void testAadharValidationUrl() {

		assertEquals("SampleText", globalConfigImpl.aadharValidationUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#aadharValidationStatusUrl()}.
	 */
	@Test
	void testAadharValidationStatusUrl() {

		assertEquals("SampleText", globalConfigImpl.aadharValidationStatusUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#deliveryTrackerUrl()}.
	 */
	@Test
	void testDeliveryTrackerUrl() {

		assertEquals("SampleText", globalConfigImpl.deliveryTrackerUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#exchangeVehicleCSVLocationPath()}.
	 */
	@Test
	void testExchangeVehicleCSVLocationPath() {

		assertEquals("SampleText", globalConfigImpl.exchangeVehicleCSVLocationPath());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#configurationUrl()}.
	 */
	@Test
	void testConfigurationUrl() {

		assertEquals("SampleText", globalConfigImpl.configurationUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#aadharVerificationUrl()}.
	 */
	@Test
	void testAadharVerificationUrl() {

		assertEquals("SampleText", globalConfigImpl.aadharVerificationUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#password()}.
	 */
	@Test
	void testPassword() {

		assertEquals("SampleText", globalConfigImpl.password());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#postRequestToken()}.
	 */
	@Test
	void testPostRequestToken() {

		assertEquals("SampleTextSampleText", globalConfigImpl.postRequestToken());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#grantType()}.
	 */
	@Test
	void testGrantType() {

		assertEquals("SampleText", globalConfigImpl.grantType());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#clientId()}.
	 */
	@Test
	void testClientId() {

		assertEquals("SampleText", globalConfigImpl.clientId());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#clientSecret()}.
	 */
	@Test
	void testClientSecret() {

		assertEquals("SampleText", globalConfigImpl.clientSecret());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#contentType()}.
	 */
	@Test
	void testContentType() {

		assertEquals("SampleText", globalConfigImpl.contentType());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#username()}.
	 */
	@Test
	void testUsername() {

		assertEquals("SampleText", globalConfigImpl.username());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#nearByRequestUrl()}.
	 */
	@Test
	void testNearByRequestUrl() {

		assertEquals("SampleTextSampleText", globalConfigImpl.nearByRequestUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#dealersbranchesUrl()}.
	 */
	@Test
	void testDealersbranchesUrl() {

		assertEquals("SampleTextSampleText", globalConfigImpl.dealersbranchesUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#pincodeCityReqUrl()}.
	 */
	@Test
	void testPincodeCityReqUrl() {

		assertEquals("SampleTextSampleText", globalConfigImpl.pincodeCityReqUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#locationDataUrl()}.
	 */
	@Test
	void testLocationDataUrl() {

		assertEquals("SampleTextSampleText", globalConfigImpl.locationDataUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#leadsUrl()}.
	 */
	@Test
	void testLeadsUrl() {

		assertEquals("SampleTextSampleText", globalConfigImpl.leadsUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#cityMasterCSVLocationPath()}.
	 */
	@Test
	void testCityMasterCSVLocationPath() {

		assertEquals("SampleText", globalConfigImpl.cityMasterCSVLocationPath());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#sfdcQueryEndpoint()}.
	 */
	@Test
	void testSfdcQueryEndpoint() {

		assertEquals("SampleTextSampleText", globalConfigImpl.sfdcQueryEndpoint());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#caseUrl()}.
	 */
	@Test
	void testCaseUrl() {

		assertEquals("SampleTextSampleText", globalConfigImpl.caseUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#tokencreateUrl()}.
	 */
	@Test
	void testTokencreateUrl() {

		assertEquals("SampleTextSampleText", globalConfigImpl.tokencreateUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#systemUrl()}.
	 */
	@Test
	void testSystemUrl() {

		assertEquals("SampleTextSampleText", globalConfigImpl.systemUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#categoryUrl()}.
	 */
	@Test
	void testCategoryUrl() {

		assertEquals("SampleTextSampleText", globalConfigImpl.categoryUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#subcategoryUrl()}.
	 */
	@Test
	void testSubcategoryUrl() {

		assertEquals("SampleTextSampleText", globalConfigImpl.subcategoryUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#internalComplainUrl()}.
	 */
	@Test
	void testInternalComplainUrl() {

		assertEquals("SampleTextSampleText", globalConfigImpl.internalComplainUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#getItemPrice()}.
	 */
	@Test
	void testGetItemPrice() {

		assertEquals("SampleTextSampleText", globalConfigImpl.getItemPrice());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#autovertMinEmiEndPoint()}.
	 */
	@Test
	void testAutovertMinEmiEndPoint() {

		assertEquals("SampleText", globalConfigImpl.autovertMinEmiEndPoint());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#autovertOfferUrlEndPoint()}.
	 */
	@Test
	void testAutovertOfferUrlEndPoint() {

		assertEquals("SampleText", globalConfigImpl.autovertOfferUrlEndPoint());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#serverName()}.
	 */
	@Test
	void testServerName() {

		assertEquals("SampleText", globalConfigImpl.serverName());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#exchangeVehicleCSVLocationReqPath()}.
	 */
	@Test
	void testExchangeVehicleCSVLocationReqPath() {

		assertEquals("SampleText", globalConfigImpl.exchangeVehicleCSVLocationReqPath());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#lttrApiKey()}.
	 */
	@Test
	void testLttrApiKey() {

		assertEquals("SampleText", globalConfigImpl.lttrApiKey());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#lttrBaseEndPoint()}.
	 */
	@Test
	void testLttrBaseEndPoint() {

		assertEquals("SampleText", globalConfigImpl.lttrBaseEndPoint());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#lttrAllCityEndPoint()}.
	 */
	@Test
	void testLttrAllCityEndPoint() {

		assertEquals("SampleText", globalConfigImpl.lttrAllCityEndPoint());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#lttrRelationsEndPoint()}.
	 */
	@Test
	void testLttrRelationsEndPoint() {

		assertEquals("SampleText", globalConfigImpl.lttrRelationsEndPoint());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#lttrPackageMasterLocation()}.
	 */
	@Test
	void testLttrPackageMasterLocation() {

		assertEquals("SampleText", globalConfigImpl.lttrPackageMasterLocation());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#lttrRelationsMasterLocation()}.
	 */
	@Test
	void testLttrRelationsMasterLocation() {

		assertEquals("SampleText", globalConfigImpl.lttrRelationsMasterLocation());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#lttrCityMasterLocation()}.
	 */
	@Test
	void testLttrCityMasterLocation() {

		assertEquals("SampleText", globalConfigImpl.lttrCityMasterLocation());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#pickupLocationUrl()}.
	 */
	@Test
	void testPickupLocationUrl() {

		assertEquals("SampleText", globalConfigImpl.pickupLocationUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#electrifyEndPoint()}.
	 */
	@Test
	void testElectrifyEndPoint() {

		assertEquals("SampleText", globalConfigImpl.electrifyEndPoint());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#electrifyToken()}.
	 */
	@Test
	void testElectrifyToken() {

		assertEquals("SampleText", globalConfigImpl.electrifyToken());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#freedoBookingLimit()}.
	 */
	@Test
	void testFreedoBookingLimit() {

		assertEquals(5, globalConfigImpl.freedoBookingLimit());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#encryptionSupportRequired()}.
	 */
	@Test
	void testEncryptionSupportRequired() {

		assertEquals(true, globalConfigImpl.encryptionSupportRequired());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#country()}.
	 */
	@Test
	void testCountry() {

		assertEquals("SampleText", globalConfigImpl.country());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#isShortTermTestRideEnabled()}.
	 */
	@Test
	void testIsShortTermTestRideEnabled() {

		assertEquals(false, globalConfigImpl.isShortTermTestRideEnabled());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#isRecentEnabled()}.
	 */
	@Test
	void testIsRecentEnabled() {

		assertEquals(false, globalConfigImpl.isRecentEnabled());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#isLongTermTestRideEnabled()}.
	 */
	@Test
	void testIsLongTermTestRideEnabled() {

		assertEquals(true, globalConfigImpl.isLongTermTestRideEnabled());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#isScooterEnabled()}.
	 */
	@Test
	void testIsScooterEnabled() {

		assertEquals(false, globalConfigImpl.isScooterEnabled());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#sfdcPartnerUrl()}.
	 */
	@Test
	void testSfdcPartnerUrl() {

		assertEquals("SampleText", globalConfigImpl.sfdcPartnerUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#organizationId()}.
	 */
	@Test
	void testOrganizationId() {

		assertEquals("SampleText", globalConfigImpl.organizationId());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#defaultRadius()}.
	 */
	@Test
	void testDefaultRadius() {

		assertEquals("SampleText", globalConfigImpl.defaultRadius());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#advantagePageUrl()}.
	 */
	@Test
	void testAdvantagePageUrl() {

		assertEquals("SampleText", globalConfigImpl.advantagePageUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#emiCalculatorUrl()}.
	 */
	@Test
	void testEmiCalculatorUrl() {

		assertEquals("SampleText", globalConfigImpl.emiCalculatorUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#partialPayUrl()}.
	 */
	@Test
	void testPartialPayUrl() {

		assertEquals("SampleText", globalConfigImpl.partialPayUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#getTestDriveSelectorUrl()}.
	 */
	@Test
	void testGetTestDriveSelectorUrl() {

		assertEquals("SampleText", globalConfigImpl.getTestDriveSelectorUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#getTestDriveLoginUrl()}.
	 */
	@Test
	void testGetTestDriveLoginUrl() {

		assertEquals("SampleText", globalConfigImpl.getTestDriveLoginUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#lttrAcknowledgeUrl()}.
	 */
	@Test
	void testLttrAcknowledgeUrl() {

		assertEquals("SampleText", globalConfigImpl.lttrAcknowledgeUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#dealerLocatorUrl()}.
	 */
	@Test
	void testDealerLocatorUrl() {

		assertEquals("SampleText", globalConfigImpl.dealerLocatorUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#googleTag()}.
	 */
	@Test
	void testGoogleTag() {

		assertEquals("SampleText", globalConfigImpl.googleTag());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#networkCodesUrl()}.
	 */
	@Test
	void testNetworkCodesUrl() {

		assertEquals("SampleText", globalConfigImpl.networkCodesUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#leadCreationUrl()}.
	 */
	@Test
	void testLeadCreationUrl() {

		assertEquals("SampleText", globalConfigImpl.leadCreationUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#leadCampaignId()}.
	 */
	@Test
	void testLeadCampaignId() {

		assertEquals("SampleText", globalConfigImpl.leadCampaignId());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#batteryUrl()}.
	 */
	@Test
	void testBatteryUrl() {
		assertEquals("SampleText", globalConfigImpl.batteryUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.GlobalConfigImpl#quickPurchaseUrl()}.
	 */
	@Test
	void testQuickPurchaseUrl() {

		assertEquals("SampleText", globalConfigImpl.quickPurchaseUrl());
	}

}
