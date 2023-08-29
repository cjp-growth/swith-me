package project.swithme.payment.test;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
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

    @Autowired
    private DataSource dataSource;

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
        executeSqlFile("../scripts/schema/schema.sql");
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

    public void executeSqlFile(String filePath) {
        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, new FileSystemResource(filePath));
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute SQL script", e);
        }
    }
}
