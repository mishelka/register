package register;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;

/**
 * register.Person register.
 */
public class ArrayRegister implements Register {
    /**
     * register.Person array.
     */
    private Person[] persons;

    /**
     * Number of persons in this register.
     */
    private int count;

    /**
     * Constructor creates an empty register with maximum size specified.
     *
     * @param size maximum size of the register
     */
    public ArrayRegister(int size) {
        persons = new Person[size];
        count = 0;
    }

    /**
     * Returns the number of persons in this register.
     *
     * @return the number of persons in this register
     */
    @Override
    public int getCount() {
        return count;
    }

    /**
     * Returns the maximum number of persons in this register.
     *
     * @return the maximum number of persons in this register.
     */
    @Override
    public int getSize() {
        return persons.length;
    }

    /**
     * Returns the person at the specified position in this register.
     *
     * @param index index of the person to return
     * @return person the person at the specified position in this register
     */
    @Override
    public Person getPerson(int index) {
        return persons[index];
    }

    /**
     * Appends the specified person to the end of this register.
     *
     * @param person person to append to this register
     */
    @Override
    public void addPerson(Person person) {
        if (findPersonByName(person.getName()) == null && findPersonByPhoneNumber(person.getPhoneNumber()) == null) {
            persons[count] = person;
            count++;
        } else {
            System.out.println("!!! Osoba nebola pridana. osoba s rovnakym menom, alebo cislom uz je v zozname.");
        }

    }

    //TODO: Implement the method findPersonByName

    /**
     * Returns the person with specified name in this register or <code>null</code>,
     * if match can not be found.
     *
     * @param name name of a person to search for
     * @return person with specified phone number
     */
    @Override
    public Person findPersonByName(String name) {
        if (!Pattern.compile("[A-Z a-z]+").matcher(name).matches()) {
            System.out.println("!!! Zadany stup nie je meno. Koniec hladania.");
            return null;
        }
        for (Person p : persons) {
            if (p != null) {
                if (p.getName().equalsIgnoreCase(name)) {
                    return p;
                }
            }
        }
        return null;

    }

    //TODO: Implement the method findPersonByPhoneNumber

    /**
     * Returns the person with specified phone number in this register or <code>null</code>,
     * if match can not be found.
     *
     * @param phoneNumber phone number of a person to search for
     * @return person with specified phone number
     */
    @Override
    public Person findPersonByPhoneNumber(String phoneNumber) {
        if (!Pattern.compile("[0-9]+").matcher(phoneNumber).matches()) {
            System.out.println("!!! Zadany stup nie je cislo. Koniec hladania.");
            return null;
        }
        for (Person p : persons) {
            if (p != null) {
                if (p.getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                    return p;
                }
            }
        }
        return null;


    }

    //TODO: Implement the method removePerson

    /**
     * Removes the specified person from the register.
     *
     * @param person person to remove
     */
    @Override
    public void removePerson(Person person) {
        int miestoVymazania = 0;
        for (int i = 0; i < count; i++) {
            if (persons[i] == null) {
                continue;
            }
            if (person.equals(persons[i])) {
                persons[i] = null; // opravit neskor
                miestoVymazania = i;
                count--;
            }

        }
        //Arrays.sort(persons); // nefunguje

        // skopirovane, zatial aby to islo - uklada to aj abecedne,
        //Arrays.sort(persons, Comparator.nullsLast(Comparator.naturalOrder()));

        presunNullsNaKoniec(persons, miestoVymazania);


    }

    private void presunNullsNaKoniec(Person[] persons, int miestoVymazania) {
        if (miestoVymazania + 1 == persons.length) {
            return;
        }
        for (int i = miestoVymazania; i < persons.length - 1; i++) {

            if (persons[i + 1] != null) {
                Person temp;
                temp = persons[i + 1];
                persons[i + 1] = null;
                persons[i] = temp;
            } else {
                break;
            }


        }

    }

    @Override
    public Person[] getPersons() {
        return persons;
    }
}
