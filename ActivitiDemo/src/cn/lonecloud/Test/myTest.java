package cn.lonecloud.Test;

import org.junit.Test;
/**
 * 简单的测试
 * @Title: myTest.java
 * @Package cn.lonecloud.Test
 * @Description: 
 * @author lonecloud
 * @date 2016年8月21日 下午9:35:12
 */
public class myTest {
	@Test
	public void Test01() {
		String str="/Users/lonecloud/test.png";
		System.out.println(str.substring(str.lastIndexOf("/")+1));
	}
}
