/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.factory.ModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.wcm.api.Page;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.service.InstanceTypeService;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for PageModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class PageModelTest {

	private final AemContext context = new AemContext();

	@InjectMocks
	private PageModel pageModel;

	@Mock
	private Resource resource;

	@Mock
	private SlingHttpServletRequest requestMock;

	@Mock
	private GlobalConfig globalConfig;

	@Mock
	private InstanceTypeService instanceTypeService;

	@Mock
	private Page currentPage;

	@Mock
	private ResourceResolver resolver;

	@Mock
	private ModelFactory modelFactory;

	@Mock
	private ValueMap value;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		context.currentResource(resource);
		context.registerService(ModelFactory.class, modelFactory);
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.models.PageModel#init()}.
	 */
	@Test
	void testInit() {
		Mockito.when(resource.getPath()).thenReturn("/content/vida/in/en");
		Mockito.when(resolver.map(Mockito.anyString())).thenReturn("/content/vida/in/en");
		Mockito.when(globalConfig.magentoUrl()).thenReturn("magentoUrl");
		Mockito.when(globalConfig.loginPageUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.profilePageUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.testDriveUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.getTestDriveSelectorUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.getTestDriveLoginUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.getLongTermTestDriveUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.getShortTermTestDriveUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.getlttrTestDriveUploadDocumentsUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.getlttrTestDriveSummaryUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.lttrAcknowledgeUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.getlttrTestDriveStatusUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.purchaseConfigUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.homePageUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.productImageFolderPath()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.googleMapUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.resourcePath()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.preBookingUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.quickReserveUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.billingShippingUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.billingPricingUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.purchaseSummaryUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.bookingStatusUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.nomineeDetailsUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.preBookingStatusUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.faqUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.getInTouchUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.analyticsUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.storeDetailsUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.productListUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.productPriceUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.productBranchesUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.contactUsUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.internalUserUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.uploadDocumentsUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.aadharValidationUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.aadharValidationStatusUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.deliveryTrackerUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.configurationUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.aadharVerificationUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.freedoBookingLimit()).thenReturn(5);
		Mockito.when(globalConfig.encryptionSupportRequired()).thenReturn(true);
		Mockito.when(globalConfig.serverName()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.sfdcPartnerUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.advantagePageUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.emiCalculatorUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.partialPayUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.dealerLocatorUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(currentPage.getProperties()).thenReturn(value);
		Mockito.when(globalConfig.country()).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(globalConfig.isShortTermTestRideEnabled()).thenReturn(true);
		Mockito.when(globalConfig.isLongTermTestRideEnabled()).thenReturn(true);
		Mockito.when(globalConfig.isScooterEnabled()).thenReturn(true);
		Mockito.when(globalConfig.isRecentEnabled()).thenReturn(true);
		Mockito.when(globalConfig.googleTag()).thenReturn("Google Tag");
		Mockito.when(globalConfig.networkCodesUrl()).thenReturn("SampleText");
		Mockito.when(globalConfig.leadCreationUrl()).thenReturn("SampleText");
		Mockito.when(globalConfig.batteryUrl()).thenReturn("SampleText");
		Mockito.when(globalConfig.quickPurchaseUrl()).thenReturn("/content/vida/in/en/loginPageUrl");
		pageModel.init();

		Mockito.when(value.get(Mockito.anyString())).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(value.get("pageNotification", Boolean.class)).thenReturn(true);
		Mockito.when(value.get("isLoginPage", Boolean.class)).thenReturn(true);
		Mockito.when(value.get("isLoginProtected", Boolean.class)).thenReturn(true);
		Mockito.when(value.get("seoSchema", String.class)).thenReturn("/content/vida/in/en/loginPageUrl");
		Mockito.when(value.get("loginouttitle", String.class)).thenReturn("loginouttitle");
		Mockito.when(value.get("sessionexpirytitle", String.class)).thenReturn("sessionexpirytitle");
		Mockito.when(value.get("primarybtnlabel", String.class)).thenReturn("primarybtnlabel");
		Mockito.when(value.get("primarybtnlink", String.class)).thenReturn("primarybtnlink");
		Mockito.when(value.get("secondarybtnlabel", String.class)).thenReturn("secondarybtnlabel");
		Mockito.when(value.get("secondarybtnlink", String.class)).thenReturn("secondarybtnlink");
		pageModel.init();
		assertNotNull(pageModel);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getGlobalService()}.
	 */
	@Test
	void testGetGlobalService() {
		assertEquals(globalConfig, pageModel.getGlobalService());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getMagentoUrl()}.
	 */
	@Test
	void testGetMagentoUrl() {
		testInit();
		assertEquals("magentoUrl", pageModel.getMagentoUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getMagentoGrapghqlUrl()}.
	 */
	@Test
	void testGetMagentoGrapghqlUrl() {
		testInit();
		assertEquals("magentoUrl/graphql", pageModel.getMagentoGrapghqlUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getLoginPageUrl()}.
	 */
	@Test
	void testGetLoginPageUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getLoginPageUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getProfilePageUrl()}.
	 */
	@Test
	void testGetProfilePageUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getProfilePageUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getTestDriveUrl()}.
	 */
	@Test
	void testGetTestDriveUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getTestDriveUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getTestDriveSelectorUrl()}.
	 */
	@Test
	void testGetTestDriveSelectorUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getTestDriveSelectorUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getTestDriveLoginUrl()}.
	 */
	@Test
	void testGetTestDriveLoginUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getTestDriveLoginUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getLongTermTestDriveUrl()}.
	 */
	@Test
	void testGetLongTermTestDriveUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getLongTermTestDriveUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getShortTermTestDriveUrl()}.
	 */
	@Test
	void testGetShortTermTestDriveUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getShortTermTestDriveUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getPurchaseConfigUrl()}.
	 */
	@Test
	void testGetPurchaseConfigUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getPurchaseConfigUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getHomePageUrl()}.
	 */
	@Test
	void testGetHomePageUrl() {
		testInit();
		assertEquals("/content/vida/in/en/loginPageUrl", pageModel.getHomePageUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getGoogleMapUrl()}.
	 */
	@Test
	void testGetGoogleMapUrl() {
		testInit();
		assertEquals("/content/vida/in/en/loginPageUrl", pageModel.getGoogleMapUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getResourcePath()}.
	 */
	@Test
	void testGetResourcePath() {
		testInit();
		assertEquals("/content/vida/in/en/loginPageUrl", pageModel.getResourcePath());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getProductImageFolderPath()}.
	 */
	@Test
	void testGetProductImageFolderPath() {
		testInit();
		assertEquals("/content/vida/in/en/loginPageUrl", pageModel.getProductImageFolderPath());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getGeoDataUrl()}.
	 */
	@Test
	void testGetGeoDataUrl() {
		testInit();
		Mockito.when(globalConfig.geoDataUrl()).thenReturn("geoDataUrl");
		assertEquals("geoDataUrl", pageModel.getGeoDataUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getServiceableCitiesUrl()}.
	 */
	@Test
	void testGetServiceableCitiesUrl() {
		testInit();
		Mockito.when(globalConfig.serviceableCitiesUrl()).thenReturn("ServiceableCitiesUrl");
		assertEquals("ServiceableCitiesUrl", pageModel.getServiceableCitiesUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#availableTestRideCities()}.
	 */
	@Test
	void testAvailableTestRideCities() {
		testInit();
		Mockito.when(globalConfig.availableTestRideCities()).thenReturn("availableTestRideCities");
		assertEquals("availableTestRideCities", pageModel.availableTestRideCities());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getLttrStateCities()}.
	 */
	@Test
	void testGetLttrStateCities() {
		testInit();
		Mockito.when(globalConfig.lttrStateCities()).thenReturn("lttrStateCities");
		assertEquals("lttrStateCities", pageModel.getLttrStateCities());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getAvailableDealerCities()}.
	 */
	@Test
	void testGetAvailableDealerCities() {
		testInit();
		Mockito.when(globalConfig.availableDealerCities()).thenReturn("availableDealerCities");
		assertEquals("availableDealerCities", pageModel.getAvailableDealerCities());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#addressTypesUrl()}.
	 */
	@Test
	void testAddressTypesUrl() {
		testInit();
		Mockito.when(globalConfig.addressTypesUrl()).thenReturn("addressTypesUrl");
		assertEquals("addressTypesUrl", pageModel.addressTypesUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getServiceableBranchesUrl()}.
	 */
	@Test
	void testGetServiceableBranchesUrl() {
		testInit();
		assertEquals("/content/vida/in/ennull", pageModel.getServiceableBranchesUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getPincodeCityUrl()}.
	 */
	@Test
	void testGetPincodeCityUrl() {
		testInit();
		assertEquals("/content/vida/in/ennull", pageModel.getPincodeCityUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#exchangeVehicleMasterUrl()}.
	 */
	@Test
	void testExchangeVehicleMasterUrl() {
		testInit();
		Mockito.when(globalConfig.exchangeVehicleMasterUrl()).thenReturn("exchangeVehicleMasterUrl");
		assertEquals("exchangeVehicleMasterUrl", pageModel.exchangeVehicleMasterUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#lttrVehicleMasterUrl()}.
	 */
	@Test
	void testLttrVehicleMasterUrl() {
		testInit();
		assertEquals("/content/vida/in/ennull", pageModel.lttrVehicleMasterUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#lttrCityMasterUrl()}.
	 */
	@Test
	void testLttrCityMasterUrl() {
		testInit();
		Mockito.when(globalConfig.lttrCityMasterUrl()).thenReturn("lttrCityMasterUrl");
		assertEquals("lttrCityMasterUrl", pageModel.lttrCityMasterUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#lttrRelationMasterUrl()}.
	 */
	@Test
	void testLttrRelationMasterUrl() {
		testInit();
		Mockito.when(globalConfig.lttrRelationMasterUrl()).thenReturn("lttrRelationMasterUrl");
		assertEquals("lttrRelationMasterUrl", pageModel.lttrRelationMasterUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#lttrPackageMasterUrl()}.
	 */
	@Test
	void testLttrPackageMasterUrl() {
		testInit();
		Mockito.when(globalConfig.lttrPackageMasterUrl()).thenReturn("lttrPackageMasterUrl");
		assertEquals("lttrPackageMasterUrl", pageModel.lttrPackageMasterUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getServerName()}.
	 */
	@Test
	void testGetServerName() {
		testInit();
		assertEquals("/content/vida/in/en/loginPageUrl", pageModel.getServerName());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getSfdcPartnerUrl()}.
	 */
	@Test
	void testGetSfdcPartnerUrl() {
		testInit();
		assertEquals("/content/vida/in/en/loginPageUrl", pageModel.getSfdcPartnerUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getShortTermServiceableCitiesUrl()}.
	 */
	@Test
	void testGetShortTermServiceableCitiesUrl() {
		testInit();
		Mockito.when(globalConfig.shortTermServiceableCitiesUrl()).thenReturn("shortTermServiceableCitiesUrl");
		assertEquals("shortTermServiceableCitiesUrl", pageModel.getShortTermServiceableCitiesUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getLongTermServiceableCitiesUrl()}.
	 */
	@Test
	void testGetLongTermServiceableCitiesUrl() {
		testInit();
		Mockito.when(globalConfig.longTermServiceableCitiesUrl()).thenReturn("longTermServiceableCitiesUrl");
		assertEquals("longTermServiceableCitiesUrl", pageModel.getLongTermServiceableCitiesUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getPreBookingUrl()}.
	 */
	@Test
	void testGetPreBookingUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getPreBookingUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getQuickReserveUrl()}.
	 */
	@Test
	void testGetQuickReserveUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getQuickReserveUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getBillingShippingUrl()}.
	 */
	@Test
	void testGetBillingShippingUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getBillingShippingUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getBillingPricingUrl()}.
	 */
	@Test
	void testGetBillingPricingUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getBillingPricingUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#purchaseSummaryUrl()}.
	 */
	@Test
	void testPurchaseSummaryUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.purchaseSummaryUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getBookingStatusUrl()}.
	 */
	@Test
	void testGetBookingStatusUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getBookingStatusUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getNomineeDetailsUrl()}.
	 */
	@Test
	void testGetNomineeDetailsUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getNomineeDetailsUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getPreBookingStatusUrl()}.
	 */
	@Test
	void testGetPreBookingStatusUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getPreBookingStatusUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getFaqUrl()}.
	 */
	@Test
	void testGetFaqUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getFaqUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getGetInTouchUrl()}.
	 */
	@Test
	void testGetGetInTouchUrl() {
		testInit();
		assertEquals("/content/vida/in/en/loginPageUrl", pageModel.getGetInTouchUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getAnalyticsUrl()}.
	 */
	@Test
	void testGetAnalyticsUrl() {
		testInit();
		assertEquals("/content/vida/in/en/loginPageUrl", pageModel.getAnalyticsUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getStoreDetailsUrl()}.
	 */
	@Test
	void testGetStoreDetailsUrl() {
		testInit();
		assertEquals("/content/vida/in/en/loginPageUrl", pageModel.getStoreDetailsUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getProductListUrl()}.
	 */
	@Test
	void testGetProductListUrl() {
		testInit();
		assertEquals("/content/vida/in/en/loginPageUrl", pageModel.getProductListUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getProductPriceUrl()}.
	 */
	@Test
	void testGetProductPriceUrl() {
		testInit();
		assertEquals("/content/vida/in/en/loginPageUrl", pageModel.getProductPriceUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getProductBranchesUrl()}.
	 */
	@Test
	void testGetProductBranchesUrl() {
		testInit();
		assertEquals("/content/vida/in/en/loginPageUrl", pageModel.getProductBranchesUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getContactUsUrl()}.
	 */
	@Test
	void testGetContactUsUrl() {
		testInit();
		assertEquals("/content/vida/in/en/loginPageUrl", pageModel.getContactUsUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getInternalUserUrl()}.
	 */
	@Test
	void testGetInternalUserUrl() {
		testInit();
		assertEquals("/content/vida/in/en/loginPageUrl", pageModel.getInternalUserUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getUploadDocumentsUrl()}.
	 */
	@Test
	void testGetUploadDocumentsUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getUploadDocumentsUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getAadharValidationUrl()}.
	 */
	@Test
	void testGetAadharValidationUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getAadharValidationUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getAadharValidationStatusUrl()}.
	 */
	@Test
	void testGetAadharValidationStatusUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getAadharValidationStatusUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getDeliveryTrackerUrl()}.
	 */
	@Test
	void testGetDeliveryTrackerUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getDeliveryTrackerUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getConfigurationUrl()}.
	 */
	@Test
	void testGetConfigurationUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getConfigurationUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getAadharVerificationUrl()}.
	 */
	@Test
	void testGetAadharVerificationUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getAadharVerificationUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getPageNotification()}.
	 */
	@Test
	void testGetPageNotification() {
		testInit();
		assertEquals(true, pageModel.getPageNotification());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getIsLoginPage()}.
	 */
	@Test
	void testGetIsLoginPage() {
		testInit();
		assertEquals(true, pageModel.getIsLoginPage());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getIsLoginProtected()}.
	 */
	@Test
	void testGetIsLoginProtected() {
		testInit();
		assertEquals(true, pageModel.getIsLoginProtected());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getSeoSchema()}.
	 */
	@Test
	void testGetSeoSchema() {
		testInit();
		assertEquals("/content/vida/in/en/loginPageUrl", pageModel.getSeoSchema());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getEnvironment()}.
	 */
	@Test
	void testGetEnvironment() {
		testInit();
		Mockito.when(instanceTypeService.getEnvironment()).thenReturn("DEV");
		assertEquals("DEV", pageModel.getEnvironment());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getLttrTestDriveUploadDocumentsUrl()}.
	 */
	@Test
	void testGetLttrTestDriveUploadDocumentsUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getLttrTestDriveUploadDocumentsUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getLttrTestDriveSummaryUrl()}.
	 */
	@Test
	void testGetLttrTestDriveSummaryUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getLttrTestDriveSummaryUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#lttrAcknowledgeUrl()}.
	 */
	@Test
	void testLttrAcknowledgeUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.lttrAcknowledgeUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getLttrTestDriveStatusUrl()}.
	 */
	@Test
	void testGetLttrTestDriveStatusUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getLttrTestDriveStatusUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getInstance()}.
	 */
	@Test
	void testGetInstance() {
		testInit();
		Mockito.when(instanceTypeService.getInstance()).thenReturn("STAGE");
		assertEquals("STAGE", pageModel.getInstance());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getDefaultCountryCode()}.
	 */
	@Test
	void testGetDefaultCountryCode() {
		testInit();
		assertEquals("+91", pageModel.getDefaultCountryCode());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getPickupLocationUrl()}.
	 */
	@Test
	void testGetPickupLocationUrl() {
		testInit();
		assertEquals("/content/vida/in/ennull", pageModel.getPickupLocationUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getFreedoBookingLimit()}.
	 */
	@Test
	void testGetFreedoBookingLimit() {
		testInit();
		assertEquals(5, pageModel.getFreedoBookingLimit());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getEncryptionSupportRequired()}.
	 */
	@Test
	void testGetEncryptionSupportRequired() {
		testInit();
		assertEquals(true, pageModel.getEncryptionSupportRequired());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getIsShortTermTestRideEnabled()}.
	 */
	@Test
	void testGetIsShortTermTestRideEnabled() {
		testInit();
		assertEquals(true, pageModel.getIsShortTermTestRideEnabled());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getIsLongTermTestRideEnabled()}.
	 */
	@Test
	void testGetIsLongTermTestRideEnabled() {
		testInit();
		assertEquals(true, pageModel.getIsLongTermTestRideEnabled());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getIsScooterEnabled()}.
	 */
	@Test
	void testGetIsScooterEnabled() {
		testInit();
		assertEquals(true, pageModel.getIsScooterEnabled());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getIsRecentEnabled()}.
	 */
	@Test
	void testGetIsRecentEnabled() {
		testInit();
		assertEquals(true, pageModel.getIsRecentEnabled());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getCountry()}.
	 */
	@Test
	void testGetCountry() {
		testInit();
		assertEquals("/content/vida/in/en/loginPageUrl", pageModel.getCountry());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getAdvantagePageUrl()}.
	 */
	@Test
	void testGetAdvantagePageUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getAdvantagePageUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getEmiCalculatorUrl()}.
	 */
	@Test
	void testGetEmiCalculatorUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getEmiCalculatorUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getPartialPaymentUrl()}.
	 */
	@Test
	void testGetPartialPaymentUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getPartialPaymentUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getDealerLocatorUrl()}.
	 */
	@Test
	void testGetDealerLocatorUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getDealerLocatorUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getGoogleTag()}.
	 */
	@Test
	void testGetGoogleTag() {
		testInit();
		assertEquals("Google Tag", pageModel.getGoogleTag());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getSecondarybtnlink()}.
	 */
	@Test
	void testGetSecondarybtnlink() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getSecondarybtnlink());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getSecondarybtnlabel()}.
	 */
	@Test
	void testGetSecondarybtnlabel() {
		testInit();
		assertEquals("secondarybtnlabel", pageModel.getSecondarybtnlabel());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getPrimarybtnlink()}.
	 */
	@Test
	void testGetPrimarybtnlink() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getPrimarybtnlink());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getPrimarybtnlabel()}.
	 */
	@Test
	void testGetPrimarybtnlabel() {
		testInit();
		assertEquals("primarybtnlabel", pageModel.getPrimarybtnlabel());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getSessionexpirytitle()}.
	 */
	@Test
	void testGetSessionexpirytitle() {
		testInit();
		assertEquals("sessionexpirytitle", pageModel.getSessionexpirytitle());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getLoginouttitle()}.
	 */
	@Test
	void testGetLoginouttitle() {
		testInit();
		assertEquals("loginouttitle", pageModel.getLoginouttitle());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getLeadCreationUrl()}.
	 */
	@Test
	void testGetLeadCreationUrl() {
		testInit();
		assertEquals("SampleText", pageModel.getLeadCreationUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getNetworkCodesUrl()}.
	 */
	@Test
	void testGetNetworkCodesUrl() {
		testInit();
		assertEquals("SampleText", pageModel.getNetworkCodesUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getBatteryUrl()}.
	 */
	@Test
	void testGetBatteryUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getBatteryUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.PageModel#getQuickPurchaseUrl()}.
	 */
	@Test
	void testGetQuickPurchaseUrl() {
		testInit();
		assertEquals("/content/vida/in/en", pageModel.getQuickPurchaseUrl());
	}

}
