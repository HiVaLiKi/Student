import java.util.ArrayList;
import java.util.List;

public class Program
{
    private String name;
    List<Course> courses;
    Program()
    {
        name="";
        courses = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setCourses(List<Course> courses)
    {
        this.courses = courses;
    }
    public List<Course> getCourseByYear(short year)
    {
        List<Course> res = new ArrayList<>();
        for(Course i : courses)
            if(i.getYear() == year && i.isRequired())
                res.add(i);
        return res;
    }
    public void pushCourse(Course course)
    {
        courses.add(course);
    }
    public List<String> getListToString()
    {
        List<String> res = new ArrayList<>();
        for(Course i: courses)
        {
            res.add("Course,"+name+","+i.toString());
        }
        return res;
    }
}
