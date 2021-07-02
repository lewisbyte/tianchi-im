# tianchi - im
### 模块介绍

#### im-webapp
##### 模块职能
* webapp模块，用于提供web接口服务调用 
* 基础业务逻辑实现：注册、登录，消息拉取等 TODO
* 群聊消息广播 TODO

#### im-datastore
##### 模块职能
* 模块化配置，sharding jdbc，postgresql 的适配 TODO
* 数据存储模块，用于数据持久化模块，对大数据表的分片等细则的实现 TODO
* 数据存储调度逻辑实现 TODO

#### im-mq
##### 模块职能
* 模块化配置，kafka 的适配 TODO
* 消息队列模块，用于异步存储、削峰填谷等作用 TODO

### 依赖层级
* webapp-> datastore-> mq (层级从高到低)









