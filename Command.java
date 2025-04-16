public interface Command
{
    /**
     *
     * @param k Split version of original command @see Controller.open()
     * @param students reference to the Class Handler for Students
     * @return String output for handling outside CRP
     * @throws Exception General exception in case there are different types
     */
    String execute(String[] k, Students students) throws Exception;
}
