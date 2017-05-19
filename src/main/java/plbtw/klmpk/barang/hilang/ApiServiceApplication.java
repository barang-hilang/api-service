package plbtw.klmpk.barang.hilang;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import plbtw.klmpk.barang.hilang.entity.Developer;
import plbtw.klmpk.barang.hilang.entity.Role;
import plbtw.klmpk.barang.hilang.entity.User;
import plbtw.klmpk.barang.hilang.repository.DeveloperRepository;
import plbtw.klmpk.barang.hilang.repository.RoleRepository;
import plbtw.klmpk.barang.hilang.repository.UserRepository;

@SpringBootApplication
public class ApiServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ApiServiceApplication.class, args);
  }

  @Bean
  public CommandLineRunner loadData(RoleRepository roleRepository,
      DeveloperRepository developerRepository,UserRepository userRepository) {
    return (args) -> {
      roleRepository.save(new Role("Admin"));
      roleRepository.save(new Role("Developer"));
      roleRepository.save(new Role("SuperDeveloper"));

      developerRepository.save(new Developer(roleRepository.findOne(2l), "tes1", "wakowakowakowa",
          "tesCoy", "wehehehe"));
      developerRepository.save(new Developer(roleRepository.findOne(2l), "tes2", "wakowakowakowa",
          "tesCoy", "wehehehe"));
      developerRepository.save(new Developer(roleRepository.findOne(2l), "tes3", "wakowakowakowa",
          "tesCoy", "wehehehe"));

      userRepository.save(new User(developerRepository.findOne(1l),"aldi@gmail.com","aldi","1234","alamat","089660553886","wakowakowakowa"));
    };
  }
}
