package product.model;

import java.awt.*;

public enum Role {
    ADMIN("ADMIN"), USER("USER");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static Role fromValue(String value){
        Role[] values = values();
        for (Role role:values) {
            if (role.value.equals(value))
                return role;
        }
        throw new IllegalArgumentException("invalid");
    }
//    public class admin {
//        private String username;
//        private String password;
//        private Role role;
//
//        public admin(String username, String password, Role role) {
//            this.username = username;
//            this.password = password;
//            this.role = role;
//        }
//
//        public String getUsername() {
//            return username;
//        }
//
//        public String getPassword() {
//            return password;
//        }
//
//        public Role getRole() {
//            return role;
//        }
//    }
}
