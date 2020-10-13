@[toc]
###  springweb项目配置文件
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200714184224219.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM4NDI1ODAz,size_16,color_FFFFFF,t_70)
由图可见，springweb项目中创建了2个WebApplicationContext，分别是spring创建的容器applicationContext.xml和springmvc创建的dispatcher-servlet.xml。
**作用**：
**springmvc&nbsp;&nbsp;&nbsp;dispatcher-servlet.xml**：在MVC三层模型中的controller控制层中，与视图解析器ViewReslover，HandlerMapping处理器映射器等打交道，用于前后端交互
**spring&nbsp;&nbsp;&nbsp;applicationContext.xml**：在MVC三层模型中的service业务层和Dao持久层中，管理bean的注入以及bean所相关依赖对象的注入
###  父子容器介绍
springmvc初始化容器的时候会在这个容器指定一个父容器，也就是spring创建的容器。
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020071419273894.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM4NDI1ODAz,size_16,color_FFFFFF,t_70)
**父子容器特点：**

> **1.父容器和子容器是相互隔离的，他们内部可以存在名称相同的bean**
> **2.子容器可以访问父容器中的bean，而父容器不能访问子容器中的bean**
> **3.调用子容器的getBean方法获取bean的时候，会沿着当前容器开始向上面的容器进行查找，直到找到对应的bean为止**
> **4.子容器中可以通过任何注入方式注入父容器中的bean，而父容器中是无法注入子容器中的bean，原因是第2点**
###  父子容器相关问题
####  spring和springmvc中为什么要有父子容器？
基于spring父容器不能访问springmvc容器里面的bean特点，可以达到隔离解耦，区分框架边界的目的。避免service层注入controller层的bean导致结构混乱
####  springmvc可以只有一个容器？
可以只使用一个容器：SpringMVC容器。这个容器里配置所有的bean
####  父子容器可以解决哪种业务场景？
场景：公司A与公司B合作，公司A项目依赖公司B项目的jar包，此时A项目和依赖的B项目jar包同时拥有很多相同名称的bean，此时就会报错bean名称重复，那么此刻父子容器就可以解决这个问题。
解决方式：A作为子容器，B作为父容器，就不用去手动更改N个相同名称的bean

参考博文：[https://blog.csdn.net/likun557/article/details/105608851](https://blog.csdn.net/likun557/article/details/105608851)




