package cn.lonecloud.Test.step01;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import cn.lonecloud.bean.step01.HelloWorld;
import cn.lonecloud.util.SpringUtil;

public class HelloWorldTest {
	
	@Test
	public void myTest(){
		ApplicationContext applicationContext = SpringUtil.getApplicationContext();
		//获得装配bean的实例
		HelloWorld hello=(HelloWorld)applicationContext.getBean("helloWorld");
		System.out.println(hello.getName());
	}
}
