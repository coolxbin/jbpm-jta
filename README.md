jbpm-jta
========

jbpm6+jta+tomcat

本项目代码用于测试jbpm6在tomcat中的持久化功能，需要先在tomcat中配置datasource和usertransaction。具体的配置可以在
http://hi.baidu.com/mallenshow/item/7a7a593b8fc7489ab90c033e 
查询到，注意本项目中配置的datasource名称为jdbc/BtmOracle，
务必在配置tomcat时配置一个这样的datasource

当前项目启动时，会报错：
No JTA TransactionSynchronizationRegistry found at default JNDI location [java:comp/TransactionSynchronizationRegistry]
javax.naming.NameNotFoundException: Name TransactionSynchronizationRegistry is not bound in this Context

该错误是未在tomcat中配置TransactionSynchronizationRegistry引起的，但是经过google良久之后还是未找到解决办法，希望有人看到能帮忙解决一下。

可以直接发到在下邮箱里面，谢谢！
