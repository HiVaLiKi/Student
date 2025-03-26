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
        //commands.put("exit", this::exit);
        commands.put("exit", Exit::exit);
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
        commands.put("open", this::load);
        commands.put("help", this::help);
    }
    public void open(String input)
    {
        Command command;
        command = getcommand(input.split(" "));
        if(input.split(" ")[0].equalsIgnoreCase("Save"))
        {
            input = input.replaceFirst(" as ", " ");
            if(input.equalsIgnoreCase("save"))
                input+=" savefile.csv";
        }
        else if(input.toLowerCase().startsWith(("open")))
            if(input.length() == 4)
                input+=" savefile.csv";
        if(command==null)
        {
            System.out.println("Command not found");
            return;
        }

        command.execute(input.split(" "));
    }
    private Command getcommand(String[] input)
    {
        if(commands.containsKey(input[0].toLowerCase()))
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
        Enroll.enroll(k, students);
    }

    private void advance(String[] k)
    {
        Advance.advance(k,students);
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
        Graduate.graduate(k,students);
    }

    private void interrupt(String[] k)
    {
        Interrupt.interrupt(k,students);
    }

    private void resume(String[] k)
    {
        Resume.resume(k,students);
    }

    private void print(String[] k)
    {
        Print.print(k,students);
    }

    private void printall(String[] k)
    {
        PrintAll.printAll(k,students);
    }

    private void enrolling(String[] k)
    {
        Enrolling.enrolling(k,students);
    }

    private void addgrade(String[] k)
    {
        AddGrade.addGrade(k,students);
    }

    private void protocol(String[] k)
    {
        Protocol.protocol(k,students);
    }

    private void report(String[] k)
    {
        Report.report(k,students);
    }

    private void save(String[] k)
    {
        Save.save(k,students);
    }

    private void load(String[] k)
    {
        Load.load(k,students);
    }

    private void help(String[] k)
    {
        System.out.println("addgrade <fn> <course> <grade>\n" +
                "advance <fn>\n" +
                "enroll <fn> <program> <group> <name>\n" +
                "enrolling <fn> <course>\n" +
                "graduate <fn>\n" +
                "interrupt <fn>\n" +
                "open <filename | empty>\n" +
                "print <fn>\n" +
                "printall <program> <year>\n" +
                "protocol <course>\n" +
                "report <fn>\n" +
                "resume <fn>\n" +
                "save\n" +
                "save as <filename>");
    }

    @FunctionalInterface
    interface Command {
        void execute(String[] parameters);
    }
}
