package cn.lonecloud.bean.step06iocfield;

public class UserBean {

	private String userName;
	
	private int age;
	
	private String address;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "UserBean [userName=" + userName + ", age=" + age + ", address="
				+ address + "]";
	}
	
	public UserBean(String userName, int age, String address) {
		super();
		this.userName = userName;
		this.age = age;
		this.address = address;
	}
}
