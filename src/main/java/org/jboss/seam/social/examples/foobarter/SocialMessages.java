package org.jboss.seam.social.examples.foobarter;

import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.LoggedIn;
import org.jboss.seam.social.Current;
import org.jboss.seam.social.MultiServicesManager;
import org.jboss.seam.social.Twitter;
import org.jboss.seam.social.examples.foobarter.model.IdentityObject;
import org.jboss.seam.social.examples.foobarter.model.SocialMessage;
import org.jboss.seam.social.twitter.TwitterTimelineService;
import org.jboss.seam.social.twitter.TwitterUserService;

@Model
@LoggedIn
public class SocialMessages {
    
    @Inject
    EntityManager em;

    @Inject
    @Twitter
    TwitterTimelineService twitterService;
    
    @Inject
    Identity identity;
    
    private String message;
    private boolean postToTwitter = false;
   
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SocialMessage> getMessages() {
        return em.createQuery("from SocialMessage m order by m.date desc limit 20").getResultList();
    }   
    
    public void post() {
    	if (postToTwitter) {
    		twitterService.updateStatus(message);
    	}
        
        SocialMessage sm = new SocialMessage();
        sm.setMessage(message);
        sm.setUser(em.find( IdentityObject.class, identity.getUser().getId()));
        sm.setDate(new Date());
        
        em.persist(sm);
    }

	public void setPostToTwitter(boolean postToTwitter) {
		this.postToTwitter = postToTwitter;
	}

	public boolean isPostToTwitter() {
		return postToTwitter;
	}
}
