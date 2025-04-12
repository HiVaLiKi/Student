import java.util.*;

public class Controller {
    private final HashMap<String, Command> commands;
    private final Students students;

    public Controller() {
        students = new Students();
        commands = new HashMap<>();
        commands.put("enroll", new Enroll());
        commands.put("exit", new Exit());
        commands.put("advance", new Advance());
        commands.put("change", new Change());
        commands.put("graduate", new Graduate());
        commands.put("interrupt", new Interrupt());
        commands.put("resume", new Resume());
        commands.put("print", new Print());
        commands.put("printall", new PrintAll());
        commands.put("enrolling", new Enrolling());
        commands.put("addgrade", new AddGrade());
        commands.put("protocol", new Protocol());
        commands.put("report", new Report());
        commands.put("save", new Save());
        commands.put("open", new Load());
        commands.put("help", new Help());
    }

    public void open(String input)
    {
        if (input.split(" ")[0].equalsIgnoreCase("Save")) {
            input = input.replaceFirst(" as ", " ");
            if (input.equalsIgnoreCase("save"))
                input += " savefile.csv";
        } else if (input.toLowerCase().startsWith(("open")) && input.length() == 4)
                input += " savefile.csv";

        Command command;
        String[] k = input.split(" ");
        command = getcommand(k);
        if(command != null)
            command.execute(k, students);
    }

    private Command getcommand(String[] input) {
        if (commands.containsKey(input[0].toLowerCase()))
            return commands.get(input[0]);
        return null;
    }
}
