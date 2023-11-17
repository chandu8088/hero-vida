package com.heromotocorp.vida.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Source;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.wcm.api.Page;
import com.heromotocorp.vida.core.utils.Constants;

/**
 * The Class DataLayerPageModel.
 */
@Model(adaptables = {SlingHttpServletRequest.class,Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class DataLayerPageModel {

	/** The current page. */
	@Inject
	@Source("script-bindings")
	private Page currentPage;

	/** The resolver. */
	@SlingObject
	private ResourceResolver resolver;

	/** The request. */
	@SlingObject
	private SlingHttpServletRequest request;

	/** The page name. */
	String pageName;
	
	/** The page category. */
	@Inject
	String pageCategory;
	
	/** The keywords. */
	@Inject
	String keywords;

	/** The Facebook content. */
	@Default(values=StringUtils.EMPTY)
	@ValueMapValue
	String fbdomaincontent;
	
	/** The Analytics Page Name. */
	@Default(values=StringUtils.EMPTY)
	@ValueMapValue
	String analyticsPageName;
	
	/** The Social Share Title. */
	@Default(values=StringUtils.EMPTY)
	@ValueMapValue
	String socialShareTitle;
	
	/** The Social Share Image. */
	@Default(values=StringUtils.EMPTY)
	@ValueMapValue
	String socialShareImage;
	
	/** The Social Share Desc. */
	@Default(values=StringUtils.EMPTY)
	@ValueMapValue
	String socialShareDesc;

	/**
	 * Gets the page category.
	 *
	 * @return the page category
	 */
	public String getPageCategory() {
		return pageCategory;
	}
 
	
	/** The is error page. */
	String isErrorPage = "false";

	/** The error code. */
	String errorCode;

	/** The error description. */
	String errorDescription;

	/**
	 * Gets the page name.
	 *
	 * @return the page name
	 */
	public String getPageName() {
		return pageName;
	}

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the error code.
	 *
	 * @param errorCode the new error code
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Gets the error description.
	 *
	 * @return the error description
	 */
	public String getErrorDescription() {
		return errorDescription;
	}

	/**
	 * Sets the error description.
	 *
	 * @param errorDescription the new error description
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	/**
	 * Sets the page name.
	 *
	 * @param pageName the new page name
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	/**
	 * Gets the error page.
	 *
	 * @return the error page
	 */
	public String getErrorPage() {
		return String.valueOf(isErrorPage);
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		String path = currentPage.getPath();
		if (!path.contains(Constants.CONF)) {
			popualateDatalayerData(path);
		}
	}

	/**
	 * Popualate datalayer data.
	 *
	 * @param path the path
	 */
	private void popualateDatalayerData(String path) {

		String[] tokensArr = path.split(Constants.PATH_SEPEARTOR);
		pageName = getTitle();
		if (tokensArr[tokensArr.length - 1].equalsIgnoreCase(Constants.PAGE_TITLE_404)
				|| currentPage.getTitle().equalsIgnoreCase(Constants.PAGE_TITLE_404)) {
			isErrorPage = "true";
			errorCode = Constants.PAGE_TITLE_404;
			errorDescription = Constants.DEC_404;
		}

	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		String title = currentPage.getTitle();
		if (StringUtils.isBlank(title)) {
			title = currentPage.getName();
		}
		return title;
	}

	public String getIsErrorPage() {
		return isErrorPage;
	}

	public void setIsErrorPage(String isErrorPage) {
		this.isErrorPage = isErrorPage;
	}

	public String getAnalyticsPageName() {
		return analyticsPageName;
	}

	public String getSocialShareTitle() {
		return socialShareTitle;
	}

	public String getSocialShareImage() {
		return socialShareImage;
	}

	public String getSocialShareDesc() {
		return socialShareDesc;
	}

	public String getKeywords() {
		return keywords;
	}
	
	public String getFbdomaincontent() {
		return fbdomaincontent;
	}

}
