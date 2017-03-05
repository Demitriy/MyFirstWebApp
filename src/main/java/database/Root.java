package database;

/**
 * Created by Dima on 09.11.2016.
 */
public enum Root {
    URL("jdbc:mysql://localhost:3306/mydbtest"),
    USERNAME("root"),
    PASSWORD("admin");

    private final String value;

    Root(String value)
    {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
