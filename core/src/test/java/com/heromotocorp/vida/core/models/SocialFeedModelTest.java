/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.heromotocorp.vida.core.config.TwitterConfig;
import com.heromotocorp.vida.core.config.YoutubeConfig;
import com.heromotocorp.vida.core.service.InstagramService;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for SocialFeedModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class SocialFeedModelTest {

	private final AemContext context = new AemContext();

	private SocialFeedModel socialFeedModel = new SocialFeedModel();

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

	@Mock
	private Resource assetResource2;

	@Mock
	private Asset asset2;

	@Mock
	private Rendition original2;

	private TwitterConfig twitterConfig;

	private YoutubeConfig youtubeConfig;

	private InstagramService instagramService;

	private String text = "[{\"created_at\":\"Thu May 25 17:44:59 +0000 2023\",\"id\":1661790477639712773,\"id_str\":\"1661790477639712773\",\"text\":\"RT @TwitterDev: ? Calling all start-ups ?\\n\\nToday we are launching our new access tier, Twitter API Pro!\\n\\nExperiment, build, and scale your\",\"truncated\":false,\"entities\":{\"hashtags\":[],\"symbols\":[],\"user_mentions\":[{\"screen_name\":\"TwitterDev\",\"name\":\"Twitter Dev\",\"id\":2244994945,\"id_str\":\"2244994945\",\"indices\":[3,14]}],\"urls\":[]},\"source\":\"<a href=\\\"https://mobile.twitter.com\\\" rel=\\\"nofollow\\\">Twitter Web App</a>\",\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":6253282,\"id_str\":\"6253282\",\"name\":\"Twitter API\",\"screen_name\":\"TwitterAPI\",\"location\":\"\",\"description\":\"Tweets about changes and service issues. Follow @TwitterDev for more.\",\"url\":\"https://t.co/8IkCzCDr19\",\"entities\":{\"url\":{\"urls\":[{\"url\":\"https://t.co/8IkCzCDr19\",\"expanded_url\":\"https://developer.twitter.com\",\"display_url\":\"developer.twitter.com\",\"indices\":[0,23]}]},\"description\":{\"urls\":[]}},\"protected\":false,\"followers_count\":5979437,\"friends_count\":35,\"listed_count\":12281,\"created_at\":\"Wed May 23 06:01:13 +0000 2007\",\"favourites_count\":4,\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":false,\"verified\":false,\"statuses_count\":3747,\"lang\":null,\"contributors_enabled\":false,\"is_translator\":false,\"is_translation_enabled\":false,\"profile_background_color\":\"C0DEED\",\"profile_background_image_url\":\"http://abs.twimg.com/images/themes/theme1/bg.png\",\"profile_background_image_url_https\":\"https://abs.twimg.com/images/themes/theme1/bg.png\",\"profile_background_tile\":true,\"profile_image_url\":\"http://pbs.twimg.com/profile_images/1445765299974795279/ExVMkDHG_normal.jpg\",\"profile_image_url_https\":\"https://pbs.twimg.com/profile_images/1445765299974795279/ExVMkDHG_normal.jpg\",\"profile_banner_url\":\"https://pbs.twimg.com/profile_banners/6253282/1633532284\",\"profile_link_color\":\"0084B4\",\"profile_sidebar_border_color\":\"C0DEED\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":true,\"has_extended_profile\":true,\"default_profile\":false,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null,\"translator_type\":\"regular\",\"withheld_in_countries\":[]},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"retweeted_status\":{\"created_at\":\"Thu May 25 17:44:05 +0000 2023\",\"id\":1661790253886177280,\"id_str\":\"1661790253886177280\",\"text\":\"? Calling all start-ups ?\\n\\nToday we are launching our new access tier, Twitter API Pro!\\n\\nExperiment, build, and sca https://t.co/o62NcmsO7k\",\"truncated\":true,\"entities\":{\"hashtags\":[],\"symbols\":[],\"user_mentions\":[],\"urls\":[{\"url\":\"https://t.co/o62NcmsO7k\",\"expanded_url\":\"https://twitter.com/i/web/status/1661790253886177280\",\"display_url\":\"twitter.com/i/web/status/1\",\"indices\":[117,140]}]},\"source\":\"<a href=\\\"https://mobile.twitter.com\\\" rel=\\\"nofollow\\\">Twitter Web App</a>\",\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":2244994945,\"id_str\":\"2244994945\",\"name\":\"Twitter Dev\",\"screen_name\":\"TwitterDev\",\"location\":\"127.0.0.1\",\"description\":\"The voice of the #TwitterDev team and your official source for updates, news, and events, related to the #TwitterAPI.\",\"url\":\"https://t.co/9wI31m3ELF\",\"entities\":{\"url\":{\"urls\":[{\"url\":\"https://t.co/9wI31m3ELF\",\"expanded_url\":\"https://developer.twitter.com/en/community\",\"display_url\":\"developer.twitter.com/en/community\",\"indices\":[0,23]}]},\"description\":{\"urls\":[]}},\"protected\":false,\"followers_count\":585832,\"friends_count\":1943,\"listed_count\":2428,\"created_at\":\"Sat Dec 14 04:35:55 +0000 2013\",\"favourites_count\":2145,\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":true,\"verified\":false,\"statuses_count\":4079,\"lang\":null,\"contributors_enabled\":false,\"is_translator\":false,\"is_translation_enabled\":false,\"profile_background_color\":\"FFFFFF\",\"profile_background_image_url\":\"http://abs.twimg.com/images/themes/theme1/bg.png\",\"profile_background_image_url_https\":\"https://abs.twimg.com/images/themes/theme1/bg.png\",\"profile_background_tile\":false,\"profile_image_url\":\"http://pbs.twimg.com/profile_images/1445764922474827784/W2zEPN7U_normal.jpg\",\"profile_image_url_https\":\"https://pbs.twimg.com/profile_images/1445764922474827784/W2zEPN7U_normal.jpg\",\"profile_banner_url\":\"https://pbs.twimg.com/profile_banners/2244994945/1660405530\",\"profile_link_color\":\"0084B4\",\"profile_sidebar_border_color\":\"FFFFFF\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":false,\"has_extended_profile\":true,\"default_profile\":false,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null,\"translator_type\":\"regular\",\"withheld_in_countries\":[]},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":false,\"retweet_count\":152,\"favorite_count\":592,\"favorited\":false,\"retweeted\":false,\"possibly_sensitive\":false,\"lang\":\"en\"},\"is_quote_status\":false,\"retweet_count\":152,\"favorite_count\":0,\"favorited\":false,\"retweeted\":false,\"lang\":\"en\"},{\"created_at\":\"Wed Apr 05 21:43:27 +0000 2023\",\"id\":1643731098793390082,\"id_str\":\"1643731098793390082\",\"text\":\"RT @TwitterDev: ? If your developer account application was under review over the last few months, we kindly ask you to re-apply if you are\",\"truncated\":false,\"entities\":{\"hashtags\":[],\"symbols\":[],\"user_mentions\":[{\"screen_name\":\"TwitterDev\",\"name\":\"Twitter Dev\",\"id\":2244994945,\"id_str\":\"2244994945\",\"indices\":[3,14]}],\"urls\":[]},\"source\":\"<a href=\\\"https://mobile.twitter.com\\\" rel=\\\"nofollow\\\">Twitter Web App</a>\",\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":6253282,\"id_str\":\"6253282\",\"name\":\"Twitter API\",\"screen_name\":\"TwitterAPI\",\"location\":\"\",\"description\":\"Tweets about changes and service issues. Follow @TwitterDev for more.\",\"url\":\"https://t.co/8IkCzCDr19\",\"entities\":{\"url\":{\"urls\":[{\"url\":\"https://t.co/8IkCzCDr19\",\"expanded_url\":\"https://developer.twitter.com\",\"display_url\":\"developer.twitter.com\",\"indices\":[0,23]}]},\"description\":{\"urls\":[]}},\"protected\":false,\"followers_count\":5979437,\"friends_count\":35,\"listed_count\":12281,\"created_at\":\"Wed May 23 06:01:13 +0000 2007\",\"favourites_count\":4,\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":false,\"verified\":false,\"statuses_count\":3747,\"lang\":null,\"contributors_enabled\":false,\"is_translator\":false,\"is_translation_enabled\":false,\"profile_background_color\":\"C0DEED\",\"profile_background_image_url\":\"http://abs.twimg.com/images/themes/theme1/bg.png\",\"profile_background_image_url_https\":\"https://abs.twimg.com/images/themes/theme1/bg.png\",\"profile_background_tile\":true,\"profile_image_url\":\"http://pbs.twimg.com/profile_images/1445765299974795279/ExVMkDHG_normal.jpg\",\"profile_image_url_https\":\"https://pbs.twimg.com/profile_images/1445765299974795279/ExVMkDHG_normal.jpg\",\"profile_banner_url\":\"https://pbs.twimg.com/profile_banners/6253282/1633532284\",\"profile_link_color\":\"0084B4\",\"profile_sidebar_border_color\":\"C0DEED\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":true,\"has_extended_profile\":true,\"default_profile\":false,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null,\"translator_type\":\"regular\",\"withheld_in_countries\":[]},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"retweeted_status\":{\"created_at\":\"Tue Apr 04 22:04:16 +0000 2023\",\"id\":1643373950930661377,\"id_str\":\"1643373950930661377\",\"text\":\"? If your developer account application was under review over the last few months, we kindly ask you to re-apply if https://t.co/DLkTXVfLwH\",\"truncated\":true,\"entities\":{\"hashtags\":[],\"symbols\":[],\"user_mentions\":[],\"urls\":[{\"url\":\"https://t.co/DLkTXVfLwH\",\"expanded_url\":\"https://twitter.com/i/web/status/1643373950930661377\",\"display_url\":\"twitter.com/i/web/status/1\",\"indices\":[117,140]}]},\"source\":\"<a href=\\\"https://mobile.twitter.com\\\" rel=\\\"nofollow\\\">Twitter Web App</a>\",\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":2244994945,\"id_str\":\"2244994945\",\"name\":\"Twitter Dev\",\"screen_name\":\"TwitterDev\",\"location\":\"127.0.0.1\",\"description\":\"The voice of the #TwitterDev team and your official source for updates, news, and events, related to the #TwitterAPI.\",\"url\":\"https://t.co/9wI31m3ELF\",\"entities\":{\"url\":{\"urls\":[{\"url\":\"https://t.co/9wI31m3ELF\",\"expanded_url\":\"https://developer.twitter.com/en/community\",\"display_url\":\"developer.twitter.com/en/community\",\"indices\":[0,23]}]},\"description\":{\"urls\":[]}},\"protected\":false,\"followers_count\":585832,\"friends_count\":1943,\"listed_count\":2428,\"created_at\":\"Sat Dec 14 04:35:55 +0000 2013\",\"favourites_count\":2145,\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":true,\"verified\":false,\"statuses_count\":4079,\"lang\":null,\"contributors_enabled\":false,\"is_translator\":false,\"is_translation_enabled\":false,\"profile_background_color\":\"FFFFFF\",\"profile_background_image_url\":\"http://abs.twimg.com/images/themes/theme1/bg.png\",\"profile_background_image_url_https\":\"https://abs.twimg.com/images/themes/theme1/bg.png\",\"profile_background_tile\":false,\"profile_image_url\":\"http://pbs.twimg.com/profile_images/1445764922474827784/W2zEPN7U_normal.jpg\",\"profile_image_url_https\":\"https://pbs.twimg.com/profile_images/1445764922474827784/W2zEPN7U_normal.jpg\",\"profile_banner_url\":\"https://pbs.twimg.com/profile_banners/2244994945/1660405530\",\"profile_link_color\":\"0084B4\",\"profile_sidebar_border_color\":\"FFFFFF\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":false,\"has_extended_profile\":true,\"default_profile\":false,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null,\"translator_type\":\"regular\",\"withheld_in_countries\":[]},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":false,\"retweet_count\":117,\"favorite_count\":394,\"favorited\":false,\"retweeted\":false,\"possibly_sensitive\":false,\"lang\":\"en\"},\"is_quote_status\":false,\"retweet_count\":117,\"favorite_count\":0,\"favorited\":false,\"retweeted\":false,\"lang\":\"en\"},{\"created_at\":\"Wed Mar 29 23:40:34 +0000 2023\",\"id\":1641223857997774848,\"id_str\":\"1641223857997774848\",\"text\":\"RT @TwitterDev: Today we are launching our new Twitter API access tiers! Were excited to share more details about our self-serve access. ?\",\"truncated\":false,\"entities\":{\"hashtags\":[],\"symbols\":[],\"user_mentions\":[{\"screen_name\":\"TwitterDev\",\"name\":\"Twitter Dev\",\"id\":2244994945,\"id_str\":\"2244994945\",\"indices\":[3,14]}],\"urls\":[]},\"source\":\"<a href=\\\"https://mobile.twitter.com\\\" rel=\\\"nofollow\\\">Twitter Web App</a>\",\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":6253282,\"id_str\":\"6253282\",\"name\":\"Twitter API\",\"screen_name\":\"TwitterAPI\",\"location\":\"\",\"description\":\"Tweets about changes and service issues. Follow @TwitterDev for more.\",\"url\":\"https://t.co/8IkCzCDr19\",\"entities\":{\"url\":{\"urls\":[{\"url\":\"https://t.co/8IkCzCDr19\",\"expanded_url\":\"https://developer.twitter.com\",\"display_url\":\"developer.twitter.com\",\"indices\":[0,23]}]},\"description\":{\"urls\":[]}},\"protected\":false,\"followers_count\":5979437,\"friends_count\":35,\"listed_count\":12281,\"created_at\":\"Wed May 23 06:01:13 +0000 2007\",\"favourites_count\":4,\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":false,\"verified\":false,\"statuses_count\":3747,\"lang\":null,\"contributors_enabled\":false,\"is_translator\":false,\"is_translation_enabled\":false,\"profile_background_color\":\"C0DEED\",\"profile_background_image_url\":\"http://abs.twimg.com/images/themes/theme1/bg.png\",\"profile_background_image_url_https\":\"https://abs.twimg.com/images/themes/theme1/bg.png\",\"profile_background_tile\":true,\"profile_image_url\":\"http://pbs.twimg.com/profile_images/1445765299974795279/ExVMkDHG_normal.jpg\",\"profile_image_url_https\":\"https://pbs.twimg.com/profile_images/1445765299974795279/ExVMkDHG_normal.jpg\",\"profile_banner_url\":\"https://pbs.twimg.com/profile_banners/6253282/1633532284\",\"profile_link_color\":\"0084B4\",\"profile_sidebar_border_color\":\"C0DEED\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":true,\"has_extended_profile\":true,\"default_profile\":false,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null,\"translator_type\":\"regular\",\"withheld_in_countries\":[]},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"retweeted_status\":{\"created_at\":\"Wed Mar 29 23:36:18 +0000 2023\",\"id\":1641222782594990080,\"id_str\":\"1641222782594990080\",\"text\":\"Today we are launching our new Twitter API access tiers! Were excited to share more details about our self-serve access. ?\",\"truncated\":false,\"entities\":{\"hashtags\":[],\"symbols\":[],\"user_mentions\":[],\"urls\":[]},\"source\":\"<a href=\\\"https://mobile.twitter.com\\\" rel=\\\"nofollow\\\">Twitter Web App</a>\",\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":2244994945,\"id_str\":\"2244994945\",\"name\":\"Twitter Dev\",\"screen_name\":\"TwitterDev\",\"location\":\"127.0.0.1\",\"description\":\"The voice of the #TwitterDev team and your official source for updates, news, and events, related to the #TwitterAPI.\",\"url\":\"https://t.co/9wI31m3ELF\",\"entities\":{\"url\":{\"urls\":[{\"url\":\"https://t.co/9wI31m3ELF\",\"expanded_url\":\"https://developer.twitter.com/en/community\",\"display_url\":\"developer.twitter.com/en/community\",\"indices\":[0,23]}]},\"description\":{\"urls\":[]}},\"protected\":false,\"followers_count\":585832,\"friends_count\":1943,\"listed_count\":2428,\"created_at\":\"Sat Dec 14 04:35:55 +0000 2013\",\"favourites_count\":2145,\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":true,\"verified\":false,\"statuses_count\":4079,\"lang\":null,\"contributors_enabled\":false,\"is_translator\":false,\"is_translation_enabled\":false,\"profile_background_color\":\"FFFFFF\",\"profile_background_image_url\":\"http://abs.twimg.com/images/themes/theme1/bg.png\",\"profile_background_image_url_https\":\"https://abs.twimg.com/images/themes/theme1/bg.png\",\"profile_background_tile\":false,\"profile_image_url\":\"http://pbs.twimg.com/profile_images/1445764922474827784/W2zEPN7U_normal.jpg\",\"profile_image_url_https\":\"https://pbs.twimg.com/profile_images/1445764922474827784/W2zEPN7U_normal.jpg\",\"profile_banner_url\":\"https://pbs.twimg.com/profile_banners/2244994945/1660405530\",\"profile_link_color\":\"0084B4\",\"profile_sidebar_border_color\":\"FFFFFF\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":false,\"has_extended_profile\":true,\"default_profile\":false,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null,\"translator_type\":\"regular\",\"withheld_in_countries\":[]},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":false,\"retweet_count\":1088,\"favorite_count\":1470,\"favorited\":false,\"retweeted\":false,\"lang\":\"en\"},\"is_quote_status\":false,\"retweet_count\":1088,\"favorite_count\":0,\"favorited\":false,\"retweeted\":false,\"lang\":\"en\"}]";

	private String text1 = "[{\"data\":[{\"id\":\"17845589873444388\",\"media_url\":\"https://scontent-xsp1-3.cdninstagram.com/v/t51.29350-15/125184787_1262509770774910_1887005416848225375_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=8ae9d6&_nc_ohc=orzvRpVPgQoAX8UykqW&_nc_ht=scontent-xsp1-3.cdninstagram.com&edm=AM6HXa8EAAAA&oh=00_AfCIRMXDiwQYGIeFrrQj3LBBvpOP6uXkpKK1H4rtuSKeaQ&oe=63643DD7\",\"permalink\":\"https://www.instagram.com/p/CHdbXZfBH08/\"},{\"id\":\"17964617683341018\",\"media_url\":\"https://scontent-xsp1-2.cdninstagram.com/v/t51.29350-15/122955467_762665838003343_574205878698911737_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=8ae9d6&_nc_ohc=GGtnm5aY8n4AX_VBRkq&_nc_ht=scontent-xsp1-2.cdninstagram.com&edm=AM6HXa8EAAAA&oh=00_AfAljWvsnJkaPFhDJWcYOHpyvD_HFEB-4AO-IXQK85M_Wg&oe=636445E4\",\"permalink\":\"https://www.instagram.com/p/CG77LjNBcwa/\"},{\"id\":\"17846480561416081\",\"media_url\":\"https://scontent-xsp1-3.cdninstagram.com/v/t51.29350-15/122256955_762799640935554_2552168651000451222_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=8ae9d6&_nc_ohc=IDqxMZP9HDYAX-T97GB&_nc_ht=scontent-xsp1-3.cdninstagram.com&edm=AM6HXa8EAAAA&oh=00_AfAC1TWKtO1aWumEhXtUjDtQYHBxwPz6_NxbCakbbEw5Sw&oe=636260EF\",\"permalink\":\"https://www.instagram.com/p/CGsLaU7hWBh/\"}],\"paging\":{\"cursors\":{\"before\":\"QVFIUmNXb0RERTA5T3F1OF9HNDFDWnVtTmlvWHZAJbE1nazhoTjBTMXdTbmFjUVMxZAFNLZA2NBNUpJYmh2SWZAkZAzg3WnNWUXVuMHpfNXMxa29rd2VOb2R2dF9n\",\"after\":\"QVFIUkRMSXEwS01ZAOHJjTlU0U3l4YjBJQVlHbHZA5Vk1scVE5bmxRd3czM1pUU1hXZATRGNzRyOFF2cUxxMmd6ZA0VkN3Fta1hXUnlEN1oybFlHNFN3UHppU05B\"},\"next\":\"https://graph.facebook.com/v8.0/17841404111152649/media?access_token=EAAD4d4HiVd8BAN2SC3vf2xIJAZAUAueEG00LmmrWZCHHNXiLFiHR3SGNFjNcAa4GUeGKrJQZBk95VXq3ZAC3R2PZBeENA9QuPfBemgTc6xJTmj4efzhIijxE33EAb02uxIlsPDGRetae2qvbT2qDbYrdeLRvii4FlEzj6e884r1qzfPJzKvLK&fields=id%2Cmedia_url%2Cpermalink&limit=3&after=QVFIUkRMSXEwS01ZAOHJjTlU0U3l4YjBJQVlHbHZA5Vk1scVE5bmxRd3czM1pUU1hXZATRGNzRyOFF2cUxxMmd6ZA0VkN3Fta1hXUnlEN1oybFlHNFN3UHppU05B\"}}]";

	private String text2 = "[{\"kind\":\"youtube#searchListResponse\",\"etag\":\"Uk3hfhRejcdiYLg4c9fx9nHoRno\",\"regionCode\":\"SG\",\"pageInfo\":{\"totalResults\":3,\"resultsPerPage\":3},\"items\":[{\"kind\":\"youtube#searchResult\",\"etag\":\"axjL9GzOY7U2IJY4QI0dmdvRHXQ\",\"id\":{\"kind\":\"youtube#video\",\"videoId\":\"HJEiE4cXsXI\"},\"snippet\":{\"publishedAt\":\"2022-06-14T10:10:43Z\",\"channelId\":\"UCIxzS690aJxqhyViPIX2X4Q\",\"title\":\"Charlie And The Fruit Factory   Loco Nuts   Funny Cartoons   Kids Videos   Kids Shows   YouTube   Go\",\"description\":\"\",\"thumbnails\":{\"default\":{\"url\":\"https://i.ytimg.com/vi/HJEiE4cXsXI/default.jpg\",\"width\":120,\"height\":90},\"medium\":{\"url\":\"https://i.ytimg.com/vi/HJEiE4cXsXI/mqdefault.jpg\",\"width\":320,\"height\":180},\"high\":{\"url\":\"https://i.ytimg.com/vi/HJEiE4cXsXI/hqdefault.jpg\",\"width\":480,\"height\":360}},\"channelTitle\":\"BrunoTheStreet\",\"liveBroadcastContent\":\"none\",\"publishTime\":\"2022-06-14T10:10:43Z\"}},{\"kind\":\"youtube#searchResult\",\"etag\":\"WPbsvUobSfkc72ZR6_uxZGm6o_4\",\"id\":{\"kind\":\"youtube#video\",\"videoId\":\"LbzsPq6gdZU\"},\"snippet\":{\"publishedAt\":\"2022-05-30T09:45:16Z\",\"channelId\":\"UCIxzS690aJxqhyViPIX2X4Q\",\"title\":\"Cartoon 2\",\"description\":\"This is Cartoon Channel.\",\"thumbnails\":{\"default\":{\"url\":\"https://i.ytimg.com/vi/LbzsPq6gdZU/default.jpg\",\"width\":120,\"height\":90},\"medium\":{\"url\":\"https://i.ytimg.com/vi/LbzsPq6gdZU/mqdefault.jpg\",\"width\":320,\"height\":180},\"high\":{\"url\":\"https://i.ytimg.com/vi/LbzsPq6gdZU/hqdefault.jpg\",\"width\":480,\"height\":360}},\"channelTitle\":\"BrunoTheStreet\",\"liveBroadcastContent\":\"none\",\"publishTime\":\"2022-05-30T09:45:16Z\"}},{\"kind\":\"youtube#searchResult\",\"etag\":\"dNFpwPbAsGf78KNbvOOJFx3POrY\",\"id\":{\"kind\":\"youtube#video\",\"videoId\":\"9GJX1MiopLo\"},\"snippet\":{\"publishedAt\":\"2022-05-30T09:42:51Z\",\"channelId\":\"UCIxzS690aJxqhyViPIX2X4Q\",\"title\":\"Cartoon charlie\",\"description\":\"This is Kids cartoon.\",\"thumbnails\":{\"default\":{\"url\":\"https://i.ytimg.com/vi/9GJX1MiopLo/default.jpg\",\"width\":120,\"height\":90},\"medium\":{\"url\":\"https://i.ytimg.com/vi/9GJX1MiopLo/mqdefault.jpg\",\"width\":320,\"height\":180},\"high\":{\"url\":\"https://i.ytimg.com/vi/9GJX1MiopLo/hqdefault.jpg\",\"width\":480,\"height\":360}},\"channelTitle\":\"BrunoTheStreet\",\"liveBroadcastContent\":\"none\",\"publishTime\":\"2022-05-30T09:42:51Z\"}}]}]";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		twitterConfig = context.getService(TwitterConfig.class);
		twitterConfig = mock(TwitterConfig.class);
		PrivateAccessor.setField(socialFeedModel, "twitterConfig", twitterConfig);

		resourceResolver = context.getService(ResourceResolver.class);
		resourceResolver = mock(ResourceResolver.class);
		PrivateAccessor.setField(socialFeedModel, "resourceResolver", resourceResolver);

		youtubeConfig = context.getService(YoutubeConfig.class);
		youtubeConfig = mock(YoutubeConfig.class);
		PrivateAccessor.setField(socialFeedModel, "youtubeConfig", youtubeConfig);

		instagramService = context.getService(InstagramService.class);
		instagramService = mock(InstagramService.class);
		PrivateAccessor.setField(socialFeedModel, "instagramService", instagramService);

	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.SocialFeedModel#init()}.
	 */
	@Test
	void testInit() {
		InputStream stream = new ByteArrayInputStream(text.getBytes());
		InputStream stream1 = new ByteArrayInputStream(text1.getBytes());
		InputStream stream2 = new ByteArrayInputStream(text2.getBytes());
		Mockito.when(twitterConfig.twitterJsonPath()).thenReturn("twitterJsonPath");
		Mockito.when(resourceResolver.getResource("twitterJsonPath")).thenReturn(assetResource);
		Mockito.when(assetResource.adaptTo(Asset.class)).thenReturn(asset);
		Mockito.when(asset.getOriginal()).thenReturn(original);
		Mockito.when(original.adaptTo(InputStream.class)).thenReturn(stream);
		

		Mockito.when(instagramService.getInstaGramJsonPath()).thenReturn("instaGramJsonPath");
		Mockito.when(resourceResolver.getResource("instaGramJsonPath")).thenReturn(assetResource1);
		Mockito.when(assetResource1.adaptTo(Asset.class)).thenReturn(asset1);
		Mockito.when(asset1.getOriginal()).thenReturn(original1);
		Mockito.when(original1.adaptTo(InputStream.class)).thenReturn(stream1);
		

		Mockito.when(youtubeConfig.youtubeJsonPath()).thenReturn("youtubeJsonPath");
		Mockito.when(resourceResolver.getResource("youtubeJsonPath")).thenReturn(assetResource2);
		Mockito.when(assetResource2.adaptTo(Asset.class)).thenReturn(asset2);
		Mockito.when(asset2.getOriginal()).thenReturn(original2);
		Mockito.when(original2.adaptTo(InputStream.class)).thenReturn(stream2);
		socialFeedModel.init();
		socialFeedModel.getTwitterText();
		socialFeedModel.getInsta();
		socialFeedModel.getYoutube();
		assertNotNull(socialFeedModel);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.SocialFeedModel#getTwitterText()}.
	 */
	@Test
	void testGetTwitterText() {
		socialFeedModel.getTwitterText();
		assertNotNull(socialFeedModel);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.SocialFeedModel#getInsta()}.
	 */
	@Test
	void testGetInsta() {
		socialFeedModel.getInsta();
		assertNotNull(socialFeedModel);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.SocialFeedModel#getYoutube()}.
	 */
	@Test
	void testGetYoutube() {
		socialFeedModel.getYoutube();
		assertNotNull(socialFeedModel);
	}

}
