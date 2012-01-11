package org.jboss.seam.social.examples.foobarter;

import java.util.List;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.seam.security.Identity;
import org.jboss.seam.social.Current;
import org.jboss.seam.social.HasStatus;
import org.jboss.seam.social.MultiServicesManager;
import org.jboss.seam.social.examples.foobarter.model.IdentityObject;
import org.jboss.seam.social.examples.foobarter.model.SocialMessage;

@Model
public class SocialMessages {
    
    @Inject
    EntityManager em;
    
    @Inject
    MultiServicesManager servicesManager;
    
    @Inject
    Identity identity;
    
    String message;
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SocialMessage> getMessages() {
        return em.createQuery("from SocialMessage m").getResultList();
    }   
    
    public void post() {
        HasStatus statusService = (HasStatus)servicesManager.getCurrentService();
        statusService.updateStatus(message);
        
        SocialMessage sm = new SocialMessage();
        sm.setMessage(message);
        sm.setUser(em.find( IdentityObject.class, identity.getUser().getId()));
        
        em.persist(sm);
    }
}
