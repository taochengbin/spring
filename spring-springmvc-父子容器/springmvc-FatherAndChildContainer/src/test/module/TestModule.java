package module;

import main.module1.Module1Config;
import main.module2.Module2Config;
import main.module2.Service3;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestModule {

    @Test
    public void test1() {
        //定义容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册bean
        context.register(Module1Config.class, Module2Config.class);
        //启动容器
        context.refresh();
    }

    @Test
    public void test2() {
        //创建父容器
        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        //向父容器中注册Module1Config配置类
        parentContext.register(Module1Config.class);
        //启动父容器
        parentContext.refresh();

        //创建子容器
        AnnotationConfigApplicationContext childContext = new AnnotationConfigApplicationContext();
        //向子容器中注册Module2Config配置类
        childContext.register(Module2Config.class);
        //给子容器设置父容器
        childContext.setParent(parentContext);
        //启动子容器
        childContext.refresh();

        //从子容器中获取Service3
        Service3 service3 = childContext.getBean(Service3.class);
        System.out.println(service3.m1());
        System.out.println(service3.m2());

    }
}
