public class Report implements Command{
    @Override
    public void execute(String[] k, Students students) {
        if (k.length < 2) {
            System.out.println("Too few arguments\nreport <fn>");
            return;
        }
        String fn = k[1];
        Student student = students.getStudentByFN(fn);
        if (student == null)
            System.out.println("Student not found");
        else
            System.out.println(student.print());
    }
}
