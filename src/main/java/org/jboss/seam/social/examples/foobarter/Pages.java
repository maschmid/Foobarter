package org.jboss.seam.social.examples.foobarter;

import org.jboss.seam.faces.security.AccessDeniedView;
import org.jboss.seam.faces.security.LoginView;
import org.jboss.seam.faces.view.config.ViewConfig;
import org.jboss.seam.faces.view.config.ViewPattern;
import org.jboss.seam.security.annotations.LoggedIn;

@ViewConfig
public interface Pages {
    
    static enum Pages1 {        
        @ViewPattern("/wall.xhtml")        
        @LoginView("/home.xhtml")
        @LoggedIn
        WALL,
        
        @ViewPattern("/twitter_user.xhtml")
        @LoggedIn
        @Follower
        @LoginView("/home.xhtml")
        @AccessDeniedView("/denied.xhtml")
        USER
    }
}
