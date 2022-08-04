# Alisa Log
## 基于Java整合ChickHouse的分布式日志收集与检索系统

***

### 实现功能
当前已完成
* UDP日志接收
* HTTP日志接收
* 监控台登录
* 监控台日志查询

***

### 项目架构
* router `路由中心`
* monitor `监控台`
* worker `日志收集器`
* client `客户端`
* common `通用基础模块`

***

### 日志接收方式

#### `Worker` UDP服务默认监听端口
> 127.0.0.1:8500

#### `Worker` HTTP接口默认地址
> `POST` http://127.0.0.1:8501/log/input

***

### 日志接收格式
```json
{
  "logs": [
    {
      "idc":"test-idc",
      "host":"0.0.0.0:0000",
      "env":"test",
      "appId":"test-app",
      "traceId":"test-trace",
      "module":"test-module",
      "category":"test-category",
      "subCategory":"test-sub-category",
      "filter1":"f1",
      "filter2":"f2",
      "file":"test.java",
      "position":"testService()",
      "level":3,
      "tag":"test-0.0.1",
      "title":"test-title",
      "content":"hello world / hello world / hello world",
      "remarks":"hi",
      "logTime": 1640970061000
    },
    {
      "idc":"test-idc",
      "host":"0.0.0.0:0000",
      "env":"test",
      "appId":"test-app",
      "traceId":"test-trace",
      "module":"test-module",
      "category":"test-category",
      "subCategory":"test-sub-category",
      "filter1":"f1",
      "filter2":"f2",
      "file":"test.java",
      "position":"testService()",
      "level":3,
      "tag":"test-0.0.1",
      "title":"test-title",
      "content":"hello world / hello world / hello world",
      "remarks":"hi",
      "logTime": 1640970061000
    }
  ]
}
```

***

### 服务默认端口
| 端口   | 所属模块    | 端口协议 | 端口用途    | 
|------|---------|------|---------|
| 8500 | worker  | UDP  | 接收小型日志  |
| 8501 | worker  | HTTP | 接收大型日志  |
| 8502 | monitor | HTTP | 提供监控台接口 |
| 8503 | router  | HTTP | 服务发现与注册 |
***

### chickhouse数据库表

```clickhouse
create table user
(
    username Nullable(varchar(32)),
    password Nullable(varchar(32)),
    auth_level Nullable(int),
    status Nullable(int),
    create_time Nullable(datetime),
    update_time Nullable(datetime)
)
    engine = Memory;


create table log
(
    id Nullable(String),
    idc Nullable(String),
    host Nullable(String),
    env Nullable(String),
    app_id Nullable(String),
    trace_id Nullable(String),
    module Nullable(String),
    category Nullable(String),
    sub_category Nullable(String),
    filter1 Nullable(String),
    filter2 Nullable(String),
    file Nullable(String),
    position Nullable(String),
    level Nullable(Int32),
    tag Nullable(String),
    title Nullable(String),
    content Nullable(String),
    remarks Nullable(String),
    log_time Nullable(Int64)
)
    engine = Memory;
```