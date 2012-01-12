package org.jboss.seam.social.examples.foobarter;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jboss.seam.faces.context.RenderScoped;
import org.jboss.seam.social.JsonMapper;
import org.jboss.seam.social.MultiServicesManager;
import org.jboss.seam.social.Twitter;
import org.jboss.seam.social.examples.foobarter.model.IdentityObject;
import org.jboss.seam.social.rest.RestVerb;
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
    @Twitter
    TwitterService twitterService;
    
    @Inject
    JsonMapper jsonMapper;
    
    List<Tweet> getTweets() {        
        //ObjectMapper mapper = new ObjectMapper();
        return jsonMapper.requestObject(twitterService.sendSignedRequest(RestVerb.GET, "http://api.twitter.com/1/statuses/user_timeline.json?user_id=" + user.getOauthId()), List.class);
    }
}
