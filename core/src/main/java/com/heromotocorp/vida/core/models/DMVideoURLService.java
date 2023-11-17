package com.heromotocorp.vida.core.models;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Reference;

import com.adobe.cq.sightly.WCMUsePojo;
import com.heromotocorp.vida.core.utils.CommonUtils;

public class DMVideoURLService extends WCMUsePojo {

	private static final String DM_IS_VIDEO_PATH = "is/content/";

	private String videoDamPath;
	
	@Reference
	private ResourceResolverFactory resolverFactory;

	@Override
	public void activate() throws Exception {

		videoDamPath = String.valueOf(get("videoDamPath", String.class));

	}

	/**
	 * @return
	 */
	public String getDMVideoPathLink() {
		if (StringUtils.isNotBlank(videoDamPath)) {
			Resource imageRes = getResourceResolver().getResource(videoDamPath
					+ "/jcr:content/metadata");
			if (Objects.nonNull(imageRes)) {
				ValueMap vMap = imageRes.getValueMap();
				String dmVideoPath = CommonUtils.getPropertyValue(vMap,
						"dam:scene7File");
				if(StringUtils.isEmpty(dmVideoPath)){
					return videoDamPath;
				}
				
				String dmPublishUrl = CommonUtils.getPropertyValue(vMap,
						"dam:scene7Domain");
				videoDamPath = StringUtils.EMPTY;
				if (!StringUtils.isBlank(dmVideoPath)
						&& !StringUtils.isBlank(dmPublishUrl)) {
					videoDamPath = dmPublishUrl + DM_IS_VIDEO_PATH + dmVideoPath;
				}
			} else {
				//LOGGER.debug("Image Resource not found : {}", imageDamPath);
				return videoDamPath; 
			}
		}
		return videoDamPath;
	}
	

}
