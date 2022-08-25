# Alisa Log
## 基于Java整合ChickHouse的分布式日志收集与检索系统
在写
***

### 实现功能
* UDP与HTTP日志接收（基于缓冲队列，使用多线程批量消费日志消息至ChickHouse）
* 日志分类划分（利用Redis存储日志级联分类信息）
* 监控台日志检索与数据展示，根据多项字段组合查询日志（AppId、时间区间、分页信息为必传字段）

![1](/X_README_IMG/1.png)

![2](/X_README_IMG/2.png)

![3](/X_README_IMG/3.png)

***

### 系统架构

![sys](/X_README_IMG/sys.png)

***

### 项目结构
* router `路由中心`
* monitor `监控台`
* worker `日志收集器`
* client `简易客户端实现（未完成）`
* common `通用基础模块`

***

### 启动方式
* 配置Redis、ChickHouse以及worker心跳注册服务配置
* 先启动路由中心'router'，再启动日志收集器'worker'
* 启动网页端监控台'monitor'，访问http://127.0.0.1:8502/#/

***

### 日志接收方式

#### `Worker` UDP服务默认监听端口
> 127.0.0.1:8500
* Json日志转为字节数组发送

#### `Worker` HTTP接口默认地址
> `POST` http://127.0.0.1:8501/log/input
* 采用 Row Json 方式发送body数据

***

### 日志接收格式
#### JSON格式
```json
{
  "idc":"test-idc",
  "env":"test",
  "host":"0.0.0.0:0000",
  "appId":"test-app",
  "token":"test-token",
  "logs": [
    {
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
#### 日志字段说明
* 日志信息公共字段

| 字段名称  | 字段解释                 |
|-------|----------------------|
| idc   | 数据中心                 |
| env   | 运行环境                 |
| host  | 主机地址                 |
| appId | 应用id                 |
| token | token令牌（写入权限校验，暂时无用） |

因为一个批次日志都是产生于一个应用内，所以从日志列表中抽离出这四个公共字段，减少网络带宽与内存负载。

* 日志信息列表 `logs`

| 字段名称        | 字段解释                                    |
|-------------|-----------------------------------------|
| traceId     | 追踪id（自定义）                               |
| module      | 模块（一级分类）                                |
| category    | 类别（二级分类）                                |
| subCategory | 子类别（三级分类）                               |
| filter1     | 过滤标识1（自定义标识，方便精确快速检索）                   |
| filter2     | 过滤标识2（自定义标识，方便精确快速检索）                   |
| file        | 文件名（日志产生于哪个代码文件）                        |
| position    | 详细位置（日志产生于文件内的哪一行或哪一个函数）                |
| level       | 日志等级（int类型，1～5）                         |
| tag         | 日志标签（自定义标识，可存放应用版本号）                    |
| title       | 日志标题（日志的简短描述）                           |
| content     | 日志内容（日志的详细内容）                           |
| remarks     | 日志备注（日志额外信息）                            |
| logTime     | 记录时间（毫秒级时间戳，如果不传，则在日志写入数据库前自动生成logTime） |

* 日志等级
##### int类型字段
##### `1` `DEBUG` `应用开发时的调试日志`
##### `2` `INFO` `应用正常运行时输出的提示性日志`
##### `3` `WARN` `应用运行过程中产生的警告性日志`
##### `4` `ERROR` `应用运行过程中产生的报错日志`
##### `5` `FATAL` `导致应用停止运行的严重错误日志`
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
* 仅供字段参考
```clickhouse
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
    log_time Nullable(Int64),
    store_time Nullable(DateTime)
)
    engine = Memory;
```