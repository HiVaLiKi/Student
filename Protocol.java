import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Protocol implements Command{
    @Override
    public void execute(String[] k, Students students) {
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
        String res = "";
        for (Student i : filteredStudents) {
            if (!i.getProgram().equals(program)) {
                program = i.getProgram();
                res+="Program " + program + ":\n";
                year = 0;
            }
            if (i.getYear() != year) {
                year = i.getYear();
                res+="    Year " + year + ":\n";
            }
            res+="        FN: " + i.getFn() + " Grade: " + i.getGradeForCourse(course)+"\n";
        }

        System.out.println(res);
    }
}
