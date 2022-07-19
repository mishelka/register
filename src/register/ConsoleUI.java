package register;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * User interface of the application.
 */
public class ConsoleUI {
    /**
     * register.Register of persons.
     */
    private Register register;

    /**
     * In JDK 6 use Console class instead.
     *
     * @see //readLine() //LukasD zakomntovane
     */
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Menu options.
     */
    private enum Option {
        PRINT, ADD, UPDATE, REMOVE, FIND, EXIT
    }

    ;

    public ConsoleUI(ArrayRegister register) {
        this.register = register;
    }

    public void run() {
        while (true) {
            switch (showMenu()) {
                case PRINT:
                    printRegister();
                    break;
                case ADD:
                    addToRegister();
                    break;
                case UPDATE:
                    updateRegister();
                    break;
                case REMOVE:
                    removeFromRegister();
                    break;
                case FIND:
                    findInRegister();
                    break;
                case EXIT:
                    return;
            }
        }
    }

    private String readLine() {
        //In JDK 6.0 and above Console class can be used
        //return System.console().readLine();

        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private Option showMenu() {
        System.out.println("Menu.");
        for (Option option : Option.values()) {
            System.out.printf("%d. %s%n", option.ordinal() + 1, option);
        }
        System.out.println("-----------------------------------------------");

        int selection = -1;
        do {
            System.out.println("Option: ");
            selection = Integer.parseInt(readLine());
        } while (selection <= 0 || selection > Option.values().length);

        return Option.values()[selection - 1];
    }

    //TODO: Implement the method printRegister
    private void printRegister() {
        // throw new UnsupportedOperationException("Method printRegister not yet implemented");
        for (int i = 0; i < register.getCount(); i++) {
            System.out.printf("%s. %s (%s) %n", (i + 1), register.getPerson(i).getName(), register.getPerson(i).getPhoneNumber());

        }
    }

    private void addToRegister() {
        System.out.println("Enter Name: ");
        String name = readLine();
        if (!Pattern.compile("[A-Z a-z]+").matcher(name).matches()) {
            System.out.println("!!! Zadany stup nie je meno.");
            addToRegister();
            return;
        }
        System.out.println("Enter Phone Number: ");
        String phoneNumber = readLine();
        if (!Pattern.compile("[0-9]+").matcher(phoneNumber).matches()) {
            System.out.println("!!! Zadany stup nie je tel. cislo.");
            addToRegister();
            return;
        }

        register.addPerson(new Person(name, phoneNumber));
    }

    //TODO: Implement the method updateRegister
    private void updateRegister() {
        printRegister();
        System.out.println("Zadaj poradove cislo osoby, kt. chces urobit update");
        int cislo;
        try {
            cislo = Integer.parseInt(readLine());
        } catch (NumberFormatException e) {
            //e.printStackTrace();
            System.out.println("Zly format cisla");
            updateRegister();
            return;
        }
        if (cislo > register.getCount() + 1 || cislo < 1) {
            System.out.println("Zadane cislo je mimo rozsah poradovych cisel osob v registry");
            updateRegister();
            return;
        }
        System.out.println("Robim update ososby: " + register.getPerson(cislo - 1));

        // pyta si nove meno osoby
        System.out.println("Zadaj nove meno osoby");
        String input1 = readLine();
        if (Pattern.compile("[A-Za-z]+").matcher(input1).matches()) {
            register.getPersons()[cislo - 1].setName(readLine());
            System.out.println("update mena prebehol");
        } else {
            System.out.println("!!!update mena neprebehol, vstup nie je meno");
            ;
        }

        // pyta si nove telefonne cislo osoby
        System.out.println("Zadaj nove telefonne cislo osoby");
        String input2 = readLine();
        if (Person.isValidPhoneNumberStatic(input2)) {
            register.getPersons()[cislo - 1].setPhoneNumber(input2);
            System.out.println("update tel. cisla prebehol");
        } else {
            System.out.println("!!!update tel cisla neprebehol, vstup nie je cislo");
        }


    }

    //TODO: Implement the method findInRegister
    private void findInRegister() {
        System.out.println("Hladat osobu podla: 1. Mena, 2. Tel. cisla, 3. EXIT");
        String intput = readLine();
        if (!Pattern.compile("[123]{1}").matcher(intput).matches()) {
            System.out.println("!!! Zle zvolena moznost");
            findInRegister();
            return;
        }
        switch (intput) {
            case "1":
                System.out.println("Zadaj hladane meno:");
                Person pName = register.findPersonByName(readLine());
                if (pName == null) {
                    System.out.println("Podla mena nebola najdena ziadna osoba");
                } else {
                    System.out.printf("Podla mena bola najdena osoba: %s%n", pName.toString());
                }
                break;
            case "2":
                System.out.println("Zadaj hladane cislo:");
                Person pPhoneNumber = register.findPersonByPhoneNumber(readLine());
                if (pPhoneNumber == null) {
                    System.out.println("Podla tel. cisla nebola najdena ziadna osoba");
                } else {
                    System.out.printf("Podla tel. cisla bola najdena osoba: %s%n", pPhoneNumber.toString());
                }
                break;
            case "3":
                return;


        }


    }

    private void removeFromRegister() {
        System.out.println("Enter index: ");
        int index = Integer.parseInt(readLine());
        Person person = register.getPerson(index - 1);
        register.removePerson(person);
    }

}
