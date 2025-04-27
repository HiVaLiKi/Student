import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Protocol implements Command{
    /**
     * Processes outputing for Student. Filters by Course and sorts by Program then by Year and finally by FN
     * 1st word - command
     * 2nd word - name of course to select from
     * @param k Split version of original command @see Controller.open()
     * @param students reference to the Class Handler for Students
     * @return result for output. Sorted and filtered list of students in course from input
     * @throws Exception
     */
    @Override
    public String execute(String[] k, Students students) throws Exception{
        if (k.length < 2)
            throw new Exception("Too few arguments\nprotocol <course>");
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

        return res;
    }
}
