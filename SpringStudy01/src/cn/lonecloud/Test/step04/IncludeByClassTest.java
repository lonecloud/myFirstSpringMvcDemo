package cn.lonecloud.Test.step04;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.lonecloud.bean.step04byinclude.ConfigAll;
import cn.lonecloud.bean.step04byinclude.Mail;

public class IncludeByClassTest {
	@Test
	public void test01(){
		ApplicationContext context=new AnnotationConfigApplicationContext(ConfigAll.class);
		Mail mail=(Mail)context.getBean("mail");
		mail.sendMail();
	}
}
