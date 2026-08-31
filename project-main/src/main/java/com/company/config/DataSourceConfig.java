package com.company.config;

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * 使用 ShardingSphere JDBC 手工创建数据源。
 * 5.5+ 兼容 Spring Boot 3 的 SnakeYAML 2.x，分片规则放在 classpath 的 sharding.yml。
 */
@Configuration
public class DataSourceConfig {

    /**
     * 从 classpath 读取 YAML 并创建分片数据源。
     * 用字节流而不是 File，避免打成 jar 后 getFile() 找不到资源。
     */
    @Bean
    public DataSource dataSource() throws SQLException, IOException {
        ClassPathResource resource = new ClassPathResource("sharding.yml");
        try (InputStream inputStream = resource.getInputStream()) {
            return YamlShardingSphereDataSourceFactory.createDataSource(inputStream.readAllBytes());
        }
    }
}
