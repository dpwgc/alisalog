# Alisa Log
## 基于ChickHouse存储的日志系统

***

### 实现功能
当前已完成
* UDP日志接收
* HTTP日志接收
* 日志查询

***

### 项目架构
* common `通用基础模块`
* router `路由中心`
* console `控制台`
* worker `日志收集器`

***

### 日志接收方式

#### UDP
> 0.0.0.0:8500

#### HTTP
> `POST` http://0.0.0.0:8501/alisa/worker/log/input

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
      "tag":"test",
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
      "tag":"test",
      "title":"test-title",
      "content":"hello world / hello world / hello world",
      "remarks":"hi",
      "logTime": 1640970061000
    }
  ]
}
```

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