package cn.yunhe.teacherPage.entity;

import java.util.Date;

public class Liuruirui {
	private String addr;
	private int id;
	private String name;
	private int age;

	private String sex;

	private Date birth;

	

	
	

	public Liuruirui(String addr, int id, String name, int age, String sex, Date birth) {
		super();
		this.addr = addr;
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.birth = birth;
	}




	public String getAddr() {
		return addr;
	}




	public void setAddr(String addr) {
		this.addr = addr;
	}








	@Override
	public String toString() {
		return "Liuruirui [addr=" + addr + ", id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex
				+ ", birth=" + birth + "]";
	}




	public Liuruirui() {
		super();
		// TODO Auto-generated constructor stub
	}




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

}
