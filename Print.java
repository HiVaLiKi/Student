public class Print implements Command {
    /**
     *
     * @param k Split version of original command @see Controller.open()
     * @param students reference to the Class Handler for Students
     * @return Student's data from @see Student.print()
     * @throws Exception general
     */
    @Override
    public String execute(String[] k, Students students) throws Exception{
        if (k.length < 2)
            throw new Exception("Too few arguments\nprint <fn>");
        String fn = k[1];
        Student student = students.getStudentByFN(fn);
        if (student == null)
            throw new Exception("No such student found by FN");
        return student.print();
    }
}
