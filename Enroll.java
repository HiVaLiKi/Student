public class Enroll implements Command{
    @Override
    /**
     * Check for valid unput. Calls @see Student.enroll() and adds said student to the StudentManager
     */
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
