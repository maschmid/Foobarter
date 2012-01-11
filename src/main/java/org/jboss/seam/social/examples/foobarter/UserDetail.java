package org.jboss.seam.social.examples.foobarter;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.faces.context.RenderScoped;
import org.jboss.seam.social.MultiServicesManager;
import org.jboss.seam.social.examples.foobarter.model.IdentityObject;
import org.jboss.seam.social.twitter.TwitterService;
import org.jboss.seam.social.twitter.model.Tweet;

@Named
@RenderScoped
public class UserDetail implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7329350178729100114L;
    
    private IdentityObject user;

    public void setUser(IdentityObject user) {
        this.user = user;
    }

    public IdentityObject getUser() {
        return user;
    }
    
    @Inject
    TwitterService twitterService;
    
    /*List<Tweet> getTweets() {
        //twitterService
    }*/
}
