public class Resume implements Command{
    /**
     * Resumes student education
     * 1st word - command
     * 2nd word - fn of student
     * @param k Split version of original command @see Controller.open()
     * @param students reference to the Class Handler for Students
     * @return string if successful
     * @throws Exception general
     */
    @Override
    public String execute(String[] k, Students students) throws Exception{
        if (k.length < 2)
            throw new Exception("Too few arguments\nresume <fn>");
        String fn = k[1];
        Student student = students.getStudentByFN(fn);
        if (student == null)
            throw new Exception("No such student found by FN");
        student.resume();
        return "Student's education resumed";
    }
}
