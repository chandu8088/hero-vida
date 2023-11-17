package com.heromotocorp.vida.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * This is the Advanced Accordion model
 */
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AdvancedAccordionModel {
    /**
     * Taking the Primary Navigation data in the below list.
     */
    @Inject
    private String title;
    
    @Inject
    private String subtitle;

   

	@Inject
    private List <AdvancedAccordionBeanModel> advanced = new ArrayList<AdvancedAccordionBeanModel>();
    /**
     * Gets heading.
     *
     * @return the heading
     */
    public String getTitle() {
        return title;
    }
    
    public String getSubtitle() {
		return subtitle;
	}

    /**
     * Gets items.
     *
     * @return the items
     */
    public List<AdvancedAccordionBeanModel> getAdvanced() {
        return advanced != null ? new ArrayList<>(advanced) : Collections.emptyList();
    }

    @PostConstruct
    public void init() {
        double delayTime =0.2;
        incrementDelayTime(delayTime);
    }

    private void incrementDelayTime(double delayTime){
        double counter = 0.1;
        for(AdvancedAccordionBeanModel i: advanced){
            i.setDelaytime(delayTime);
            delayTime += counter;
        }
    }
}
