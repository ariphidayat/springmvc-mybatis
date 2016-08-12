package org.arip.util;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Arip Hidayat on 12/08/2016.
 */
public class DataSourceFactory implements TargetRegistry {

    private ConcurrentMap<String, DataSource> map = new ConcurrentHashMap<>();

    @Override
    public DataSource getTarget(String context) {
        String schema = ContextHolder.getContext();
        DataSource dataSource = map.get(schema);

        if (dataSource == null) {
            dataSource = getDataSource("postgres", "P0stgres", schema);
            dataSource = map.putIfAbsent(schema, dataSource);
            if (dataSource == null) {
                // put success
                dataSource = map.get(schema);
            }
        }

        return dataSource;
    }

    private DataSource getDataSource(String username, String password, String schema) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl("jdbc:postgresql://localhost:5432/arip_db?currentSchema=" + schema);
        return dataSource;
    }
}
