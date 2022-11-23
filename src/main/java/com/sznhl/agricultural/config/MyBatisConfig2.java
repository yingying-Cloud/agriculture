package com.sznhl.agricultural.config;

import com.mysql.cj.jdbc.MysqlXADataSource;
import com.sznhl.agricultural.bean.DataSource2;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;


@Configuration
@MapperScan(basePackages = "com.sznhl.agricultural.dao.yiwu", sqlSessionTemplateRef = "db2SqlSessionTemplate")
public class MyBatisConfig2 {

    // 配置数据源
    //将DataSource2穿入到我们的AtomikosDataSourceBean来进行管理
    @Primary
    @Bean(name = "ds2")
    public DataSource ds2(DataSource2 dataSource) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(dataSource.getUrl());
        mysqlXaDataSource.setPassword(dataSource.getPassword());
        mysqlXaDataSource.setUser(dataSource.getUsername());

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("ds2");
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

    @Primary
    @Bean(name = "ds2SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("ds2") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return bean.getObject();
    }

    @Primary
    @Bean(name = "db2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("ds2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}