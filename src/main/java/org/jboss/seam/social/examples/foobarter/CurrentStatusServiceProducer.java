package org.jboss.seam.social.examples.foobarter;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.jboss.seam.social.Current;
import org.jboss.seam.social.HasStatus;
import org.jboss.seam.social.MultiServicesManager;
import org.jboss.seam.social.oauth.OAuthService;

public class CurrentStatusServiceProducer {
    @Named
    @Produces
    @Current    
    HasStatus getCurrentStatusService(MultiServicesManager manager) {
        return (HasStatus)manager.getCurrentService();
    }
    
    @Named
    @Produces
    @Current    
    OAuthService getCurrentService(MultiServicesManager manager) {
        return manager.getCurrentService();
    }
}
