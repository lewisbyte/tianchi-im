package tianchi.lewis.indi.im.conf;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
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

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        // 设置规则适配的表
        shardingRuleConfig.getBindingTableGroups().add("t_user");
        shardingRuleConfig.getBindingTableGroups().add("t_message");
        shardingRuleConfig.getBindingTableGroups().add("t_room");
        // 设置分表策略
        shardingRuleConfig.getTableRuleConfigs().add(messageRule());
        shardingRuleConfig.getTableRuleConfigs().add(userRule());
        shardingRuleConfig.getTableRuleConfigs().add(roomRule());

        shardingRuleConfig.setDefaultDataSourceName(dsConfig.getDsName());
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new NoneShardingStrategyConfiguration());

        Properties properties = new Properties();
        properties.setProperty("sql.show", "true");

        return ShardingDataSourceFactory.createDataSource(dataSourceMap(), shardingRuleConfig, new ConcurrentHashMap<>(16), properties);
    }


    /**
     * 消息表分片规则
     *
     * @return
     */
    private TableRuleConfiguration messageRule() {
        TableRuleConfiguration tableRule = new TableRuleConfiguration();
        // 设置逻辑表名
        tableRule.setLogicTable("t_message");
        tableRule.setActualDataNodes("ds${0}.t_message_${0}");
        tableRule.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("roomid", "t_message_$->{ roomid % 1}"));
        tableRule.setKeyGenerator(customKeyGenerator());
        tableRule.setKeyGeneratorColumnName("id");
        return tableRule;
    }

    /**
     * 用户表分片规则
     *
     * @return
     */
    private TableRuleConfiguration userRule() {
        TableRuleConfiguration tableRule = new TableRuleConfiguration();
        // 设置逻辑表名
        tableRule.setLogicTable("t_user");
        tableRule.setActualDataNodes("ds${0}.t_user_${0}");
        tableRule.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("id", "t_user_$->{id % 1}"));
        tableRule.setKeyGenerator(customKeyGenerator());
        tableRule.setKeyGeneratorColumnName("id");
        return tableRule;
    }

    /**
     * 房间表分片规则
     *
     * @return
     */
    private TableRuleConfiguration roomRule() {
        TableRuleConfiguration tableRule = new TableRuleConfiguration();
        // 设置逻辑表名
        tableRule.setLogicTable("t_room");
        tableRule.setActualDataNodes("ds${0}.t_room_${0}");
        tableRule.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("id", "t_room_$->{id % 1}"));
        tableRule.setKeyGenerator(customKeyGenerator());
        tableRule.setKeyGeneratorColumnName("id");
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
        dataSourceMap.put(dsConfig.getDsName(), ds0);
        return dataSourceMap;
    }

    /**
     * 自定义主键生成器
     */
    private KeyGenerator customKeyGenerator() {
        return new CustomSnowflakeKeyGenerator(snowflake);
    }

}
