package workshop.person.entity;

public class PersonEntity {
	private String name;
	private char gender;
	private String ssn;
	private String address;
	private String phone;
	
	//default constructor
	public PersonEntity() {
		
	}

	//overloading constructor
	public PersonEntity(String name, String ssn, String address, String phone) {
		this.name = name;
		//this.ssn = ssn;
		setSsn(ssn);
		this.address = address;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
		if(ssn != null) {
			char genderNum = ssn.charAt(6);
			if(genderNum == '1' || genderNum == '3') {
				setGender('³²');
			}else {
				setGender('¿©');
			}
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}	
	
}