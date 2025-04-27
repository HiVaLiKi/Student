import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Save implements Command{
    /**
     * Saves All Data to a file in csv format
     * 1st word - command
     * 2nd word - filename
     * @param k Split version of original command @see Controller.open()
     * @param students reference to the Class Handler for Students
     * @return Successful messsage
     * @throws Exception General
     */
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
