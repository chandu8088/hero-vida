package com.heromotocorp.vida.core.models;

import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Source;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.Externalizer;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;
import com.heromotocorp.vida.core.utils.Constants;

/**
 * The type Back Navigation model.
 */
@Model(adaptables = { SlingHttpServletRequest.class,
		Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BackNavigationModel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(BackNavigationModel.class);

	/** The sling http servlet request. */
	@SlingObject
	private Resource resource;

	/** The current page. */
	@Inject
	@Source("script-bindings")
	private Page currentPage;

	/** The Constant INDEX. */
	public static final String INDEX = "index";

	/** The Constant MASTER. */
	public static final String MASTER = "/master";

	/** The Constant OWNERS_MANUAL. */
	public static final String OWNERS_MANUAL = "owner_manual_header";

	/** The externalizer. */
	private Externalizer externalizer;

	/** The Constant OWNERS_MANUAL_PATH. */
	public static final String OWNERS_MANUAL_PATH = "/profile/owners-manual";

	@PostConstruct
	public void init() {
		LOGGER.info("Executing init method of BackNavigationModel.");
		externalizer = resource.getResourceResolver().adaptTo(Externalizer.class);
		getBackNavPath();
	}

	public String getBackNavPath() {
		String mainpath = currentPage.getParent().getPath();
		Resource res = resource.getResourceResolver().getResource(mainpath);
		Resource parent = res.getParent().getParent();
		if (parent != null) {
			Iterable<Resource> subChild = parent.getChildren();
			Iterator<Resource> subItr = subChild.iterator();
			while (subItr.hasNext()) {
				Resource subRes = subItr.next();
				if (Constants.CQ_PAGE.equals(subRes.getValueMap().get(JcrConstants.JCR_PRIMARYTYPE, String.class))) {
					return subRes.getPath() + MASTER;
				} else if (mainpath.contains(OWNERS_MANUAL)) {
					return resource.getResourceResolver().map(externalizer.publishLink(resource.getResourceResolver(),OWNERS_MANUAL_PATH));
				} else {
					Iterable<Resource> children = subRes.getParent().getParent().getChildren();
					Iterator<Resource> iterator = children.iterator();
					while (iterator.hasNext()) {
						Resource next = iterator.next();
						if (INDEX.equals(next.getName())) {
							return next.getPath() + MASTER;
						}
					}
				}
			}
		}
		return "";
	}

}
