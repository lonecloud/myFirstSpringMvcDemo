package cn.lonecloud.Test.step06;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import cn.lonecloud.bean.step06iocfield.UserBean;
import cn.lonecloud.util.SpringUtil;

public class GetDefaulltByConstractTest {
	/**
	 * 测试在构造函数中对该实例进行初始化变量
	 * @Description:
	 */
	@Test
	public void test(){
		ApplicationContext applicationContext = SpringUtil.getApplicationContext();
		//使用类类型获取该bean的实例
		UserBean userBean = applicationContext.getBean(UserBean.class);
		System.out.println(userBean);
	}
}
