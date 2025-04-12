public class Advance implements Command{
    @Override
    public void execute(String[] k, Students students) {
        if (k.length < 2) {
            System.out.println("Too few arguments\nadvance <fn>");
            return;
        }
        String fn = k[1];
        boolean flag = false;
        Student student = students.getStudentByFN(fn);
        if (student != null)
            flag = student.advance();
        if (!flag)
            System.out.println("Error in advancing student");
    }
}
