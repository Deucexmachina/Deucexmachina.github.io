package PersonnelManagement;

public class Regulars extends Employee {
    private String role;  // managerial or rank and file
    private String department;
    private String status;  // regular, contractual

    public Regulars(int id, String lastname, String firstname, String middlename, String gender, String birthday,
                    String role, String department, String status) {
        super(id, lastname, firstname, middlename, gender, birthday);
        this.role = role;
        this.department = department;
        this.status = status;
    }

    // Setters
    public void setRole(String role) {
        this.role = role;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getters
    public String getRole() {
        return role;
    }

    public String getDepartment() {
        return department;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public void displayEmployeeDetails() {
        super.displayEmployeeDetails();
        System.out.println("Role: " + role);
        System.out.println("Department: " + department);
        System.out.println("Status: " + status);
    }
}
