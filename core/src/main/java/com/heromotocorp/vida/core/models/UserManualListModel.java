package com.heromotocorp.vida.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.jcr.resource.api.JcrResourceConstants;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.NameConstants;
import com.heromotocorp.vida.core.vo.ListModelVO;

/**
 * The User Manual List Model.
 */
@Model(adaptables = { Resource.class,
		SlingHttpServletRequest.class }, resourceType = "vida/components/usermanuallist", defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class UserManualListModel {

	@ChildResource
	public List<Resource> listpaths;

	/** The resource resolver . */
	@SlingObject
	private ResourceResolver resourceResolver;

	/** The Constant PAGEPATH. */
	public static final String PAGEPATH = "pagepath";

	public List<ListModelVO> getChildPagesPath() {
		List<ListModelVO> childPaths = new ArrayList<>();
		for (Resource page : listpaths) {
			page = resourceResolver.getResource(page.getValueMap().get(PAGEPATH).toString());
			if (page.hasChildren()
					&& JcrResourceConstants.NT_SLING_ORDERED_FOLDER.equals(page.getValueMap().get(JcrConstants.JCR_PRIMARYTYPE, String.class))) {
				Iterator<Resource> listChildren = page.listChildren();
				while (listChildren.hasNext()) {
					Resource childRes = listChildren.next();
					Iterable<Resource> children = childRes.getChildren();
					Iterator<Resource> itr = children.iterator();
					while (itr.hasNext()) {
						ListModelVO listModelVO = new ListModelVO();
						childRes = itr.next();
						if (NameConstants.NT_PAGE.equals(childRes.getValueMap().get(JcrConstants.JCR_PRIMARYTYPE, String.class))) {
							listModelVO.setPagePath(childRes.getPath());
							String path = childRes.getPath() + JcrConstants.JCR_CONTENT;
							listModelVO.setTitle(childRes.getChild(path).getValueMap().get(JcrConstants.JCR_TITLE, String.class));
							childPaths.add(listModelVO);
						}
					}
				}
			} else if (page.hasChildren() && NameConstants.NT_PAGE.equals(page.getValueMap().get(JcrConstants.JCR_PRIMARYTYPE, String.class))) {
				Iterator<Resource> listChildren = page.listChildren();
				while (listChildren.hasNext()) {
					Resource res = listChildren.next();
					ListModelVO listModelVO = new ListModelVO();
					if (NameConstants.NT_PAGE.equals(res.getValueMap().get(JcrConstants.JCR_PRIMARYTYPE, String.class))) {
						listModelVO.setPagePath(res.getPath());
						String path = res.getPath() + JcrConstants.JCR_CONTENT;
						listModelVO.setTitle(res.getChild(path).getValueMap().get(JcrConstants.JCR_TITLE, String.class));
						childPaths.add(listModelVO);
					}
				}
			}
		}
		return childPaths;
	}

}
