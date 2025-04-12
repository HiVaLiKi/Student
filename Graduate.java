public class Graduate implements Command{
    @Override
    public void execute(String[] k, Students students) {
        if (k.length < 2) {
            System.out.println("Too few arguments\ngraduate <fn>");
            return;
        }
        String fn = k[1];
        boolean flag = false;
        Student student = students.getStudentByFN(fn);
        if (student != null)
            flag = student.graduate();
        if (flag)
            System.out.println("Student graduated successfully");
        else
            System.out.println("Error in student graduation");
    }
}
