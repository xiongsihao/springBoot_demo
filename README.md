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

# 3. websocket

websocket实现实时在线聊天案例

运行启动类，浏览器访问http://localhost:8080/

![](http://cdn.xiongsihao.com/202006142110_286.png)

使用 用户1登录，当前在线人数显示为1

![](http://cdn.xiongsihao.com/202006142112_118.png)

新开一个浏览器窗口访问http://localhost:8080/，并使用 用户2登录，当前在线人数显示为2

![](http://cdn.xiongsihao.com/202006142113_450.png)

分别在用户1和用户2窗口发送消息，消息实时推送

![](http://cdn.xiongsihao.com/202006142115_865.png)

[详细步骤](http://xiongsihao.com/blog/49)

# 4. FreeMarker

[详细步骤](http://xiongsihao.com/blog/50)

springBoot整合FreeMarker，实现页面静态化，代码生成器

# 5. fileUpload

springBoot文件上传案例

# 6. restful

restful风格crud

需修改application.properties文件数据库配置，并创建一个数据库表account

包含字段：id int，name varchar(20)，money float

# 7. scheduler

定时器任务，可配置指定时间后执行指定操作

# 8. shiro

springBoot整合apache shiro，一个最基本的权限管理案例；

详细步骤：http://xiongsihao.com/blog/36

# 9. springDataJpaAndRedis

springBoot整合JPA和redis

# 10. swagger

一个很好用的接口调试工具，自动生成接口文档

开启服务后访问既可浏览UI界面：http://localhost:8080/swagger-ui.html   

# 11. yml

yml文件，注入基本数据和map数据

# 12. system-monitor
应用监控案例，可实时监控应用运行状态、应用信息、内存使用情况等
system-monitor为监控客户端  system-monitor-admin为监控服务端
详细步骤：http://xiongsihao.com/blog/52