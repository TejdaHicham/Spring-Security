package ma.emsi.thymeleaf;

import ma.emsi.thymeleaf.entities.Patient;
import ma.emsi.thymeleaf.repositories.PatientRepository;
import ma.emsi.thymeleaf.security.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class ThymeleafApplication {

    public static void main(String[] args) {

        SpringApplication.run(ThymeleafApplication.class, args);
    }
    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null, "Siham", new Date(), true, 278));
            patientRepository.save(new Patient(null, "Saad", new Date(), false, 112));
            patientRepository.save(new Patient(null, "Reda", new Date(), false, 244));
            patientRepository.save(new Patient(null, "Karim", new Date(), true, 198));
            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });
        };
    }
    //@Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder = passwordEncoder();
        return args -> {
            UserDetails u1 = jdbcUserDetailsManager.loadUserByUsername("user11");
            if(u1==null)
            jdbcUserDetailsManager.createUser(User.withUsername("user11").password(passwordEncoder().encode("123")).roles("USER").build());
            UserDetails u2 = jdbcUserDetailsManager.loadUserByUsername("user22");
            if(u2==null)
            jdbcUserDetailsManager.createUser(User.withUsername("user22").password(passwordEncoder().encode("123")).roles("USER").build());
            UserDetails a2 = jdbcUserDetailsManager.loadUserByUsername("admin2");
            if(a2==null)
            jdbcUserDetailsManager.createUser(User.withUsername("admin2").password(passwordEncoder().encode("123")).roles("USER", "ADMIN").build());
        };
    }
    //@Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("user1", "123", "user1.gmail.com", "123");
            accountService.addNewUser("user2", "123", "user2.gmail.com", "123");
            accountService.addNewUser("admin", "123", "admin.gmail.com", "123");

            accountService.addRoleToUser("user1", "USER");
            accountService.addRoleToUser("user2", "USER");
            accountService.addRoleToUser("admin", "USER");
            accountService.addRoleToUser("admin", "ADMIN");
        };
        }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
