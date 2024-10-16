package PersonnelManagement;

public class GeneralServices extends Employee {
    private String department;
    private String status;  // contractual or job order

    public GeneralServices(int id, String lastname, String firstname, String middlename, String gender, String birthday,
                           String department, String status) {
        super(id, lastname, firstname, middlename, gender, birthday);
        this.department = department;
        this.status = status;
    }

    // Setters
    public void setDepartment(String department) {
        this.department = department;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getters
    public String getDepartment() {
        return department;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public void displayEmployeeDetails() {
        super.displayEmployeeDetails();
        System.out.println("Department: " + department);
        System.out.println("Status: " + status);
    }
}
