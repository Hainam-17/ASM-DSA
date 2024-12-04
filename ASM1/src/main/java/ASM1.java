/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.*;

/**
 *
 * @author ASUS
 */
public class ASM1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Student Management Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. View Students List");
            System.out.println("5. Sort Students by Marks");
            System.out.println("6. Search Student by ID");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    editStudent(sc);
                    break;
                case 3:
                    deleteStudent(sc);
                    break;
                case 4:
                    viewStudents();
                    break;
                case 5:
                    sortStudents();
                    break;
                case 6:
                    searchStudent(sc);
                    break;
            }
        } while (choice != 0);
    }

    private static List<Student> students = new ArrayList<>();

    private static void addStudent(Scanner sc) {
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Student Name: ");
        String fullName = sc.nextLine();

        double marks;
        do {
            System.out.print("Enter Marks (0 - 10): ");
            marks = sc.nextDouble();
            if (marks < 0 || marks > 10) {
                System.out.println("Invalid marks. Please enter a value between 0 and 10.");
            }
        } while (marks < 0 || marks > 10);

        students.add(new Student(id, fullName, marks));
        System.out.println("Student added successfully.");
    }

    private static void editStudent(Scanner sc) {
        System.out.print("Enter Student ID to edit: ");
        String id = sc.nextLine();
        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.print("Enter new Name: ");
                String newName = sc.nextLine();

                double newMarks;
                do {
                    System.out.print("Enter new Marks (0 - 10): ");
                    newMarks = sc.nextDouble();
                    if (newMarks < 0 || newMarks > 10) {
                        System.out.println("Invalid marks. Please enter a value between 0 and 10.");
                    }
                } while (newMarks < 0 || newMarks > 10);

                students.set(students.indexOf(student), new Student(id, newName, newMarks));
                System.out.println("Student updated successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private static void deleteStudent(Scanner sc) {
        System.out.print("Enter Student ID to delete: ");
        String id = sc.nextLine();
        students.removeIf(student -> student.getId().equals(id));
        System.out.println("Student deleted successfully.");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private static void sortStudents() {
        mergeSort(students, 0, students.size() - 1);
        System.out.println("Students sorted by marks.");

    }

    private static void searchStudent(Scanner sc) {
        System.out.print("Enter Student ID to search: ");
        String id = sc.nextLine();
        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Merge Sort
    private static void mergeSort(List<Student> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);
            merge(list, left, mid, right);
        }
    }

    private static void merge(List<Student> list, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        List<Student> leftList = new ArrayList<>();
        List<Student> rightList = new ArrayList<>();

        for (int i = 0; i < n1; ++i) leftList.add(list.get(left + i));
        for (int i = 0; i < n2; ++i) rightList.add(list.get(mid + 1 + i));

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftList.get(i).getMarks() <= rightList.get(j).getMarks()) {
                list.set(k, leftList.get(i));
                i++;
            } else {
                list.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            list.set(k, leftList.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            list.set(k, rightList.get(j));
            j++;
            k++;
        }
    }

    // Quick Sort
    private static void quickSort(List<Student> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);

            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private static int partition(List<Student> list, int low, int high) {
        Student pivot = list.get(high); 
        int i = low - 1; 

        for (int j = low; j < high; j++) {
            if (list.get(j).getMarks() <= pivot.getMarks()) {
                i++;           
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return i + 1;
    }
}

class Student {
    private String id;
    private String fullName;
    private double marks;

    public Student(String id, String fullName, double marks) {
        this.id = id;
        this.fullName = fullName;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public double getMarks() {
        return marks;
    }

    public String getRank() {
        if (marks < 5.0) return "Fail";
        if (marks < 6.5) return "Medium";
        if (marks < 7.5) return "Good";
        if (marks < 9.0) return "Very Good";
        return "Excellent";
    }

    public String toString() {
        return "ID: " + id + ", Name: " + fullName + ", Marks: " + marks + ", Rank: " + getRank();
    }
}
