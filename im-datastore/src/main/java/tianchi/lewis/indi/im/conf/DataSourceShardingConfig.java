package tianchi.lewis.indi.im.conf;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.zaxxer.hikari.HikariDataSource;
import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingsphere.api.config.strategy.NoneShardingStrategyConfiguration;
import io.shardingsphere.core.keygen.KeyGenerator;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * sharding-jdbc 的数据源配置
 * </p>
 */
@Configuration
public class DataSourceShardingConfig {
    private static final Snowflake snowflake = IdUtil.createSnowflake(1, 1);

    @Autowired
    private DSConfig dsConfig;

    /**
     * 需要手动配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        // 设置分库策略
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
        // 设置规则适配的表
        shardingRuleConfig.getBindingTableGroups().add("t_order");
        // 设置分表策略
//        shardingRuleConfig.getTableRuleConfigs().add(orderTableRule());
        shardingRuleConfig.setDefaultDataSourceName("ds0");
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new NoneShardingStrategyConfiguration());

        Properties properties = new Properties();
        properties.setProperty("sql.show", "true");

        return ShardingDataSourceFactory.createDataSource(dataSourceMap(), shardingRuleConfig, new ConcurrentHashMap<>(16), properties);
    }


    /**
     *  消息表分片规则
     * @return
     */
    private TableRuleConfiguration messageRule(){
        TableRuleConfiguration tableRule = new TableRuleConfiguration();
        return tableRule;
    }

    /**
     *  用户表分片规则
     * @return
     */
    private TableRuleConfiguration userRule(){
        TableRuleConfiguration tableRule = new TableRuleConfiguration();
        return tableRule;
    }


    /**
     *  房间表分片规则
     * @return
     */
    private TableRuleConfiguration roomRule(){
        TableRuleConfiguration tableRule = new TableRuleConfiguration();
        return tableRule;
    }

    private Map<String, DataSource> dataSourceMap() {
        Map<String, DataSource> dataSourceMap = new HashMap<>(16);
        // 配置数据源
        HikariDataSource ds0 = new HikariDataSource();
        ds0.setDriverClassName(dsConfig.getClassName());
        ds0.setJdbcUrl(dsConfig.getUrl());
        ds0.setUsername(dsConfig.getUsername());
        ds0.setPassword(dsConfig.getPassword());
        dataSourceMap.put("ds0", ds0);
        return dataSourceMap;
    }
    /**
     * 自定义主键生成器
     */
    private KeyGenerator customKeyGenerator() {
        return new CustomSnowflakeKeyGenerator(snowflake);
    }

}
