package project.swithme.order.test;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RdbInitializationConfiguration {

    private static final String SET_FOREIGN_KEY_CHECKS_FALSE = "SET FOREIGN_KEY_CHECKS = 0";
    private static final String SET_FOREIGN_KEY_CHECKS_TRUE = "SET FOREIGN_KEY_CHECKS = 1";
    private static final String ALL_TABLE_NAMES =
        "SELECT table_name FROM information_schema.TABLES WHERE TABLE_SCHEMA = ?";
    private final Set<String> tableNames = new HashSet<>();
    private final EntityManager entityManager;
    private final String schema;


    public RdbInitializationConfiguration(
        @Value("${schema_name}")
        String schema,
        EntityManager entityManager
    ) {
        this.schema = schema;
        this.entityManager = entityManager;
    }

    @PostConstruct
    public void afterPropertiesSet() {
        Query query = entityManager.createNativeQuery(ALL_TABLE_NAMES);
        query.setParameter(1, schema);

        List<Object> tables = query.getResultList();
        for (Object obj : tables) {
            tableNames.add(obj.toString());
        }
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
