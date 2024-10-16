package ClassSample;

public class Person {
    // Attributes
    public int id;
    private String fullname;
    private int yob; // Write-only attribute
    private int age; // Read-only attribute

    // Constructor
    public Person(){
        this.id = 0;
        this.fullname = "";
        this.age = 0;
    }

    // Setters (Mutators)
    public void setFullName(String fullname){
        this.fullname = fullname;
    }
    public void setYOB(int yob){
        this.yob = yob;
    }

    // Getters (Accessors)
    public String getFullName(){
        return this.fullname.toUpperCase();
    }
    public int getAge(){
        this.age = 2024 - this.yob;
        return this.age;
    }
}
