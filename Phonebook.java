


public class Phonebook
{
    // Storage of contacts.
    private Person[] contacts;
    // Number of contacts present in the phonebook.
    private int size;

    /**
     * Create a phonebook of size 50.
     */
    public Phonebook()
    {
        contacts = new Person[50];
        size = 0;
    }

    /**
     * @return Number of contacts stored in this phonebook.
     */
    public int getSize()
    {
        // Complete this method. Done?
        return  size;
    }

    /**
     * Get the contact at index.
     * 
     * @param index Index to get contact.
     * @return Person object from index. Null if index is not valid or out of range.
     */
    public Person getContactAtIndex(int index)
    {
        // Complete this method.Done
        if(index >= 0 && index < size)
        {
            return contacts[index];
        }
        return null;
    }

    /**
     * Get the person object based on a given id.
     * 
     * @param id Target id.
     * @return Person object that has this id. Null if it does not exist.
     */
    public Person getContact(String id)
    {
        // Complete this method
        for(int i = 0; i < size; i++)
        {
            if(contacts[i] != null && contacts[i].getId().equals(id))
            {
                return contacts[i];
            }
        }
        return null;
    }

    /**
     * Checks if this phonebook has contacts or not.
     * 
     * @return True or False.
     */
    public boolean isEmpty()
    {
        return this.getSize() == 0;
    }

    /**
     * Increase number of contacts present in this phonebook.
     */
    public void incrSize()
    {
        this.size++;
    }

    /**
     * Decrease number of contacts present in this phonebook.
     */
    public void decrSize()
    {
        this.size--;
    }

    /**
     * Increases the size of the phonebook whenever it is full.
     */
    private void increasePhonebookMaxSize()
    {
        // Complete this method
        Person[] replace= new Person[contacts.length * 2];
        for (int i = 0; i < contacts.length; i++) {
            replace[i] = contacts[i];
        }
    contacts = replace;
    }

    /**
     * Inserts a new person object at its appropriate lexicographic location in the phonebook.
     * 
     * @param p Person to be addded to the Phonebook.    DONE
     */
    public void insert(Person p)
    {
        // Complete this method
        if (p == null) 
        {
            throw new IllegalArgumentException("Invalid");
        }
        if (size == contacts.length) 
        {
            // contacts = Arrays.copyOf(contacts, contacts.length + 1);
            increasePhonebookMaxSize();
        }
        for(Person person: contacts)
        {
            if (person!=null&&person.getId().equals(p.getId()))
            {
                System.out.println("ID already added!");
                break;
            }
        }
            
        int pos = findIndexInsertion(p);
        adjustPhonebook(pos, size, "b");
        contacts[pos] = p;
        size++;
        

    }

    /**
     * Searches in what index should this person object with the given be inserted.
     * 
     * @param p Person object to be inserted into the phonebook.
     * @return Appropriate index (position).
     */
    private int findIndexInsertion(Person p)
    {
        // Complete this method
        for (int i = 0; i < size; i++) 
        {
            if (contacts[i].compareTo(p) > 0) {
                return i;
            }
        }
        return size;
    }

    /**
     * Delete a person based on their contact id.
     * 
     * @param id Contact ID of that contact.
     * @return Deleted contact.
     */
    public Person deleteContact(String id)
    {
        // Complete this method...
        for (int i = 0; i < size; i++) 
        {
            if (contacts[i] != null && contacts[i].getId().equals(id)) 
            {
                Person removed = contacts[i];
                adjustPhonebook(i, size - 1, "f");
                contacts[size - 1] = null;
                size--;
                return removed;
            }
        }
        return null;
    }

    /**
     * Adjusts the existing contacts in a phonebook from a given starting index to where it ends,
     * following a particular direction.
     * 
     * @param start Index to start adjustment from.
     * @param end Index to end adjustment into.
     * @param direction Direction in which the adjustment must be made. direction = "f" if element
     *        at index 0 takes the value of the element next to it (e.g. index 1). direction = "b"
     *        if element at index 1 takes the value of the element behind it (e.g. index 0).
     */
    private void adjustPhonebook(int start, int end, String direction)
    {
        // Complete this method...
        if (direction.equals("f")) 
        {
            for (int i = start; i < end; i++) 
            {
                contacts[i] = contacts[i + 1];
            }
        } else if (direction.equals("b")) 
        {
            for (int i = end; i > start; i--) 
            {
                contacts[i] = contacts[i - 1];
            }
        }
    }

    /**
     * Uses ellipsis to ambiguously accept as many country codes as possible. <br>
     * <br>
     * For example: <br>
     * <br>
     * If we have: printContactsFromCountryCodes(1, 2, 3) <br>
     * <br>
     * Then we get: countryCodes = { 1, 2, 3 };
     * 
     * @param countryCodes Area codes to be used as a filter.
     * @return Contacts on this phonebook under a particular area code set by the user.
     */
    public String printContactsFromCountryCodes(int... countryCodes)
    {
        // Complete this method.
        // String[] countries=new String[20];
        String res="Here are the students from ";

        //if only one 
        //Here are the students from Philippines:
        //if multiple countries
        //Here are the students from Philippines, Indonesia and Myanmar: 
        String countries = "";

        int[] nums={60,62,63,65,66,84,673,855,856,95,670};
        String[] MENUS={"Federation of Malaysia","Republic of Indonesia","Republic of the Philippines","Republic of Singapore","Kingdom of Thailand","Socialist Republic of Vietnam",
            "Brunei Darussalam","Kingdom of Cambodia","Lao People's Democratic Republic","Republic of the Union of Myanmar","Democratic republic of Timor Leste","No More"};

        // int index = 0;
        // for(int ints :countryCodes)
        // {
        //     if(ints == nums[index])
        //     {
        //         countries+=MENUS[index];
        //         countries+=", ";
        //         index++;
        //     }
        //     if(countryCodes[index]==0)
        //     {
        //         break;
        //     }
        // }

        for(int code: countryCodes)
        {
            for(int i=0; i< nums.length;i++)
            {
                if(code == nums[i])
                {
                    if(!countries.isEmpty())
                    {
                        countries+=',';
                    }
                    countries+=MENUS[i];
                    break;
                }
            }
        }
        res+=countries;
        res+=":\n"; 
        for (Person p : contacts) 
        {
            if (p != null) 
            {
                for (int code : countryCodes) 
                {
                    if (p.getCountryCode() == (code)) 
                    {
                        res+=p.toString();
                        res+='\n';
                    }
                }
            }
        }
        return res;
    }

    /**
     * Print the entire phonebook without any filter or so...
     * 
     * @return The entire list of contacts present in this phonebook.
     */
    @Override
    public String toString()
    {
        // Complete this method.
        // StringBuilder result = new StringBuilder();
        String result = "Phonebook List:\n";
        for (int i = 0; i < size; i++) 
        {
            result+=contacts[i];
            result += '\n';
        }
        return result;
    }
}
