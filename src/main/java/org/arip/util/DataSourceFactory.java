package org.arip.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Arip Hidayat on 12/08/2016.
 */
@Configuration
@PropertySource("classpath:database.properties")
public class DataSourceFactory implements TargetRegistry {

    private ConcurrentMap<String, DataSource> map = new ConcurrentHashMap<>();

    @Autowired
    private Environment env;

    @Override
    public DataSource getTarget(String context) {
        DataSource dataSource = map.get(context);

        if (dataSource == null) {
            dataSource = getDataSource(context);
            dataSource = map.putIfAbsent(context, dataSource);
            if (dataSource == null) {
                // put success
                dataSource = map.get(context);
            }
        }

        return dataSource;
    }

    private DataSource getDataSource(String schemaName) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        dataSource.setUrl(env.getProperty("db.url") + "?currentSchema=" + schemaName);
        return dataSource;
    }
}
