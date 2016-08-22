package cn.lonecloud.Test.step08;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import cn.lonecloud.bean.step08iocfieldbyProperty.UserBean;
import cn.lonecloud.util.SpringUtil;

public class TestField {
	@Test
	public void test(){
		ApplicationContext applicationContext = SpringUtil.getApplicationContextBySelf("step08/applicationContext.xml");
		UserBean userBean = applicationContext.getBean(UserBean.class);
		System.out.println(userBean);
	}
}
