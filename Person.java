package lab6;

public class Person {

	private String givenName;
	private String surname;
	private int phoneNumber;
	
	public Person (String arg1, String arg2, int arg3) {
		givenName = arg1;
		surname = arg2;
		phoneNumber = arg3;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public String getFullName() {
		return givenName + " " + surname;
	}
	
	public int getPhoneNumber() {
		return phoneNumber;
	}
	
}
