package trainingportal.model;

public final class Role {
    public static final Long ADMIN = 1L;
    public static final Long EMPLOYEE = 2L;
    public static final Long TRAINER = 3L;
    public static final Long MANAGER = 4L;

    private Long roleId;
    private String roleName;

    // If you have only static members and want to simulate a static
    // class in Java, then you can make the constructor private.


    public Role(Long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}