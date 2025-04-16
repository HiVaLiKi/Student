import java.util.*;

/**
 * Students manager
 */
public class Students
{
    private final HashMap<String, Student> students;
    protected Students()
    {
        students = new HashMap<>();
    }
    protected Student getStudentByFN(String fn)
    {
        if(students.containsKey(fn))
            return students.get(fn);
        return null;
    }
    protected void add(Student student) throws Exception
    {
        if(student!=null)
            if(students.containsKey(student.getFn()))
                throw new Exception("Student with FN "+student.getFn()+" already exists");
            else
                students.put(student.getFn(), student);
    }
    protected ArrayList<Student> getSet()
    {
        return new ArrayList<>( students.values().stream().toList() );
    }
}