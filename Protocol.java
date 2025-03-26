import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Protocol
{
    public static void protocol(String[] k, List<Student> students)
    {
        if(k.length < 2)
        {
            System.out.println("Too few arguments\nprotocol <course>");
            return;
        }
        String course = k[1];
        List<Student> filteredStudents = new ArrayList<>(students);
        filteredStudents.removeIf(i -> !i.isInCourse(course));
        filteredStudents.sort( (a,b) -> a.getFn().compareTo(b.getFn()));
        List<String> programs = new ArrayList<>();
        short maxYear = 0;
        for(Student i: filteredStudents)
        {
            programs.add(i.getProgram());
            if(maxYear < i.getYear())
                maxYear = i.getYear();
        }
        programs = programs.stream().distinct().collect(Collectors.toList());
        for(String i: programs)
        {
            System.out.println(i + ":");
            for(short year=1;year<=maxYear; year++)
            {
                System.out.println("    Year: "+year);
                for(Student student: filteredStudents)
                {
                    if(student.getYear()==year && student.getProgram().equals(i))
                        System.out.println("        FN: "+student.getFn()+" Grade: "+student.getGradeForCourse(course));
                }
            }
        }

        //for(Student i: filteredStudents)
        //    System.out.println("FN: "+i.getFn()+" Grade: "+i.getGradeForCourse(course));
    }
}
