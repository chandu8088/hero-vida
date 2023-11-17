package com.heromotocorp.vida.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

/**
 * This model is to get the internal image and image redirecting link
 * of the primary navigation segment.
 */
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PrimaryNavigationBeanModel {

    @Inject
    private String primarynavtext;

    @Inject
    private String primarynavtextlink;

    @Inject
    private String profilelinkcheckbox;

    @Inject
    private String loginregisterlinkcheckbox;

    /**
     * Gets primarynavtext.
     *
     * @return the primarynavtext
     */
    public String getPrimarynavtext() {
        return primarynavtext;
    }

    /**
     * Gets primarynavtextlink.
     *
     * @return the primarynavtextlink
     */
    public String getPrimarynavtextlink() {
        return primarynavtextlink;
    }

    /**
     * Gets profilelinkcheckbox.
     *
     * @return the profilelinkcheckbox
     */
    public String getProfilelinkcheckbox() {
        return profilelinkcheckbox;
    }

    /**
     * Gets loginregisterlinkcheckbox.
     *
     * @return the loginregisterlinkcheckbox
     */
    public String getLoginregisterlinkcheckbox() {
        return loginregisterlinkcheckbox;
    }
}
