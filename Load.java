import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Load implements Command{
    /**
     * Reads data from passed filename in String.
     * 1st word - command
     * 2nd word - filename
     * @param k Split version of original command @see Controller.open()
     * @param students reference to the Class Handler for Students
     * @return Succesfull!
     */
    @Override
    public String execute(String[] k, Students students) {
        String filename = k[1];
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(",");
                if (words[0].equalsIgnoreCase("Student")) {
                    Student student = new Student();
                    student.setName(words[1]);
                    student.setFn(words[2]);
                    student.setYear(Short.parseShort(words[3]));
                    student.setProgram(words[4]);
                    student.setGroup(Short.parseShort(words[5]));
                    student.setAverageGrade(Double.parseDouble(words[6]));
                    student.setNumOfGrades(Integer.parseInt(words[7]));
                    if (words[8].equalsIgnoreCase("Studying") ||
                            words[8].equalsIgnoreCase("Pause") ||
                            words[8].equalsIgnoreCase("Graduated"))
                        student.setStatus(words[8]);
                    else {
                        System.out.println("Invalid status parameter, skipped student FN: " + words[2]);
                        continue;
                    }
                    for (int i = 9; i < words.length; i++) {
                        student.pushCourseGrade(words[i].split("=")[0], Double.parseDouble(words[i].split("=")[1]));
                    }
                    try{
                        students.add(student);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else if (words[0].equalsIgnoreCase("Program")) {
                    Program program = new Program();
                    program.setName(words[1]);
                    Programs.getInstance().pushProgram(program);
                } else if (words[0].equalsIgnoreCase("Course")) {
                    Course course = new Course(words[2], words[3], Integer.parseInt(words[4]));
                    Programs.getInstance().pushCourseToProgram(course, words[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Data Loaded!";
    }
}
