import java.util.*;

public class Programs {
    private static Programs instance;
    private HashMap<String, Program> programs;

    private Programs() {
        programs = new HashMap<>();
    }

    public static Programs getInstance() {
        if (instance == null) {
            instance = new Programs();
        }
        return instance;
    }

    public Program getProgramByName(String name)
    {
        if(programs.containsKey(name))
            return programs.get(name);
        return null;
    }

    public List<Program> getPrograms() {
        return new ArrayList<>(programs.values());
    }
    public void pushProgram(Program program)
    {
        programs.put(program.getName(), program);
    }
    public void pushCourseToProgram(Course course, String program)
    {
        if(programs.containsKey(program))
            programs.get(program).pushCourse(course);
    }
}
