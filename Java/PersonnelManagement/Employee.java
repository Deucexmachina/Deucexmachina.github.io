package PersonnelManagement;

public class Employee {
    public int id;
    private String lastname;
    private String firstname;
    private String middlename;
    private String gender;
    private String birthday;

    public Employee(int id, String lastname, String firstname, String middlename, String gender, String birthday) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.gender = gender;
        this.birthday = birthday;
    }

    // Setters
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    // Getters
    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void displayEmployeeDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + firstname + " " + middlename + " " + lastname);
        System.out.println("Gender: " + gender);
        System.out.println("Birthday: " + birthday);
    }
}
