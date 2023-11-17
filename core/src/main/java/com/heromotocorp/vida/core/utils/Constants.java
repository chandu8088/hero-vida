package com.heromotocorp.vida.core.utils;

import java.util.HashMap;
import java.util.Map;

import com.day.cq.wcm.api.NameConstants;

/**
 * The Class Constants.
 */
public class Constants {

	/**
	 * Instantiates a new constants.
	 */
	public Constants() {
		//throw new IllegalStateException("Constants class");
	}

	/**
	 * The Constant COMA.
	 */
	public static final String COMA = ",";

	/**
	 * The Constant COMA.
	 */
	public static final String COLON = ":";
	/**
	 * The Constant TAB.
	 */
	public static final String TAB = "\t";

	/**
	 * The Constant SPACE.
	 */
	public static final String SPACE = " ";

	/**
	 * The Constant PERCENT_TWENTY.
	 */
	public static final String PERCENT_TWENTY = "%20";

	/**
	 * The Constant DMPL_COUNTRY.
	 */
	public static final String DMPL_COUNTRY = "dmpl__Country__c='";

	/**
	 * The Constant DMPL_STATE.
	 */
	public static final String DMPL_STATE = "'+and+dmpl__State__c+='";

	/**
	 * The Constant DMPL_CITY.
	 */
	public static final String DMPL_CITY = "'+and+dmpl__city__c+='";

	/**
	 * The Constant SINGLE_QUOTE.
	 */
	public static final String SINGLE_QUOTE = "'";

	/**
	 * The Constant NAME.
	 */
	public static final String NAME = "Name";

	/**
	 * The Constant ISACTIVE.
	 */
	public static final String ISACTIVE = "dmpl__IsActive__c";

	/**
	 * The Constant RECORDS.
	 */
	public static final String RECORDS = "records";
	
	/**
	 * The Constant KYC Document values.
	 */
	public static final String VALUES = "values";


	/**
	 * The Constant Label.
	 */
	public static final String LABEL = "label";
	
	/**
	 * The Constant Value.
	 */
	public static final String VALUE = "value";
	
	/**
	 * The Constant GROUP_BY_LOCATION.
	 */
	public static final Object GROUP_BY_LOCATION = "'+group+by+dmpl__city__c+,+dmpl__State__c+,+dmpl__Country__c";

	/**
	 * The Constant COUNTRY.
	 */
	public static final String COUNTRY = "dmpl__Country__c";

	/**
	 * The Constant STATE.
	 */
	public static final String STATE = "dmpl__State__c";

	/**
	 * The Constant CITY.
	 */
	public static final String CITY = "dmpl__City__c";

	/**
	 * The Page Resource Type.
	 */
	public static final String PAGERESOURCETYPE = "vida/components/page";

	/**
	 * The Grant Type.
	 */
	public static final String GRANTTYPE = "grant_type";

	/**
	 * The Client Id.
	 */
	public static final String CLIENTID = "client_id";

	/**
	 * The Client Credential.
	 */
	public static final String CLIENTCRED = "client_credentials";

	/**
	 * The Client Secret.
	 */
	public static final String CLIENTSECRET = "client_secret";

	/**
	 * The Username.
	 */
	public static final String USERNAME = "username";

	/**
	 * The Password.
	 */
	public static final String PSWD = "password";

	/**
	 * The Content Type.
	 */
	public static final String CONTENTTYPE = "Content-Type";

	/**
	 * The Bearer.
	 */
	public static final String BEARER = "Bearer";

	/**
	 * The Basic.
	 */
	public static final String BASIC = "Basic";

	/**
	 * The Access Token.
	 */
	public static final String ACCESSTOKEN = "access_token";

	/**
	 * The Access Token.
	 */
	public static final String AUTHORIZATION = "Authorization";

	/**
	 * The Branches.
	 */
	public static final String BRANCHES = "dmpl__Branches__r";

	/**
	 * The Address.
	 */
	public static final String ADDRESS = "dmpl__AddressId__r";

	/**
	 * The State.
	 */
	public static final String STATEKEY = "state";

	/**
	 * The Cities.
	 */
	public static final String CITIESKEY = "cities";

	/**
	 * The Branch Type.
	 */
	public static final String BRANCHTYPE = "dmpl__BranchType__c";

	/**
	 * The Id.
	 */
	public static final String ID = "Id";

	/**
	 * The Partner Account Id.
	 */
	public static final String PARTNERACCOUNTID = "dmpl__PartnerAccountId__c";
	
	/**
	 * The TestRide Start Date.
	 */
	public static final String TESTRIDESTARTDATE = "TestRideStartDate__c";
	
	/**
	 * The Phone number.
	 */
	public static final String PHONENUMBERC = "dmpl__Phone__c";
	
	/**
	 * The Service Phone number.
	 */
	public static final String SERVICEPHNUMBERC = "dmpl__ServicePhone__c";

	/**
	 * The Postal Code.
	 */
	public static final String POSTALCODE = "dmpl__PostalCode__c";

	/**
	 * The Attributes.
	 */
	public static final String ATTRIBUTES = "attributes";

	/**
	 * The Address_C.
	 */
	public static final String ADDRESS_C = "Address__c";

	/**
	 * The Geo Location.
	 */
	public static final String GEOLOCATION = "dmpl__GeoLocation__c";

	/**
	 * The Geo Latitude.
	 */
	public static final String LATITUDE = "latitude";

	/**
	 * The Geo Longitude.
	 */
	public static final String LONGITUDE = "longitude";

	/**
	 * The Experience Center Name.
	 */
	public static final String EXPERIENCECENTERNAME = "experienceCenterName";
	
	/**
	 * The TestRide Available.
	 */
	public static final String TESTRIDEAVAILABLE = "testRideAvailable";
	
	/**
	 * The Phone Number.
	 */
	public static final String PHONENUMBER = "phonenumber";
	
	/**
	 * The Service Phone Number.
	 */
	public static final String SERVICEPHONENUMBER = "servicephonenumber";

	/**
	 * The Type.
	 */
	public static final String TYPE = "type";

	/**
	 * The Account Partner Id.
	 */
	public static final String ACCOUNTPARTNERIDKEY = "accountpartnerId";

	/**
	 * The Address.
	 */
	public static final String ADDRESSKEY = "address";

	/**
	 * The Postal code.
	 */
	public static final String PINCODE = "postalCode";

	/**
	 * The Vida Reader Service User.
	 */
	public static final String READERSERVICEUSER = "vidareaderservice";

	/**
	 * The Vida Writer Service User.
	 */
	public static final String WRITERSERVICEUSER = "vidawriterservice";
	/**
	 * The Constant EMAIL.
	 */
	public static final String EMAIL = "Email";

	/**
	 * The Constant MOBILEPHONE.
	 */
	public static final String MOBILEPHONE = "MobilePhone";

	/**
	 * The Constant UTMSOURCE.
	 */
	public static final String UTMSOURCE = "utm_source";

	/**
	 * The Constant UTMMEDIUM.
	 */
	public static final String UTMMEDIUM = "utm_medium";

	/**
	 * The Constant UTMCAMPAIGN.
	 */
	public static final String UTMCAMPAIGN = "utm_campaign";

	/**
	 * The Constant UTMADGROUP.
	 */
	public static final String UTMADGROUP = "utm_adgroup";

	/**
	 * The Constant LASTNAME.
	 */
	public static final String LASTNAME = "LastName";

	/**
	 * The Constant FIRSTNAME.
	 */
	public static final String FIRSTNAME = "FirstName";

	/**
	 * The Constant CONF.
	 */
	public static final String CONF = "/conf/";

	/**
	 * The Constant PAGE_TITLE_404.
	 */
	public static final String PAGE_TITLE_404 = "404";

	/**
	 * The Constant DEC_404.
	 */
	public static final String DEC_404 = "Page Not Found";

	/**
	 * The Constant HOME.
	 */
	public static final String HOME = "Home";

	/**
	 * The Constant PATH_SEPEARTOR.
	 */
	public static final String PATH_SEPEARTOR = "/";

	/**
	 * The Constant LANGUAGE_MASTERS.
	 */
	public static final String LANGUAGE_MASTERS = "language-masters";

	/**
	 * The Constant SUPPLIEDNAME.
	 */
	public static final String SUPPLIEDNAME = "SuppliedName";

	/**
	 * The Constant SUPPLIEDCOMPANY.
	 */
	public static final String SUPPLIEDCOMPANY = "SuppliedCompany";

	/**
	 * The Constant SUPPLIEDEMAIL.
	 */
	public static final String SUPPLIEDEMAIL = "SuppliedEmail";

	/**
	 * The Constant SUPPLIEDPHONE.
	 */
	public static final String SUPPLIEDPHONE = "SuppliedPhone";

	/**
	 * The Constant UTMSOURCEC.
	 */
	public static final String UTMSOURCEC = "UTMSource__c";

	/**
	 * The Constant UTMMEDIUMC.
	 */
	public static final String UTMMEDIUMC = "UTMMedium__c";

	/**
	 * The Constant UTMCAMPAIGNC.
	 */
	public static final String UTMCAMPAIGNC = "UTMCampaign__c";

	/**
	 * The Constant UTMADGROUPC.
	 */
	public static final String UTMADGROUPC = "UTMAdgroup__c";

	/**
	 * The Constant STATUS.
	 */
	public static final String STATUS = "Status";

	/**
	 * The Constant ORIGIN.
	 */
	public static final String ORIGIN = "Origin";

	/**
	 * The Constant SUBJECT.
	 */
	public static final String SUBJECT = "Subject";

	/**
	 * The Constant PRIORITY.
	 */
	public static final String PRIORITY = "Priority";

	/**
	 * The Constant ITISSUE.
	 */
	public static final String ITISSUE = "IT Issues/queries";

	/**
	 * The Constant UNASSIGNED.
	 */
	public static final String UNASSIGNED = "Unassigned";

	/**
	 * The Constant WEB.
	 */
	public static final String WEB = "Web";

	/**
	 * The constant THREEMEDIUM.
	 */
	public static final String THREEMEDIUM = "3-Medium";

	/**
	 * The Constant Message.
	 */
	public static final String MESSAGE = "Message";

	/**
	 * The Constant ALLSERVICE.
	 */
	public static final String ALLSERVICE = "dmpl__AllowService__c";

	/**
	 * The Constant ISSERVICECENTER.
	 */
	public static final String ISSERVICECENTER = "isServiceCenter";

	/**
	 * The Constant FILTERkEY.
	 */
	public static final String FILTERkEY = "FilterKey";

	/**
	 * The Constant TWOTHOUSAND.
	 */
	public static final String DEFAULTRADIUS = "50";

	/**
	 * The Constant ORZANIZATIONID.
	 */
	public static final String ORZANIZATIONID = "OrganizationId";

	/**
	 * The Constant TWENTYEIGHT.
	 */
	public static final String TWENTYEIGHT = "28";

	/**
	 * The Constant LATITUDECAPS.
	 */
	public static final String LATITUDECAPS = "Latitude";

	/**
	 * The Constant LONGITUDE.
	 */
	public static final String LONGITUDECAPS = "Longitude";

	/**
	 * The Constant CHARGINGSTATIONID.
	 */
	public static final String CHARGINGSTATIONID = "ChargingStationId";

	/**
	 * The Constant CHARGINGSTATIONNAME.
	 */
	public static final String CHARGINGSTATIONNAME = "ChargingStationName";

	/**
	 * The Constant CHARGING_STATION_ADDRESS.
	 */
	public static final String CHARGING_STATION_ADDRESS = "ChargingStationAddress";

	/**
	 * The Constant CHARGING_STATION_LATITUDE.
	 */
	public static final String CHARGING_STATION_LATITUDE = "ChargingStationLatitude";

	/**
	 * The Constant CHARGING_STATION_LONGITUDE.
	 */
	public static final String CHARGING_STATION_LONGITUDE = "ChargingStationLongitude";

	/**
	 * The Constant ITEMS.
	 */
	public static final String ITEMS = "items";

	/**
	 * The Constant VIDEOID.
	 */
	public static final String VIDEOID = "videoId";

	/**
	 * The Constant SNIPPET.
	 */
	public static final String SNIPPET = "snippet";

	/**
	 * The Constant TITLE.
	 */
	public static final String TITLE = "title";

	/**
	 * The Constant DESCRIPTION.
	 */
	public static final String DESCRIPTION = "description";

	/**
	 * The Constant YOUTUBEURL.
	 */
	public static final String YOUTUBEURL = "https://www.youtube.com/embed/";

	/**
	 * The Constant DATA.
	 */
	public static final String DATA = "data";

	/**
	 * The Constant MEDIAURL.
	 */
	public static final String MEDIAURL = "media_url";

	/**
	 * The Constant TEXT.
	 */
	public static final String TEXT = "text";

	/**
	 * The Constant ENTITIES.
	 */
	public static final String ENTITIES = "entities";

	/**
	 * The Constant URLS.
	 */
	public static final String URLS = "urls";

	/**
	 * The Constant URL.
	 */
	public static final String URL = "url";

	/**
	 * The Constant PERMALINK.
	 */
	public static final String PERMALINK = "permalink";

	public static final String DMPL_ITEM_CODE_C = "dmpl__ItemCode__c";

	public static final String DMPL_SKU_CODE_C = "dmpl__SKUCode__c";

	public static final String CHARGING_TIME_C = "ChargingTime__c";

	public static final String RANGE_C = "Range__c";

	public static final String TOP_SPEED_C = "TopSpeed__c";

	public static final String HORSE_POWER_KWH_C = "HorsePower_Display_Name__c";

	public static final String DMPL_PRODUCT_COLOR_C = "dmpl__ProductColor__c";

	public static final String SKU_DESCRIPTION_C = "SKUDescription__c";

	public static final String GROSS_WEIGHT_C = "GrossWeight__c";

	public static final String SF_ITEM_ATTRIBUTE_SKU = "dmpl__SKUs__r";
	
	
	public static final String RidingModes__c = "RidingModes__c";
	
	public static final String SeatingType__c = "SeatingType__c";

	/**
	 * The constant PRODUCTIMAGEPATH.
	 */
	public static final String PRODUCTIMAGEPATH = "/content/dam/vida/productimage/";
	
	public static final String CONTENT_DAM_VIDA_CONFIG_CITY_MASTER_CSV = "/content/dam/vida/config/City_Master.csv";	
	

	/**
	 * The constant PNGEXTENSION.
	 */
	public static final String PNGEXTENSION = ".png";
	/**
	 * The constant NAMEFILED.
	 */
	public static final String NAMEFILED = "name";
	/**
	 * The constant VARIANTS.
	 */
	public static final String VARIANTS = "variants";
	/**
	 * The constant SKU.
	 */
	public static final String SKU = "sku";
	/**
	 * The constant RANGE.
	 */
	public static final String RANGE = "range";
	/**
	 * The constant CHARGINGTIME.
	 */
	public static final String CHARGINGTIME = "charging_time";
	/**
	 * The constant ACCELERATOR.
	 */
	public static final String ACCELERATOR = "accelerator";
	/**
	 * The constant TOPSPEED.
	 */
	public static final String TOPSPEED = "top_speed";
	/**
	 * The constant LEADSOURCE.
	 */
	public static final String LEADSOURCE = "LeadSource";

	/**
	 * The constant WEBSITE.
	 */
	public static final String WEBSITE = "Website";

	/**
	 * The constant SF ID.
	 */
	public static final String SF_ID = "sf_id";

	/** The Constant ROOT_PATH. */
	public static final String ROOT_PATH = "/content/vida/";

	/** The Constant BRAND_NAME. */
	public static final String BRAND_NAME = "vida";

	protected static final Map<String, String> COLORGRADIANT = new HashMap<String, String>() {
		{
			put("Blue", "linear-gradient(180deg, #00A1E7 0%, #0F78A6 65.1%, #00A1E7 100%)");
			put("Red", "linear-gradient(180deg, #FF0000 0%, #B51818 67.71%, #FF0000 100%)");
			put("Matt Grey", "linear-gradient(180deg, #5B5B5B 0%, #7D7D7D 64.06%, #5B5B5B 100%)");
			put("Metallic Silver", "linear-gradient(180deg, #FFFFFF 0%, #D9D9D9 63.54%, #FFFFFF 100%)");
		}
	};

	public static final String VARIANTSKU = "variant_sku";

	public static final String ONROADPRICE = "onRoadPrice";

	public static final String EFFECTIVEPRICE = "effectivePrice";

	public static final String COLOR = "color";

	public static final String OTHERISSUES = "Other issues/queries";

	/** The Constant REGEX_ALL_WHITESPACE. */
	public static final String REGEX_ALL_WHITESPACE = "\\s+";

	/** The Constant KEY_FOOTER_END_PRIVATE_KEY. */
	public static final String KEY_FOOTER_END_PRIVATE_KEY = "-----END PRIVATE KEY-----";

	/** The Constant KEY_HEADER_BEGIN_PRIVATE_KEY. */
	public static final String KEY_HEADER_BEGIN_PRIVATE_KEY = "-----BEGIN PRIVATE KEY-----";

	/** The CIPHY R TYP E RS A EC B OAEP with SH A 1 and MGF 1 padding. */
	public static final String CIPHYR_TYPE_RSA_ECB_OAEPWithSHA_1AndMGF1Padding = "RSA/ECB/OAEPWithSHA-1AndMGF1Padding";

	/** The ciphyr type 2. */
	public static final String CIPHYR_TYPE_2 = "RSA";

	public static final String KEY_PATH = "/etc/truststore/private-key/jcr:content";
	
	public static final String DOT = ".";
	
	public static final String JSON = "json";
	
	/** The Constant SEARCH_KEY. */
	public static final String SEARCH_KEY = "searchKey";
	/** The Constant DECODING_CHARACTER. */
	public static final String DECODING_CHARACTER = "%20";
	/**
	 * The constant UTF_8.
	 */
	public static final String UTF_8 = "utf-8";
	/**
	 * The constant FORWARD_SLASH.
	 */
	public static final String FORWARD_SLASH = "/";

	/**
	 * The constant CONTENT_TYPE_APPLICATION_JSON.
	 */
	public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json"
;
	/** The Constant PAGE_CONTENT. */
	public static final String CQ_PAGE = NameConstants.NT_PAGE;

	/** The Constant PATH_CONTENT. */
	public static final String PATH_CONTENT = "/content";

	/** The Constant URL_EXT_HTML. */
	public static final String URL_EXT_HTML = ".html";

	/** The Constant PATH_DAM. */
	public static final String PATH_DAM = "/content/dam";

	/** The lttr pickup location endpoint. */
	public static final String LTTR_PICKUP_LOCATION_ENDPOINT = "/v1/isCityOperational";

	/** The Constant X_API_KEY. */
	public static final String X_API_KEY = "x-api-key";
	
	//public static final String LTTR_VEHICLE_AVAILBILITY_ENDPOINT="/v1/getAvailableModel?source=vida";
	public static final String LTTR_VEHICLE_AVAILBILITY_ENDPOINT="/v1/getAllAvailableModelByCity?source=vida";

	public static final String CITY_PRODUCT = "city_state_id";

	/** The Constant VIDA_PATH. */
	public static final String VIDA_PATH = "/apps/vida";
	
	/** The Constant UTF. */
	public static final String UTF = "UTF-8";

}
