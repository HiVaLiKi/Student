public class Print implements Command {
    @Override
    public void execute(String[] k, Students students) {
        if (k.length < 2) {
            System.out.println("Too few arguments\nprint <fn>");
            return;
        }
        String fn = k[1];
        Student student = students.getStudentByFN(fn);
        if (student != null)
            System.out.println(student.print());
        else
            System.out.println("No such student found by FN");
    }
}
