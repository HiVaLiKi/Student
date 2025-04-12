public class Interrupt implements Command{
    @Override
    public void execute(String[] k, Students students) {
        if (k.length < 2) {
            System.out.println("Too few arguments\ninterrupt <fn>");
            return;
        }
        String fn = k[1];
        Student student = students.getStudentByFN(fn);
        if (student != null)
            student.interrupt();
        else
            System.out.println("No such student found by FN");
    }
}
