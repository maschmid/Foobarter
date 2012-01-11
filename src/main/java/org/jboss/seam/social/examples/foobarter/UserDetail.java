package org.jboss.seam.social.examples.foobarter;

import javax.enterprise.inject.Model;

import org.jboss.seam.social.examples.foobarter.model.IdentityObject;

@Model
public class UserDetail {
    private IdentityObject user;

    public void setUser(IdentityObject user) {
        this.user = user;
    }

    public IdentityObject getUser() {
        return user;
    }    
}
