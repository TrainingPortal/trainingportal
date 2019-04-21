package trainingportal.model;

public final class Role {
    public static final Long ADMIN = 1L;
    public static final Long EMPLOYEE = 2L;
    public static final Long TRAINER = 3L;
    public static final Long MANAGER = 4L;

    // If you have only static members and want to simulate a static
    // class in Java, then you can make the constructor private.
    private Role() {}
}