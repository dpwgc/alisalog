# Alisa Log

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