package cn.lonecloud.Test.step05;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import cn.lonecloud.bean.step05byioc.Ibean;
import cn.lonecloud.bean.step05byioc.helper.BeanHelper;
import cn.lonecloud.util.SpringUtil;
/**
 * 这里是测试进行ref和constract进行依赖注入的测试类
 * @Title: RefAndConstractTest.java
 * @Package cn.lonecloud.Test.step05
 * @Description: 
 * @author lonecloud
 * @date 2016年8月6日 上午9:58:17
 */
public class RefAndConstractTest {
	/**
	 * 用于测试set和constract方法进行依赖注入
	 * @Description:
	 */
	@Test
	public void Test01(){
		ApplicationContext context=SpringUtil.getApplicationContext();
		BeanHelper beanHelper=(BeanHelper)context.getBean("beanHelp");
		Ibean bean = beanHelper.getBean();
		bean.say();
	}
}
