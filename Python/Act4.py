import json

class StudentRecordManager:
    def __init__(self):
        self.records = []
        self.filename = None

    def open_file(self, filename):
        try:
            with open(filename, 'r') as file:
                self.records = json.load(file)
            self.filename = filename
            print("File loaded successfully.")
        except FileNotFoundError:
            print("File not found.")
        except json.JSONDecodeError:
            print("Invalid file format.")

    def save_file(self):
        if self.filename:
            with open(self.filename, 'w') as file:
                json.dump(self.records, file)
            print("File saved successfully.")
        else:
            print("No file opened. Use 'Save As' instead.")

    def save_as_file(self, filename):
        with open(filename, 'w') as file:
            json.dump(self.records, file)
        self.filename = filename
        print("File saved successfully.")

    def show_all_records(self):
        for record in self.records:
            print(record)

    def order_by_lastname(self):
        self.records.sort(key=lambda x: x['fullname'][1])
        print("Records sorted by last name.")

    def order_by_grade(self):
        self.records.sort(key=lambda x: (0.6 * x['class_standing'] + 0.4 * x['major_exam']), reverse=True)
        print("Records sorted by grade.")

    def show_student_record(self, student_id):
        for record in self.records:
            if record['id'] == student_id:
                print(record)
                return
        print("Student not found.")

    def add_record(self, student_id, first_name, last_name, class_standing, major_exam):
        if len(str(student_id)) != 6:
            print("Student ID must be a six-digit number.")
            return
        self.records.append({
            'id': student_id,
            'fullname': (first_name, last_name),
            'class_standing': class_standing,
            'major_exam': major_exam
        })
        print("Record added successfully.")

    def edit_record(self, student_id, first_name=None, last_name=None, class_standing=None, major_exam=None):
        for record in self.records:
            if record['id'] == student_id:
                if first_name:
                    record['fullname'] = (first_name, record['fullname'][1])
                if last_name:
                    record['fullname'] = (record['fullname'][0], last_name)
                if class_standing is not None:
                    record['class_standing'] = class_standing
                if major_exam is not None:
                    record['major_exam'] = major_exam
                print("Record updated successfully.")
                return
        print("Student not found.")

    def delete_record(self, student_id):
        self.records = [record for record in self.records if record['id'] != student_id]
        print("Record deleted successfully.")


def main():
    manager = StudentRecordManager()
    while True:
        print("\nMenu:")
        print("1. Open File")
        print("2. Save File")
        print("3. Save As File")
        print("4. Show All Students Record")
        print("5. Order by Last Name")
        print("6. Order by Grade")
        print("7. Show Student Record")
        print("8. Add Record")
        print("9. Edit Record")
        print("10. Delete Record")
        print("11. Exit")
        choice = input("Enter your choice: ")
        
        if choice == "1":
            filename = input("Enter filename: ")
            manager.open_file(filename)
        elif choice == "2":
            manager.save_file()
        elif choice == "3":
            filename = input("Enter filename: ")
            manager.save_as_file(filename)
        elif choice == "4":
            manager.show_all_records()
        elif choice == "5":
            manager.order_by_lastname()
        elif choice == "6":
            manager.order_by_grade()
        elif choice == "7":
            student_id = int(input("Enter Student ID: "))
            manager.show_student_record(student_id)
        elif choice == "8":
            student_id = int(input("Enter Student ID: "))
            first_name = input("Enter First Name: ")
            last_name = input("Enter Last Name: ")
            class_standing = int(input("Enter Class Standing: "))
            major_exam = int(input("Enter Major Exam: "))
            manager.add_record(student_id, first_name, last_name, class_standing, major_exam)
        elif choice == "9":
            student_id = int(input("Enter Student ID: "))
            first_name = input("Enter New First Name (leave blank to keep current): ") or None
            last_name = input("Enter New Last Name (leave blank to keep current): ") or None
            class_standing = input("Enter New Class Standing (leave blank to keep current): ")
            major_exam = input("Enter New Major Exam (leave blank to keep current): ")
            manager.edit_record(student_id, first_name, last_name, int(class_standing) if class_standing else None, int(major_exam) if major_exam else None)
        elif choice == "10":
            student_id = int(input("Enter Student ID: "))
            manager.delete_record(student_id)
        elif choice == "11":
            print("Exiting program.")
            break
        else:
            print("Invalid choice. Try again.")

if __name__ == "__main__":
    main()
