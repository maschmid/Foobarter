package org.jboss.seam.social.examples.foobarter;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.events.DeferredAuthenticationEvent;
import org.jboss.seam.social.examples.foobarter.authenticator.OAuthUser;
import org.jboss.seam.social.examples.foobarter.model.IdentityObject;

public class UserDataSync {
    
    @Inject
    EntityManager em;
    
    public void onDeferredEvent(@Observes DeferredAuthenticationEvent event, Identity identity) {
        if (event.isSuccess()) {
            OAuthUser oauthUser = (OAuthUser)identity.getUser();
            IdentityObject io = em.find(IdentityObject.class, oauthUser.getKey());
            io.setFullName(oauthUser.getUserProfile().getFullName());           
            io.setProfileImageUrl(oauthUser.getUserProfile().getProfileImageUrl());
        }
    }
}
