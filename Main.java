import java.util.Scanner;

public class Main
{

    private static final String[][] MENUS = { {
            // Main Menu
            "Add New Contact", "Edit Contact", "Delete Contact", "View Phonebook", "Exit" },
            {
                    // Edit Contact Menu
                    "Student Number", "First Name", "Last Name", "Occupation","Gender", "Country Code",
                    "Area Code", "Phone Number", "None - Go back to Main Menu" },
            {
                    // Menu for View Phonebook
                    "View All", "View Contact through ID", "View Contacts through Country Code",
                    "Go back to Main Menu" },
                    //Added menu for countries
            {
                    "Federation of Malaysia ","Republic of Indonesia ","Republic of the Philippines ","Republic of Singapore ","Kingdom of Thailand ","Socialist Republic of Vietnam ",
                    "Brunei Darussalam ","Kingdom of Cambodia ","Lao People's Democratic Republic ","Republic of the Union of Myanmar ","Democratic republic of Timor Leste ","No More "}};
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args)
    {
        Phonebook pb = new Phonebook();
        boolean exit = false;
        // Person p1=new Person("2023301607", "First Name", "Last Name", "Occupation","Gender", "123",60,789);
        // pb.insert(p1);
        // Person p2=new Person("2023301690", "America", "Albama", "Occupation","Gender", "123",63,789);
        // pb.insert(p2);
        // Person p3=new Person("2023301601", "Zealnd Name", "Zamora Name", "Occupation","Gender", "123",63,789);
        // pb.insert(p3);
        while (true)
        {
            System.out.println("Phonebook");
            showMenu(1, 1);
            System.out.print("Select an option: ");
            // int opt = input.nextInt();
            int opt = Integer.parseInt(prompt("Select an option: "));

            switch (opt)
            {
                case 1 :
                {
                    pb.insert(createNewPerson());
                    //Block Labeling
                    OUTER:
                    while (true) 
                    {
                        System.out.print("Do you want to add another entry [Y/N]?");
                        String ch=input.nextLine();
                        switch (ch) 
                        {
                            case "Y", "y" :
                            {
                                pb.insert(createNewPerson());
                            }
                            case "N", "n" : 
                            {
                                break OUTER;
                            }
                            default :
                            {
                                System.out.println("Invalid input!");
                            }
                        }
                    }
                    break;
                }
                //Edit Contact
                case 2 :
                
                {

                    String toEdit=prompt("Enter Contact ID to edit:");
                    Person E = pb.getContact(toEdit);
                    if(E != null)
                    {
                        // boolean back=false;
                        EDIT:
                        while(true)
                        {
                            System.out.println("Here's the existing information about "+ toEdit+ ":");
                            System.out.println(E);
                            System.out.println("\nWhich of the following information do you wish to change?");
                            showMenu(2, 1);
                            
                            int tochange = Integer.parseInt(prompt("Select an option: "));
                            String newid, newfname, newlname, newsex, newoccupation, newcontactNum;
                            int newcountryCode, newareaCode;
                            switch(tochange) 
                            {
                                case 1 : 
                                {
                                    newid=prompt("Enter new ID: ");
                                    E.setId(newid);
                                    break;
                                }
                                case 2 : 
                                {
                                    newfname=prompt("Enter new First name: ");
                                    E.setFName(newfname);
                                    break;
                                }
                                case 3 : 
                                {
                                    newlname=prompt("Enter new Last Name: ");
                                    E.setLName(newlname);
                                    break;
                                }
                                
                                case 4 : 
                                {
                                    newoccupation=prompt("Enter new Occupation: ");
                                    E.setOccupation(newoccupation);
                                    break;
                                }
                                case 5 : 
                                {
                                    newsex=prompt("Enter new Sex: ");
                                    E.setSex(newsex);
                                    break;
                                }
                                case 6 : 
                                {
                                    newcountryCode=Integer.parseInt(prompt("Enter new Country Code: "));
                                    E.setCountryCode(newcountryCode);
                                    break;
                                }
                                case 7 : 
                                {
                                    newareaCode=Integer.parseInt(prompt("Enter new Area Code: "));
                                    E.setAreaCode(newareaCode);
                                    break;
                                }
                                case 8 : 
                                {
                                    newcontactNum=prompt("Enter new Contact Number: ");
                                    E.setContactNum(newcontactNum);
                                    break;
                                }
                                case 9 : 
                                {
                                    // back=true;
                                    break EDIT;
                                }
                                
                                
                                
                                // default : throw new AssertionError();
                                
                                
                            }
                            // break;
                        }
                    }
                    else
                    {
                        System.out.println("Contact does not exist!");
                        break;
                    }    
                    break;   
                }
                //Delete Contact
                case 3 :
                {
                    String id = prompt("Enter contact ID to delete: ");
                    Person p = pb.getContact(id);
                    if (p != null)
                    {
                        Person deletedContact = pb.deleteContact(id);
                        if (deletedContact != null)
                        {
                            System.out.println("Contact "+deletedContact.getId()+" has been successfully deleted!");
                        }
                    }
                    else
                    {
                        System.out.println("This contact does not exist!");
                    }
                    break;
                }
                //View Contact/s
                case 4 :
                {
                    OUTER_1:
                    while (true) 
                    {
                        showMenu(3, 1);
                        int showOpt = Integer.parseInt(prompt("Enter option:"));
                        switch (showOpt) 
                        {
                            //View All
                            case 1 : 
                            {
                                if(pb.getSize()==0)
                                {
                                    System.out.println("Phonebook is empty!");
                                }
                                System.out.println(pb);
                                break;
                            }
                            //View by ID
                            case 2 : 
                            {
                                while(true)
                                {
                                    String targetId = prompt("Enter ID to search: ");
                                    Person target = pb.getContact(targetId);
                                    if (target != null)
                                    {
                                        System.out.println(target);
                                        break;
                                    }
                                    else
                                    {
                                        System.out.println("No contact exists with that ID!");
                                        break;
                                    }
                                    
                                }
                                break;
                            }
                            //View by country code
                            
                            case 3 : 
                            {
                                int ccCount = 0;
                                int[] countryCodes = new int[9];
                                System.out.println("From which country do you wish to view? Enter multiple values if you want.");
                                showMenu(4, 1);
                                while (true)
                                {
                                    
                                    int countryCode = Integer.parseInt(prompt("Enter Country Code:"));
                                    // Print if input is 0
                                    if (countryCode == 12)
                                    {
                                        System.out.println(pb.printContactsFromCountryCodes(countryCodes));
                                        break;
                                    }
                                    // Check if area code is already inputted
                                    boolean exists = false;
                                    for (int a : countryCodes)
                                    {
                                        if (a == countryCode)
                                        {
                                            System.out.println(
                                                    "This area code has already been inputted!");
                                            exists = true;
                                            break;
                                        }
                                    }
                                    // Only add if area codes isn't part of the array...
                                    //Original Code
                                    if (!exists)
                                    {
                                        countryCodes[ccCount] = convertChoices(countryCode);
                                        ccCount++;
                                    }
                                }
                                break;
                            }
                            
                            case 4 : 
                            {
                                break OUTER_1;
                            }
                        }
                    }

                }
                System.out.println("");
                break;
                case 5 : 
                {
                    exit = true;
                }
            }
            //Edit Contact
            //Delete Contact
            //View Phonebook
            if (exit)
                break;
        }
    }

    /**
     * Show menu based on given index. <br>
     * <br>
     * 1 for Main Menu. <br>
     * <br>
     * 2 for Edit Contact Menu. <br>
     * <br>
     * 3 for View Phonebook Menu. <br>
     * <br>
     * 4 for Country Code Menu.
     * 
     * @param menuIdx Index of the menu to be shown.
     * @param inlineTexts Number of menu options to be printed in a single line. Set to 1 if you
     *        want every line to only have one menu option.
     */
    private static void showMenu(int menuIdx, int inlineTexts)
    {
        String[] menu = MENUS[menuIdx - 1];
        int count = 0;
        String space = inlineTexts == 0 ? "" : "%-12s";
        String fmt = "[%d] " + space;
        for (int i = 0; i < menu.length; i++)
        {
            System.out.printf(fmt, i + 1, menu[i]);
            if (inlineTexts != 0)
            {
                count += 1;
            }
            if (count % inlineTexts == 0)
            {
                System.out.print("\n");
            }
        }
    }

    /**
     * Convert choices from the menu into their appropriate country code values.
     * 
     * @return Country code value of the menu choice.
     */
    private static int convertChoices(int choice)
    {
        // Complete this method.
            switch (choice) 
            {
                    case 1 -> {
                        return 60;
                }
                    case 2 -> {
                        return 62;
                }
                    case 3 -> {
                        return 63;
                }
                    case 4 -> {
                        return 65;
                }
                    case 5 -> {
                        return 66;
                }
                    case 6 -> {
                        return 84;
                }
                    case 7 -> {
                        return 673;
                }
                    case 8 -> {
                        return 855;
                }
                    case 9 -> {
                        return 856;
                }
                    case 10 -> {
                        return 95;
                }
                    case 11 -> {
                        return 670;
                }
                    default -> {
                }
            }
        return 0;
        // default -> throw new AssertionError();
    }

    /**
     * Create a new person object using a slightly complicated setup.
     * 
     * @return Newly created person object.
     */
    private static Person createNewPerson()
    {
        String id, fname, lname, sex, occupation, contactNum;
        int countryCode, areaCode;
        id = prompt("Enter Contact ID: ");
        fname = prompt("Enter First Name: ");
        lname = prompt("Enter Last Name: ");
        occupation = prompt("Enter Occupation: ");
        sex = prompt("Enter sex/gender: ");
        countryCode = Integer.parseInt(prompt("Enter Country Code: "));
        areaCode = Integer.parseInt(prompt("Enter Area Code: "));
        contactNum = prompt("Enter Contact Number: ");
        return new Person(id, fname, lname, sex, occupation, contactNum, countryCode, areaCode);
    }

    /**
     * Receive prompt and return the inputted value back to the variable or process that requires
     * it. Data type is String. Do not forget to type cast if possible.
     * 
     * @param phrase Phrase to be given to user when requiring input.
     * @return Returns the data needed.
     */
    private static String prompt(String phrase)
    {
        System.out.print(phrase);
        return input.nextLine();
    }
}
