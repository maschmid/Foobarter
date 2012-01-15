package org.jboss.seam.social.examples.foobarter.model;

import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.solder.servlet.WebApplication;
import org.jboss.solder.servlet.event.Initialized;
import org.jboss.seam.transaction.Transactional;

/**
 * Loads initial data in a platform-independent way
 *
 * @author Marek Schmidt
 */
public class PopulateDatabase {
    
    @PersistenceContext
    EntityManager entityManager;
    
    @Transactional
    public void loadData(@Observes @Initialized WebApplication webapp) {
        
    	if (entityManager.createQuery("from IdentityObjectType t where t.name = 'USER'").getResultList().size() == 0) {    	
    		// Object types
    		IdentityObjectType USER = new IdentityObjectType();
    		USER.setName("USER");
    		entityManager.persist(USER);
    	}
    	
    	if (entityManager.createQuery("from IdentityObjectType t where t.name = 'GROUP'").getResultList().size() == 0) {    	
    		IdentityObjectType GROUP = new IdentityObjectType();
    	    GROUP.setName("GROUP");
    	    entityManager.persist(GROUP);
    	}
    	
    	if (entityManager.createQuery("from IdentityObjectRelationshipType t where t.name = 'JBOSS_IDENTITY_MEMBERSHIP'").getResultList().size() == 0) {    	    	        
    		// Object relationship types
    		IdentityObjectRelationshipType jbossIdentityMembership = new IdentityObjectRelationshipType();
    		jbossIdentityMembership.setName("JBOSS_IDENTITY_MEMBERSHIP");
    		entityManager.persist(jbossIdentityMembership);
    	}
    	
    	if (entityManager.createQuery("from IdentityObjectRelationshipType t where t.name = 'JBOSS_IDENTITY_ROLE'").getResultList().size() == 0) {        
    		IdentityObjectRelationshipType jbossIdentityRole = new IdentityObjectRelationshipType();
    		jbossIdentityRole.setName("JBOSS_IDENTITY_ROLE");
    		entityManager.persist(jbossIdentityRole);
    	}
    }
}
