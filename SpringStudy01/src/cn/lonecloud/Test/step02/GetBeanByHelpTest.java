package cn.lonecloud.Test.step02;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import cn.lonecloud.bean.step02.helper.BeanHelper;
import cn.lonecloud.util.SpringUtil;

public class GetBeanByHelpTest {
	/**
	 * 这样的作用可以防止不需要修改代码只需要修改xml里面的值就能获取相对于你想要的对应的类型
	 * 
	 * @Description:这种依赖注入(DI)为对象的依赖关系管理有用的特性，使大型Java项目开发管理中更优雅的，高度灵活和便于维护。
	 */
	@Test
	public void getHelpTest() {
		ApplicationContext applicationContext = SpringUtil
				.getApplicationContext();
		// 获取beanHelp类的实例
		BeanHelper beanHelper = (BeanHelper) applicationContext
				.getBean("beanhelper");
		// 获取xml里面设置的Ibean里面的上设置的实例的bean的类型
		beanHelper.getBean().say();
	}
}
