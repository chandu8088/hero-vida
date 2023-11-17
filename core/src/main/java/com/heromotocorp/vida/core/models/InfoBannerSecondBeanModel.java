package com.heromotocorp.vida.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;


/**
 * The type Info Banner Secondary bean model.
 */
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class InfoBannerSecondBeanModel {

    
    
    @Inject
    private String accordiontitle;

    @Inject
    private String accordiondescription;
      
    
    private double delaytime;
    

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
    public String getAccordiontitle() {
        return accordiontitle;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getAccordiondescription() {
        return accordiondescription;
    }


}
