package register;

import java.util.*;

/**
 * register.Person register.
 */
public class SetRegister implements Register {
    private SortedSet<Person> persons = new TreeSet<>();

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Person getPerson(int index) {
        Iterator<Person> it = persons.iterator();
        int i = 0;
        while(it.hasNext()) {
            if(i == index) return it.next();
            i++;
        }
        return null;
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
