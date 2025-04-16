public class Advance implements Command{
    @Override
    /**
     * checks for valid input. Finds student and calls @see Student.advance() for further handling
     */
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
