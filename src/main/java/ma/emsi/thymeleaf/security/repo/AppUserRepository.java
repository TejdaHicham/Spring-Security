package ma.emsi.thymeleaf.security.repo;

import ma.emsi.thymeleaf.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
}
