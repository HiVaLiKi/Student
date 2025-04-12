public class Enrolling implements Command{
    @Override
    public void execute(String[] k, Students students) {
        if (k.length < 3) {
            System.out.println("Too few arguments\nenrolling <fn> <course>");
            return;
        }
        String fn = k[1];
        String newcourse = k[2];
        boolean flag = false;
        Student student = students.getStudentByFN(fn);
        if (student != null)
            flag = student.enrolling(newcourse);
        else
            System.out.println("No such student found by FN");
        if (!flag)
            System.out.println("Error in enrolling");
        else
            System.out.println("Student enrolled");
    }
}
