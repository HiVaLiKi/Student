import java.io.*;
import java.util.*;

public class Controller
{
    private final HashMap<String, Command> commands;
    private static List<Student> students;
    public Controller()
    {
        students = new ArrayList<>();
        commands = new HashMap<>();
        commands.put("exit", this::exit);
        commands.put("enroll", this::enroll);
        commands.put("advance", this::advance);
        commands.put("change", this::change);
        commands.put("graduate", this::graduate);
        commands.put("interrupt", this::interrupt);
        commands.put("resume", this::resume);
        commands.put("print", this::print);
        commands.put("printall", this::printall);
        commands.put("enrolling", this::enrolling);
        commands.put("addgrade", this::addgrade);
        commands.put("protocol", this::protocol);
        commands.put("report", this::report);
        commands.put("save", this::save);
        commands.put("load", this::load);
    }
    public void open(String input)
    {
        Command command;
        command = getcommand(input.split(" "));
        if(input.split(" ")[0].equalsIgnoreCase("Save"))
            input = input.replaceFirst(" as", "");
        else if(input.startsWith("Save")&&input.length()>4)
            input+="savefile.csv";
        else if(input.startsWith(("Open")))
            if(input.split(" ").length == 1)
                input+="savefile.csv";
        if(command==null)
        {
            System.out.println("Command not found");
            return;
        }

        command.execute(input.split(" "));
    }
    private Command getcommand(String[] input)
    {
        if(commands.containsKey(input[0]))
            return commands.get(input[0]);
        return null;
    }

    private void exit(String[] k)
    {
        System.out.println("Closing...Goodbye");
        System.exit(0);
    }

    private void enroll(String[] k)
    {
        if(k.length < 5)
        {
            System.out.println("Too few arguments\nenroll <fn> <program> <group> <name>");
            return;
        }
        String fn = k[1];
        String program = k[2];
        short group;
        try {
            group = Short.parseShort(k[3]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for group, should be a number");
            return;
        }
        //Short group = Short.parseShort(k[3]);
        String name = k[4];
        for(int i=5; i < k.length;i++)
            name+=" "+k[i];
        Student student = new Student();
        boolean flagEnroll = student.enroll(name, fn, program, group);
        if(flagEnroll)
            students.add(student);
        else
            System.out.println("Error in student enrolling");
    }

    private void advance(String[] k)
    {
        if(k.length < 2)
        {
            System.out.println("Too few arguments\nadvance <fn>");
            return;
        }
        String fn = k[1];
        boolean flag = false;
        Student student = getStudentByFN(fn);
        if(student != null)
            flag = student.advance();
        if(!flag)
            System.out.println("Error in advancing student");
    }

    private void change(String[] k)
    {
        Student student = null;
        boolean flag = false;
        for(Student i: students)
        {
            if(i.getFn().equals(k[1])) {
                student = i;
                break;
            }
        }
        if(k[2].equals("program"))
        {
            student.changeProgram(k[3]);
        }
    }

    private void graduate(String[] k)
    {
        if(k.length < 2)
        {
            System.out.println("Too few arguments\ngraduate <fn>");
            return;
        }
        String fn = k[1];
        boolean flag = false;
        Student student = getStudentByFN(fn);
        if(student != null)
            flag = student.graduate();
        if(flag)
           System.out.println("Student graduated successfully");
        else
            System.out.println("Error in student graduation");
    }

    private void interrupt(String[] k)
    {
        if(k.length < 2)
        {
            System.out.println("Too few arguments\ninterrupt <fn>");
            return;
        }
        String fn = k[1];
        Student student = getStudentByFN(fn);
        if(student != null)
            student.interrupt();
        else
            System.out.println("No such student found by FN");
    }

    private void resume(String[] k)
    {
        if(k.length < 2)
        {
            System.out.println("Too few arguments\nresume <fn>");
            return;
        }
        String fn = k[1];
        Student student = getStudentByFN(fn);
        if(student != null)
            student.resume();
        else
            System.out.println("No such student found by FN");
    }

    private Student getStudentByFN(String fn)
    {
        for(Student i: students)
            if(fn.equals(i.getFn()))
                return i;
        return null;
    }

    private void print(String[] k)
    {
        if(k.length < 2)
        {
            System.out.println("Too few arguments\nprint <fn>");
            return;
        }
        String fn = k[1];
        Student student = getStudentByFN(fn);
        if(student != null)
            System.out.println(student.print());
        else
            System.out.println("No such student found by FN");
    }

    private void printall(String[] k)
    {
        if(k.length < 3)
        {
            System.out.println("Too few arguments\nprintall <program> <year>");
            return;
        }
        String program = k[1];
        short year;
        try {
            year = Short.parseShort(k[2]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for year, should be a number");
            return;
        }
        for(Student i: students)
            if(i.getProgram().equals(program) && i.getYear() == year)
                System.out.println(i.print());
    }
    private void enrolling(String[] k)
    {
        if(k.length < 3)
        {
            System.out.println("Too few arguments\nenrolling <fn> <course>");
            return;
        }
        String fn = k[1];
        String newcourse = k[2];
        boolean flag = false;
        Student student = getStudentByFN(fn);
        if(student != null)
            flag = student.enrolling(newcourse);
        else
            System.out.println("No such student found by FN");
        if(!flag)
            System.out.println("Error in enrolling");
        else
            System.out.println("Student enrolled");
    }

    private void addgrade(String[] k)
    {
        if(k.length < 4)
        {
            System.out.println("Too few arguments\naddgrade <fn> <course> <grade>");
            return;
        }
        boolean flag = false;
        String fn = k[1];
        String course = k[2];
        Double grade;
        try {
            grade = Double.parseDouble(k[3]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for grade, should be a number");
            return;
        }
        Student student = getStudentByFN(fn);
        if(student != null)
            flag = student.addGrade(course, grade);
        else
            System.out.println("Student not found");
        if(!flag)
            System.out.println("Error in grading course for student");
        else
            System.out.println("Course graded successfully");
    }

    private void protocol(String[] k)
    {
        if(k.length < 2)
        {
            System.out.println("Too few arguments\nprotocol <course>");
            return;
        }
        String course = k[1];
        List <Student> filteredStudents = new ArrayList<>(students);
        filteredStudents.removeIf(i -> !i.isInCourse(course));
        filteredStudents.sort( (a,b) -> a.getFn().compareTo(b.getFn()));
        for(Student i: filteredStudents)
            System.out.println(i.print());
    }

    private void report(String[] k)
    {
        if(k.length < 2)
        {
            System.out.println("Too few arguments\nreport <fn>");
            return;
        }
        String fn = k[1];
        Student student = students.stream().filter(i -> i.getFn().equals(fn)).findFirst().orElse(null);
        if(student == null)
            System.out.println("Student not found");
        else
            System.out.println(student.print());
    }

    private void save(String[] k)
    {
        String filename = k[1];
        try(FileWriter myWriter = new FileWriter(filename))
        {
            List<Program> programs = Programs.getInstance().getPrograms();
            //FileWriter myWriter = new FileWriter("savefile.csv");
            for(Student i: students)
                myWriter.write(i.toString() +"\n");
            for(Program i: programs)
                myWriter.write("Program,"+i.getName()+"\n");
            for(Program i: programs)
            {
                List<String> courseList = i.getListToString();
                for(String ii: courseList)
                    myWriter.write(ii + "\n");
            }
            //Planned to write to temp file, then rename it. Couldn't figure out how to delete/overwrite the old file
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void load(String[] k)
    {
        String filename = k[1];
        try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] words = line.split(",");
                if(words[0].equalsIgnoreCase("Student"))
                {
                    Student student = new Student();
                    student.setName(words[1]);
                    student.setFn(words[2]);
                    student.setYear(Short.parseShort(words[3]));
                    student.setProgram(words[4]);
                    student.setGroup(Short.parseShort(words[5]));
                    student.setAverageGrade(Double.parseDouble(words[6]));
                    student.setNumOfGrades(Integer.parseInt(words[7]));
                    student.setStatus(words[8]);
                    for(int i = 9; i < words.length; i++)
                    {
                        Pair<String, Double> pair = new Pair<>(words[i].split("=")[0],Double.parseDouble(words[i].split("=")[1]));
                        student.pushCourseGrade(pair);
                    }
                    students.add(student);
                }
                else if(words[0].equalsIgnoreCase("Program"))
                {
                    Program program = new Program();
                    program.setName(words[1]);
                    Programs.getInstance().pushProgram(program);
                }
                else if(words[0].equalsIgnoreCase("Course"))
                {
                    Course course = new Course(words[2], words[3], Integer.parseInt(words[4]));
                    Programs.getInstance().pushCourseToProgram(course, words[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FunctionalInterface
    interface Command {
        void execute(String[] parameters);
    }
}
