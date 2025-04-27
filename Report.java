public class Report implements Command{
    /**
     * Returns data for a student
     * 1st word - command
     * 2nd word - fn of student
     * @see Print()
     * @param k Split version of original command @see Controller.open()
     * @param students reference to the Class Handler for Students
     * @return print statement for student
     * @throws Exception general
     */
    @Override
    public String execute(String[] k, Students students) throws Exception{
        if (k.length < 2)
            throw new Exception("Too few arguments\nreport <fn>");
        String fn = k[1];
        Student student = students.getStudentByFN(fn);
        if (student == null)
            throw new Exception("Student not found");
        return student.print();
    }
}
