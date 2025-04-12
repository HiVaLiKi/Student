import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Protocol {
    public static void protocol(String[] k, Students students) {
        if (k.length < 2) {
            System.out.println("Too few arguments\nprotocol <course>");
            return;
        }
        String course = k[1];
        List<Student> filteredStudents = new ArrayList<>(students.getSet());
        filteredStudents.removeIf(i -> !i.isInCourse(course));
        filteredStudents.sort(Comparator.comparing(Student::getProgram).thenComparing(Student::getYear).thenComparing(Student::getFn));

        String program = "";
        short year = 0;
        for (Student i : filteredStudents) {
            if (!i.getProgram().equals(program)) {
                program = i.getProgram();
                System.out.println("Program " + program + ":");
                year = 0;
            }
            if (i.getYear() != year) {
                year = i.getYear();
                System.out.println("    Year " + year + ":");
            }
            System.out.println("        FN: " + i.getFn() + " Grade: " + i.getGradeForCourse(course));
        }
    }
}
