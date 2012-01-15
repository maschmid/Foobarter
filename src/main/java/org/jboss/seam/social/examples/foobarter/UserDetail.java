package org.jboss.seam.social.examples.foobarter;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.node.ObjectNode;
import org.jboss.seam.faces.context.RenderScoped;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.Secures;
import org.jboss.seam.social.JsonMapper;
import org.jboss.seam.social.Twitter;
import org.jboss.seam.social.examples.foobarter.model.IdentityObject;
import org.jboss.seam.social.oauth.OAuthService;
import org.jboss.seam.social.oauth.OAuthUser;
import org.jboss.seam.social.rest.RestVerb;
import org.jboss.seam.social.twitter.Tweet;
import org.jboss.seam.social.twitter.TwitterFriendService;
import org.jboss.seam.social.twitter.TwitterTimelineService;
import org.jboss.seam.social.twitter.TwitterProfile;
import org.jboss.seam.social.twitter.TwitterUserService;

@Named
@RequestScoped
public class UserDetail {
	
	@Inject
	EntityManager em;

    /**
     * 
     */
    private static final long serialVersionUID = -7329350178729100114L;
    
    private String screenName;
    private IdentityObject user;

    public IdentityObject getUser() {
    	if (user == null) {
    		user = (IdentityObject) em.createQuery("from IdentityObject o where o.screenName = :name").setParameter("name", screenName).getSingleResult();
    	}
        return user;
    }
    
    public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getScreenName() {
		return screenName;
	}
	
	@Inject
	@Twitter
	OAuthService oauthService;

	@Inject
    @Twitter
    TwitterTimelineService twitterTimelineService;
    
    @Inject
    @Twitter
    TwitterFriendService twitterFriendService;
    
    @Inject
    @Twitter
    TwitterUserService twitterUserService;
    
    @Inject
    JsonMapper jsonService;
    
    List<Tweet> getTweets() throws JsonParseException, JsonMappingException, IOException {                
        return twitterTimelineService.getUserTimeline(Long.parseLong(getUser().getOauthId()));
    }
    
    @Secures @Follower
    public boolean isFollower(Identity identity) {
    	OAuthUser currentUser = (OAuthUser)identity.getUser();
    	
    	// Allow user to view own's posts
    	if (currentUser.getOauthId().equals(getUser().getOauthId())) {
    		return true;
    	}
    	
    	TwitterProfile profile = twitterUserService.getUserProfile(user.getScreenName());
    	if (profile.isProtected()) {
    		
    		ObjectNode root = jsonService.requestObject(oauthService.sendSignedRequest(RestVerb.GET, "http://api.twitter.com/1/friendships/show.json?target_id="+getUser().getOauthId()+"&source_id="+currentUser.getOauthId()), ObjectNode.class);
    		return root.get("relationship").get("target").get("followed_by").getBooleanValue();
    		
    		//return twitterFriendService.friendshipExists(((TwitterProfile)currentUser.getUserProfile()).getScreenName(), getUser().getScreenName());
    	}
    	else {
    		return true;
    	}
    }
}
