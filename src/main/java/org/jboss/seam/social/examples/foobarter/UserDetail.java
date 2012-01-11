package org.jboss.seam.social.examples.foobarter;

import javax.inject.Named;

import org.jboss.seam.faces.context.RenderScoped;
import org.jboss.seam.social.examples.foobarter.model.IdentityObject;

@Named
@RenderScoped
public class UserDetail {

    private IdentityObject user;

    public void setUser(IdentityObject user) {
        this.user = user;
    }

    public IdentityObject getUser() {
        return user;
    }    
}
