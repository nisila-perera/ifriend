import java.util.*;
import java.time.*;

class ContactList {
    private Contacts[] contactsArray;
    private int nextIndex;
    private int size;
    private int loadFact;

    ContactList(int size, int loadFact) {
        contactsArray = new Contacts[size];
        this.size = size;
        this.loadFact = loadFact;
        nextIndex = 0;
    }

    // --------------------Array Check size---------------------
    private boolean isFull() {
        return nextIndex >= size;
    }

    // --------------------EXTEND ARRAYS--------------------
    private void extendArrays() {
        Contacts[] tempContactsArray = new Contacts[size + loadFact];
        for (int i = 0; i < contactsArray.length; i++) {
            tempContactsArray[i] = contactsArray[i];
        }
        contactsArray = tempContactsArray;
    }

    // -----------------ADD Method-------------------------
    public void addc(Contacts contact) {
        if (isFull()) {
            extendArrays();
        }
        contactsArray[nextIndex++] = contact;
    }

    // ---------------------------SEARCH METHOD--------------------------
    public int searchByNameOrPhoneNumber(String nameOrPhone) {
        for (int i = 0; i < nextIndex; i++) {
            if (contactsArray[i].getContactName().equals(nameOrPhone)
                    || contactsArray[i].getContactPhone().equals(nameOrPhone)) {
                return i;
            }
        }
        return -1;
    }

    // ------------------UPDATE NAME------------------------
    public void updateName(int index, String name) {
        contactsArray[index].setContactName(name);
    }

    // ------------------UPDATE PhoneNUmber------------------------
    public void updatePhoneNumber(int index, String phoneNumber) {
        contactsArray[index].setContactPhone(phoneNumber);
    }

    // ------------------UPDATE CompanyName------------------------
    public void updateCompanyName(int index, String companyName) {
        contactsArray[index].setContactCompany(companyName);
    }

    // ------------------UPDATE Salary------------------------
    public void updateSalary(int index, double salary) {
        contactsArray[index].setContactSalary(salary);
    }

    public Contacts[] tempArray() {
        Contacts[] tempContactArray = new Contacts[size];
        for (int i = 0; i < nextIndex; i++) {
            tempContactArray[i] = contactsArray[i];
        }
        contactsArray = tempContactArray;
        return tempContactArray;
    }

    // ------------Size Return to Sort---------------------
    public int getSize() {
        return nextIndex;
    }

    // ---------------------------DELETE-------------------------------
    public void delete(int index) {
        Contacts[] tempContactArray = new Contacts[contactsArray.length - 1];
        for (int i = 0; i < index; i++) {
            tempContactArray[i] = contactsArray[i];
        }
        for (int i = index; i < tempContactArray.length; i++) {
            tempContactArray[i] = contactsArray[i + 1];
        }
        size--;
        nextIndex--;
        contactsArray = tempContactArray;
    }
}


class Contacts{
    private String contactId;
    private String contactName;
    private String contactPhone;
    private String contactCompany;
    private double contactSalary;
    private String contactBday;

    // Constructor

    public Contacts(String contactId, String contactName, String contactPhone, String contactCompany, double contactSalary, String contactBday) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.contactCompany = contactCompany;
        this.contactSalary = contactSalary;
        this.contactBday = contactBday;
    }

    // Getters

    public String getContactId() {
        return contactId;
    }

    public String getContactName() {
        return contactName;
    }
    
    public String getContactPhone() {
        return contactPhone;
    }
    
    public String getContactCompany() {
        return contactCompany;
    }
    
    public double getContactSalary() {
        return contactSalary;
    }
    
    public String getContactBday() {
        return contactBday;
    }

    // Setters

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public void setContactCompany(String contactCompany) {
        this.contactCompany = contactCompany;
    }

    public void setContactSalary(double contactSalary) {
        this.contactSalary = contactSalary;
    }

    public void setContactBday(String contactBday) {
        this.contactBday = contactBday;
    }
}

class Example{
    // -------------------CREATE AN ARRAYS ----------------

    // public static String[] idArray = new String[0];
    // public static String[] nameArray=new String[0];
    // public static String[] phoneNumberArray=new String[0];
    // public static String[] companyNameArray=new String[0];
    // public static double[] salaryArray=new double[0];
    // public static String[] birthdayArray=new String[0];
    

    public static Contacts[] contactsArray=new Contacts[0];
    
	    //----------------------CLEAR CONSOLE --------------------
		public final static void clearConsole() { 
			try {
			final String os = System.getProperty("os.name"); 
			if (os.contains("Windows")) {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
			System.out.print("\033[H\033[2J"); 
			System.out.flush();
			}
			} catch (final Exception e) {
			e.printStackTrace();
			// Handle any exceptions.
			}
		}
    //-----------------MAIN METHOD--------------------
    public static void main(String[] args){
        homepage();
    }
    //-------------------GENERATE ID----------------
    public static String generateId(){
		if(contactsArray.length==0){
			return "C0001";
		}
		String lastId=contactsArray[contactsArray.length-1].getContactId();
		int lastNo=Integer.parseInt(lastId.substring(1));
		return String.format("C%04d",lastNo+1);
	}
    //-----------------HOME PAGE--------------------
    public static void homepage(){
        Scanner input = new Scanner(System.in);
        System.out.println("=======================iFRIEND CONTACT ORGANIZER============================");
        System.out.println("\n[01] Add Contacts");
        System.out.println("\n[02] Update Contacts");
        System.out.println("\n[03] Delete Contacts");
        System.out.println("\n[04] Search Contacts");
        System.out.println("\n[05] List Contacts");
        System.out.println("\n[06] Exit");
        System.out.print("\nEnter option to continue : ");
        int option=input.nextInt();
        clearConsole();
        switch(option){
            case 1 : addContacts();break;
            case 2 : updateContacts();break;
            case 3 : deleteContacts();break;
            case 4 : searchContacts();break;
            case 5 : listContacts();break;
            case 6 : exit();break;
            default : System.out.println("Invalid option...");break;
        }

    }
    //------------------------VALIDATE PHONENUMBER---------------------------
    public static boolean isValidPhoneNumber(String phoneNumber){
        if(phoneNumber.length()==10 && phoneNumber.charAt(0)=='0'){
            for(int i=1; i<phoneNumber.length(); i++){
                if(!Character.isDigit(phoneNumber.charAt(i))){
                    return false;
                }
            }
            return true;
        }
        return false;

    }
    //-------------------VALIDATE SALARY----------------------
    public static boolean isValidSalary(double salary){
        return salary>0;
    }
    // -------------------BIRTHDAY VALIDATION----------------
	public static boolean isValidBirthday(String birthday){
        String y=birthday.substring(0,4);
		int year=Integer.parseInt(y);
		String m=birthday.substring(5,7);
		int month=Integer.parseInt(m);
		String d=birthday.substring(8,10);
		int day=Integer.parseInt(d);
		LocalDate currentDate = LocalDate.now();
		int currentMonthValue = currentDate.getMonthValue();
		int currentYear=currentDate.getYear();    
		int currentMonthDate=currentDate.getDayOfMonth();
			
		if(year%4!=0 & month==2){
			if(day>28){
				return false;
			}else{
				return true;
			}
		}
		if(year%4==0 & month==2){
			if(day>29){
				return false;
			}else{
				return true;
			}
		}
		if(month==4 || month==6 || month==9 || month==11){
			if(day>30){
				return false;					
			}
		}
		if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
			if(day>31){
				return false;
			}	
		}
		if(month>12){
			return false;
		}
		if(year<currentYear){
			return true;
			}else if(year==currentYear){
									
				if(month>currentMonthValue){
					return true;
				}else if(month==currentMonthValue){
									
					if(day<=currentMonthDate){
						return true;
					}
				}
			}
					return false;
    }
    //-------------------------EXTEND ARRAYS-----------------------
    public static void extendArrays(){
        Contacts[] tempContacts=new Contacts[contactsArray.length+1];

        for(int i=0; i<contactsArray.length; i++){
            tempContacts[i]=contactsArray[i];
        }

        contactsArray=tempContacts;

        // String[] tempIdArray=new String[idArray.length+1];
        // String[] tempNameArray=new String[nameArray.length+1];
        // String[] tempPhoneNumberArray=new String[phoneNumberArray.length+1];
        // String[] tempCompanyNameArray=new String[companyNameArray.length+1];
        // double[] tempSalaryArray=new double[salaryArray.length+1];
        // String[] tempBirthdayArray=new String[birthdayArray.length+1];

        // for(int i=0; i<idArray.length; i++) {
        //     tempIdArray[i]=idArray[i];
        //     tempNameArray[i]=nameArray[i];
        //     tempPhoneNumberArray[i]=phoneNumberArray[i];
        //     tempCompanyNameArray[i]=companyNameArray[i];
        //     tempSalaryArray[i]=salaryArray[i];
        //     tempBirthdayArray[i]=birthdayArray[i];
        // }
        
        // idArray=tempIdArray;
        // nameArray=tempNameArray;
        // phoneNumberArray=tempPhoneNumberArray;
        // companyNameArray=tempCompanyNameArray;
        // salaryArray=tempSalaryArray;
        // birthdayArray=tempBirthdayArray;
    }
    //-----------------ADD CONTACTS--------------------
    public static void addContacts(){
        Scanner input=new Scanner(System.in);
        do{
            System.out.println("======================ADD CONTACTS================");
            String id = generateId();
            System.out.println("\n"+id);
            System.out.println("=============");
            System.out.print("Name : ");
            String name=input.next();
            String phoneNumber;
            L1:do{
                System.out.print("Phone Number : ");
                phoneNumber  = input.next();
                if(!isValidPhoneNumber(phoneNumber)){
                    System.out.println("\n\tInvalid phone number...");
                    System.out.print("\nDo you want to input phone number again : ");
                    char ch = input.next().charAt(0);
                    if(ch=='Y'||ch=='y'){
                        System.out.print("\033[5A");
                        System.out.print("\033[0J");
                        continue L1;
                    }else if(ch=='N'||ch=='n'){
                        clearConsole();
                        homepage();
                    }
                }

            }while(!isValidPhoneNumber(phoneNumber));

            System.out.print("Company Name : ");
            String companyName  = input.next();
            double salary;

            L2:do{
                System.out.print("Salary : ");
                salary=input.nextDouble();
                if(!isValidSalary(salary)){
                    System.out.println("\n\tInvalid salary...");
                    System.out.print("\nDo you want to input salary again : ");
                    char ch=input.next().charAt(0);
                    if(ch=='Y'||ch=='y'){
                        System.out.print("\033[5A");
                        System.out.print("\033[0J");
                        continue L2;
                    }else if(ch=='N'||ch=='n'){
                        clearConsole();
                        homepage();
                    }
                }

            }while(!isValidSalary(salary));
            String birthday;

            L3:do{
                System.out.print("Birthday : ");
                birthday = input.next();
                if(!isValidBirthday(birthday)){
                    System.out.println("\n\tInvalid birthday...");
                    System.out.print("\nDo you want to input birthday again : ");
                    char ch=input.next().charAt(0);
                    if(ch=='Y'||ch=='y'){
                        System.out.print("\033[5A");
                        System.out.print("\033[0J");
                        continue L3;
                    }else if(ch=='N'||ch=='n'){
                        clearConsole();
                        homepage();
                    }
                }

            }while(!isValidBirthday(birthday));

            extendArrays();

            Contacts contacts = new Contacts(id, name, phoneNumber, companyName, salary, birthday);
            contactsArray[contactsArray.length-1]=contacts;
            
            
            // idArray[idArray.length-1]=id;
            // nameArray[nameArray.length-1]=name;
            // phoneNumberArray[phoneNumberArray.length-1]=phoneNumber;
            // companyNameArray[companyNameArray.length-1]=companyName;
            // salaryArray[salaryArray.length-1]=salary;
            // birthdayArray[birthdayArray.length-1]=birthday;
            

            System.out.println("\n\tContact has been added successfully...");
            System.out.print("\nDo you want to add another contact : ");
            char ch=input.next().charAt(0);
            if(ch=='Y'||ch=='y'){
                clearConsole();
                addContacts();
            }else if(ch=='N'||ch=='n'){
                clearConsole();
                homepage();
            }

        }while(true);

    }
    //---------------------------SEARCH METHOD--------------------------
    public static int searchByNameOrPhoneNumber(String nameOrPhone){
        for(int i=0; i<contactsArray.length; i++){
            if(contactsArray[i].getContactName().equals(nameOrPhone) || contactsArray[i].getContactPhone().equals(nameOrPhone)){
                return i;
            }
        }
        return -1;
    }
    //--------------------------PRINT DETAILS---------------------------
    public static void printDetails(int index){
        System.out.println("Contact Id : "+contactsArray[index].getContactId());
        System.out.println("Name : "+contactsArray[index].getContactName());
        System.out.println("Phone Number : "+contactsArray[index].getContactPhone());
        System.out.println("Companu Name : "+contactsArray[index].getContactCompany());
        System.out.println("Salary : "+contactsArray[index].getContactSalary());
        System.out.println("Birthday : "+contactsArray[index].getContactBday());
    }
    //--------------------------SEARCH CONTACT-------------------------
    public static void searchContacts(){
        Scanner input=new Scanner(System.in);
        do{
            System.out.println("=====================SEARCH CONTACTS======================");
            System.out.print("\nSearch contact by name or phone number : ");
            String nameOrPhone=input.next();
            int index = searchByNameOrPhoneNumber(nameOrPhone);

            if(index == -1){
                System.out.println("\n\tNo contact found for "+nameOrPhone);
                System.out.print("\nDo you want to try a new search : ");
                char ch=input.next().charAt(0);
                if(ch=='Y'||ch=='y'){
                    clearConsole();
                    searchContacts();
                }else if(ch=='N'||ch=='n'){
                    clearConsole();
                    homepage();
                }
            }else{
                printDetails(index);
                System.out.print("\nDo you want to search another contact : ");
                char ch=input.next().charAt(0);
                if(ch=='Y'||ch=='y'){
                    clearConsole();
                    searchContacts();
                }else if(ch=='N'||ch=='n'){
                    clearConsole();
                    homepage();
                }
            }

        }while(true);
    }
    //--------------------------UPDATE NAME----------------------------
    public static void updateName(int index){
        Scanner input=new Scanner(System.in);
        System.out.println("\n Update Name");
        System.out.println("===================");
        System.out.print("\nInput new name : ");
        String newName = input.next();
        contactsArray[index].setContactName(newName);
    }
    //--------------------------UPDATE PHONE----------------------------
    public static void updatePhoneNumber(int index){
        Scanner input=new Scanner(System.in);
        System.out.println("\n Update Phonenumber");
        System.out.println("========================");
        System.out.print("\nInput new phone number : ");
        String newPhoneNumber = input.next();
        contactsArray[index].setContactPhone(newPhoneNumber);
    }
    //--------------------------UPDATE COMPANY--------------------------
    public static void updateCompanyName(int index){
        Scanner input=new Scanner(System.in);
        System.out.println("\n Update Company Name");
        System.out.println("===========================");
        System.out.print("\nInput new company name : ");
        String newCompanyName = input.next();
        contactsArray[index].setContactCompany(newCompanyName);
    }
    //--------------------------UPDATE SALARY---------------------------
    public static void updateSalary(int index){
        Scanner input=new Scanner(System.in);
        System.out.println("\n Update Salary");
        System.out.println("==================");
        System.out.print("\nInput new salary : ");
        double newSalary = input.nextDouble();
        contactsArray[index].setContactSalary(newSalary);
    }
    //--------------------------UPDATE CONTACTS-----------------------
    public static void updateContacts(){
        Scanner input = new Scanner(System.in);
        do{
            System.out.println("=======================UPDATE CONTACTS================");
            System.out.print("\nSearch contact by name or phone number : ");
            String nameOrPhone=input.next();
            int index = searchByNameOrPhoneNumber(nameOrPhone);

            if(index == -1){
                System.out.println("\n\tNo contact found for "+nameOrPhone);
                System.out.print("\nDo you want to try a new search : ");
                char ch=input.next().charAt(0);
                if(ch=='Y'||ch=='y'){
                    clearConsole();
                    updateContacts();
                }else if(ch=='N'||ch=='n'){
                    clearConsole();
                    homepage();
                }
            }
            else{
                printDetails(index);

                System.out.println("\nWhat do you want to update");
                System.out.println("\n\t[01] Name");
                System.out.println("\t[02] Phone number");
                System.out.println("\t[03] Company Name");
                System.out.println("\t[04] Salary");
                System.out.println("\nEnter an option to continue...");
                int option=input.nextInt();
                switch(option){
                    case 1 : updateName(index);break;
                    case 2 : updatePhoneNumber(index);break;
                    case 3 : updateCompanyName(index);break;
                    case 4 : updateSalary(index);break;
                    default : System.out.println("\n\tInvalid option...");
                }
                System.out.println("\nContact has been update successfully.");
                System.out.print("\nDo you want to update another contact : ");
                char ch=input.next().charAt(0);
                if(ch=='Y'||ch=='y'){
                    clearConsole();
                    updateContacts();
                }else if(ch=='N'|| ch=='n'){
                    clearConsole();
                    homepage();
                }
            }
        }while(true);
    }
    //---------------------------DELETE-------------------------------
    public static void delete(int index){
        Contacts[] tempContacts=new Contacts[contactsArray.length-1];
        // String[] tempIdArray=new String[idArray.length-1];
        // String[] tempNameArray=new String[nameArray.length-1];
        // String[] tempPhoneNumberArray=new String[phoneNumberArray.length-1];
        // String[] tempCompanyNameArray=new String[companyNameArray.length-1];
        // double[] tempSalaryArray=new double[salaryArray.length-1];
        // String[] tempBirthdayArray=new String[birthdayArray.length-1];

        for(int i=index; i<contactsArray.length-1; i++) {
            contactsArray[i]=contactsArray[i+1];
            // idArray[i]=idArray[i+1];
            // nameArray[i]=nameArray[i+1];
            // phoneNumberArray[i]=phoneNumberArray[i+1];
            // companyNameArray[i]=companyNameArray[i+1];
            // salaryArray[i]=salaryArray[i+1];
            // birthdayArray[i]=birthdayArray[i+1];
        }
        for(int i=0; i<contactsArray.length-1; i++) {
            tempContacts[i]=contactsArray[i];
            // tempIdArray[i]=idArray[i];
            // tempNameArray[i]=nameArray[i];
            // tempPhoneNumberArray[i]=phoneNumberArray[i];
            // tempCompanyNameArray[i]=companyNameArray[i];
            // tempSalaryArray[i]=salaryArray[i];
            // tempBirthdayArray[i]=birthdayArray[i];
        }
        contactsArray=tempContacts;
        // idArray=tempIdArray;
        // nameArray=tempNameArray;
        // phoneNumberArray=tempPhoneNumberArray;
        // companyNameArray=tempCompanyNameArray;
        // salaryArray=tempSalaryArray;
        // birthdayArray=tempBirthdayArray;
 
    }
    //--------------------------DELETE CONTACTS-----------------------
    public static void deleteContacts(){
        Scanner input = new Scanner(System.in);
        do{
            System.out.println("======================DELETE CONTACTS================");
            System.out.print("\nSearch contact by name or phone number : ");
            String nameOrPhone=input.next();
            int index = searchByNameOrPhoneNumber(nameOrPhone);

            if(index == -1){
                System.out.println("\n\tNo contact found for "+nameOrPhone);
                System.out.print("\nDo you want to try a new search : ");
                char ch=input.next().charAt(0);
                if(ch=='Y'||ch=='y'){
                    clearConsole();
                    deleteContacts();
                }else if(ch=='N'||ch=='n'){
                    clearConsole();
                    homepage();
                }
            }else{
                printDetails(index);
                L1:do{
                    System.out.print("\nDo you want to delete this contact : ");
                    char ch=input.next().charAt(0);
                    if(ch=='Y'||ch=='y'){
                        delete(index);
                        System.out.println("\nContact has been deleted successfully...");
                        break L1;
                    }else if(ch=='N'||ch=='n'){
                        break L1;
                    }

                }while(true);

                System.out.println("\nDo you want to delete another contact : ");
                char ch=input.next().charAt(0);
                if(ch=='Y'||ch=='y'){
                    clearConsole();
                    deleteContacts();
                }else if(ch=='N'||ch=='n'){
                    clearConsole();
                    homepage();
                }
                
            }

        }while(true);

    }
    //--------------------------LIST CONTACTS------------------------
    public static void listContacts(){
        Scanner input = new Scanner(System.in);
        System.out.println("=======================SORT CONTACTS==========================");
        System.out.println("\n[01] Sorting by name");
        System.out.println("\n[02] Sorting by salary");
        System.out.println("\n[03] Sorting by birthday");
        System.out.print("\nEnter option to continue : ");
        int option=input.nextInt();
        switch(option){
            case 1 : sortByName();break;
            case 2 : sortBySalary();break;
            case 3 : sortByBirthday();break;
            default : System.out.println("\n\tInvalid option...");
        }

    }
    //--------------------------SORT BY NAME-------------------------
    public static void sortByName(){
        Scanner input=new Scanner(System.in);
        do{
            System.out.println("============LIST CONTACT BY NAME============\n");
            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.println("|  Contact Id  |     Name     |   Phone Number   |    Company    |    Salary    |      Birthday     |");
            System.out.println("+---------------------------------------------------------------------------------------------------+");

            sortingByName();

            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.print("\nDo you want to go homepage : ");
            char ch=input.next().charAt(0);
            if(ch=='Y'||ch=='y'){
                clearConsole();
                homepage();
            }else if(ch=='N'|| ch=='n'){
                clearConsole();
                listContacts();
            }


        }while(true);
    }
    //--------------------------SORT BY SALARY-------------------------
    public static void sortBySalary(){
        Scanner input=new Scanner(System.in);
        do{
            System.out.println("============LIST CONTACT BY NAME============\n");
            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.println("|  Contact Id  |     Name     |   Phone Number   |    Company    |    Salary    |      Birthday     |");
            System.out.println("+---------------------------------------------------------------------------------------------------+");

            sortingBySalary();

            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.print("\nDo you want to go homepage : ");
            char ch=input.next().charAt(0);
            if(ch=='Y'||ch=='y'){
                clearConsole();
                homepage();
            }else if(ch=='N'|| ch=='n'){
                clearConsole();
                listContacts();
            }


        }while(true);
    }
    //--------------------------SORT BY BIRTHDAY-------------------------
    public static void sortByBirthday(){
        Scanner input=new Scanner(System.in);
        do{
            System.out.println("============LIST CONTACT BY NAME============\n");
            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.println("|  Contact Id  |     Name     |   Phone Number   |    Company    |    Salary    |      Birthday     |");
            System.out.println("+---------------------------------------------------------------------------------------------------+");

            sortingByBirthday();

            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.print("\nDo you want to go homepage : ");
            char ch=input.next().charAt(0);
            if(ch=='Y'||ch=='y'){
                clearConsole();
                homepage();
            }else if(ch=='N'|| ch=='n'){
                clearConsole();
                listContacts();
            }


        }while(true);
    }
    //-------------------------NAME SORT---------------------------
    public static void sortingByName(){
        Contacts[] tempContacts=new Contacts[contactsArray.length];
        // String[] tempIdArray=new String[idArray.length];
        // String[] tempNameArray=new String[nameArray.length];
        // String[] tempPhoneNumberArray=new String[phoneNumberArray.length];
        // String[] tempCompanyNameArray=new String[companyNameArray.length];
        // double[] tempSalaryArray=new double[salaryArray.length];
        // String[] tempBirthdayArray=new String[birthdayArray.length];

        for(int i=0; i<contactsArray.length; i++){
            tempContacts[i]=contactsArray[i];
            // tempIdArray[i]=idArray[i];
            // tempNameArray[i]=nameArray[i];
            // tempPhoneNumberArray[i]=phoneNumberArray[i];
            // tempCompanyNameArray[i]=companyNameArray[i];
            // tempSalaryArray[i]=salaryArray[i];
            // tempBirthdayArray[i]=birthdayArray[i];
        }
        for(int j=1; j<contactsArray.length; j++){
            for(int i=0; i<contactsArray.length-j; i++){
                if(tempContacts[i].getContactName().compareTo(tempContacts[i+1].getContactName())>0){
                    Contacts tempC=tempContacts[i];
                    tempContacts[i]=tempContacts[i+1];
                    tempContacts[i+1]=tempC;

                    // String tempName=tempNameArray[i];
                    // tempNameArray[i]=tempNameArray[i+1];
                    // tempNameArray[i+1]=tempName;

                    // String tempId=tempIdArray[i];
                    // tempIdArray[i]=tempIdArray[i+1];
                    // tempIdArray[i+1]=tempId;

                    // String tempPhoneNumber=tempPhoneNumberArray[i];
                    // tempPhoneNumberArray[i]=tempPhoneNumberArray[i+1];
                    // tempPhoneNumberArray[i+1]=tempPhoneNumber;

                    // String tempCompanyName=tempCompanyNameArray[i];
                    // tempCompanyNameArray[i]=tempCompanyNameArray[i+1];
                    // tempCompanyNameArray[i+1]=tempCompanyName;

                    // double tempSalary=tempSalaryArray[i];
                    // tempSalaryArray[i]=tempSalaryArray[i+1];
                    // tempSalaryArray[i+1]=tempSalary;

                    // String tempBirthday=tempBirthdayArray[i];
                    // tempBirthdayArray[i]=tempBirthdayArray[i+1];
                    // tempBirthdayArray[i+1]=tempBirthday;

                }
            }
        }

        for(int i=0; i<contactsArray.length; i++) {
            System.out.printf("| %-12s| %-12s| %-12s| %-12s| %-12.2f| %-12s|\n",tempContacts[i].getContactId(),tempContacts[i].getContactName(),tempContacts[i].getContactPhone(),tempContacts[i].getContactCompany(),tempContacts[i].getContactSalary(),tempContacts[i].getContactBday());
        }

    }
    //-------------------------SALARY SORT---------------------------
    public static void sortingBySalary(){
        Contacts[] tempContacts=new Contacts[contactsArray.length];
        // String[] tempIdArray=new String[idArray.length];
        // String[] tempNameArray=new String[nameArray.length];
        // String[] tempPhoneNumberArray=new String[phoneNumberArray.length];
        // String[] tempCompanyNameArray=new String[companyNameArray.length];
        // double[] tempSalaryArray=new double[salaryArray.length];
        // String[] tempBirthdayArray=new String[birthdayArray.length];

        for(int i=0; i<contactsArray.length; i++){
            tempContacts[i]=contactsArray[i];
            // tempIdArray[i]=idArray[i];
            // tempNameArray[i]=nameArray[i];
            // tempPhoneNumberArray[i]=phoneNumberArray[i];
            // tempCompanyNameArray[i]=companyNameArray[i];
            // tempSalaryArray[i]=salaryArray[i];
            // tempBirthdayArray[i]=birthdayArray[i];
        }
        for(int j=1; j<contactsArray.length; j++){
            for(int i=0; i<contactsArray.length-j; i++){
                if(tempContacts[i].getContactSalary()>tempContacts[i+1].getContactSalary()){
                    Contacts tempC=tempContacts[i];
                    tempContacts[i]=tempContacts[i+1];
                    tempContacts[i+1]=tempC;
                    // String tempName=tempNameArray[i];
                    // tempNameArray[i]=tempNameArray[i+1];
                    // tempNameArray[i+1]=tempName;

                    // String tempId=tempIdArray[i];
                    // tempIdArray[i]=tempIdArray[i+1];
                    // tempIdArray[i+1]=tempId;

                    // String tempPhoneNumber=tempPhoneNumberArray[i];
                    // tempPhoneNumberArray[i]=tempPhoneNumberArray[i+1];
                    // tempPhoneNumberArray[i+1]=tempPhoneNumber;

                    // String tempCompanyName=tempCompanyNameArray[i];
                    // tempCompanyNameArray[i]=tempCompanyNameArray[i+1];
                    // tempCompanyNameArray[i+1]=tempCompanyName;

                    // double tempSalary=tempSalaryArray[i];
                    // tempSalaryArray[i]=tempSalaryArray[i+1];
                    // tempSalaryArray[i+1]=tempSalary;

                    // String tempBirthday=tempBirthdayArray[i];
                    // tempBirthdayArray[i]=tempBirthdayArray[i+1];
                    // tempBirthdayArray[i+1]=tempBirthday;

                }
            }
        }

        for(int i=0; i<contactsArray.length; i++) {
            System.out.printf("| %-12s| %-12s| %-12s| %-12s| %-12.2f| %-12s|\n",tempContacts[i].getContactId(),tempContacts[i].getContactName(),tempContacts[i].getContactPhone(),tempContacts[i].getContactCompany(),tempContacts[i].getContactSalary(),tempContacts[i].getContactBday());
        }

    }

    //-------------------------BIRTHDAY SORT---------------------------
    public static void sortingByBirthday(){
        Contacts[] tempContacts=new Contacts[contactsArray.length];
        // String[] tempIdArray=new String[idArray.length];
        // String[] tempNameArray=new String[nameArray.length];
        // String[] tempPhoneNumberArray=new String[phoneNumberArray.length];
        // String[] tempCompanyNameArray=new String[companyNameArray.length];
        // double[] tempSalaryArray=new double[salaryArray.length];
        // String[] tempBirthdayArray=new String[birthdayArray.length];

        for(int i=0; i<contactsArray.length; i++){
            tempContacts[i]=contactsArray[i];
            // tempIdArray[i]=idArray[i];
            // tempNameArray[i]=nameArray[i];
            // tempPhoneNumberArray[i]=phoneNumberArray[i];
            // tempCompanyNameArray[i]=companyNameArray[i];
            // tempSalaryArray[i]=salaryArray[i];
            // tempBirthdayArray[i]=birthdayArray[i];
        }
        for(int j=1; j<contactsArray.length; j++){
            for(int i=0; i<contactsArray.length-j; i++){
                if(tempContacts[i].getContactBday().compareTo(tempContacts[i+1].getContactBday())>0){
                    Contacts tempC=tempContacts[i];
                    tempContacts[i]=tempContacts[i+1];
                    tempContacts[i+1]=tempC;
                    // String tempName=tempNameArray[i];
                    // tempNameArray[i]=tempNameArray[i+1];
                    // tempNameArray[i+1]=tempName;

                    // String tempId=tempIdArray[i];
                    // tempIdArray[i]=tempIdArray[i+1];
                    // tempIdArray[i+1]=tempId;

                    // String tempPhoneNumber=tempPhoneNumberArray[i];
                    // tempPhoneNumberArray[i]=tempPhoneNumberArray[i+1];
                    // tempPhoneNumberArray[i+1]=tempPhoneNumber;

                    // String tempCompanyName=tempCompanyNameArray[i];
                    // tempCompanyNameArray[i]=tempCompanyNameArray[i+1];
                    // tempCompanyNameArray[i+1]=tempCompanyName;

                    // double tempSalary=tempSalaryArray[i];
                    // tempSalaryArray[i]=tempSalaryArray[i+1];
                    // tempSalaryArray[i+1]=tempSalary;

                    // String tempBirthday=tempBirthdayArray[i];
                    // tempBirthdayArray[i]=tempBirthdayArray[i+1];
                    // tempBirthdayArray[i+1]=tempBirthday;

                }
            }
        }

        for(int i=0; i<contactsArray.length; i++) {
            System.out.printf("| %-12s| %-12s| %-12s| %-12s| %-12.2f| %-12s|\n",tempContacts[i].getContactId(),tempContacts[i].getContactName(),tempContacts[i].getContactPhone(),tempContacts[i].getContactCompany(),tempContacts[i].getContactSalary(),tempContacts[i].getContactBday());
        }

    }
    //-----------------------EXIT--------------------------
    public static void exit(){
        System.exit(0);
    }

}
