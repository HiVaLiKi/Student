public class Graduate implements Command{
    @Override
    /**
     * Check for valid unput. Filters a student by FN. calls @see Student.graduate()
     */
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
