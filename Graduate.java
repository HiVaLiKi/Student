import java.util.List;

public class Graduate
{
    public static void graduate(String[] k, List<Student> students)
    {
        if(k.length < 2)
        {
            System.out.println("Too few arguments\ngraduate <fn>");
            return;
        }
        String fn = k[1];
        boolean flag = false;
        Student student = students.stream().filter(i -> i.getFn().equals(fn)).findFirst().orElse(null);//getStudentByFN(fn,students);
        if(student != null)
            flag = student.graduate();
        if(flag)
            System.out.println("Student graduated successfully");
        else
            System.out.println("Error in student graduation");
    }
}
