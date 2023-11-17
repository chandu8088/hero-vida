/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;

import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for ProductInfoModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class ProductInfoModelTest {

	@InjectMocks
	private ProductInfoModel productInfoModel;

	@Mock
	private Resource resource;

	@Mock
	private ResourceResolver resourceResolver;

	@Mock
	private Resource assetResource;

	@Mock
	private Asset asset;

	@Mock
	private Rendition original;

	@Mock
	private Resource assetResource1;

	@Mock
	private Asset asset1;

	@Mock
	private Rendition original1;

	private String text = "{\r\n" + "	\"items\": [{\r\n" + "		\"name\": \"V1 PRO\",\r\n"
			+ "		\"sku\": \"V1PRASBRCEL\",\r\n" + "		\"sf_id\": \"a1Gp0000000FaIzEAK\",\r\n"
			+ "		\"__typename\": \"ConfigurableProduct\",\r\n" + "		\"variants\": [{\r\n"
			+ "			\"__typename\": \"SimpleProduct\",\r\n"
			+ "			\"name\": \"VIDA V1 PRO SC 8P RMV MAT PEARL WHITE\",\r\n"
			+ "			\"sku\": \"V1PRASBRCELMWT\",\r\n" + "			\"range\": \"165 km\",\r\n"
			+ "			\"charging_time\": \"65 min\",\r\n" + "			\"accelerator\": \"3.94 sec\",\r\n"
			+ "			\"top_speed\": \"80 kmph\",\r\n" + "			\"color\": \"White\",\r\n"
			+ "			\"description\": \"VIDA V1 PRO SC 8P RMV MAT PEARL WHITE\",\r\n"
			+ "			\"weight\": \"115.0\",\r\n" + "			\"ridingModes\": \"Eco, Ride, Sports, Custom\",\r\n"
			+ "			\"sf_id\": \"a1mp0000000EsOQAA0\",\r\n" + "			\"seatingType\": \"3.94 kWh\"\r\n"
			+ "		}, {\r\n" + "			\"__typename\": \"SimpleProduct\",\r\n"
			+ "			\"name\": \"VIDA V1 PRO SC 8P RMV MAT SPORTS RED\",\r\n"
			+ "			\"sku\": \"V1PRASBRCELMSR\",\r\n" + "			\"range\": \"165 km\",\r\n"
			+ "			\"charging_time\": \"65 min\",\r\n" + "			\"accelerator\": \"3.5 sec\",\r\n"
			+ "			\"top_speed\": \"80 kmph\",\r\n" + "			\"color\": \"Red\",\r\n"
			+ "			\"description\": \"VIDA V1 PRO SC 8P RMV MAT SPORTS RED\",\r\n"
			+ "			\"weight\": \"275.0\",\r\n" + "			\"ridingModes\": \"Eco, Ride, Sports, Custom\",\r\n"
			+ "			\"sf_id\": \"a1mp0000000EsORAA0\",\r\n" + "			\"seatingType\": \"3.94 kWh\"\r\n"
			+ "		}, {\r\n" + "			\"__typename\": \"SimpleProduct\",\r\n"
			+ "			\"name\": \"VIDA V1 PRO SC 8P RMV MAT ABRAX ORANGE\",\r\n"
			+ "			\"sku\": \"V1PRASBRCELMAO\",\r\n" + "			\"range\": \"165 km\",\r\n"
			+ "			\"charging_time\": \"65 min\",\r\n" + "			\"accelerator\": \"3.5 sec\",\r\n"
			+ "			\"top_speed\": \"80 kmph\",\r\n" + "			\"color\": \"Orange\",\r\n"
			+ "			\"description\": \"VIDA V1 PRO SC 8P RMV MAT ABRAX ORANGE\",\r\n"
			+ "			\"weight\": \"275.0\",\r\n" + "			\"ridingModes\": \"Eco, Ride, Sports, Custom\",\r\n"
			+ "			\"sf_id\": \"a1mp0000000EsOTAA0\",\r\n" + "			\"seatingType\": \"3.94 kWh\"\r\n"
			+ "		}]\r\n" + "	}]\r\n" + "}";

	private String text1 = "{\r\n" + "	\"items\": [{\r\n" + "		\"name\": \"V1 PRO\",\r\n"
			+ "		\"sku\": \"V1PRASBRCEL\",\r\n" + "		\"sf_id\": \"a1W5h0000000gO5EAI\",\r\n"
			+ "		\"__typename\": \"ConfigurableProduct\",\r\n" + "		\"variants\": [{\r\n"
			+ "			\"__typename\": \"SimpleProduct\",\r\n"
			+ "			\"name\": \"VIDA V1 PRO SC 8P RMV MAT PEARL WHITE\",\r\n"
			+ "			\"sku\": \"V1PRASBRCELMWT\",\r\n" + "			\"range\": \"110 km\",\r\n"
			+ "			\"charging_time\": \"65 min\",\r\n" + "			\"accelerator\": \"3.2 sec\",\r\n"
			+ "			\"top_speed\": \"80 kmph\",\r\n" + "			\"color\": \"White\",\r\n"
			+ "			\"description\": \"VIDA V1 PRO SC 8P RMV MAT PEARL WHITE\",\r\n"
			+ "			\"weight\": \"275.0\",\r\n" + "			\"ridingModes\": \"Eco, Ride, Sports, Custom\",\r\n"
			+ "			\"sf_id\": \"a245h000000EPryAAG\",\r\n" + "			\"seatingType\": \"3.94 kWh\"\r\n"
			+ "		}, {\r\n" + "			\"__typename\": \"SimpleProduct\",\r\n"
			+ "			\"name\": \"VIDA V1 PRO SC 8P RMV MAT SPORTS RED\",\r\n"
			+ "			\"sku\": \"V1PRASBRCELMSR\",\r\n" + "			\"range\": \"110 km\",\r\n"
			+ "			\"charging_time\": \"65 min\",\r\n" + "			\"accelerator\": \"3.2 sec\",\r\n"
			+ "			\"top_speed\": \"80 kmph\",\r\n" + "			\"color\": \"Red\",\r\n"
			+ "			\"description\": \"VIDA V1 PRO SC 8P RMV MAT SPORTS RED\",\r\n"
			+ "			\"weight\": \"275.0\",\r\n" + "			\"ridingModes\": \"Eco, Ride, Sports, Custom\",\r\n"
			+ "			\"sf_id\": \"a245h000000EPrzAAG\",\r\n" + "			\"seatingType\": \"3.94 kWh\"\r\n"
			+ "		}, {\r\n" + "			\"__typename\": \"SimpleProduct\",\r\n"
			+ "			\"name\": \"VIDA V1 PRO SC 8P RMV MAT ABRAX ORANGE\",\r\n"
			+ "			\"sku\": \"V1PRASBRCELMAO\",\r\n" + "			\"range\": \"110 km\",\r\n"
			+ "			\"charging_time\": \"65 min\",\r\n" + "			\"accelerator\": \"3.2 sec\",\r\n"
			+ "			\"top_speed\": \"80 kmph\",\r\n" + "			\"color\": \"Orange\",\r\n"
			+ "			\"description\": \"VIDA V1 PRO SC 8P RMV MAT ABRAX ORANGE\",\r\n"
			+ "			\"weight\": \"275.0\",\r\n" + "			\"ridingModes\": \"Eco, Ride, Sports, Custom\",\r\n"
			+ "			\"sf_id\": \"a245h000000EPs0AAG\",\r\n" + "			\"seatingType\": \"3.94 kWh\"\r\n"
			+ "		}, {\r\n" + "			\"__typename\": \"SimpleProduct\",\r\n"
			+ "			\"name\": \"VIDA V1 PRO SC 8P RMV  MATT CYAN BLUE\",\r\n"
			+ "			\"sku\": \"V1PRASBRCELMCB\",\r\n" + "			\"range\": \"110 km\",\r\n"
			+ "			\"charging_time\": \"65 min\",\r\n" + "			\"accelerator\": \"3.2 sec\",\r\n"
			+ "			\"top_speed\": \"80 kmph\",\r\n" + "			\"color\": \"Blue\",\r\n"
			+ "			\"description\": \"VIDA V1 PRO SC 8P RMV  MATT CYAN BLUE\",\r\n"
			+ "			\"weight\": \"275.0\",\r\n" + "			\"ridingModes\": \"Eco, Ride, Sports, Custom\",\r\n"
			+ "			\"sf_id\": \"a249C0000000FAuQAM\",\r\n" + "			\"seatingType\": \"3.94 kWh\"\r\n"
			+ "		}, {\r\n" + "			\"__typename\": \"SimpleProduct\",\r\n"
			+ "			\"name\": \"VIDA V1 PRO SC 8P RMV BLACK\",\r\n" + "			\"sku\": \"V1PRASBRCELBLK\",\r\n"
			+ "			\"range\": \"110 km\",\r\n" + "			\"charging_time\": \"65 min\",\r\n"
			+ "			\"accelerator\": \"3.2 sec\",\r\n" + "			\"top_speed\": \"80 kmph\",\r\n"
			+ "			\"color\": \"Black\",\r\n" + "			\"description\": \"VIDA V1 PRO SC 8P RMV BLACK\",\r\n"
			+ "			\"weight\": \"275.0\",\r\n" + "			\"ridingModes\": \"Eco, Ride, Sports, Custom\",\r\n"
			+ "			\"sf_id\": \"a249C0000000FB4QAM\",\r\n" + "			\"seatingType\": \"3.94 kWh\"\r\n"
			+ "		}]\r\n" + "	}, {\r\n" + "		\"name\": \"V1 PLUS\",\r\n" + "		\"sku\": \"V1PLASARCEL\",\r\n"
			+ "		\"sf_id\": \"a1W5h0000000gO4EAI\",\r\n" + "		\"__typename\": \"ConfigurableProduct\",\r\n"
			+ "		\"variants\": [{\r\n" + "			\"__typename\": \"SimpleProduct\",\r\n"
			+ "			\"name\": \"VIDA V1 PLUS SC 7P RMV MAT PEARL WHITE\",\r\n"
			+ "			\"sku\": \"V1PLASARCELMWT\",\r\n" + "			\"range\": \"100 km\",\r\n"
			+ "			\"charging_time\": \"65 min\",\r\n" + "			\"accelerator\": \"3.4 sec\",\r\n"
			+ "			\"top_speed\": \"80 kmph\",\r\n" + "			\"color\": \"White\",\r\n"
			+ "			\"description\": \"VIDA V1 PLUS SC 7P RMV MAT PEARL WHITE\",\r\n"
			+ "			\"weight\": \"274.0\",\r\n" + "			\"ridingModes\": \"Eco, Ride, Sports, Custom\",\r\n"
			+ "			\"sf_id\": \"a245h000000EPs1AAG\",\r\n" + "			\"seatingType\": \"3.44 kWh\"\r\n"
			+ "		}, {\r\n" + "			\"__typename\": \"SimpleProduct\",\r\n"
			+ "			\"name\": \"VIDA V1 PLUS SC 7P RMV MAT SPORTS RED\",\r\n"
			+ "			\"sku\": \"V1PLASARCELMSR\",\r\n" + "			\"range\": \"100 km\",\r\n"
			+ "			\"charging_time\": \"65 min\",\r\n" + "			\"accelerator\": \"3.4 sec\",\r\n"
			+ "			\"top_speed\": \"80 kmph\",\r\n" + "			\"color\": \"Red\",\r\n"
			+ "			\"description\": \"VIDA V1 PLUS SC 7P RMV MAT SPORTS RED\",\r\n"
			+ "			\"weight\": \"274.0\",\r\n" + "			\"ridingModes\": \"Eco, Ride, Sports, Custom\",\r\n"
			+ "			\"sf_id\": \"a245h000000EPt6AAG\",\r\n" + "			\"seatingType\": \"3.44 kWh\"\r\n"
			+ "		}, {\r\n" + "			\"__typename\": \"SimpleProduct\",\r\n"
			+ "			\"name\": \"VIDA V1 PLUS SC 7P RMV MAT ABRAX ORANGE\",\r\n"
			+ "			\"sku\": \"V1PLASARCELMAO\",\r\n" + "			\"range\": \"100 km\",\r\n"
			+ "			\"charging_time\": \"65 min\",\r\n" + "			\"accelerator\": \"3.4 sec\",\r\n"
			+ "			\"top_speed\": \"80 kmph\",\r\n" + "			\"color\": \"Orange\",\r\n"
			+ "			\"description\": \"VIDA V1 PLUS SC 7P RMV MAT ABRAX ORANGE\",\r\n"
			+ "			\"weight\": \"274.0\",\r\n" + "			\"ridingModes\": \"Eco, Ride, Sports, Custom\",\r\n"
			+ "			\"sf_id\": \"a249C0000000DNmQAM\",\r\n" + "			\"seatingType\": \"3.44 kWh\"\r\n"
			+ "		}]\r\n" + "	}]\r\n" + "}";

	private String text2 = "[{\r\n" + "		\"item_sf_id\": \"a1W5h0000000gO5EAI\",\r\n"
			+ "		\"item_sku\": \"V1PRASBRCEL\",\r\n" + "		\"variant_sf_id\": \"a245h000000EPryAAG\",\r\n"
			+ "		\"variant_name\": \"VIDA V1 PRO SC 8P RMV MAT PEARL WHITE\",\r\n"
			+ "		\"item_name\": \"V1 PRO\",\r\n" + "		\"city_state_id\": \"AHMEDABAD~GUJARAT~INDIA\",\r\n"
			+ "		\"variant_sku\": \"V1PRASBRCELMWT\",\r\n" + "		\"onRoadPrice\": \"145900\",\r\n"
			+ "		\"exShowRoomPrice\": \"145900\",\r\n" + "		\"price\": \"160366.67\",\r\n"
			+ "		\"minLeaseEMI\": \"\",\r\n" + "		\"minLeaseDownPayment\": \"\",\r\n"
			+ "		\"minLoanDonpayment\": \"\",\r\n" + "		\"minLoanEMI\": \"\",\r\n"
			+ "		\"leaseOfferRL\": \"https://staging-portal.autovertplug.com/lease-plans/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjaXR5IjoiQUhNRURBQkFEIiwiYXNzZXRzIjpbeyJza3UiOiJWMVBSQVNCUkNFTE1XVCIsImRpc3BsYXlfbmFtZSI6IlZpZGEiLCJleF9zaG93cm9vbV9wcmljZSI6IjE0NTkwMCIsIm9uX3JvYWRfcHJpY2UiOiIxNDU5MDAiLCJyb2FkX3RheCI6IjAiLCJmYW1lX3N1YnNpZHkiOjIyNDg1LCJzdGF0ZV9zdWJzaWR5IjoyMDAwMCwic21hcnRfY2FyZF9mZWUiOiIwIiwicnRvX3JlZ2lzdHJhdGlvbl9mZWUiOiIwIiwiZGlzY291bnQiOiIwIiwiY29tcHJlaGVuc2l2ZV9pbnN1cmFuY2VfcHJpY2UiOiIwIiwiYWRkb25faW5zdXJhbmNlX3ByaWNlIjoiMCIsIm5vbl92ZWhpY2xlX2Ftb3VudCI6IjAiLCJhY2Nlc3NvcmllcyI6W10sInByaWNpbmdfbm90ZXMiOiJJbm51YWdyYWwgT2ZmZXJzIn1dLCJzaGlwdG9fY2l0eSI6IkFITUVEQUJBRCJ9.M27L2K1oRAwV0v482C2T_D7CA1i1GFLBpln7zeZdYHg\",\r\n"
			+ "		\"loanOfferURL\": \"https://staging-portal-v2.autovertplug.com/calculator/d11e8e5c5ea3?ask\\u003deyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjaXR5IjoiQUhNRURBQkFEIiwiYXNzZXRzIjpbeyJza3UiOiJWMVBSQVNCUkNFTE1XVCIsImRpc3BsYXlfbmFtZSI6IlYxIFBSTyIsImV4X3Nob3dyb29tX3ByaWNlIjoiMTQ1OTAwIiwib25fcm9hZF9wcmljZSI6IjE0NTkwMCIsInJvYWRfdGF4IjoiMCIsImZhbWVfc3Vic2lkeSI6MjI0ODUsInN0YXRlX3N1YnNpZHkiOjIwMDAwLCJzbWFydF9jYXJkX2ZlZSI6IjAiLCJydG9fcmVnaXN0cmF0aW9uX2ZlZSI6IjAiLCJkaXNjb3VudCI6IjAiLCJjb21wcmVoZW5zaXZlX2luc3VyYW5jZV9wcmljZSI6IjAiLCJhZGRvbl9pbnN1cmFuY2VfcHJpY2UiOiIwIiwibm9uX3ZlaGljbGVfYW1vdW50IjoiMCIsImFjY2Vzc29yaWVzIjpbW11dLCJwcmljaW5nX25vdGVzIjoiSW5udWFncmFsIE9mZmVycyJ9XSwic2hpcHRvX2NpdHkiOiJBSE1FREFCQUQifQ._7lsGIueU-vVUF2kiQqf5qycvBzLRFQ_dhK44q8X9Ag\\u0026canApply\\u003dfalse\",\r\n"
			+ "		\"effectivePrice\": \"125900\",\r\n" + "		\"portablechargerPrice\": \"20000\",\r\n"
			+ "		\"fame2IncentivePrice\": \"-22485\",\r\n" + "		\"stateSubsidyPrice\": \"-20000\"\r\n"
			+ "	}, {\r\n" + "		\"item_sf_id\": \"a1W5h0000000gO5EAI\",\r\n"
			+ "		\"item_sku\": \"V1PRASBRCEL\",\r\n" + "		\"variant_sf_id\": \"a245h000000EPrzAAG\",\r\n"
			+ "		\"variant_name\": \"VIDA V1 PRO SC 8P RMV MAT SPORTS RED\",\r\n"
			+ "		\"item_name\": \"V1 PRO\",\r\n" + "		\"city_state_id\": \"AHMEDABAD~GUJARAT~INDIA\",\r\n"
			+ "		\"variant_sku\": \"V1PRASBRCELMSR\",\r\n" + "		\"onRoadPrice\": \"145900\",\r\n"
			+ "		\"exShowRoomPrice\": \"145900\",\r\n" + "		\"price\": \"160366.67\",\r\n"
			+ "		\"minLeaseEMI\": \"\",\r\n" + "		\"minLeaseDownPayment\": \"\",\r\n"
			+ "		\"minLoanDonpayment\": \"\",\r\n" + "		\"minLoanEMI\": \"\",\r\n"
			+ "		\"leaseOfferRL\": \"https://staging-portal.autovertplug.com/lease-plans/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjaXR5IjoiQUhNRURBQkFEIiwiYXNzZXRzIjpbeyJza3UiOiJWMVBSQVNCUkNFTE1TUiIsImRpc3BsYXlfbmFtZSI6IlZpZGEiLCJleF9zaG93cm9vbV9wcmljZSI6IjE0NTkwMCIsIm9uX3JvYWRfcHJpY2UiOiIxNDU5MDAiLCJyb2FkX3RheCI6IjAiLCJmYW1lX3N1YnNpZHkiOjIyNDg1LCJzdGF0ZV9zdWJzaWR5IjoyMDAwMCwic21hcnRfY2FyZF9mZWUiOiIwIiwicnRvX3JlZ2lzdHJhdGlvbl9mZWUiOiIwIiwiZGlzY291bnQiOiIwIiwiY29tcHJlaGVuc2l2ZV9pbnN1cmFuY2VfcHJpY2UiOiIwIiwiYWRkb25faW5zdXJhbmNlX3ByaWNlIjoiMCIsIm5vbl92ZWhpY2xlX2Ftb3VudCI6IjAiLCJhY2Nlc3NvcmllcyI6W10sInByaWNpbmdfbm90ZXMiOiJJbm51YWdyYWwgT2ZmZXJzIn1dLCJzaGlwdG9fY2l0eSI6IkFITUVEQUJBRCJ9.QtjiMFeaJC2bz6SL1oLKuBQLtXXO-Mwluqf6AYDEOp4\",\r\n"
			+ "		\"loanOfferURL\": \"https://staging-portal-v2.autovertplug.com/calculator/d11e8e5c5ea3?ask\\u003deyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjaXR5IjoiQUhNRURBQkFEIiwiYXNzZXRzIjpbeyJza3UiOiJWMVBSQVNCUkNFTE1TUiIsImRpc3BsYXlfbmFtZSI6IlYxIFBSTyIsImV4X3Nob3dyb29tX3ByaWNlIjoiMTQ1OTAwIiwib25fcm9hZF9wcmljZSI6IjE0NTkwMCIsInJvYWRfdGF4IjoiMCIsImZhbWVfc3Vic2lkeSI6MjI0ODUsInN0YXRlX3N1YnNpZHkiOjIwMDAwLCJzbWFydF9jYXJkX2ZlZSI6IjAiLCJydG9fcmVnaXN0cmF0aW9uX2ZlZSI6IjAiLCJkaXNjb3VudCI6IjAiLCJjb21wcmVoZW5zaXZlX2luc3VyYW5jZV9wcmljZSI6IjAiLCJhZGRvbl9pbnN1cmFuY2VfcHJpY2UiOiIwIiwibm9uX3ZlaGljbGVfYW1vdW50IjoiMCIsImFjY2Vzc29yaWVzIjpbW11dLCJwcmljaW5nX25vdGVzIjoiSW5udWFncmFsIE9mZmVycyJ9XSwic2hpcHRvX2NpdHkiOiJBSE1FREFCQUQifQ.BEfF1kXPFw9G85fK8SuBZkrilZT_n0R8D1umwkunw04\\u0026canApply\\u003dfalse\",\r\n"
			+ "		\"effectivePrice\": \"125900\",\r\n" + "		\"portablechargerPrice\": \"20000\",\r\n"
			+ "		\"fame2IncentivePrice\": \"-22485\",\r\n" + "		\"stateSubsidyPrice\": \"-20000\"\r\n" + "	}]";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductInfoModel#init()}.
	 */
	@Test
	void testInit() {
		InputStream stream = new ByteArrayInputStream(text.getBytes());
		InputStream stream1 = new ByteArrayInputStream(text1.getBytes());
		InputStream stream2 = new ByteArrayInputStream(text2.getBytes());
		InputStream stream3 = new ByteArrayInputStream(text.getBytes());
		InputStream stream4 = new ByteArrayInputStream(text1.getBytes());
		Mockito.when(resourceResolver.getResource("/content/dam/vida/config/product-master.json"))
				.thenReturn(assetResource);
		Mockito.when(assetResource.adaptTo(Asset.class)).thenReturn(asset);
		productInfoModel.init();

		Mockito.when(asset.getOriginal()).thenReturn(original);
		Mockito.when(original.adaptTo(InputStream.class)).thenReturn(stream, stream1, stream3, stream4);
		Mockito.when(resourceResolver.getResource("/content/dam/vida/config/price-master.json"))
				.thenReturn(assetResource1);
		Mockito.when(assetResource1.adaptTo(Asset.class)).thenReturn(asset1);
		Mockito.when(asset1.getOriginal()).thenReturn(null);
		productInfoModel.init();

		Mockito.when(asset1.getOriginal()).thenReturn(original1);
		Mockito.when(original1.adaptTo(InputStream.class)).thenReturn(stream2);
		productInfoModel.init();
		productInfoModel.getProductMaster();
		productInfoModel.getPriceMaster();
		productInfoModel.getPriceMasterMap();
		productInfoModel.getProductInfoBannerObj();
		productInfoModel.getCitypriceMap();
		productInfoModel.getDisplayCitypriceMap();
		assertNotNull(productInfoModel);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductInfoModel#getProductMaster()}.
	 */
	@Test
	void testGetProductMaster() {
		productInfoModel.getProductMaster();
		assertNotNull(productInfoModel);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductInfoModel#getPriceMaster()}.
	 */
	@Test
	void testGetPriceMaster() {
		productInfoModel.getPriceMaster();
		assertNotNull(productInfoModel);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductInfoModel#getPriceMasterMap()}.
	 */
	@Test
	void testGetPriceMasterMap() {
		productInfoModel.getPriceMasterMap();
		assertNotNull(productInfoModel);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductInfoModel#getProductInfoBannerObj()}.
	 */
	@Test
	void testGetProductInfoBannerObj() {
		productInfoModel.getProductInfoBannerObj();
		assertNotNull(productInfoModel);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductInfoModel#getCity()}.
	 * @throws NoSuchFieldException 
	 */
	@Test
	void testGetCity() throws NoSuchFieldException {
		String city = "State~City";
		PrivateAccessor.setField(productInfoModel, "city", city);
		productInfoModel.getCity();
		assertNotNull(productInfoModel);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductInfoModel#getCitypriceMap()}.
	 */
	@Test
	void testGetCitypriceMap() {
		productInfoModel.getCitypriceMap();
		assertNotNull(productInfoModel);
	}

}
