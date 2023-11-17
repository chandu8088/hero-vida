/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Iterator;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.factory.ModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.commons.Externalizer;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for BackNavigationModel
 */
@ExtendWith(MockitoExtension.class)
@ExtendWith(AemContextExtension.class)
class BackNavigationModelTest {

	private final AemContext context = new AemContext();

	private BackNavigationModel backNavigationModel = new BackNavigationModel();

	@Mock
	private Resource resource;

	@Mock
	Resource res;

	@Mock
	private Resource parent;

	@Mock
	private Resource subparent;

	private ResourceResolver resourceResolver;

	@Mock
	private Externalizer externalizer;

	@Mock
	private Page currentPage;

	@Mock
	private Page parentPage;

	@Mock
	private Resource next;

	@Mock
	private ModelFactory modelFactory;

	@Mock
	private Iterable<Resource> subChild;

	@Mock
	private Iterator<Resource> subItr;

	@Mock
	private Resource subRes;

	private ValueMap valueMap;

	@Mock
	private Iterable<Resource> children;

	@Mock
	private Iterator<Resource> iterator;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		context.currentResource(resource);
		context.registerService(ModelFactory.class, modelFactory);

		resourceResolver = Mockito.mock(ResourceResolver.class);
		valueMap = Mockito.mock(ValueMap.class);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.BackNavigationModel#init()}.
	 * 
	 * @throws NoSuchFieldException
	 */
	@Test
	void testInit() throws NoSuchFieldException {
		PrivateAccessor.setField(backNavigationModel, "currentPage", currentPage);
		PrivateAccessor.setField(backNavigationModel, "resource", resource);
		Mockito.when(resource.getResourceResolver()).thenReturn(resourceResolver);
		Mockito.when(resourceResolver.adaptTo(Externalizer.class)).thenReturn(externalizer);
		Mockito.when(currentPage.getParent()).thenReturn(parentPage);
		Mockito.when(parentPage.getPath()).thenReturn("/content/vida/in/en/owner_manual_header");
		Mockito.when(resourceResolver.getResource("/content/vida/in/en/owner_manual_header")).thenReturn(res);
		Mockito.when(res.getParent()).thenReturn(parent);
		Mockito.when(parent.getParent()).thenReturn(parent);
		Mockito.when(parent.getChildren()).thenReturn(subChild);
		Mockito.when(subChild.iterator()).thenReturn(subItr);
		Mockito.when(subItr.hasNext()).thenReturn(true, true, true, true, false);
		Mockito.when(subItr.next()).thenReturn(subRes);
		Mockito.when(subRes.getValueMap()).thenReturn(valueMap);
		Mockito.when(valueMap.get(JcrConstants.JCR_PRIMARYTYPE, String.class)).thenReturn("cq:Page");
		Mockito.when(subRes.getPath()).thenReturn("/content/vida/in/en");
		backNavigationModel.init();

		Mockito.when(valueMap.get(JcrConstants.JCR_PRIMARYTYPE, String.class)).thenReturn("page");
		Mockito.when(externalizer.publishLink(resourceResolver, "/profile/owners-manual"))
				.thenReturn("/content/vida/in/en");
		Mockito.when(resourceResolver.map("/content/vida/in/en")).thenReturn("/content/vida/in/en");
		backNavigationModel.init();

		Mockito.when(parentPage.getPath()).thenReturn("/content/vida/in/en");
		Mockito.when(resourceResolver.getResource("/content/vida/in/en")).thenReturn(res);
		Mockito.when(subRes.getParent()).thenReturn(subparent);
		Mockito.when(subparent.getParent()).thenReturn(subparent);
		Mockito.when(subparent.getChildren()).thenReturn(children);
		Mockito.when(children.iterator()).thenReturn(iterator);
		Mockito.when(iterator.hasNext()).thenReturn(true, true, false);
		Mockito.when(iterator.next()).thenReturn(next);
		Mockito.when(next.getName()).thenReturn("index");
		Mockito.when(next.getPath()).thenReturn("/content/vida/in/en");
		backNavigationModel.init();

		Mockito.when(next.getName()).thenReturn("title");
		backNavigationModel.init();

		Mockito.when(res.getParent()).thenReturn(parent);
		Mockito.when(parent.getParent()).thenReturn(null);
		backNavigationModel.init();

		assertNotNull(backNavigationModel);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.BackNavigationModel#getBackNavPath()}.
	 */
	@Test
	void testGetBackNavPath() {
	}

}
