package cn.lonecloud.Test.step03;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.lonecloud.bean.step03.ConfigClass;
import cn.lonecloud.bean.step03.IBean;
/**
 * 使用这个可以获取这个类的里面的设置从而不需要编写xml文档
 * @Title: ConfigByClassTest.java
 * @Package cn.lonecloud.Test.step03
 * @Description: 
 * @author lonecloud
 * @date 2016年8月5日 下午3:07:37
 */
public class ConfigByClassTest {
	@Test
	public void configTest(){
		//获取该config里面的对象设置 
		ApplicationContext applicationContext=new AnnotationConfigApplicationContext(ConfigClass.class);
		//@Bean(name="hello")获取该注解的实例
		IBean iBean=(IBean)applicationContext.getBean("hello");
		IBean iBean2=(IBean)applicationContext.getBean("hello2");
		iBean.say();//实现IBean01
		iBean2.say();//实现IBean02
		
	}
}
