import java.util.HashMap;

public interface StudentPerson {
    String getFn();

    String getProgram();

    short getYear();

    void setName(String name);

    void setFn(String fn);

    void setYear(short year);

    void setProgram(String program);

    void setGroup(short group);

    void setAverageGrade(double averageGrade);

    void setNumOfGrades(int numOfGrades);

    void setStatus(String status);

    void pushCourseGrade(String course, double grade);

    double getGradeForCourse(String course);

    boolean isInCourse(String course);
}
