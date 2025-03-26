import java.util.List;

public class Enrolling
{
    public static void enrolling(String[] k, List<Student> students)
    {
        if(k.length < 3)
        {
            System.out.println("Too few arguments\nenrolling <fn> <course>");
            return;
        }
        String fn = k[1];
        String newcourse = k[2];
        boolean flag = false;
        Student student = students.stream().filter(i -> i.getFn().equals(fn)).findFirst().orElse(null);//getStudentByFN(fn,students);
        if(student != null)
            flag = student.enrolling(newcourse);
        else
            System.out.println("No such student found by FN");
        if(!flag)
            System.out.println("Error in enrolling");
        else
            System.out.println("Student enrolled");
    }
}
