/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.jcr.resource.api.JcrResourceConstants;
import org.apache.sling.models.factory.ModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.NameConstants;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for UserManualListModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class UserManualListModelTest {

	private final AemContext context = new AemContext();

	@InjectMocks
	private UserManualListModel userManualListModel;

	private Resource page;

	private List<Resource> listpaths = new ArrayList<>();

	@Mock
	private ResourceResolver resourceResolver;

	@Mock
	private ModelFactory modelFactory;

	private ValueMap value;

	@Mock
	Iterator<Resource> listChildren;

	Resource childRes;

	Resource res;

	@Mock
	Iterable<Resource> children;

	@Mock
	Iterator<Resource> itr;

	private ValueMap valueMap;

	private ValueMap resMap;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);

		page = Mockito.mock(Resource.class);
		value = Mockito.mock(ValueMap.class);
		childRes = Mockito.mock(Resource.class);
		res = Mockito.mock(Resource.class);
		valueMap = Mockito.mock(ValueMap.class);
		resMap = Mockito.mock(ValueMap.class);
		context.currentResource(page);
		context.registerService(ModelFactory.class, modelFactory);

		listpaths.add(page);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.UserManualListModel#getChildPagesPath()}.
	 * 
	 * @throws NoSuchFieldException
	 */
	@Test
	void testGetChildPagesPath() throws NoSuchFieldException {
		PrivateAccessor.setField(userManualListModel, "listpaths", listpaths);

		Mockito.when(page.getValueMap()).thenReturn(value);
		Mockito.when(page.getValueMap().get("pagepath")).thenReturn("/content/vida/test");
		Mockito.when(resourceResolver.getResource("/content/vida/test")).thenReturn(page);
		Mockito.when(resourceResolver.getResource("/content/vida/test")).thenReturn(page);
		Mockito.when(page.hasChildren()).thenReturn(true, true, true);
		Mockito.when(value.get(JcrConstants.JCR_PRIMARYTYPE, String.class))
				.thenReturn(JcrResourceConstants.NT_SLING_ORDERED_FOLDER);
		Mockito.when(page.listChildren()).thenReturn(listChildren);
		Mockito.when(listChildren.hasNext()).thenReturn(true, true, false, true, true, false);
		Mockito.when(listChildren.next()).thenReturn(childRes, res);
		Mockito.when(childRes.getChildren()).thenReturn(children);
		Mockito.when(children.iterator()).thenReturn(itr);
		Mockito.when(itr.hasNext()).thenReturn(true, true, false, true, true, false);
		Mockito.when(itr.next()).thenReturn(childRes);
		Mockito.when(childRes.getValueMap()).thenReturn(valueMap);
		Mockito.when(valueMap.get(JcrConstants.JCR_PRIMARYTYPE, String.class)).thenReturn(NameConstants.NT_PAGE, "");
		Mockito.when(childRes.getPath()).thenReturn("/content/vida/test");
		Mockito.when(childRes.getChild("/content/vida/testjcr:content")).thenReturn(childRes);
		Mockito.when(valueMap.get(JcrConstants.JCR_TITLE, String.class)).thenReturn("title");
		Mockito.when(res.getValueMap()).thenReturn(resMap);
		Mockito.when(resMap.get(JcrConstants.JCR_PRIMARYTYPE, String.class)).thenReturn(NameConstants.NT_PAGE, "");
		Mockito.when(res.getPath()).thenReturn("/content/vida/in/en");
		Mockito.when(res.getChild("/content/vida/in/enjcr:content")).thenReturn(childRes);

		userManualListModel.getChildPagesPath();

		Mockito.when(value.get(JcrConstants.JCR_PRIMARYTYPE, String.class))
				.thenReturn(NameConstants.NT_PAGE);
		userManualListModel.getChildPagesPath();

		Mockito.when(value.get(JcrConstants.JCR_PRIMARYTYPE, String.class))
				.thenReturn("");
		userManualListModel.getChildPagesPath();

		Mockito.when(page.hasChildren()).thenReturn(false);
		userManualListModel.getChildPagesPath();
		
		assertNotNull(userManualListModel);
	}

}
