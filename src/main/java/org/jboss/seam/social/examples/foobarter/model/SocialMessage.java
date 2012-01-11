package org.jboss.seam.social.examples.foobarter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SocialMessage {
    private Long id;
    private String message;
    private IdentityObject user;
  
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @ManyToOne
    public IdentityObject getUser() {
        return user;
    }
    
    public void setUser(IdentityObject user) {
        this.user = user;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
