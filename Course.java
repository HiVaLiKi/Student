public class Course {
    private enum Type {
        REQUIRED,
        OPTIONAL
    }

    private final String name;
    private Type type;
    private final int year;

    /**
     * @param name name of Course
     * @param type Either required or optional
     * @param year which year the course is from
     */
    protected Course(String name, String type, int year) {
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
                System.out.println("Invalid course type: " + type + "Defaulted to Optional");
            }
        }
        this.year = year;
    }

    /**
     * @return true, if course is required
     */
    protected boolean isRequired() {
        return type == Type.REQUIRED;
    }

    protected int getYear() {
        return year;
    }

    protected String getName() {
        return name;
    }

    /**
     * Override of toString in a csv format
     *
     * @return data in a csv format
     */
    @Override
    public String toString() {
        return name + "," + type + "," + year;
    }
}
