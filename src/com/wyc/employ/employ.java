package com.wyc.employ;

public class employ {
    //ְ����š����������䡢�Ա𡢼����¹���
	private int id;
	private String name;
	private int age;
	private int sex;
	private int por;
	private double money;
	private double ywze;//����ҵ���ܶ�;
	private int time; //���¹���ʱ��
	
	public employ(){
		
	}
	
	public employ(int id, String name, int age, int sex, int por, double money,
			double ywze, int time) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.por = por;
		this.money = 0;
		this.ywze = 0;
		this.time = 0;
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
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getPor() {
		return por;
	}
	public void setPor(int por) {
		this.por = por;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public double getYwze() {
		return ywze;
	}
	public void setYwze(double ywze) {
		this.ywze = ywze;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	
}
