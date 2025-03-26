import java.util.List;

public class Advance
{
    public static void advance(String[] k, List<Student> students)
    {
        if(k.length < 2)
        {
            System.out.println("Too few arguments\nadvance <fn>");
            return;
        }
        String fn = k[1];
        boolean flag = false;
        Student student = students.stream().filter(i -> i.getFn().equals(fn)).findFirst().orElse(null);//getStudentByFN(fn,students);
        if(student != null)
            flag = student.advance();
        if(!flag)
            System.out.println("Error in advancing student");
    }
}
