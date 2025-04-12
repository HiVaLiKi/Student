import java.util.HashMap;

public class StudentInfo implements StudentPerson {
    protected enum Status {
        STUDYING,
        PAUSE,
        GRADUATED
    }

    protected String name;
    protected String fn;
    protected short year;
    protected String program;
    protected short group;
    protected double averageGrade;
    protected int numOfGrades;
    protected HashMap<String, Double> courseGrade;
    protected Status status;

    StudentInfo() {
        numOfGrades = 0;
        this.courseGrade = new HashMap<>();
    }

    @Override
    public String getFn() {
        return fn;
    }

    @Override
    public String getProgram() {
        return program;
    }

    @Override
    public short getYear() {
        return year;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setFn(String fn) {
        this.fn = fn;
    }

    @Override
    public void setYear(short year) {
        this.year = year;
    }

    @Override
    public void setProgram(String program) {
        this.program = program;
    }

    @Override
    public void setGroup(short group) {
        this.group = group;
    }

    @Override
    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    @Override
    public void setNumOfGrades(int numOfGrades) {
        this.numOfGrades = numOfGrades;
    }

    @Override
    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

    @Override
    public void pushCourseGrade(String course, double grade) {
        courseGrade.put(course, grade);
    }

    @Override
    public double getGradeForCourse(String course) {
        if (courseGrade.containsKey(course))
            return courseGrade.get(course);
        return -1;
    }

    public boolean isInCourse(String course) {
        return courseGrade.containsKey(course);
    }
}
