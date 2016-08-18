package org.arip.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
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
    public DataSource getTarget(String context) throws PropertyVetoException {
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

    private DataSource getDataSource(String dbName) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("db.driver"));
        dataSource.setJdbcUrl(env.getProperty("db.url") + dbName);
        dataSource.setUser(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("db.maxPoolSize")));
        dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("db.minPoolSize")));
        dataSource.setMaxStatements(Integer.parseInt(env.getProperty("db.maxStatement")));
        return dataSource;
    }
}
