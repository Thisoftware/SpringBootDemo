package com.demo.boot.core.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.demo.boot.api.enums.DataSourceTypeEnum;
import com.demo.boot.core.util.AESUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = "com.demo.boot.core.dao.**.mapper", value="sqlSessionFactory")
@EnableAutoConfiguration(exclude = DruidDataSourceAutoConfigure.class)
public class MybatisPlusConfiguration {

    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    @Value("${decrypt.status}")
    private String decryptStatus;

    @Value("${decrypt.key}")
    private String decryptKey;

    @Bean(name = "dataSource1")
    @ConfigurationProperties(prefix = "spring.datasource.druid.source1")
    public DataSource dataSource1() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "dataSource2")
    @ConfigurationProperties(prefix = "spring.datasource.druid.source2")
    public DataSource dataSource2() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 动态数据源配置
     */
    @Bean
    @Primary
    public DataSource multipleDataSource(@Qualifier("dataSource1") DataSource dataSource1, @Qualifier("dataSource2") DataSource dataSource2) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceTypeEnum.DATA_SOURCE1.getCode(), decryptPassword(dataSource1, Boolean.parseBoolean(decryptStatus)));
        targetDataSources.put(DataSourceTypeEnum.DATA_SOURCE2.getCode(), decryptPassword(dataSource2, Boolean.parseBoolean(decryptStatus)));
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(dataSource1);
        return dynamicDataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(multipleDataSource(dataSource1(), dataSource2()));

        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        //添加插件功能
        //sqlSessionFactory.setPlugins(paginationInterceptor());
        return sqlSessionFactory.getObject();
    }

    /**
     * 解密数据库密码
     * @param dataSource
     * @param decryptStatus
     * @return
     */
    private Object decryptPassword(DataSource dataSource, boolean decryptStatus){
        if(decryptStatus){
            DruidDataSource druidDataSource = (DruidDataSource) dataSource;
            druidDataSource.setPassword(AESUtil.decrypt(druidDataSource.getPassword(), decryptKey));
            return druidDataSource;
        }
        return dataSource;
    }
}
