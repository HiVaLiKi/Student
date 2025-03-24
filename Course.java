public class Course
{
    private enum Type
    {
        REQUIRED,
        OPTIONAL
    }
    private String name;
    private Type type;
    private int year;
    Course(String name, String type, int year)
    {
        this.name = name;
        this.type = Type.valueOf(type);
        //this.type = type.equals("Required")?Type.REQUIRED:Type.OPTIONAL;
        this.year = year;
    }
    public boolean isRequired()
    {
        return type == Type.REQUIRED;
    }
    public int getYear()
    {
        return year;
    }
    public String getName()
    {
        return name;
    }
    @Override
    public String toString()
    {
        return name+","+type+","+year;
    }
}
