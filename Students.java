import java.util.*;

/**
 * Students manager
 */
public class Students {
    private final HashMap<String, Student> students;

    protected Students() {
        students = new HashMap<>();
    }

    /**
     * Looks up a Student by its FN
     *
     * @param fn Search criteria
     * @return Reference to the student
     */
    protected Student getStudentByFN(String fn) {
        if (students.containsKey(fn))
            return students.get(fn);
        return null;
    }

    /**
     * Tries to enroll a student in the system
     *
     * @param student reference to the student to be enrolled
     * @throws Exception General
     */
    protected void add(Student student) throws Exception {
        if (student != null)
            if (students.containsKey(student.getFn()))
                throw new Exception("Student with FN " + student.getFn() + " already exists");
            else
                students.put(student.getFn(), student);
    }

    /**
     * @return a copy to the list of students
     */
    protected ArrayList<Student> getSet() {
        return new ArrayList<>(students.values().stream().toList());
    }
}