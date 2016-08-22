package cn.lonecloud.bean.step05byioc;
/**
 * 用于对实现说中文的一个实现类
 * @Title: ChineseBean.java
 * @Package cn.lonecloud.bean.step02
 * @Description: 
 * @author lonecloud
 * @date 2016年8月5日 上午11:47:59
 */
public class ChineseBean implements Ibean{

	@Override
	public void say() {
		// TODO Auto-generated method stub
		System.out.println("我是说中文的");
	}
	
}
