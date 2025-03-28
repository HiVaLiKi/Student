import java.util.List;

public class Enroll
{
    public static void enroll(String[] k, List<Student> students)
    {
            if(k.length < 5)
            {
                System.out.println("Too few arguments\nenroll <fn> <program> <group> <name>");
                return;
            }
            String fn = k[1];
            String program = k[2];
            short group;
            try {
                group = Short.parseShort(k[3]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for group, should be a number");
                return;
            }
            //Short group = Short.parseShort(k[3]);
            String name = k[4];
            for(int i=5; i < k.length;i++)
                name+=" "+k[i];
            Student student = new Student();
            boolean flagEnroll = student.enroll(name, fn, program, group);
            if(flagEnroll)
                students.add(student);
            else
                System.out.println("Error in student enrolling");
    }
}
