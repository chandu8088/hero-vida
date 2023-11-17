package com.heromotocorp.vida.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is the Main Info Banner model
 */
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class InfoBannerModel {
    /**
     * Taking the Info Banner data in the below list.
     */
	
	@Inject
	private String bannertype;
	      
	@Inject
    private String title;
    
    @Inject
    private String description;
    
    
    public String getBannertype() {
		return bannertype;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

    @Inject
    private List <InfoBannerFirstBeanModel> multivalues;

	@Inject
	private List <InfoBannerSecondBeanModel> accordionbanner = new ArrayList<InfoBannerSecondBeanModel>();

        
    public List<InfoBannerFirstBeanModel> getMultivalues() {
        return multivalues != null ? new ArrayList<>(multivalues) : Collections.emptyList();
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public List<InfoBannerSecondBeanModel> getAccordionbanner() {
        return accordionbanner != null ? new ArrayList<>(accordionbanner) : Collections.emptyList();
    }

    @PostConstruct
    public void init() {
        double delayTime =0.2;
        incrementDelayTime(delayTime);
    }

    private void incrementDelayTime(double delayTime){
        double counter = 0.1;
        for(InfoBannerSecondBeanModel i: accordionbanner){
            i.setDelaytime(delayTime);
            delayTime += counter;
        }
    }
}
