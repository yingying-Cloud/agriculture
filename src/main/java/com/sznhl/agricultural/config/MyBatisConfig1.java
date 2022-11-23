package com.sznhl.agricultural.config;

import com.mysql.cj.jdbc.MysqlXADataSource;
import com.sznhl.agricultural.bean.DataSource1;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;


@Configuration
@MapperScan(basePackages = "com.sznhl.agricultural.dao.common", sqlSessionTemplateRef = "db1SqlSessionTemplate")
public class MyBatisConfig1 {

    // 配置数据源
    //将DataSource1穿入到我们的AtomikosDataSourceBean来进行管理
    @Bean(name = "ds1")
    public DataSource ds1(DataSource1 dataSource) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(dataSource.getUrl());
        mysqlXaDataSource.setPassword(dataSource.getPassword());
        mysqlXaDataSource.setUser(dataSource.getUsername());

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("ds1");
        xaDataSource.setMinPoolSize(20);
        xaDataSource.setMaxPoolSize(200);
        xaDataSource.setMaxLifetime(20000);
        xaDataSource.setBorrowConnectionTimeout(60);
        xaDataSource.setLoginTimeout(30);
        xaDataSource.setMaintenanceInterval(60);
        xaDataSource.setMaxIdleTime(60);
        xaDataSource.setTestQuery("select 1");

        return xaDataSource;
    }

    @Bean(name = "ds1SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("ds1") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return bean.getObject();
    }

    @Bean(name = "db1SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("ds1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}