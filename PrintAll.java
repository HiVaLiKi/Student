import java.util.List;

public class PrintAll
{
    public static void printAll(String[] k, List<Student> students)
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
}
