public class Exit implements Command{
    /**
     * Simple exit
     * @param k Split version of original command @see Controller.open()
     * @param students reference to the Class Handler for Students
     * @return doesn't matter really
     */
    @Override
    public String execute(String[] k, Students students) {
        System.out.println("Closing...Goodbye");
        System.exit(0);
        return null;
    }
}
