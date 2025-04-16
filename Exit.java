public class Exit implements Command{
    @Override
    public String execute(String[] k, Students students) {
        System.out.println("Closing...Goodbye");
        System.exit(0);
        return null;
    }
}
