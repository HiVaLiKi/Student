import java.util.*;

/**
 * Programs manager
 * Handes operations for adding, extracting info
 * SIngleton pattern because why not
 */
public class Programs {
    private static Programs instance;
    private final HashMap<String, Program> programs;

    private Programs() {
        programs = new HashMap<>();
    }

    /**
     *
     * @return instance of the class so it's the same everywhere
     */
    protected static Programs getInstance() {
        if (instance == null) {
            instance = new Programs();
        }
        return instance;
    }

    /**
     *
     * @param name which program name are we looking for
     * @return reference to the said program
     */
    protected Program getProgramByName(String name) {
        if (programs.containsKey(name))
            return programs.get(name);
        return null;
    }

    protected List<Program> getPrograms() {
        return new ArrayList<>(programs.values());
    }

    protected void pushProgram(Program program) {
        programs.put(program.getName(), program);
    }

    /**
     *
     * @param course course reference to add to a program
     * @param program where to add course
     */
    protected void pushCourseToProgram(Course course, String program) {
        if (programs.containsKey(program))
            programs.get(program).pushCourse(course);
    }
}
