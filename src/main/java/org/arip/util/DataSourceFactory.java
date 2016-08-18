package org.arip.util;

import org.apache.commons.dbcp2.BasicDataSource;
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
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url") + dbName);
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        dataSource.setDefaultAutoCommit(false);
        dataSource.setMaxTotal(Integer.parseInt(env.getProperty("cp.maxActive")));
        dataSource.setMaxIdle(Integer.parseInt(env.getProperty("cp.maxIdle")));
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("cp.minIdle")));
        dataSource.setMaxWaitMillis(Long.parseLong(env.getProperty("cp.maxWait")));
        return dataSource;
    }
}
