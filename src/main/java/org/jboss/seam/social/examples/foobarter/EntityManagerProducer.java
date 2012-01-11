package org.jboss.seam.social.examples.foobarter;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.jboss.solder.core.ExtensionManaged;

public class EntityManagerProducer {
    @Produces
    @ExtensionManaged
    @ConversationScoped
    @PersistenceUnit
    EntityManagerFactory emf;
}
