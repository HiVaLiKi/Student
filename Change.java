public class Change {
    public static void change(String[] k, Students students)
    {
        if(k.length < 4)
        {
            System.out.println("Too few arguments\nchange <fn> <option>(program | group | year) <value>");
            return;
        }
        String fn = k[1];
        String option = k[2];
        String value = k[3];
        Student student = students.getStudentByFN(fn);
        boolean flag = false;
        if(option.equalsIgnoreCase("program"))
            flag = student.changeProgram(value);
        else if(option.equalsIgnoreCase("group"))
            flag = student.changeGroup(value);
        else if(option.equalsIgnoreCase("year"))
            flag = student.changeYear(value);
        else
            System.out.println("Something wrong with command\nchange <fn> <option>(program | group | year) <value>");
        if(!flag) {
            System.out.println("Error in change");
            return;
        }
        System.out.println("Success");
    }
}
