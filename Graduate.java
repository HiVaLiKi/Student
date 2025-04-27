public class Graduate implements Command{
    /**
     * Check for valid unput. Filters a student by FN. calls @see Student.graduate()
     * 1st word - command
     * 2nd word - fn of student
     * @param k Split version of original command @see Controller.open()
     * @param students reference to the Class Handler for Students
     * @return Successful message
     * @throws Exception General. In addition to passing Exceptions it handles cases where there are fewer than expected arguments or not found student
     */
    @Override
    public String execute(String[] k, Students students) throws Exception{
        if (k.length < 2)
            throw new Exception("Too few arguments\ngraduate <fn>");
        String fn = k[1];
        Student student = students.getStudentByFN(fn);
        if (student == null)
            throw new Exception("Student not found");
        student.graduate();
        return "Student graduated!";
    }
}
