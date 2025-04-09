import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private HashMap<String, Double> courseGrade;
    private Status status;

    Student()
    {
        numOfGrades = 0;
        this.courseGrade = new HashMap<>();
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
        boolean flag = status==Status.STUDYING;
        if(flag)
            if(pushNewCoursesForYear())
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
            if(courseGrade.containsKey(course.getName()))
            {
                System.out.println("Course "+course.getName() + " already enrolled");
                continue;
            }
            courseGrade.put(course.getName(), 2.0);
            averageGrade=(averageGrade*numOfGrades+2)/(numOfGrades + 1);
            numOfGrades++;
        }
        return true;
    }
    public boolean changeProgram(String value)
    {
        boolean flag = true;
        for(short i=1;i<year;i++)
        {
            Program failsafe = Programs.getInstance().getProgramByName(value);
            if(failsafe == null)
            {
                System.out.println("No such program");
                return false;
            }
            List<Course> list = failsafe.getCourseByYear(i);
            for(Course ii: list)
            {
                if(courseGrade.get(ii.getName()) == null || courseGrade.get(ii.getName()) < 3.00)
                {
                    flag = false;
                    break;
                }
            }
            if(!flag)
                break;
        }
        if(!flag)
        {
            System.out.println("Students hasn't completed all past courses for new Program");
            return false;
        }
        this.program = value;
        return true;
    }

    public boolean changeGroup(String value)
    {
        try {
            this.group = Short.parseShort(value);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for group, should be a number");
            return false;
        }
        return true;
    }

    public boolean changeYear(String value)
    {
        short year;
        boolean flag = true;
        try {
            year = Short.parseShort(value);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for year, should be a number");
            return false;
        }
        if(this.year+1!=year)
        {
            System.out.println("Trying to change year that's not the next one");
            return false;
        }
        short tolerance = 2;
        for(short i=1;i<year;i++)
        {
            Program failsafe = Programs.getInstance().getProgramByName(program);
            if(failsafe == null)
            {
                System.out.println("No such program");
                return false;
            }
            List<Course> list = failsafe.getCourseByYear(i);
            for(Course ii: list)
                if(courseGrade.get(ii.getName()) == null)
                {
                    flag = false;
                    break;
                }
                else if(courseGrade.get(ii.getName()) < 3.00)
                {
                    tolerance--;
                    if(tolerance==0)
                        break;
                }
            if(!flag || tolerance==0)
                break;
        }
        if(tolerance==0||!flag)
        {
            System.out.println("Student need to take more exams");
            return false;
        }
        flag = advance();
        return flag;
    }

    public boolean graduate()
    {
        boolean flag = true;
        for(double i: courseGrade.values())
            if(i<3.0)
            {
                flag=false;
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
        for (Map.Entry<String, Double> entry : courseGrade.entrySet())
        {
            res+="\t <" + entry.getKey() + " , " + entry.getValue() + ">,\n";
        }
        res += "}\n}";
        return res;
    }
    public boolean enrolling(String newCourse)
    {
        if(status != Status.STUDYING)
        {
            System.out.println("Can't enroll when interrupted");
            return false;
        }
        boolean flag = false;
        if(courseGrade.containsKey(newCourse))
        {
            System.out.println("Course already loaded");
            return false;
        }
        Program program = Programs.getInstance().getProgramByName(this.program);
        List<Course> list = program.getCourseByYear(year);
        for(Course i: list)
            if(i.getName().equals(newCourse))
            {
                flag = true;
                courseGrade.put(i.getName(), 2.00);
                averageGrade = (averageGrade*numOfGrades + 2.00)/(numOfGrades +1);
                numOfGrades++;
                break;
            }
        if(!flag)
        {
            System.out.println("Course unavailable");
            return false;
        }
        return true;
    }

    public boolean addGrade(String course, Double grade)
    {
        boolean flag = false;
        if(courseGrade.containsKey(course))
            {
                averageGrade = (averageGrade*numOfGrades+grade-courseGrade.get(course)) / numOfGrades;
                courseGrade.put(course,grade);
                flag = true;
            }
        if(!flag)
            System.out.println("Course not enrolled for student");
        return flag;
    }

    public void interrupt()
    {
        if(status != Status.GRADUATED)
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
        return courseGrade.containsKey(course);
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
    public void pushCourseGrade(String course, double grade)
    {
        courseGrade.put(course, grade);
    }

    public double getGradeForCourse(String course)
    {
        if(courseGrade.containsKey(course))
            return courseGrade.get(course);
        return -1;
    }

    @Override
    public String toString()
    {
        String res ="";
        res += "Student,"+name+","+fn+","+year+","+program+","+group+","+averageGrade+","+numOfGrades+","+status+",";
        for (Map.Entry<String, Double> i : courseGrade.entrySet())
            res+=i.getKey()+"="+i.getValue()+",";
        return res;
    }
}