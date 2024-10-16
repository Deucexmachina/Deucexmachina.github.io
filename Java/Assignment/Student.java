package ClassSample;

public class Student extends Person {
    private String course;

    public Student(){
        this.course = "";
    }

    public void setCourse(String course){
        this.course = course;
    }

    public String getCourse(){
        return this.course;
    }
}
