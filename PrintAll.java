import java.util.ArrayList;
import java.util.List;

public class PrintAll implements Command{
    /**
     *
     * @param k Split version of original command @see Controller.open()
     * @param students reference to the Class Handler for Students
     * @return String concatenation of all Student.print() for said year and program
     * @throws Exception general
     */
    @Override
    public String execute(String[] k, Students students) throws Exception{
        if (k.length < 3)
            throw new Exception("Too few arguments\nprintall <program> <year>");
        String program = k[1];
        short year;
        try {
            year = Short.parseShort(k[2]);
        } catch (NumberFormatException e) {
            throw new Exception("Invalid input for year, should be a number");
        }
        List<Student> temp = new ArrayList<>(students.getSet());
        String res = "";
        for (Student i : temp)
            if (i.getProgram().equals(program) && i.getYear() == year)
                res+=i.print()+"\n";

        return res;
    }
}
