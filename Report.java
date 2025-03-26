import java.util.List;

public class Report
{
    public static void report(String[] k, List<Student> students)
    {
        if(k.length < 2)
        {
            System.out.println("Too few arguments\nreport <fn>");
            return;
        }
        String fn = k[1];
        Student student = students.stream().filter(i -> i.getFn().equals(fn)).findFirst().orElse(null);
        if(student == null)
            System.out.println("Student not found");
        else
            System.out.println(student.print());
    }
}
