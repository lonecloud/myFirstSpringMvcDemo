package cn.lonecloud.Test.step07;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import cn.lonecloud.bean.step07byImport.Helper.BeanHelper;
import cn.lonecloud.util.SpringUtil;

public class TestImport {
	@Test
	public void test01(){
		//测试说明通过
		ApplicationContext applicationContext = SpringUtil.getApplicationContextBySelf("step07/applicationContext.xml");
		BeanHelper beanHelper = applicationContext.getBean(BeanHelper.class);
		beanHelper.getBean().say();
	}
}
