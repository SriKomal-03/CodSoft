import java.util.*;

class Course {
    public String courseCode;
    public String title;
    public String description;
    public int capacity;
    public int registeredStudents;

    public Course(String courseCode, String title, String description, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.registeredStudents = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRegisteredStudents() {
        return registeredStudents;
    }

    public boolean registerStudent() {
        if (registeredStudents < capacity) {
            registeredStudents++;
            return true;
        }
        return false;
    }

    public boolean removeStudent() {
        if (registeredStudents > 0) {
            registeredStudents--;
            return true;
        }
        return false;
    }

    public boolean isAvailable() {
        return registeredStudents < capacity;
    }

    public void displayCourseDetails() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Capacity: " + capacity);
        System.out.println("Registered Students: " + registeredStudents);
        System.out.println("Available Slots: " + (capacity - registeredStudents));
    }
}

class Student {
    private String studentId;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerForCourse(Course course) {
        if (course.registerStudent()) {
            registeredCourses.add(course);
            System.out.println("Successfully registered for " + course.getTitle());
        } else {
            System.out.println("Course " + course.getTitle() + " is full.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            if (course.removeStudent()) {
                registeredCourses.remove(course);
                System.out.println("Successfully dropped " + course.getTitle());
            } else {
                System.out.println("Failed to drop the course.");
            }
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    public void displayRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            System.out.println("Registered Courses for " + name + ":");
            for (Course course : registeredCourses) {
                System.out.println(course.getTitle());
            }
        }
    }
}

class CourseRegistrationSystem {
    private Map<String, Course> courseCatalog;
    private Map<String, Student> studentDatabase;

    public CourseRegistrationSystem() {
        courseCatalog = new HashMap<>();
        studentDatabase = new HashMap<>();
    }

    public void addCourse(String courseCode, String title, String description, int capacity) {
        Course course = new Course(courseCode, title, description, capacity);
        courseCatalog.put(courseCode, course);
    }

    public void addStudent(String studentId, String name) {
        Student student = new Student(studentId, name);
        studentDatabase.put(studentId, student);
    }

    public Course getCourse(String courseCode) {
        return courseCatalog.get(courseCode);
    }

    public Student getStudent(String studentId) {
        return studentDatabase.get(studentId);
    }

    public void displayAllCourses() {
        System.out.println("Available Courses:");
        for (Course course : courseCatalog.values()) {
            course.displayCourseDetails();
            System.out.println("-----------------------");
        }
    }

    public void registerStudentForCourse(String studentId, String courseCode) {
        Student student = studentDatabase.get(studentId);
        Course course = courseCatalog.get(courseCode);

        if (student != null && course != null) {
            student.registerForCourse(course);
        } else {
            System.out.println("Invalid student or course.");
        }
    }

    public void removeStudentFromCourse(String studentId, String courseCode) {
        Student student = studentDatabase.get(studentId);
        Course course = courseCatalog.get(courseCode);

        if (student != null && course != null) {
            student.dropCourse(course);
        } else {
            System.out.println("Invalid student or course.");
        }
    }
}

public class Task_5{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        // Add courses
        system.addCourse("CS101", "Introduction to Programming", "Learn the basics of programming using Java", 30);
        system.addCourse("CS102", "Data Structures", "Learn about data structures and algorithms", 20);
        system.addCourse("CS103", "Database Systems", "Learn about relational databases and SQL", 25);

        // Add students
        system.addStudent("S001", "John Doe");
        system.addStudent("S002", "Jane Smith");

        // Start registration process
        boolean exit = false;
        while (!exit) {
            System.out.println("\n1. View All Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    system.displayAllCourses();
                    break;

                case 2:
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter course code to register: ");
                    String courseCode = scanner.nextLine();
                    system.registerStudentForCourse(studentId, courseCode);
                    break;

                case 3:
                    System.out.print("Enter student ID: ");
                    String studentIdDrop = scanner.nextLine();
                    System.out.print("Enter course code to drop: ");
                    String courseCodeDrop = scanner.nextLine();
                    system.removeStudentFromCourse(studentIdDrop, courseCodeDrop);
                    break;

                case 4:
                    System.out.print("Enter student ID to view registered courses: ");
                    String studentIdView = scanner.nextLine();
                    Student student = system.getStudent(studentIdView);
                    if (student != null) {
                        student.displayRegisteredCourses();
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 5:
                    exit = true;
                    System.out.println("Exiting the system.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
