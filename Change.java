public class Change implements Command{
    /**
     * Checks for valid input. Checks for type of change and calls corresponding method in Student
     * 1st word - command
     * 2nd word - fn of student
     * 3rd word - what is supposed to be changed - program | group | year
     * 4th - to what value to change
     * @param k Split version of original command @see Controller.open()
     * @param students reference to the Class Handler for Students
     * @return Successfull message
     * @throws Exception General
     */
    @Override
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
