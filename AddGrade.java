public class AddGrade implements Command{
    /**
     * Processes AddGrade instructions. Checks if input fields are valid, finds certain Student by FN and calls @see Student.addGrade() for futher handling
     * 1st word - command
     * 2nd word - fn of student
     * 3rd word - course name
     * 4th - Grade to be added
     * @param k Split version of original command @see Controller.open()
     * @param students reference to the Class Handler for Students
     * @return Successful message or Exception
     * @throws Exception General. Handles parameter count, Double Conversion and Incorrect student errors. Also passes
     */
    @Override
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
