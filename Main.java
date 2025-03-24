import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Controller system = new Controller();
        system.open("load k.csv");
        system.open("enroll 11 SIT 1 Valeri");
        system.open("advance 11");
        //system.open("load k.csv");
        system.open("print 11");

        String input;
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            input = scanner.nextLine();
            system.open(input);
            if(input.equalsIgnoreCase("exit"))
                break;
        }
    }
}
