package org.jboss.seam.social.examples.foobarter;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.jboss.seam.faces.context.RenderScoped;
import org.jboss.seam.social.Twitter;
import org.jboss.seam.social.examples.foobarter.model.IdentityObject;
import org.jboss.seam.social.twitter.Tweet;
import org.jboss.seam.social.twitter.TwitterTimelineService;

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
    @Twitter
    TwitterTimelineService twitterTimelineService;
    
    List<Tweet> getTweets() throws JsonParseException, JsonMappingException, IOException {                
        return twitterTimelineService.getUserTimeline(Long.parseLong(user.getOauthId()));
    }
}
