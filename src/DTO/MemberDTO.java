package DTO;

public class MemberDTO {
	private int memberNumber;
	private int age;
	private int birthyy, birthmm, birthdd;
	private String id, password, name, gender, email, address;
	private String memberProfile;
	private int phone_one, phone_two, phone_thr;
	
	public String getMemberProfile() {
		return memberProfile;
	}
	public void setMemberProfile(String memberProfile) {
		this.memberProfile = memberProfile;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	public int getBirthyy() {
		return birthyy;
	}
	public void setBirthyy(int birthyy) {
		this.birthyy = birthyy;
	}
	public int getBirthmm() {
		return birthmm;
	}
	public void setBirthmm(int birthmm) {
		this.birthmm = birthmm;
	}
	public int getBirthdd() {
		return birthdd;
	}
	public void setBirthdd(int birthdd) {
		this.birthdd = birthdd;
	}
	public int getPhone_one() {
		return phone_one;
	}
	public void setPhone_one(int phone_one) {
		this.phone_one = phone_one;
	}
	public int getPhone_two() {
		return phone_two;
	}
	public void setPhone_two(int phone_two) {
		this.phone_two = phone_two;
	}
	public int getPhone_thr() {
		return phone_thr;
	}
	public void setPhone_thr(int phone_thr) {
		this.phone_thr = phone_thr;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	

}
