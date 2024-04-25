# SE3336作业二
软件测试小组作业，后端白盒测试。

提供完整后端代码以及数据库sql脚本，相关文档均在\docs目录下。

在运行时，请记得更改`..\src\main\resources`下的`application.properties`文件中的数据库身份验证。

- ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/bookstore_project?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
  spring.datasource.username=root
  spring.datasource.password=******  // 请替换为你的password
  spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
  ```
  
  
  
  2024/4/12  更新：
  
  ​	Java版本更新为Java 11以支持Mockito 3.4.0
  
  ​	更改文件为pom.xml，记得拉取更新。

