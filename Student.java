import java.util.List;
import java.util.Map;

/**
 * Methods for processing StudentInfo
 */
public class Student extends StudentInfo {
    protected Student() {
        super();
    }

    /**
     * sets the data for a new student and adds the corresponding courses
     * @param name self-explanatory
     * @param fn self-explanatory
     * @param program self-explanatory
     * @param group self-explanatory
     * @throws Exception general
     */
    protected void enroll(String name, String fn, String program, short group) throws Exception{
        this.name = name;
        this.fn = fn;
        year = 1;
        this.program = program;
        this.group = group;
        status = Status.STUDYING;
        averageGrade = 2.0;
        pushNewCoursesForYear();
    }

    /**
     *
     * @throws Exception general
     */
    protected void advance() throws Exception{
        year++;
        if (status == Status.STUDYING) {
            try {
                pushNewCoursesForYear();
            } catch (Exception e) {
                year --;
                throw new Exception(e);
            }
        }
    }

    /**
     * Takes courses for the program of student by calling @see Program.getCourseByYear() and adds them to the student's data
     * @throws Exception
     */
    private void pushNewCoursesForYear() throws Exception {
        Program programbas = Programs.getInstance().getProgramByName(program);
        if (programbas == null) {
            throw new Exception("No such program found");
        }
        List<Course> courses = programbas.getCourseByYear(year);
        for (Course course : courses) {
            if (courseGrade.containsKey(course.getName()))
                continue;
            courseGrade.put(course.getName(), 2.0);
            averageGrade = (averageGrade * numOfGrades + 2) / (numOfGrades + 1);
            numOfGrades++;
        }
    }

    protected void changeProgram(String value) throws Exception{
        for (short i = 1; i < year; i++) {
            Program failsafe = Programs.getInstance().getProgramByName(value);
            if (failsafe == null) {
                throw new Exception("No such program");
            }
            List<Course> list = failsafe.getCourseByYear(i);
            for (Course ii : list) {
                if (courseGrade.get(ii.getName()) == null || courseGrade.get(ii.getName()) < 3.00) {
                    throw new Exception("Students hasn't completed all past courses for new Program");
                }
            }
        }
        this.program = value;
    }

    protected void changeGroup(String value) throws Exception{
        try {
            this.group = Short.parseShort(value);
        } catch (NumberFormatException e) {
            throw new Exception("Invalid input for group, should be a number");
        }
    }

    protected void changeYear(String value) throws Exception{
        short year;
        try {
            year = Short.parseShort(value);
        } catch (NumberFormatException e) {
            throw new Exception("Invalid input for year, should be a number");
        }
        if (this.year + 1 != year) {
            throw new Exception("Trying to change year that's not the next one");
        }
        short tolerance = 2;
        for (short i = 1; i < year; i++) {
            Program failsafe = Programs.getInstance().getProgramByName(program);
            if (failsafe == null) {
                throw new Exception("No such program");
            }
            List<Course> list = failsafe.getCourseByYear(i);
            for (Course ii : list)
                if (courseGrade.get(ii.getName()) == null) {
                    throw new Exception("Student need to take more exams");
                } else if (courseGrade.get(ii.getName()) < 3.00) {
                    tolerance--;
                    if (tolerance == 0)
                        throw new Exception("Student need to take more exams");
                }
        }
        advance();
    }

    protected void graduate() throws Exception{
        for (double i : courseGrade.values())
            if (i < 3.0) {
                throw new Exception("Student didn't finish all exams successfully");
            }
        status = Status.GRADUATED;
    }

    /**
     *
     * @return beautified data of student
     */
    protected String print() {
        String res = "Student " + fn + " { \n";
        res += "Name: " + name +
                ", Year: " + year +
                ", Program: " + program +
                ", Group: " + group +
                ", Average grade: " + averageGrade +
                ", Status : " + status + "\n";
        res += "Pair <Course, Grade> {\n";
        for (Map.Entry<String, Double> entry : courseGrade.entrySet()) {
            res += "\t <" + entry.getKey() + " , " + entry.getValue() + ">,\n";
        }
        res += "}\n}";
        return res;
    }

    protected void enrolling(String newCourse) throws Exception{
        if (status != Status.STUDYING)
            throw new Exception("Can't enroll when interrupted");
        if (courseGrade.containsKey(newCourse))
            throw new Exception("Course already loaded");
        Program program = Programs.getInstance().getProgramByName(this.program);
        List<Course> list = program.getCourseByYear(year);
        for (Course i : list)
            if (i.getName().equals(newCourse)) {
                courseGrade.put(i.getName(), 2.00);
                averageGrade = (averageGrade * numOfGrades + 2.00) / (numOfGrades + 1);
                numOfGrades++;
                return;
            }
        throw new Exception("Course unavailable");
    }

    protected void addGrade(String course, Double grade) throws Exception{
        if (courseGrade.containsKey(course)) {
            averageGrade = (averageGrade * numOfGrades + grade - courseGrade.get(course)) / numOfGrades;
            courseGrade.put(course, grade);
        }
        else
            throw new Exception("Course not enrolled for student");
    }

    protected void interrupt() {
        if (status != Status.GRADUATED)
            status = Status.PAUSE;
    }

    protected void resume() {
        if (status != Status.GRADUATED)
            status = Status.STUDYING;
    }

    /**
     *
     * @return data of student in csv format
     */
    @Override
    public String toString() {
        String res = "";
        res += "Student," + name + "," + fn + "," + year + "," + program + "," + group + "," + averageGrade + "," + numOfGrades + "," + status + ",";
        for (Map.Entry<String, Double> i : courseGrade.entrySet())
            res += i.getKey() + "=" + i.getValue() + ",";
        return res;
    }
}