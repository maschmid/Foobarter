package org.jboss.seam.social.examples.foobarter;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.jboss.seam.social.examples.foobarter.model.IdentityObject;

@Named
@ViewScoped
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
}
