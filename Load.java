import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Load
{
    public static void load(String[] k, List<Student> students)
    {
        String filename = k[1];
        try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] words = line.split(",");
                if(words[0].equalsIgnoreCase("Student"))
                {
                    Student student = new Student();
                    student.setName(words[1]);
                    student.setFn(words[2]);
                    student.setYear(Short.parseShort(words[3]));
                    student.setProgram(words[4]);
                    student.setGroup(Short.parseShort(words[5]));
                    student.setAverageGrade(Double.parseDouble(words[6]));
                    student.setNumOfGrades(Integer.parseInt(words[7]));
                    student.setStatus(words[8]);
                    for(int i = 9; i < words.length; i++)
                    {
                        Pair<String, Double> pair = new Pair<>(words[i].split("=")[0],Double.parseDouble(words[i].split("=")[1]));
                        student.pushCourseGrade(pair);
                    }
                    students.add(student);
                }
                else if(words[0].equalsIgnoreCase("Program"))
                {
                    Program program = new Program();
                    program.setName(words[1]);
                    Programs.getInstance().pushProgram(program);
                }
                else if(words[0].equalsIgnoreCase("Course"))
                {
                    Course course = new Course(words[2], words[3], Integer.parseInt(words[4]));
                    Programs.getInstance().pushCourseToProgram(course, words[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
