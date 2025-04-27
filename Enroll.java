public class Enroll implements Command{
    /**
     * Check for valid unput. Calls @see Student.enroll() and adds said student to the StudentManager
     * 1st word - command
     * 2nd word - fn of student
     * 3rd word - program name for enrolling student
     * 4th - which group to enroll into
     * 5th+ - name of student. Take all remaining words of the line
     * @param k Split version of original command @see Controller.open()
     * @param students reference to the Class Handler for Students
     * @return Successful message
     * @throws Exception General
     */
    @Override
    public String execute(String[] k, Students students) throws Exception{
        if (k.length < 5)
            throw new Exception("Too few arguments\nenroll <fn> <program> <group> <name>");
        String fn = k[1];
        String program = k[2];
        short group;
        try {
            group = Short.parseShort(k[3]);
        } catch (NumberFormatException e) {
            throw new Exception("Invalid input for group, should be a number");
        }
        String name = k[4];
        for (int i = 5; i < k.length; i++)
            name += " " + k[i];
        Student student = new Student();
        student.enroll(name, fn, program, group);
        students.add(student);
        return "Student enrolled!";
    }
}
