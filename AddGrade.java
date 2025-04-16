public class AddGrade implements Command{
    @Override
    /**
     * Processes AddGrade instructions. Checks if input fields are valid, finds certain Student by FN and calls @see Student.addGrade() for futher handling
     */
    public String execute(String[] k, Students students) throws Exception{
        if (k.length < 4)
            throw new Exception("Too few arguments\naddgrade <fn> <course> <grade>");
        String fn = k[1];
        String course = k[2];
        double grade;
        try {
            grade = Double.parseDouble(k[3]);
        } catch (NumberFormatException e) {
            throw new Exception("Invalid input for grade, should be a number");
        }
        Student student = students.getStudentByFN(fn);
        if (student == null)
            throw new Exception("Student not found");
        student.addGrade(course, grade);
        return "Graded added!";
    }
}
