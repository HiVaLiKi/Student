import java.util.ArrayList;
import java.util.List;

/**
 * Individual Program handler. it has a list of courses for the said program
 */
public class Program {
    private String name;
    private final List<Course> courses;

    protected Program() {
        name = "";
        courses = new ArrayList<>();
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    /**
     * Gets a list of courses for a selected year
     *
     * @param year Which courses to filter
     * @return list of courses for the said year
     */
    protected List<Course> getCourseByYear(short year) {
        List<Course> res = new ArrayList<>();
        for (Course i : courses)
            if (i.getYear() == year && i.isRequired())
                res.add(i);
        return res;
    }

    protected void pushCourse(Course course) {
        courses.add(course);
    }

    /**
     * @return a list of strings each containing the data for a course @see Course.toString() in a csv format
     */
    protected List<String> getListToString() {
        List<String> res = new ArrayList<>();
        for (Course i : courses)
            res.add("Course," + name + "," + i.toString());
        return res;
    }
}
