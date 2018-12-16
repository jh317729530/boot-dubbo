# boot-dubbo
SpringBoot + Dubbo 的项目

# `启动指南`

### 项目结构
```
├─boot-dubbo
│  │  
│  ├─boot-dubbo-api--------------服务接口和服务模型常量工具类等
│  │  ├─annontation-------------------自定义注解类
│  │  ├─base-------------------基础类 一般继承用到
│  │  ├─constant---------------常量类  存放常量
│  │  ├─domain-----------------服务模型
|  |  ├─entity-----------------实体类
|  |  ├─exception--------------自定义异常
|  |  ├─service----------------服务接口声明
|  |  ├─utils------------------工具类
│  │ 
│  ├─boot-dubbo-consumer-----------------服务消费者
|  |  ├─combiz------------------程序业务相关
|  |  ├─configuration-----------配置类
|  |  ├─controller--------------controller，你懂的
|  |  ├─handler------------------处理类相关
|  |  ├─interceptor--------------自定义拦截器
│  │ 
|  ├─boot-dubbo-provider-----------------服务提供者
|  |  ├─aspect-------------------切面相关
|  |  ├─mapper-------------------mapper，你懂的
|  |  ├─service.impl-------------服务的实现
│
```

### 环境须知
- mysql一个
- jdk1.8
- IDE插件一个，`lombok插件`，具体百度即可

### 运行步骤
- 依次运行 ProviderApplication 和 ConsumerApplication 即可
- 数据库什么的请随意
- 打开浏览器访问 http://localhost:8081/sayHello?name=a 

----

### 相关技术
- 集成Mybatis
- 使用PageHelper分页插件



### 目录结构

------------



