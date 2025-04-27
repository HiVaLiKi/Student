public class Help implements Command{
    /**
     * List of commands possible
     * @param k Split version of original command @see Controller.open()
     * @param students reference to the Class Handler for Students
     * @return helpful list of commands
     */
    @Override
    public String execute(String[] k, Students students)
    {
        return "addgrade <fn> <course> <grade>\n" +
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
                "save as <filename>";
    }
}
