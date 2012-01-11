package org.jboss.seam.social.examples.foobarter;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.jboss.seam.social.examples.foobarter.model.IdentityObject;

@Named
@ViewScoped
public class UserDetail {
    private IdentityObject user;

    public void setUser(IdentityObject user) {
        this.user = user;
    }

    public IdentityObject getUser() {
        return user;
    }    
}
