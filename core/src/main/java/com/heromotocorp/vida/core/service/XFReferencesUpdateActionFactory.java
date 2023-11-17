package com.heromotocorp.vida.core.service;

import java.util.Collections;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.PropertyType;
import javax.jcr.RepositoryException;
import javax.jcr.Value;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.xf.ExperienceFragmentsServiceFactory;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;
import com.day.cq.wcm.commons.ReferenceSearch;
import com.day.cq.wcm.msm.api.LiveActionFactory;
import com.day.cq.wcm.msm.api.LiveRelationship;
import com.day.cq.wcm.msm.api.LiveRelationshipManager;
import com.day.cq.wcm.msm.api.RolloutManager;
import com.day.cq.wcm.msm.commons.BaseAction;
import com.day.cq.wcm.msm.commons.BaseActionFactory;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;

/**
 * A factory to update experience fragments link on rollout.
 */
@Component(service = LiveActionFactory.class, property = {
		LiveActionFactory.LIVE_ACTION_NAME + "=" + XFReferencesUpdateActionFactory.LIVE_ACTION_CLASS_NAME,
		LiveActionFactory.LIVE_ACTION_NAME + "=" + XFReferencesUpdateActionFactory.LIVE_ACTION_NAME })
public class XFReferencesUpdateActionFactory extends BaseActionFactory<BaseAction> {

	/** The Constant LIVE_ACTION_CLASS_NAME. */
	public static final String LIVE_ACTION_CLASS_NAME = "XFReferencesUpdateAction";

	/** The Constant LIVE_ACTION_NAME. */
	public static final String LIVE_ACTION_NAME = "referencesUpdateXF";

	/** The Constant CONTENT_PATH_REGEXP. */
	private static final String CONTENT_PATH_REGEXP = "/content/(vida)[\\w\\-/]*";

	/** The Constant CONTENT_PATH_PATTERN. */
	private static final Pattern CONTENT_PATH_PATTERN = Pattern.compile(CONTENT_PATH_REGEXP);

	/** The rollout manager. */
	@Reference
	private RolloutManager rolloutManager;

	/** The relationship manager. */
	@Reference
	private LiveRelationshipManager relationshipManager;

	/** The experience fragments service factory. */
	@Reference
	ExperienceFragmentsServiceFactory experienceFragmentsServiceFactory;

	/**
	 * New action instance.
	 *
	 * @param valueMap the value map
	 * @return the XF references update action
	 */
	@Override
	protected XFReferencesUpdateAction newActionInstance(ValueMap valueMap) {
		return new XFReferencesUpdateAction(valueMap, this);

	}

	/**
	 * Creates a new XFReferencesUpdateAction object.
	 *
	 * @return the string
	 */
	public String createsAction() {
		return LIVE_ACTION_NAME;
	}

	/**
	 * The Class XFReferencesUpdateAction.
	 */
	class XFReferencesUpdateAction extends BaseAction {

		/** The log. */
		private Logger log = LoggerFactory.getLogger(XFReferencesUpdateAction.class);

		/**
		 * Instantiates a new XF references update action.
		 *
		 * @param valueMap the value map
		 * @param factory  the factory
		 */
		public XFReferencesUpdateAction(ValueMap valueMap, XFReferencesUpdateActionFactory factory) {
			super(valueMap, factory);
		}

		/**
		 * Do execute.
		 *
		 * @param source       the source
		 * @param target       the target
		 * @param relation     the relation
		 * @param resetRollout the reset rollout
		 * @throws WCMException the WCM exception
		 */
		@Override
		public void doExecute(Resource source, Resource target, LiveRelationship relation, boolean resetRollout)
				throws WCMException {
			if (Objects.nonNull(target)) {
				ResourceResolver resolver = target.getResourceResolver();
				PageManager pageManager = resolver.adaptTo(PageManager.class);
				if (Objects.nonNull(relation)) {
					Page targetPage = pageManager.getPage(relation.getLiveCopy().getPath());
					if (Objects.nonNull(source)) {
						String sourcePath = source.getPath();
						Resource sourceRoot = resolver.getResource(sourcePath);
						Node sourceNode = sourceRoot.adaptTo(Node.class);
						try {
							PropertyIterator pi = sourceNode.getProperties();

							while (pi.hasNext()) {
								Property property = pi.nextProperty();
								if (property.isMultiple()) {
									for (Value value : property.getValues()) {
										processSingleValue(value, resolver, target, pageManager, targetPage);
									}
								} else {
									processSingleValue(property.getValue(), resolver, target, pageManager, targetPage);
								}
							}

						} catch (RepositoryException e) {
							log.error("RepositoryException", e);
						}
					}
				}
			}
		}

		/**
		 * Process single value.
		 *
		 * @param value       the value
		 * @param resolver    the resolver
		 * @param target      the target
		 * @param pageManager the page manager
		 * @param targetPage  the target page
		 * @throws RepositoryException the repository exception
		 */
		private void processSingleValue(Value value, ResourceResolver resolver, Resource target,
				PageManager pageManager, Page targetPage) throws RepositoryException {
			if (value.getType() != PropertyType.STRING) {
				return;
			}
			String ctaPath = value.getString();
			if (ctaPath == null || !ctaPath.startsWith("/content/vida")) {
				return;
			}
			Matcher pathMatcher = CONTENT_PATH_PATTERN.matcher(ctaPath);
			while (pathMatcher.find()) {
				Resource cta = resolver.getResource(pathMatcher.group());
				adjustReferences(pageManager, cta, target, targetPage);
			}
		}

		/**
		 * Adjust references.
		 *
		 * @param pageManager the page manager
		 * @param cta         the cta
		 * @param target      the target
		 * @param targetPage  the target page
		 * @throws RepositoryException the repository exception
		 */
		private void adjustReferences(PageManager pageManager, Resource cta, Resource target, Page targetPage)
				throws RepositoryException {
			if (Objects.nonNull(targetPage) && !target.getPath().contains("countryselector") && Objects.nonNull(cta)) {
				String[] array = targetPage.getPath().replace("/content/experience-fragments/vida/", StringUtils.EMPTY)
						.split(Constants.PATH_SEPEARTOR);
				Page ctaPage = pageManager.getPage(cta.getPath());
				String targetCountryLanguage = array[0] + Constants.PATH_SEPEARTOR + array[1];
				String ctaCountryLanguage = CommonUtils.getCountryCode(ctaPage) + Constants.PATH_SEPEARTOR
						+ CommonUtils.getLanguageCode(ctaPage);
				String targetPagePath = cta.getPath().replace(ctaCountryLanguage, targetCountryLanguage);
				new ReferenceSearch().adjustReferences(target.adaptTo(Node.class), cta.getPath(), targetPagePath, false,
						Collections.emptySet());
			}
		}

		/**
		 * Handles.
		 *
		 * @param source       the source
		 * @param target       the target
		 * @param relation     the relation
		 * @param resetRollout the reset rollout
		 * @return true, if successful
		 * @throws RepositoryException the repository exception
		 * @throws WCMException        the WCM exception
		 */
		@Override
		protected boolean handles(Resource source, Resource target, LiveRelationship relation, boolean resetRollout)
				throws RepositoryException, WCMException {
			return source.getPath().contains("experience-fragments");
		}

	}
}