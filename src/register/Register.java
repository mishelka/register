package register;

public interface Register {
    int getCount();

    int getSize();

    Person getPerson(int index);

    void addPerson(Person person);

    Person findPersonByName(String name);

    Person findPersonByPhoneNumber(String phoneNumber);

    void removePerson(Person person);

    Person[] getPersons();
}
