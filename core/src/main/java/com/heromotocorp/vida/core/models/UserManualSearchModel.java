package com.heromotocorp.vida.core.models;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.Externalizer;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.heromotocorp.vida.core.utils.Constants;
import com.heromotocorp.vida.core.vo.SearchResultVO;

/**
 * This is the User Manual Search model
 */
@Model(adaptables = { SlingHttpServletRequest.class,
		Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class UserManualSearchModel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserManualSearchModel.class);

	/** The sling http servlet request. */
	@Self
	private SlingHttpServletRequest request;

	/** The sling http servlet response. */
	@Self
	private SlingHttpServletResponse response;

	/** The resource resolver . */
	@SlingObject
	private ResourceResolver resourceResolver;

	/** The query builder. */
	private QueryBuilder queryBuilder;

	/** The externalizer. */
	private Externalizer externalizer;

	/** The searchKey. */
	private String searchKey;

	/** The session. */
	private Session session;

	/** The current page. */
	@Inject
	private Page currentPage;

	/** The Constant CONTENT_PATH. */
	public static final String CONTENT_PATH = "/content/";

	@PostConstruct
	public void init() {
		LOGGER.info("Executing init method.");
		searchKey = request.getParameter(Constants.SEARCH_KEY);
		session = resourceResolver.adaptTo(Session.class);
		queryBuilder = resourceResolver.adaptTo(QueryBuilder.class);
		externalizer = resourceResolver.adaptTo(Externalizer.class);
	}

	public List<SearchResultVO> getSearchResultItems() throws RepositoryException, IOException {

		List<SearchResultVO> resources = new LinkedList<>();
		try {
			// get resource resolver, session and queryBuilder objects.
			if (null == resourceResolver) {
				LOGGER.info("resolver not found.");
				return new ArrayList<>();
			}
			
			String ownerPath = "experience-fragments/vida/in/en/acpa";
			String workshopPath = "experience-fragments/vida/in/en/workshop_manual_vida";
			String sparesPath = "experience-fragments/vida/in/en/spares_manual_vida_v";
			String ownerHeaderPath = "experience-fragments/vida/in/en/owner_manual_header";
			String faqMobilePath = "experience-fragments/vida/in/en/faq_mobile";
			String searchRootPath = "";
			// get search arguments
			if (currentPage.getPath().contains(ownerPath)) {
				searchRootPath = CONTENT_PATH + ownerPath;
			}
			if (currentPage.getPath().contains(sparesPath)) {
				searchRootPath = CONTENT_PATH + sparesPath;
			}
			if (currentPage.getPath().contains(workshopPath)) {
				searchRootPath = CONTENT_PATH + workshopPath;
			}
			if (currentPage.getPath().contains(ownerHeaderPath)) {
				searchRootPath = CONTENT_PATH + ownerHeaderPath;
			}
			if (currentPage.getPath().contains(faqMobilePath)) {
				searchRootPath = CONTENT_PATH + faqMobilePath;
			}
			if (Objects.nonNull(searchKey)) {
				String fulltextSearchTerm = URLDecoder.decode(searchKey, Constants.UTF_8)
						.replace(Constants.DECODING_CHARACTER, StringUtils.EMPTY);
				LOGGER.info("Keyword to search : {}", fulltextSearchTerm);

				// search for resources
				Map<String, String> map = new HashMap<>();

				map.put("path", searchRootPath);
				map.put("type", Constants.CQ_PAGE);

				// Predicate for full text search if keyword is entered.
				if (!fulltextSearchTerm.isEmpty()) {
					map.put("fulltext", fulltextSearchTerm);
				}

				map.put("p.limit", "-1");

				LOGGER.info("Here is the query PredicateGroup : {} ", PredicateGroup.create(map));

				Query query = queryBuilder.createQuery(PredicateGroup.create(map), session);
				SearchResult result = query.getResult();

				// paging metadata
				LOGGER.info("Total number of results : {}", result.getTotalMatches());
				resources = new LinkedList<>();

				// add all the items to the result list
				for (Hit hit : query.getResult().getHits()) {
					LOGGER.debug("Hit : {}", hit.getPath());
					SearchResultVO searchResultItem = new SearchResultVO();
					Map<String, String> excerpts = hit.getExcerpts();
					String successfulPagePath = externalizer.publishLink(hit.getResource().getResourceResolver(),
							hit.getResource().getPath());
					if (successfulPagePath.contains("master")) {
						searchResultItem.setLink(resourceResolver.map(successfulPagePath));
						searchResultItem.setTitle(hit.getResource().getChild(hit.getResource().getPath() + JcrConstants.JCR_CONTENT)
								.getValueMap().get(JcrConstants.JCR_TITLE, String.class));
						searchResultItem.setDescription(excerpts.get(JcrConstants.JCR_DESCRIPTION));
						// searchResultItem.setExcerpts(excerpts.get("jcr:description"));
						resources.add(searchResultItem);
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Error while decoding the query term.", e);
		} catch (RepositoryException e) {
			LOGGER.error("[performSearch] There was an issue getting the resource", e);
		}
		return resources;
	}
}
