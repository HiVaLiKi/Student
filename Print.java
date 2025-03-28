import java.util.List;

public class Print
{
    public static void print(String[] k, List<Student> students)
    {
        if(k.length < 2)
        {
            System.out.println("Too few arguments\nprint <fn>");
            return;
        }
        String fn = k[1];
        Student student = students.stream().filter(i -> i.getFn().equals(fn)).findFirst().orElse(null);//getStudentByFN(fn,students);
        if(student != null)
            System.out.println(student.print());
        else
            System.out.println("No such student found by FN");
    }
}
