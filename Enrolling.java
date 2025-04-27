public class Enrolling implements Command{
    /**
     * Check for valid unput. Filters a student by fn and calls @see Student.enrolling()
     * 1st word - command
     * 2nd word - fn of student
     * 3rd word - Name of course to be enrolled into
     * @param k Split version of original command @see Controller.open()
     * @param students reference to the Class Handler for Students
     * @return Successful message
     * @throws Exception General
     */
    @Override
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
