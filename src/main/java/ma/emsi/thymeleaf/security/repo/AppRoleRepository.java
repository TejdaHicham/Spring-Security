package ma.emsi.thymeleaf.security.repo;

import ma.emsi.thymeleaf.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {

}
