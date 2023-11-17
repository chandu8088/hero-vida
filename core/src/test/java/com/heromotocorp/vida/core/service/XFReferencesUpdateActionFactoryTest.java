/**
 * 
 */
package com.heromotocorp.vida.core.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.Locale;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Value;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.adobe.cq.xf.ExperienceFragmentsServiceFactory;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;
import com.day.cq.wcm.msm.api.LiveCopy;
import com.day.cq.wcm.msm.api.LiveRelationship;
import com.day.cq.wcm.msm.api.LiveRelationshipManager;
import com.day.cq.wcm.msm.api.RolloutManager;
import com.heromotocorp.vida.core.service.XFReferencesUpdateActionFactory.XFReferencesUpdateAction;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for XFReferencesUpdateActionFactory
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class XFReferencesUpdateActionFactoryTest {

	private final AemContext context = new AemContext();

	@InjectMocks
	private XFReferencesUpdateActionFactory xfReferencesUpdateActionFactory;
	
	private RolloutManager rolloutManager;

	private LiveRelationshipManager relationshipManager;

	private ExperienceFragmentsServiceFactory experienceFragmentsServiceFactory;

	private ValueMap valueMap;

	private XFReferencesUpdateAction xFReferencesUpdateAction;

	@Mock
	private Resource source;
	
	@Mock
	private Resource target;
	
	@Mock
	private LiveRelationship relation;
	
	@Mock
	private ResourceResolver resolver;
	
	@Mock
	private PageManager pageManager;
	
	@Mock
	private Page targetPage;
	
	@Mock
	private Resource sourceRoot;
	
	@Mock
	private Resource cta;
	
	@Mock
	private Node sourceNode;
	
	@Mock
	private Node targetNode;
	
	@Mock
	private PropertyIterator pi;
	
	@Mock
	private PropertyIterator iter;
	
	@Mock
	private NodeIterator nodeIter;
	
	@Mock
	private LiveCopy liveCopy;
	
	@Mock
	private Property property;
	
	@Mock
	private Property p;
	
	@Mock
	private Value value;
	
	@Mock
	private Value value1;
	
	@Mock
	private Page ctaPage;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		valueMap = Mockito.mock(ValueMap.class);

		rolloutManager = context.getService(RolloutManager.class);
		rolloutManager = Mockito.mock(RolloutManager.class);
		PrivateAccessor.setField(xfReferencesUpdateActionFactory, "rolloutManager", rolloutManager);

		relationshipManager = context.getService(LiveRelationshipManager.class);
		relationshipManager = mock(LiveRelationshipManager.class);
		PrivateAccessor.setField(xfReferencesUpdateActionFactory, "relationshipManager", relationshipManager);

		experienceFragmentsServiceFactory = context.getService(ExperienceFragmentsServiceFactory.class);
		experienceFragmentsServiceFactory = mock(ExperienceFragmentsServiceFactory.class);
		PrivateAccessor.setField(xfReferencesUpdateActionFactory, "experienceFragmentsServiceFactory",
				experienceFragmentsServiceFactory);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.service.XFReferencesUpdateActionFactory#newActionInstance(org.apache.sling.api.resource.ValueMap)}.
	 * @throws WCMException 
	 */
	@Test
	void testNewActionInstanceValueMap() throws WCMException {
		xfReferencesUpdateActionFactory.newActionInstance(valueMap);
		assertNotNull(xfReferencesUpdateActionFactory);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.service.XFReferencesUpdateActionFactory#createsAction()}.
	 */
	@Test
	void testCreatesAction() {
		xfReferencesUpdateActionFactory.createsAction();
		assertNotNull(xfReferencesUpdateActionFactory);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.service.XFReferencesUpdateAction#doExecute()}.
	 * @throws WCMException 
	 * @throws RepositoryException 
	 */
	@Test
	void testDoExecute() throws WCMException, RepositoryException {
		Value[] values = {value};
		
		Mockito.when(target.getResourceResolver()).thenReturn(resolver);
		Mockito.when(resolver.adaptTo(PageManager.class)).thenReturn(pageManager);
		Mockito.when(relation.getLiveCopy()).thenReturn(liveCopy);
		Mockito.when(liveCopy.getPath()).thenReturn("/content/vida/in");
		Mockito.when(pageManager.getPage("/content/vida/in")).thenReturn(targetPage);
		Mockito.when(source.getPath()).thenReturn("/content/vida/source");
		Mockito.when(resolver.getResource("/content/vida/source")).thenReturn(sourceRoot);
		Mockito.when(sourceRoot.adaptTo(Node.class)).thenReturn(sourceNode);
		Mockito.when(sourceNode.getProperties()).thenReturn(pi);
		Mockito.when(pi.hasNext()).thenReturn(true, true, false);
		Mockito.when(pi.nextProperty()).thenReturn(property);
		Mockito.when(property.isMultiple()).thenReturn(true, false);
		Mockito.when(property.getValues()).thenReturn(values);
		Mockito.when(value.getType()).thenReturn(1);
		Mockito.when(value.getString()).thenReturn("/content/vida");
		Mockito.when(resolver.getResource("/content/vida")).thenReturn(cta);
		Mockito.when(target.getPath()).thenReturn("/content/vida/in/en/target");
		Mockito.when(targetPage.getPath()).thenReturn("/content/experience-fragments/vida/in/en");
		Mockito.when(cta.getPath()).thenReturn("/content/vida/in/en");
		Mockito.when(pageManager.getPage("/content/vida/in/en")).thenReturn(ctaPage);
		Mockito.when(target.adaptTo(Node.class)).thenReturn(targetNode);
		Mockito.when(ctaPage.getLanguage(Boolean.FALSE)).thenReturn(Locale.ENGLISH);
		Mockito.when(targetNode.getProperties()).thenReturn(iter);
		Mockito.when(iter.hasNext()).thenReturn(false);
		Mockito.when(targetNode.getNodes()).thenReturn(nodeIter);
		Mockito.when(nodeIter.hasNext()).thenReturn(false);
		Mockito.when(property.getValue()).thenReturn(value1);
		Mockito.when(value1.getType()).thenReturn(10);
		xFReferencesUpdateAction = xfReferencesUpdateActionFactory.newActionInstance(valueMap);
		xFReferencesUpdateAction.doExecute(source, target, relation, false);
		assertNotNull(xFReferencesUpdateAction);
	}
	
	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.service.XFReferencesUpdateAction#doExecute()}.
	 * @throws WCMException 
	 * @throws RepositoryException 
	 */
	@Test
	void testDoExecute1() throws WCMException, RepositoryException {
		Value[] values = {value};
		
		Mockito.when(target.getResourceResolver()).thenReturn(resolver);
		Mockito.when(resolver.adaptTo(PageManager.class)).thenReturn(pageManager);
		Mockito.when(relation.getLiveCopy()).thenReturn(liveCopy);
		Mockito.when(liveCopy.getPath()).thenReturn("/content/vida/in");
		Mockito.when(pageManager.getPage("/content/vida/in")).thenReturn(targetPage);
		Mockito.when(source.getPath()).thenReturn("/content/vida/source");
		Mockito.when(resolver.getResource("/content/vida/source")).thenReturn(sourceRoot);
		Mockito.when(sourceRoot.adaptTo(Node.class)).thenReturn(sourceNode);
		Mockito.when(sourceNode.getProperties()).thenReturn(pi);
		Mockito.when(pi.hasNext()).thenReturn(true, true, false);
		Mockito.when(pi.nextProperty()).thenReturn(property);
		Mockito.when(property.isMultiple()).thenReturn(true, false);
		Mockito.when(property.getValues()).thenReturn(values);
		Mockito.when(value.getType()).thenReturn(1);
		Mockito.when(value.getString()).thenReturn("/content/vida");
		Mockito.when(property.getValue()).thenReturn(value1);
		Mockito.when(value1.getType()).thenReturn(1);
		Mockito.when(value.getString()).thenReturn("damcontent");
		xFReferencesUpdateAction = xfReferencesUpdateActionFactory.newActionInstance(valueMap);
		xFReferencesUpdateAction.doExecute(source, target, relation, false);
		
		Mockito.when(pi.hasNext()).thenReturn(true, true, false);
		Mockito.when(property.isMultiple()).thenReturn(true, false);
		Mockito.when(value.getString()).thenReturn(null);
		xFReferencesUpdateAction = xfReferencesUpdateActionFactory.newActionInstance(valueMap);
		xFReferencesUpdateAction.doExecute(source, target, relation, false);
		
		Mockito.when(pi.hasNext()).thenReturn(true, true, false);
		Mockito.when(property.isMultiple()).thenReturn(true, false);
		Mockito.when(value.getString()).thenReturn(null);
		Mockito.when(pageManager.getPage("/content/vida/in")).thenReturn(null);
		xFReferencesUpdateAction = xfReferencesUpdateActionFactory.newActionInstance(valueMap);
		xFReferencesUpdateAction.doExecute(source, target, relation, false);

		xFReferencesUpdateAction.doExecute(null, target, relation, false);

		xFReferencesUpdateAction.doExecute(source, target, null, false);
		
		xFReferencesUpdateAction.doExecute(source, null, relation, false);
		assertNotNull(xFReferencesUpdateAction);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.service.XFReferencesUpdateAction#handles()}.
	 * @throws WCMException 
	 * @throws RepositoryException 
	 */
	@Test
	void testHandles() throws RepositoryException, WCMException {
		Mockito.when(source.getPath()).thenReturn("/content/vida/in");
		xFReferencesUpdateAction = xfReferencesUpdateActionFactory.newActionInstance(valueMap);
		xFReferencesUpdateAction.handles(source, target, relation, false);
		assertNotNull(xfReferencesUpdateActionFactory);
	}

}
