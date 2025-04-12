public class Help implements Command{
    @Override
    public void execute(String[] k, Students students)
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
}
