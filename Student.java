import java.util.ArrayList;
import java.util.List;

public class Student
{
    private enum Status
    {
        STUDYING,
        PAUSE,
        GRADUATED
    }
    private String name;
    private String fn;
    private short year;
    private String program;
    private short group;
    private double averageGrade;
    private int numOfGrades;
    private ArrayList< Pair<String, Double>> courseGrade;
    private Status status;

    Student()
    {
        numOfGrades = 0;
        this.courseGrade = new ArrayList<>();
    }
    public boolean enroll(String name, String fn, String program, short group)
    {
        this.name = name;
        this.fn = fn;
        year = 1;
        this.program = program;
        this.group = group;
        status = Status.STUDYING;
        averageGrade = 2.0;
        return pushNewCoursesForYear();
    }
    public String getFn()
    {
        return fn;
    }
    public boolean advance()
    {
        year++;
        boolean flag = status!=Status.PAUSE;//flag = pushNewCoursesForYear();
        if(!flag)
            pushNewCoursesForYear();
        if(flag)
            return true;
        year--;
        return false;
    }
    private boolean pushNewCoursesForYear()
    {
        Program programbas = Programs.getInstance().getProgramByName(program);
        if(programbas == null)
        {
            System.out.println("No such program found");
            return false;
        }
        List <Course> courses = programbas.getCourseByYear(year);
        for(Course course: courses) {
            courseGrade.add(new Pair<>(course.getName(), 2.0));
            numOfGrades++;
        }
        return true;
    }
    public boolean changeProgram(String program)
    {
        boolean checkPastCourses = false;
        Programs programs = Programs.getInstance();
        Program programbas = programs.getProgramByName(program);
        return false;
    }

    public boolean graduate()
    {
        boolean flag = true;
        for(Pair<String, Double> i: courseGrade)
            if(i.getRight() < 3.0)
            {
                flag = false;
                break;
            }
        if(flag)
        {
            status = Status.GRADUATED;
            return true;
        }
        System.out.println("Student didn't finish all exams successfully");
        return false;
    }
    public String print()
    {
        String res = "Student " + fn + " { \n";
        res+= "Name: " + name +
                ", Year: " + year +
                ", Program: " + program +
                ", Group: " + group +
                ", Average grade: " + averageGrade +
                ", Status : " + status + "\n";
        res+="Pair <Course, Grade> {\n";
        for(Pair<String, Double> pair: courseGrade)
        {
            res += "\t <" + pair.getLeft() + " , " + pair.getRight() + ">,\n";
        }
        res += "}\n}";
        return res;
    }
    public boolean enrolling(String newCourse)
    {
        if(status == Status.PAUSE)
        {
            System.out.println("Can't enroll when interrupted");
            return false;
        }
        boolean flag = false;
        for(Pair<String, Double> i: courseGrade)
            if(i.getLeft().equals(newCourse))
            {
                System.out.println("Course already loaded");
                return false;
            }
        //Programs programs = Programs.getInstance();
        Program program = Programs.getInstance().getProgramByName(this.program);
        List<Course> list = program.getCourseByYear(year);
        for(Course i: list)
            if(i.getName().equals(newCourse))
            {
                flag = true;
                courseGrade.add(new Pair<>(i.getName(), 2.00));
                averageGrade = (averageGrade*numOfGrades + 2.00)/(numOfGrades +1);
                numOfGrades++;
                break;
            }
        if(!flag)
        {
            System.out.println("Course not existing");
            return false;
        }
        return true;
    }

    public boolean addGrade(String course, Double grade)
    {
        boolean flag = false;
        for(Pair<String, Double> i: courseGrade)
            if(i.getLeft().equals(course))
            {
                averageGrade = (averageGrade*numOfGrades+grade-i.getRight()) / numOfGrades;
                i.setRight(grade);
                flag = true;
                break;
            }
        if(!flag)
            System.out.println("Course not enrolled for student");
        return flag;
    }

    public void interrupt()
    {
        status = Status.PAUSE;
    }
    public void resume()
    {
        status = Status.STUDYING;
    }
    public String getProgram()
    {
        return program;
    }
    public short getYear()
    {
        return year;
    }
    public boolean isInCourse(String course)
    {
        ArrayList< Pair<String, Double>> res = courseGrade;
        return res.stream().anyMatch(i -> i.getLeft().equals(course));
        //return res.removeIf(i -> !i.getLeft().getName().equals(course));
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setFn(String fn)
    {
        this.fn=fn;
    }
    public void setYear(short year)
    {
        this.year=year;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setGroup(short group) {
        this.group = group;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public void setNumOfGrades(int numOfGrades) {
        this.numOfGrades = numOfGrades;
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }
    public void pushCourseGrade(Pair<String, Double> pair)
    {
        courseGrade.add(pair);
    }

    @Override
    public String toString()
    {
        String res ="";
        res += "Student,"+name+","+fn+","+year+","+program+","+group+","+averageGrade+","+numOfGrades+","+status+",";
        for(Pair<String, Double> i: courseGrade)
            res+=i.getLeft()+"="+i.getRight()+",";
        return res;
    }
}