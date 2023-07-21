package project.swithme.order.test;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class RdbInitializationConfiguration {

    private static final String SET_FOREIGN_KEY_CHECKS_FALSE = "SET FOREIGN_KEY_CHECKS = 0";
    private static final String SET_FOREIGN_KEY_CHECKS_TRUE = "SET FOREIGN_KEY_CHECKS = 1";

    private final EntityManager entityManager;
    private Set<String> tableNames;

    public RdbInitializationConfiguration(
            EntityManager entityManager,
            Set<String> tableNames
    ) {
        this.entityManager = entityManager;
        this.tableNames = tableNames;
    }

    @PostConstruct
    public void afterPropertiesSet() {
        Metamodel metamodel = entityManager.getMetamodel();
        tableNames = metamodel.getEntities().stream()
                .filter(isEntityTypeAndNotNull())
                .map(toLowerCase())
                .collect(Collectors.toUnmodifiableSet());
    }

    private static Function<EntityType<?>, String> toLowerCase() {
        return entityType -> entityType.getName().toLowerCase();
    }

    private Predicate<EntityType<?>> isEntityTypeAndNotNull() {
        return entityType -> entityType.getJavaType()
                .getAnnotation(Entity.class) != null;
    }

    @Transactional
    public void truncateAllEntity() {
        entityManager.flush();
        entityManager.clear();

        entityManager.createNativeQuery(SET_FOREIGN_KEY_CHECKS_FALSE).executeUpdate();

        for (String tableName : tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
        }

        entityManager.createNativeQuery(SET_FOREIGN_KEY_CHECKS_TRUE).executeUpdate();
    }
}
