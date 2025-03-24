import java.util.ArrayList;
import java.util.List;

public class Programs {
    private static Programs instance;
    private List<Program> programs;

    private Programs() {
        programs = new ArrayList<>();

        //Program pr1 = new Program();
        //pr1.setName("SIT");
        //Course c1 = new Course("Mat", "REQUIRED", 1);
        //Course c2 = new Course("UP", "REQUIRED", 2);
        //List<Course> cs = new ArrayList<>();
        //cs.add(c1);
        //cs.add(c2);
        //pr1.setCourses(cs);
        //programs.add(pr1);
    }

    public static Programs getInstance() {
        if (instance == null) {
            instance = new Programs();
        }
        return instance;
    }

    //public void addProgram(Program program) {
    //    programs.add(program);
    //}

    public Program getProgramByName(String name)
    {
        for(Program i: programs)
        {
            if(i.getName().equals(name))
                return i;
        }
        return null;
    }

    public List<Program> getPrograms() {
        return programs;
    }
    public void pushProgram(Program program)
    {
        programs.add(program);
    }
    public void pushCourseToProgram(Course course, String program)
    {
        for(Program i: programs)
            if(i.getName().equalsIgnoreCase(program))
            {
                i.pushCourse(course);
            }
    }
}
