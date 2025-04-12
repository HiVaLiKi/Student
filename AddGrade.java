import java.util.List;

public class AddGrade {
    public static void addGrade(String[] k, Students students) {
        if (k.length < 4) {
            System.out.println("Too few arguments\naddgrade <fn> <course> <grade>");
            return;
        }
        boolean flag = false;
        String fn = k[1];
        String course = k[2];
        double grade;
        try {
            grade = Double.parseDouble(k[3]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for grade, should be a number");
            return;
        }
        Student student = students.getStudentByFN(fn);
        if (student != null)
            flag = student.addGrade(course, grade);
        else
            System.out.println("Student not found");
        if (!flag)
            System.out.println("Error in grading course for student");
        else
            System.out.println("Course graded successfully");
    }
}
