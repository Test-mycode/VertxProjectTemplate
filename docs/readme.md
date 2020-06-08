## 当前目录写项目文档和说明

# 开发规范：
1. 开发语言：Kotlin
2. JVM版本： 11
3. 依赖库：优先使用Vertx 里的扩展库
4. 项目依赖的基础库要注意更新
5. 源文件编码和程序内部字符串编码都为 UTF-8

### 后端代码组织规范：
1. 阻塞IO必须放到work里运行
2. 使用函数级别的controller，不使用类级别controller，每个请求对应个一函数调用
3. 路由配置统一写在一个或多个文件中，文件名为： router.kt或 (前缀)Router.kt
4. controller以文件为单位组织，相关的写入到文件中，根据功能组分目录
5. 错误处理统一抛出异常，WebVerticle.doFailureHandle 里统一处理
6. 参数校验，可做扩展函数统一封装，错误同样抛异常


### 编码规范：
https://www.kotlincn.net/docs/reference/coding-conventions.html

### 数据库规范
1. 主用数据库 PostgresSql
2. 业务表都需要三个扩展字段： other：jsonb， create_time：timestamp ， modified_time：timestamp
