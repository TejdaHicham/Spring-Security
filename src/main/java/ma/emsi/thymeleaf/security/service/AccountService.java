package ma.emsi.thymeleaf.security.service;

import ma.emsi.thymeleaf.security.entities.AppUser;
import ma.emsi.thymeleaf.security.entities.AppRole;

public interface AccountService {
    AppUser addNewUser(String username, String password, String email, String confirmPassword);
    AppRole addNewRole(String role);
    void addRoleToUser(String username, String role);
    void removeRoleFromUser(String username, String role);

    AppUser loadUserByUsername(String username);
}
