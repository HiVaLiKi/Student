public class Change implements Command{
    @Override
    /**
     * Checks for valid input. Checks for type of change and calls corresponding method in Student
     */
    public String execute(String[] k, Students students) throws Exception{
        if (k.length < 4)
            throw new Exception("Too few arguments\nchange <fn> <option>(program | group | year) <value>");
        String fn = k[1];
        String option = k[2];
        String value = k[3];
        Student student = students.getStudentByFN(fn);
        if (option.equalsIgnoreCase("program"))
            student.changeProgram(value);
        else if (option.equalsIgnoreCase("group"))
            student.changeGroup(value);
        else if (option.equalsIgnoreCase("year"))
            student.changeYear(value);
        else
            throw new Exception("Something wrong with command\nchange <fn> <option>(program | group | year) <value>");
        return "Change sucessfull!";
    }
}
