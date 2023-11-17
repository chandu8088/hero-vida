package com.heromotocorp.vida.core.schedulers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.UnsupportedRepositoryOperationException;
import javax.jcr.ValueFactory;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.AssetManager;
import com.day.cq.dam.api.Rendition;
import com.day.cq.replication.Replicator;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.config.impl.MapAPISchedulerConfig;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for MapAPIScheduler
 */
@ExtendWith(AemContextExtension.class)
class MapAPISchedulerTest {

	private final AemContext context = new AemContext();

	private MapAPIScheduler mapAPIScheduler;

	private MapAPISchedulerConfig mapAPISchedulerConfig;

	private Scheduler scheduler;

	private ScheduleOptions scheduleOptions;

	private ResourceResolverFactory resolverFactory;

	private Replicator replicator;

	private GlobalConfig globalConfig;

	private ClientConfig clientConfig;

	private ResourceResolver resolver;

	private Resource assetResource;

	private Asset asset;

	private Rendition original;

	private CloseableHttpResponse httpResponse;

	private HttpEntity entity;

	private CloseableHttpClient httpClient;

	private CloseableHttpResponse httpResponse1;

	private HttpEntity entity1;

	private CloseableHttpClient httpClient1;

	private AssetManager assetManager;

	private Session session;

	private ValueFactory factory;

	private String text = "City ,State ,Country ,Latitude,Longitude ,Branch Id,Parter Account ID\n"
			+ "New Delhi ,Delhi ,India ,28.6448,77.216721,a0Ip0000003g2lfEAA,001p00000102MzHAAU\n"
			+ "Bengaluru,Karnataka,India ,12.972442,77.580643,a0Ip0000003g9znEAA,001p00000104AZ1AAM\n";

	@BeforeEach
	public void setup() throws Exception {
		mapAPIScheduler = context.registerService(new MapAPIScheduler());
		mapAPISchedulerConfig = Mockito.mock(MapAPISchedulerConfig.class);
		scheduler = context.getService(Scheduler.class);
		scheduler = mock(Scheduler.class);
		PrivateAccessor.setField(mapAPIScheduler, "scheduler", scheduler);
		scheduleOptions = Mockito.mock(ScheduleOptions.class);
		resolver = Mockito.mock(ResourceResolver.class);
		assetResource = Mockito.mock(Resource.class);
		asset = Mockito.mock(Asset.class);
		original = Mockito.mock(Rendition.class);
		httpResponse = Mockito.mock(CloseableHttpResponse.class);
		entity = Mockito.mock(HttpEntity.class);
		httpClient = Mockito.mock(CloseableHttpClient.class);
		httpResponse1 = Mockito.mock(CloseableHttpResponse.class);
		entity1 = Mockito.mock(HttpEntity.class);
		httpClient1 = Mockito.mock(CloseableHttpClient.class);
		assetManager = Mockito.mock(AssetManager.class);
		session = Mockito.mock(Session.class);
		factory = Mockito.mock(ValueFactory.class);

		resolverFactory = context.getService(ResourceResolverFactory.class);
		resolverFactory = mock(ResourceResolverFactory.class);
		PrivateAccessor.setField(mapAPIScheduler, "resolverFactory", resolverFactory);

		replicator = context.getService(Replicator.class);
		replicator = mock(Replicator.class);
		PrivateAccessor.setField(mapAPIScheduler, "replicator", replicator);

		globalConfig = context.getService(GlobalConfig.class);
		globalConfig = mock(GlobalConfig.class);
		PrivateAccessor.setField(mapAPIScheduler, "globalConfig", globalConfig);

		clientConfig = context.getService(ClientConfig.class);
		clientConfig = mock(ClientConfig.class);
		PrivateAccessor.setField(mapAPIScheduler, "clientConfig", clientConfig);
	}

	@Test
	void testActivate() {
		Mockito.when(mapAPISchedulerConfig.schedulerName()).thenReturn("Map Scheduler");
		Mockito.when(mapAPISchedulerConfig.cronExpression()).thenReturn("0 0/5 * 1/1 * ? *");
		Mockito.when(mapAPISchedulerConfig.isMapAPISchedulerEnabled()).thenReturn(true);
		Mockito.when(scheduler.EXPR("0 0/5 * 1/1 * ? *")).thenReturn(scheduleOptions);
		mapAPIScheduler.activate(mapAPISchedulerConfig);

		assertNotNull(mapAPIScheduler);
	}

	@Test
	void testDeactivate() {
		mapAPIScheduler.deactivate(mapAPISchedulerConfig);

		assertNotNull(mapAPIScheduler);
	}

	@Test
	void testRun()
			throws ClientProtocolException, IOException, UnsupportedRepositoryOperationException, RepositoryException {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			mapAPIScheduler.run();

			InputStream stream = new ByteArrayInputStream(text.getBytes());
			testActivate();
			Mockito.when(globalConfig.postRequestToken()).thenReturn("token");
			Mockito.when(globalConfig.grantType()).thenReturn("grantType");
			Mockito.when(globalConfig.clientId()).thenReturn("clientId");
			Mockito.when(globalConfig.clientSecret()).thenReturn("clientSecret");
			Mockito.when(globalConfig.username()).thenReturn("username");
			Mockito.when(globalConfig.password()).thenReturn("password");
			Mockito.when(globalConfig.contentType()).thenReturn("contentType");
			Mockito.when(CommonUtils.getResourceResolver(resolverFactory, Constants.WRITERSERVICEUSER))
					.thenReturn(resolver);
			Mockito.when(resolver.getResource("/content/dam/vida/config/City_Master.csv")).thenReturn(assetResource);
			Mockito.when(assetResource.adaptTo(Asset.class)).thenReturn(asset);
			Mockito.when(globalConfig.nearByRequestUrl()).thenReturn("nearByRequestUrl");
			Mockito.when(asset.getOriginal()).thenReturn(null);
			Mockito.when(globalConfig.cityMasterCSVLocationPath()).thenReturn(null);
			mapAPIScheduler.run();

			Mockito.when(globalConfig.cityMasterCSVLocationPath()).thenReturn("CityPath");
			Mockito.when(resolver.getResource("CityPath")).thenReturn(assetResource);
			Mockito.when(assetResource.adaptTo(Asset.class)).thenReturn(asset);
			Mockito.when(globalConfig.nearByRequestUrl()).thenReturn("nearByRequestUrl");
			Mockito.when(asset.getOriginal()).thenReturn(original);
			Mockito.when(original.getStream()).thenReturn(stream);
			Mockito.when(HttpClients.createDefault()).thenReturn(httpClient);
			Mockito.when(httpClient.execute(Mockito.any(HttpGet.class))).thenReturn(httpResponse);
			Mockito.when(httpResponse.getEntity()).thenReturn(entity);
			Mockito.when(globalConfig.electrifyEndPoint()).thenReturn("electrifyEndPoint");
			Mockito.when(clientConfig.client()).thenReturn(httpClient1);
			Mockito.when(httpClient1.execute(Mockito.any(HttpPost.class))).thenReturn(httpResponse1);
			Mockito.when(globalConfig.defaultRadius()).thenReturn("50");
			Mockito.when(globalConfig.organizationId()).thenReturn("1");
			Mockito.when(httpResponse1.getEntity()).thenReturn(entity1);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"[{\"attributes\":{\"type\":\"dmpl__Branch__c\",\"url\":\"/services/data/v52.0/sobjects/dmpl__Branch__c/a0K9C000000UpcaUAC\"},\"Id\":\"a0K9C000000UpcaUAC\",\"Name\":\"RAGHUVIR MOTOR AGENCIES P LTD\",\"dmpl__AllowInventory__c\":false,\"dmpl__AllowPurchase__c\":false,\"dmpl__AllowSales__c\":true,\"dmpl__AllowService__c\":true,\"dmpl__BranchType__c\":\"Experience Centre\",\"dmpl__BusinessHoursId__c\":\"01m5h00000090P9AAI\",\"dmpl__IsActive__c\":true,\"dmpl__IsHomeDeliveryAvailable__c\":true,\"dmpl__IsPickupDropAvailable__c\":false,\"dmpl__MobileNumber__c\":7588534236,\"dmpl__Phone__c\":\"0240-2363711\",\"dmpl__PartnerAccountId__c\":\"0019C000003ZMR8QAO\",\"dmpl__AddressId__c\":\"a0c9C000008kXuGQAU\",\"dmpl__GeoLocation__c\":{\"latitude\":19.87303178,\"longitude\":75.31521934},\"TestRideStartDate__c\":\"2023-06-22\",\"dmpl__BusinessHoursId__r\":{\"attributes\":{\"type\":\"BusinessHours\",\"url\":\"/services/data/v52.0/sobjects/BusinessHours/01m5h00000090P9AAI\"},\"Id\":\"01m5h00000090P9AAI\",\"FridayEndTime\":\"00:00:00.000Z\",\"FridayStartTime\":\"00:00:00.000Z\",\"IsActive\":true,\"IsDefault\":true,\"MondayEndTime\":\"00:00:00.000Z\",\"MondayStartTime\":\"00:00:00.000Z\",\"Name\":\"Default\",\"SaturdayEndTime\":\"00:00:00.000Z\",\"SaturdayStartTime\":\"00:00:00.000Z\",\"SundayEndTime\":\"00:00:00.000Z\",\"SundayStartTime\":\"00:00:00.000Z\",\"ThursdayEndTime\":\"00:00:00.000Z\",\"ThursdayStartTime\":\"00:00:00.000Z\",\"TimeZoneSidKey\":\"Asia/Colombo\",\"TuesdayEndTime\":\"00:00:00.000Z\",\"TuesdayStartTime\":\"00:00:00.000Z\",\"WednesdayEndTime\":\"00:00:00.000Z\",\"WednesdayStartTime\":\"00:00:00.000Z\"},\"dmpl__AddressId__r\":{\"attributes\":{\"type\":\"dmpl__ContactAddress__c\",\"url\":\"/services/data/v52.0/sobjects/dmpl__ContactAddress__c/a0c9C000008kXuGQAU\"},\"Id\":\"a0c9C000008kXuGQAU\",\"dmpl__City__c\":\"AURANGABAD\",\"dmpl__State__c\":\"MAHARASHTRA\",\"dmpl__Country__c\":\"India\",\"dmpl__PostalCode__c\":\"431005\",\"dmpl__Street__c\":\"Raghuvir motor agencies Pvt ltd, adalat rd opp district court\"},\"ResponseCode__c\":\"002\"},{\"attributes\":{\"type\":\"dmpl__Branch__c\",\"url\":\"/services/data/v52.0/sobjects/dmpl__Branch__c/a0K9C000000UplJUAS\"},\"Id\":\"a0K9C000000UplJUAS\",\"Name\":\"WH - RAGHUVIR MOTOR AGENCIES P LTD\",\"dmpl__AllowInventory__c\":true,\"dmpl__AllowPurchase__c\":false,\"dmpl__AllowSales__c\":true,\"dmpl__AllowService__c\":false,\"dmpl__BranchType__c\":\"Warehouse\",\"dmpl__BusinessHoursId__c\":\"01m5h00000090P9AAI\",\"dmpl__IsActive__c\":true,\"dmpl__IsHomeDeliveryAvailable__c\":false,\"dmpl__IsPickupDropAvailable__c\":false,\"dmpl__MobileNumber__c\":9960059880,\"dmpl__Phone__c\":\"0240-2363711\",\"dmpl__PartnerAccountId__c\":\"0019C000003ZMR8QAO\",\"dmpl__AddressId__c\":\"a0c9C000008kYZIQA2\",\"dmpl__GeoLocation__c\":{\"latitude\":19.87135481197249,\"longitude\":75.31537035223441},\"dmpl__BusinessHoursId__r\":{\"attributes\":{\"type\":\"BusinessHours\",\"url\":\"/services/data/v52.0/sobjects/BusinessHours/01m5h00000090P9AAI\"},\"Id\":\"01m5h00000090P9AAI\",\"FridayEndTime\":\"00:00:00.000Z\",\"FridayStartTime\":\"00:00:00.000Z\",\"IsActive\":true,\"IsDefault\":true,\"MondayEndTime\":\"00:00:00.000Z\",\"MondayStartTime\":\"00:00:00.000Z\",\"Name\":\"Default\",\"SaturdayEndTime\":\"00:00:00.000Z\",\"SaturdayStartTime\":\"00:00:00.000Z\",\"SundayEndTime\":\"00:00:00.000Z\",\"SundayStartTime\":\"00:00:00.000Z\",\"ThursdayEndTime\":\"00:00:00.000Z\",\"ThursdayStartTime\":\"00:00:00.000Z\",\"TimeZoneSidKey\":\"Asia/Colombo\",\"TuesdayEndTime\":\"00:00:00.000Z\",\"TuesdayStartTime\":\"00:00:00.000Z\",\"WednesdayEndTime\":\"00:00:00.000Z\",\"WednesdayStartTime\":\"00:00:00.000Z\"},\"dmpl__AddressId__r\":{\"attributes\":{\"type\":\"dmpl__ContactAddress__c\",\"url\":\"/services/data/v52.0/sobjects/dmpl__ContactAddress__c/a0c9C000008kYZIQA2\"},\"Id\":\"a0c9C000008kYZIQA2\",\"dmpl__City__c\":\"AURANGABAD\",\"dmpl__State__c\":\"MAHARASHTRA\",\"dmpl__Country__c\":\"India\",\"dmpl__PostalCode__c\":\"431005\",\"dmpl__Street__c\":\"Raghuvir motor agencies Pvt ltd, adalat rd opp district court\"},\"ResponseCode__c\":\"002\"},{\"attributes\":{\"type\":\"dmpl__Branch__c\",\"url\":\"/services/data/v52.0/sobjects/dmpl__Branch__c/a0K9C000000UpxTUAS\"},\"Id\":\"a0K9C000000UpxTUAS\",\"Name\":\"DHANLAXMI MOTORS\",\"dmpl__AllowInventory__c\":false,\"dmpl__AllowPurchase__c\":false,\"dmpl__AllowSales__c\":true,\"dmpl__AllowService__c\":false,\"dmpl__BranchType__c\":\"Experience Centre\",\"dmpl__BusinessHoursId__c\":\"01m5h00000090P9AAI\",\"dmpl__IsActive__c\":true,\"dmpl__IsHomeDeliveryAvailable__c\":true,\"dmpl__IsPickupDropAvailable__c\":false,\"dmpl__MobileNumber__c\":9762106210,\"dmpl__Phone__c\":\"8888876601\",\"dmpl__PartnerAccountId__c\":\"0019C000003ZOGWQA4\",\"dmpl__AddressId__c\":\"a0c9C000008kZhBQAU\",\"dmpl__GeoLocation__c\":{\"latitude\":19.83968201,\"longitude\":75.24688575},\"TestRideStartDate__c\":\"2023-06-22\",\"dmpl__BusinessHoursId__r\":{\"attributes\":{\"type\":\"BusinessHours\",\"url\":\"/services/data/v52.0/sobjects/BusinessHours/01m5h00000090P9AAI\"},\"Id\":\"01m5h00000090P9AAI\",\"FridayEndTime\":\"00:00:00.000Z\",\"FridayStartTime\":\"00:00:00.000Z\",\"IsActive\":true,\"IsDefault\":true,\"MondayEndTime\":\"00:00:00.000Z\",\"MondayStartTime\":\"00:00:00.000Z\",\"Name\":\"Default\",\"SaturdayEndTime\":\"00:00:00.000Z\",\"SaturdayStartTime\":\"00:00:00.000Z\",\"SundayEndTime\":\"00:00:00.000Z\",\"SundayStartTime\":\"00:00:00.000Z\",\"ThursdayEndTime\":\"00:00:00.000Z\",\"ThursdayStartTime\":\"00:00:00.000Z\",\"TimeZoneSidKey\":\"Asia/Colombo\",\"TuesdayEndTime\":\"00:00:00.000Z\",\"TuesdayStartTime\":\"00:00:00.000Z\",\"WednesdayEndTime\":\"00:00:00.000Z\",\"WednesdayStartTime\":\"00:00:00.000Z\"},\"dmpl__AddressId__r\":{\"attributes\":{\"type\":\"dmpl__ContactAddress__c\",\"url\":\"/services/data/v52.0/sobjects/dmpl__ContactAddress__c/a0c9C000008kZhBQAU\"},\"Id\":\"a0c9C000008kZhBQAU\",\"dmpl__City__c\":\"AURANGABAD\",\"dmpl__State__c\":\"MAHARASHTRA\",\"dmpl__Country__c\":\"India\",\"dmpl__PostalCode__c\":\"431001\",\"dmpl__Street__c\":\"P.No.-5,Gut No.-3,Opp-Master Cook Hotel,Beed By Pass\"},\"ResponseCode__c\":\"002\"},{\"attributes\":{\"type\":\"dmpl__Branch__c\",\"url\":\"/services/data/v52.0/sobjects/dmpl__Branch__c/a0K9C000000UpxYUAS\"},\"Id\":\"a0K9C000000UpxYUAS\",\"Name\":\"WH - DHANLAXMI MOTORS\",\"dmpl__AllowInventory__c\":true,\"dmpl__AllowPurchase__c\":false,\"dmpl__AllowSales__c\":true,\"dmpl__AllowService__c\":false,\"dmpl__BranchType__c\":\"Warehouse\",\"dmpl__BusinessHoursId__c\":\"01m5h00000090P9AAI\",\"dmpl__IsActive__c\":true,\"dmpl__IsHomeDeliveryAvailable__c\":false,\"dmpl__IsPickupDropAvailable__c\":false,\"dmpl__MobileNumber__c\":8888928385,\"dmpl__Phone__c\":\"8888928385\",\"dmpl__PartnerAccountId__c\":\"0019C000003ZOGWQA4\",\"dmpl__AddressId__c\":\"a0c9C000008kZiAQAU\",\"dmpl__GeoLocation__c\":{\"latitude\":19.85160172502036,\"longitude\":75.32996594398345},\"dmpl__BusinessHoursId__r\":{\"attributes\":{\"type\":\"BusinessHours\",\"url\":\"/services/data/v52.0/sobjects/BusinessHours/01m5h00000090P9AAI\"},\"Id\":\"01m5h00000090P9AAI\",\"FridayEndTime\":\"00:00:00.000Z\",\"FridayStartTime\":\"00:00:00.000Z\",\"IsActive\":true,\"IsDefault\":true,\"MondayEndTime\":\"00:00:00.000Z\",\"MondayStartTime\":\"00:00:00.000Z\",\"Name\":\"Default\",\"SaturdayEndTime\":\"00:00:00.000Z\",\"SaturdayStartTime\":\"00:00:00.000Z\",\"SundayEndTime\":\"00:00:00.000Z\",\"SundayStartTime\":\"00:00:00.000Z\",\"ThursdayEndTime\":\"00:00:00.000Z\",\"ThursdayStartTime\":\"00:00:00.000Z\",\"TimeZoneSidKey\":\"Asia/Colombo\",\"TuesdayEndTime\":\"00:00:00.000Z\",\"TuesdayStartTime\":\"00:00:00.000Z\",\"WednesdayEndTime\":\"00:00:00.000Z\",\"WednesdayStartTime\":\"00:00:00.000Z\"},\"dmpl__AddressId__r\":{\"attributes\":{\"type\":\"dmpl__ContactAddress__c\",\"url\":\"/services/data/v52.0/sobjects/dmpl__ContactAddress__c/a0c9C000008kZiAQAU\"},\"Id\":\"a0c9C000008kZiAQAU\",\"dmpl__City__c\":\"AURANGABAD\",\"dmpl__State__c\":\"MAHARASHTRA\",\"dmpl__Country__c\":\"India\",\"dmpl__PostalCode__c\":\"431001\",\"dmpl__Street__c\":\"P.No.-5,Gut No.-3,Opp-Master Cook Hotel,Beed By Pass\"},\"ResponseCode__c\":\"002\"}]");
			Mockito.when(EntityUtils.toString(entity1)).thenReturn(
					"{\"access_token\":\"00D5h000004zDWU!AQEAQACaYxWAqNHE4L35go7ZSeD23TxdoE73_rLFrW8yAHaBdtvcUb7jB3312.ow2qPas6Ud9.4VwrpEz5Ybee1of8mQhqTv\",\"instance_url\":\"https://vidaworld.my.salesforce.com\",\"id\":\"https://login.salesforce.com/id/00D5h000004zDWUEA2/0055h000006CcsGAAS\",\"token_type\":\"Bearer\",\"issued_at\":\"1690363697815\",\"signature\":\"ufQFGoXyWmL2iwYS3OU9h0VmYR4vKsVD0UJ5P5LrdHo=\"}",
					"[{\r\n" + "	\"attributes\": {\r\n" + "		\"type\": \"dmpl__Branch__c\",\r\n"
							+ "		\"url\": \"/services/data/v52.0/sobjects/dmpl__Branch__c/a0K9C000000UpcaUAC\"\r\n"
							+ "	},\r\n" + "	\"Id\": \"a0K9C000000UpcaUAC\",\r\n"
							+ "	\"ChargingStationId\": \"RAGHUVIR MOTOR AGENCIES P LTD\",\r\n"
							+ "	\"ChargingStationName\": \"ChargingStationName\",\r\n"
							+ "	\"ChargingStationLatitude\": \"19.87303178\",\r\n"
							+ "	\"ChargingStationLongitude\": \"75.31521934\",\r\n"
							+ "	\"dmpl__AllowService__c\": false,\r\n"
							+ "	\"dmpl__BranchType__c\": \"Experience Centre\",\r\n"
							+ "	\"dmpl__BusinessHoursId__c\": \"01m5h00000090P9AAI\",\r\n"
							+ "	\"dmpl__IsActive__c\": true,\r\n" + "	\"dmpl__IsHomeDeliveryAvailable__c\": true,\r\n"
							+ "	\"dmpl__IsPickupDropAvailable__c\": false,\r\n"
							+ "	\"dmpl__MobileNumber__c\": 7588534236,\r\n"
							+ "	\"dmpl__Phone__c\": \"0240-2363711\",\r\n"
							+ "	\"dmpl__PartnerAccountId__c\": \"0019C000003ZMR8QAO\",\r\n"
							+ "	\"dmpl__AddressId__c\": \"a0c9C000008kXuGQAU\",\r\n"
							+ "	\"ChargingStationAddress\": \"ChargingStationAddress\"\r\n" + "}]");
			Mockito.when(resolver.adaptTo(AssetManager.class)).thenReturn(assetManager);
			Mockito.when(resolver.adaptTo(Session.class)).thenReturn(session);
			Mockito.when(session.getValueFactory()).thenReturn(factory);
			mapAPIScheduler.run();
		});
	}

}
