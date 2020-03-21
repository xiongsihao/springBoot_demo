# 1. mail

springBoot发送邮件案例，包含纯文本文件，html邮件，带附件邮件，带图片的邮件，html模板邮件，异步发送邮件。

自行修改application.properties文件内的邮箱参数

# 2. QQ_Login

springBoot整合QQ登录案例

需要修改的地方：

- application.yml文件
  - redirect_URI
  - appid
  - appkey

- qqconnectconfig.properties文件
  - app_ID
  - app_KEY
  - redirect_URI

- QQController.java文件
  - qqcallback方法映射路径，修改为QQ回调域名后面的地址

详细步骤：http://xiongsihao.com/blog/37

# 3. fileUpload

springBoot文件上传案例

# 4. restful

restful风格crud

需修改application.properties文件数据库配置，并创建一个数据库表account

包含字段：id int，name varchar(20)，money float

# 5. scheduler

定时器任务，可配置指定时间后执行指定操作

# 6. shiro

springBoot整合apache shiro，一个最基本的权限管理案例；

详细步骤：http://xiongsihao.com/blog/36

# 7. springDataJpaAndRedis

springBoot整合JPA和redis

# 8. swagger

一个很好用的接口调试工具，自动生成接口文档

开启服务后访问：http://localhost:8080/swagger-ui.html既可浏览UI界面

# 9. yml

yml文件，注入基本数据和map数据

