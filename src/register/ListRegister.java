package register;

import java.util.*;
import java.util.regex.Pattern;

/**
 * register.Person register.
 */
public class ListRegister implements Register {
    private List<Person> persons = new ArrayList<>();

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Person getPerson(int index) {
        return persons.get(index);
    }

    @Override
    public void addPerson(Person person) {
        persons.add(person);
    }

    @Override
    public Person findPersonByName(String name) {
        //persons.stream().forEach(p -> System.out.println(p));
        return persons.parallelStream()
               .filter(p -> p.getName().contains(name))
               .findFirst()
               .get();
    }

    @Override
    public Person findPersonByPhoneNumber(String phoneNumber) {
        for(Person p : persons) {
            if(p.getPhoneNumber().equals(phoneNumber)) return p;
        }
        return null;
    }

    @Override
    public void removePerson(Person person) {
        persons.remove(person);
    }
}
