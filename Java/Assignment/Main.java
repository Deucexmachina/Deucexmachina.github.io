package ClassSample;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int studSize;
        int empSize;
        int choose;

        System.out.print("\033[H\033[2J");  
        System.out.flush();  

        do{
            System.out.print("Are you entering a student [1] or an employee [2]?: ");
            choose = input.nextInt();
        }while((choose!=1)&&(choose!=2));

        // Baryabols
        int id, yob;
        String fullname, dep, course;

        switch(choose){
            case 1:
            System.out.print("How many student records will you enter?: ");
            studSize = input.nextInt();

            Student studs[] = new Student[studSize];

            for(int i = 0; i < studSize; i++){
                System.out.print("Enter ID of student [" + (i+1) + "]: ");
                id = input.nextInt();
                input.nextLine(); // Same as cin.ignore()
                System.out.print("Enter fullname of student [" + (i+1) + "]: ");
                fullname = input.nextLine();
                System.out.print("Enter YOB of student [" + (i+1) + "]: ");
                yob = input.nextInt();
                input.nextLine(); // Same as cin.ignore()

                System.out.print("Enter course of student [" + (i+1) + "]: ");
                course = input.nextLine();

                // Save data using setters
                studs[i] = new Student(); // Instantiate the Student object
                studs[i].id = id;
                studs[i].setFullName(fullname);
                studs[i].setYOB(yob);
                studs[i].setCourse(course);
            }

            // Display all student records
            System.out.println();
            System.out.println("LIST OF STUDENTS");
            System.out.println("---------------------------------------------");
            for(int i = 0; i < studSize; i++){
                System.out.println("Student #: " + (i+1));
                System.out.println("ID: " + studs[i].id);
                System.out.println("Name: " + studs[i].getFullName());
                System.out.println("Age: " + studs[i].getAge());
                System.out.println("Course: " + studs[i].getCourse());
                System.out.println("---------------------------------------------");
            }

            case 2:
            System.out.print("How many employee records will you enter?: ");
            empSize = input.nextInt();

            Employee emps[] = new Employee[empSize];

            for(int i = 0; i < empSize; i++){
                System.out.print("Enter ID of employee [" + (i+1) + "]: ");
                id = input.nextInt();
                input.nextLine(); // Same as cin.ignore()
                System.out.print("Enter fullname of employee [" + (i+1) + "]: ");
                fullname = input.nextLine();
                System.out.print("Enter YOB of employee [" + (i+1) + "]: ");
                yob = input.nextInt();
                input.nextLine(); // Same as cin.ignore()

                System.out.print("Enter department of employee [" + (i+1) + "]: ");
                dep = input.nextLine();

                // Save data using setters
                emps[i] = new Employee(); // Instantiate the Employee object
                emps[i].id = id;
                emps[i].setFullName(fullname);
                emps[i].setYOB(yob);
                emps[i].setDepartment(dep);
            }

            // Display all employee records
            System.out.println();
            System.out.println("LIST OF EMPLOYEES");
            System.out.println("---------------------------------------------");
            for(int i = 0; i < empSize; i++){
                System.out.println("Employee #: " + (i+1));
                System.out.println("ID: " + emps[i].id);
                System.out.println("Name: " + emps[i].getFullName());
                System.out.println("Age: " + emps[i].getAge());
                System.out.println("Department: " + emps[i].getDepartment());
                System.out.println("---------------------------------------------");
            }
        }
    }
}
