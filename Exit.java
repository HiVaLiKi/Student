public class Exit implements Command{
    @Override
    public void execute(String[] k, Students students) {
        System.out.println("Closing...Goodbye");
        System.exit(0);
    }
}
