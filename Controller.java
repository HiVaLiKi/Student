import java.util.*;

/**
 * This class is the control manager of the System. It handles the input and executes the according commands
 * Used by calling the Controller.open() method
 */
public class Controller implements CLI {
    private final HashMap<String, Command> commands;
    private final Students students;

    /**
     * Constructor loading the commands in the Hashmap
     */
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

    /**
     * input stream for the System. Handles reading and executing of command. If needed ads default filenames when Saving/Loading
     *
     * @param input String input corresponding to CLI command
     */
    @Override
    public void open(String input) {
        if (input.split(" ")[0].equalsIgnoreCase("Save")) {
            input = input.replaceFirst(" as ", " ");
            if (input.length() == 4)
                input += " savefile.csv";
        } else if (input.toLowerCase().startsWith(("open")) && input.length() == 4)
            input += " savefile.csv";

        Command command;
        String[] k = input.split(" ");
        command = getcommand(k);
        if (command != null) {
            try {
                String res = command.execute(k, students);
                System.out.println(res);//May be returned for outside handling
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * checks if input corresponds to any command
     *
     * @param input command String split for easier lookup
     * @return reference to the object executing the command
     */
    private Command getcommand(String[] input) {
        if (commands.containsKey(input[0].toLowerCase()))
            return commands.get(input[0]);
        return null;
    }
}
