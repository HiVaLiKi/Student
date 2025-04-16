public class Enrolling implements Command{
    @Override
    /**
     * Check for valid unput. Filters a student by fn and calls @see Student.enrolling()
     */
    public String execute(String[] k, Students students) throws Exception{
        if (k.length < 3)
            throw new Exception("Too few arguments\nenrolling <fn> <course>");
        String fn = k[1];
        String newcourse = k[2];
        Student student = students.getStudentByFN(fn);
        if (student == null)
            throw new Exception("No such student found by FN");
        student.enrolling(newcourse);
        return "Student enrolled in course!";
    }
}
