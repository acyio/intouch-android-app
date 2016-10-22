import java.util.*;


//since contact is abstract, only instantiates while returning, since contact cannot be instantiated
public class ContactFactory {
	public Contact createContact(String contactType) {
		if(contactType.equals("PersonalContact")) {
			return new PersonalContact();
		}
		else if (contactType.equals("BusinessContact")) {
			return new BusinessContact();
		}
	}
}