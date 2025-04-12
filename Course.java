public class Course {
    private enum Type {
        REQUIRED,
        OPTIONAL
    }

    private final String name;
    private Type type;
    private final int year;

    Course(String name, String type, int year) {
        this.name = name;
        try {
            this.type = Type.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            if (type.equalsIgnoreCase("REQUIRED"))
                this.type = Type.REQUIRED;
            else if (type.equalsIgnoreCase("OPTIONAL"))
                this.type = Type.OPTIONAL;
            else {
                this.type = Type.OPTIONAL;
                throw new IllegalArgumentException("Invalid course type: " + type + "Defaulted to Optional");
            }
        }
        this.year = year;
    }

    public boolean isRequired() {
        return type == Type.REQUIRED;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "," + type + "," + year;
    }
}
