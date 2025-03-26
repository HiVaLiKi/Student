import java.util.List;

public class Interrupt
{
    public static void interrupt(String[] k, List<Student> students)
    {
        if(k.length < 2)
        {
            System.out.println("Too few arguments\ninterrupt <fn>");
            return;
        }
        String fn = k[1];
        Student student = students.stream().filter(i -> i.getFn().equals(fn)).findFirst().orElse(null);//getStudentByFN(fn,students);
        if(student != null)
            student.interrupt();
        else
            System.out.println("No such student found by FN");
    }
}
