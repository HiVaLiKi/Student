public class Advance implements Command{
    /**
     * checks for valid input. Finds student and calls @see Student.advance() for further handling
     * 1st word - command
     * 2nd word - fn of student
     * @param k Split version of original command @see Controller.open()
     * @param students reference to the Class Handler for Students
     * @return Successfull message
     * @throws Exception General. Handles parameter count and student not found errors. Passes also
     */
    @Override
    public String execute(String[] k, Students students) throws Exception{
        if (k.length < 2)
            throw new Exception("Too few arguments\nadvance <fn>");
        String fn = k[1];
        Student student = students.getStudentByFN(fn);
        if (student == null)
            throw new Exception("Student not found");
        student.advance();
        return "Student advanced!";
    }
}
