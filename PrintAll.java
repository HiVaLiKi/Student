import java.util.ArrayList;
import java.util.List;

public class PrintAll implements Command{
    @Override
    public void execute(String[] k, Students students) {
        if (k.length < 3) {
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
        List<Student> temp = new ArrayList<>(students.getSet());
        for (Student i : temp)
            if (i.getProgram().equals(program) && i.getYear() == year)
                System.out.println(i.print());
    }
}
