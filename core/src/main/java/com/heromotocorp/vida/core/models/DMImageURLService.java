package com.heromotocorp.vida.core.models;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Reference;

import com.adobe.cq.sightly.WCMUsePojo;
import com.google.common.net.UrlEscapers;
import com.heromotocorp.vida.core.utils.CommonUtils;

public class DMImageURLService extends WCMUsePojo {

	private static final String DM_IS_IMAGE_PATH = "is/image/";

	private String imageDamPath;
	
	@Override
	public void activate() throws Exception {

		imageDamPath = String.valueOf(get("imageDamPath", String.class));

	}

	/**
	 * @return
	 */
	public String getDMImagePathLink() {
		if (StringUtils.isNotBlank(imageDamPath)&& StringUtils.contains(imageDamPath, "/content/dam")
				&& !StringUtils.contains(imageDamPath, ".gif")) {
			Resource imageRes = getResourceResolver().getResource(imageDamPath
					+ "/jcr:content/metadata");
			if (Objects.nonNull(imageRes)) {
				ValueMap vMap = imageRes.getValueMap();
				String dmImagePath = CommonUtils.getPropertyValue(vMap,
						"dam:scene7File");
				if(StringUtils.isEmpty(dmImagePath)){
					return imageDamPath;
				}
				String dmPublishUrl = "https://media.vidaworld.com/";
				imageDamPath = StringUtils.EMPTY;
				if (!StringUtils.isBlank(dmImagePath)) {
					imageDamPath = UrlEscapers.urlFragmentEscaper().escape(dmPublishUrl + DM_IS_IMAGE_PATH + dmImagePath)+"?fmt=webp";
				}
			} else {
				return imageDamPath; 
			}
		}
		return imageDamPath;
	}

	public String  getImageforWidth360(){
		if(getDMImagePathLink().contains(DM_IS_IMAGE_PATH)){
			return  getDMImagePathLink()+"&wid=360";
		}
		return imageDamPath;
	} 
	
	public String  getImageforWidth1024(){
		if(getDMImagePathLink().contains(DM_IS_IMAGE_PATH)){
			return  getDMImagePathLink()+"&wid=1024";
		}
		return imageDamPath;
	} 
	
	

}
