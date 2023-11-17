package com.heromotocorp.vida.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;


/**
 * The type Banner carousel bean model.
 */
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AdvancedAccordionBeanModel {

    @Inject
    private String heading;

    @Inject
    private String description;
    
    
    @Inject
    private String accordionicon;
    
    
    private double delaytime;
    
    
    public String getAccordionicon() {
		return accordionicon;
	}

    public double getDelaytime() {
        return delaytime;
    }

    public void setDelaytime(double delaytime) {
        this.delaytime = delaytime;
    }

    /**
     * Gets heading.
     *
     * @return the heading
     */
    public String getHeading() {
        return heading;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

}
