import java.util.*;

public class Students
{
    private final HashMap<String, Student> students;
    Students()
    {
        students = new HashMap<>();
    }
    public Student getStudentByFN(String fn)
    {
        if(students.containsKey(fn))
            return students.get(fn);
        return null;
    }
    public void add(Student student)
    {
        if(student!=null)
            if(students.containsKey(student.getFn()))
                System.out.println("Student with FN "+student.getFn()+" already exists");
            else
                students.put(student.getFn(), student);
    }
    public ArrayList<Student> getSet()
    {
        return new ArrayList<>( students.values().stream().toList() );
    }
}
    /*private final TreeSet<Student> students;//Don't ask why not Hashmap, even tho it's bellow

    Students() {
        students = new TreeSet<>(Comparator.comparing(Student::getFn));
    }

    public Student getStudentByFN(String fn) {
        Student dummyStudent = new Student();
        dummyStudent.setFn(fn);
        Student res = students.ceiling(dummyStudent);
        if (res != null)
            if (res.getFn().equals(fn))
                return res;
        return null;
    }

    public void add(Student student) {
        if (student != null)
            if (!students.add(student))
                System.out.println("Student with FN " + student.getFn() + " already exists");
    }

    public ArrayList<Student> getSet() {
        return new ArrayList<Student>(students);
    }
}*/
