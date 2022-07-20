package register;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * register.Person.
 */
public class Person implements Comparable<Person> {
    /**
     * Name of this person.
     */
    private String name;

    /**
     * Phone number of this person.
     */
    private String phoneNumber;

    /**
     * Construct a person.
     *
     * @param name        name of the person
     * @param phoneNumber phone number of the person
     */
    public Person(String name, String phoneNumber) {
        this.name = name;
        this.setPhoneNumber(phoneNumber);
    }

    /**
     * Returns name of this person.
     *
     * @return name of this person
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of this person.
     *
     * @param nameNew name of this person
     */
    public void setName(String nameNew) {
        name = nameNew;
    }

    /**
     * Returns phone number of this person.
     *
     * @return phone number of this person
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number of this person.
     *
     * @param phoneNumberNew phone number of this person
     */
    public void setPhoneNumber(String phoneNumberNew) {
        if (!isValidPhoneNumber(phoneNumberNew)) {
            System.out.println("Zadany vstup nie je telefonne cislo");
            return;
        }
        phoneNumber = phoneNumberNew;
    }

    //TODO: Implement method isValidPhoneNumber

    /**
     * Validates the phone number. Valid phone numbers contains only digits.
     *
     * @param phoneNumber phone number to validate
     * @return <code>true</code> if phone number is valid, <code>false</code> otherwise
     */
    public boolean isValidPhoneNumber(String phoneNumber) {
        Matcher matcher = Pattern.compile("[0-9]+").matcher(phoneNumber);
        matcher.matches();
        return matcher.matches();

    }

    public static boolean isValidPhoneNumberStatic(String phoneNumber) { // moja vlastna metoda
        Matcher matcher = Pattern.compile("[0-9]+").matcher(phoneNumber);
        matcher.matches();
        return matcher.matches();

    }

    /**
     * Returns a string representation of the person.
     *
     * @return string representation of the person.
     */
    public String toString() {
        return name + " (" + phoneNumber + ")";
    }

    @Override
    public int compareTo(Person o) {
        return this.getName().compareToIgnoreCase(o.getName());
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Person)) return false;
        Person p = (Person) obj;
        return this.name.equals(p.getName())
                && this.phoneNumber.equals(p.getPhoneNumber());
    }
}
