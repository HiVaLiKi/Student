import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Save implements Command{
    @Override
    public String execute(String[] k, Students students) throws Exception{
        String filename = k[1];
        try (FileWriter myWriter = new FileWriter(filename)) {
            List<Program> programs = Programs.getInstance().getPrograms();
            for (Student i : students.getSet())
                myWriter.write(i.toString() + "\n");
            for (Program i : programs)
                myWriter.write("Program," + i.getName() + "\n");
            for (Program i : programs) {
                List<String> courseList = i.getListToString();
                for (String ii : courseList)
                    myWriter.write(ii + "\n");
            }
            //Planned to write to temp file, then rename it. Couldn't figure out how to delete/overwrite the old file
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Data saved!";
    }
}
