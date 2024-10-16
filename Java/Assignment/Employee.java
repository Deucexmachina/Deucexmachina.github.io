package ClassSample;

public class Employee extends Person {
    private String department;

    public Employee(){
        this.department = "";
    }

    public void setDepartment(String department){
        this.department = department;
    }

    public String getDepartment(){
        return this.department;
    }
}
