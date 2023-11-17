/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
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

import com.day.cq.commons.Externalizer;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.heromotocorp.vida.core.utils.Constants;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for UserManualSearchModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class UserManualSearchModelTest {

	private final AemContext context = new AemContext();

	@InjectMocks
	private UserManualSearchModel userManualSearchModel;

	@Mock
	private ResourceResolver resourceResolver;

	@Mock
	private Resource resource;

	@Mock
	private Resource childRes;

	@Mock
	private ModelFactory modelFactory;

	@Mock
	private SlingHttpServletRequest request;

	@Mock
	private SlingHttpServletResponse response;

	private QueryBuilder queryBuilder;

	private Externalizer externalizer;

	private Session session;

	private Page currentPage;
	
	@Mock
	private PredicateGroup predicate;
	
	@Mock
	private Query query;
	
	private SearchResult result;
	
	@Mock
	private Hit hit;
	
	private Map<String, String> excerpts = new HashMap<>();
	
	private List<Hit> hitList = new ArrayList<>();
	
	private ValueMap valueMap;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		context.currentResource(resource);
		context.registerService(ModelFactory.class, modelFactory);

		queryBuilder = Mockito.mock(QueryBuilder.class);
		externalizer = Mockito.mock(Externalizer.class);
		session = Mockito.mock(Session.class);
		currentPage = Mockito.mock(Page.class);
		result = Mockito.mock(SearchResult.class);
		valueMap = Mockito.mock(ValueMap.class);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.UserManualSearchModel#init()}.
	 */
	@Test
	void testInit() {
		Mockito.when(request.getParameter(Constants.SEARCH_KEY)).thenReturn("searchKey");
		Mockito.when(resourceResolver.adaptTo(Session.class)).thenReturn(session);
		Mockito.when(resourceResolver.adaptTo(QueryBuilder.class)).thenReturn(queryBuilder);
		Mockito.when(resourceResolver.adaptTo(Externalizer.class)).thenReturn(externalizer);
		userManualSearchModel.init();

		assertNotNull(userManualSearchModel);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.UserManualSearchModel#getSearchResultItems()}.
	 * @throws NoSuchFieldException 
	 * @throws IOException 
	 * @throws RepositoryException 
	 */
	@Test
	void testGetSearchResultItems() throws NoSuchFieldException, RepositoryException, IOException {
		PrivateAccessor.setField(userManualSearchModel, "currentPage", currentPage);
		
		excerpts.put(JcrConstants.JCR_DESCRIPTION, "desc");
		Mockito.mockStatic(PredicateGroup.class);
		
		Mockito.when(currentPage.getPath()).thenReturn("");
		userManualSearchModel.getSearchResultItems();
		
		testInit();
		hitList.add(hit);
		Mockito.when(currentPage.getPath()).thenReturn("experience-fragments/vida/in/en/workshop_manual_vida");
		Mockito.when(PredicateGroup.create(Mockito.anyMap())).thenReturn(predicate);
		Mockito.when(queryBuilder.createQuery(predicate, session)).thenReturn(query);
		Mockito.when(query.getResult()).thenReturn(result);
		Mockito.when(result.getTotalMatches()).thenReturn(10L);
		Mockito.when(result.getHits()).thenReturn(hitList);
		Mockito.when(hit.getPath()).thenReturn("/content/vida/in");
		Mockito.when(hit.getExcerpts()).thenReturn(excerpts);
		Mockito.when(hit.getResource()).thenReturn(resource);
		Mockito.when(resource.getResourceResolver()).thenReturn(resourceResolver);
		Mockito.when(resource.getPath()).thenReturn("/content/vida/in/master");
		Mockito.when(externalizer.publishLink(resourceResolver, "/content/vida/in/master")).thenReturn("/content/vida/in/master");
		Mockito.when(resourceResolver.map("/content/vida/in/master")).thenReturn("/content/vida/in/master");
		Mockito.when(resource.getChild("/content/vida/in/masterjcr:content")).thenReturn(childRes);
		Mockito.when(childRes.getValueMap()).thenReturn(valueMap);
		Mockito.when(valueMap.get(JcrConstants.JCR_TITLE, String.class)).thenReturn("Title");
		userManualSearchModel.getSearchResultItems();
		
		Mockito.when(currentPage.getPath()).thenReturn("experience-fragments/vida/in/en/spares_manual_vida_v");
		userManualSearchModel.getSearchResultItems();
		
		Mockito.when(currentPage.getPath()).thenReturn("experience-fragments/vida/in/en/owner_manual_header");
		userManualSearchModel.getSearchResultItems();
		
		Mockito.when(currentPage.getPath()).thenReturn("experience-fragments/vida/in/en/faq_mobile");
		userManualSearchModel.getSearchResultItems();
		
		Mockito.when(currentPage.getPath()).thenReturn("experience-fragments/vida/in/en/acpa");
		userManualSearchModel.getSearchResultItems();
		
		assertNotNull(userManualSearchModel);
	}

}
