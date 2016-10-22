public class PersonalContact extends Contact implements Observer{
	private String category; //business or personal
	private String name;
	private String phoneNumber;
	private String email;
	private boolean changed;

	public PersonalContact() { //factory method is the constructor
	}

	@Override
	public void update() {
		this.changed = false;
	}

	public String getCategory() {return category;}
	public String getName() {return name};
	public String getPhoneNumber() {return phoneNumber;}
	public String getEmail() {return email;}

	public void setCategory(String newCat) { //will need to create new object if the object is now a business contact
		category = newCat;
		this.changed = true;
	} 
	public void setName(String newName) {
		name = newName;
		this.changed = true;
	}
	public void setPhoneNumber(String newNumber) {
		phoneNumber = newNumber;
		this.changed = true;
	}
	public void setEmail(String newEmail) {
		email = newEmail;
		this.changed = true;
	}
}