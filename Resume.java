import java.util.List;

public class Resume
{
    public static void resume(String[] k, List<Student> students)
    {
        if(k.length < 2)
        {
            System.out.println("Too few arguments\nresume <fn>");
            return;
        }
        String fn = k[1];
        Student student = students.stream().filter(i -> i.getFn().equals(fn)).findFirst().orElse(null);//getStudentByFN(fn,students);
        if(student != null)
            student.resume();
        else
            System.out.println("No such student found by FN");
    }
}
