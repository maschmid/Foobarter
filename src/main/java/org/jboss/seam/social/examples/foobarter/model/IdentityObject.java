package org.jboss.seam.social.examples.foobarter.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.jboss.seam.security.annotations.management.IdentityEntity;
import static org.jboss.seam.security.annotations.management.EntityType.IDENTITY_OBJECT;
import org.jboss.seam.security.annotations.management.IdentityProperty;
import org.jboss.seam.security.annotations.management.PropertyType;

/**
 * This entity contains identity objects, e.g. users and groups
 *
 * @author Shane Bryzak
 */
@IdentityEntity(IDENTITY_OBJECT)
@Entity
public class IdentityObject implements Serializable {
    private static final long serialVersionUID = -4623023512038059728L;
  
    @Id
    @IdentityProperty(PropertyType.NAME) private String name;
    private IdentityObjectType type;

    private String oauthId;
    private String fullName;
    private String profileImageUrl;
    private String serviceName;
    private String screenName;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getFullName() { 
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @ManyToOne
    @IdentityProperty(PropertyType.TYPE)
    @JoinColumn(name = "IDENTITY_OBJECT_TYPE_ID")
    public IdentityObjectType getType() {
        return type;
    }

    public void setType(IdentityObjectType type) {
        this.type = type;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setOauthId(String oauthId) {
        this.oauthId = oauthId;
    }

    public String getOauthId() {
        return oauthId;
    }

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getScreenName() {
		return screenName;
	}
}
