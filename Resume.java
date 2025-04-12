public class Resume implements Command{
    public void execute(String[] k, Students students) {
        if (k.length < 2) {
            System.out.println("Too few arguments\nresume <fn>");
            return;
        }
        String fn = k[1];
        Student student = students.getStudentByFN(fn);
        if (student != null)
            student.resume();
        else
            System.out.println("No such student found by FN");
    }
}
