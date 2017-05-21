package plbtw.klmpk.barang.hilang;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import plbtw.klmpk.barang.hilang.entity.Developer;
import plbtw.klmpk.barang.hilang.entity.Role;
import plbtw.klmpk.barang.hilang.entity.User;
import plbtw.klmpk.barang.hilang.entity.KategoriBarang;
import plbtw.klmpk.barang.hilang.entity.Barang;
import plbtw.klmpk.barang.hilang.repository.BarangRepository;
import plbtw.klmpk.barang.hilang.repository.DeveloperRepository;
import plbtw.klmpk.barang.hilang.repository.KategoriBarangRepository;
import plbtw.klmpk.barang.hilang.repository.RoleRepository;
import plbtw.klmpk.barang.hilang.repository.UserRepository;

@SpringBootApplication
public class ApiServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ApiServiceApplication.class, args);
  }

  @Bean
  public CommandLineRunner loadData(RoleRepository roleRepository,
      DeveloperRepository developerRepository,UserRepository userRepository, KategoriBarangRepository kategoriBarangRepository, BarangRepository barangRepository) {
    return (args) -> {
      roleRepository.save(new Role("Admin"));
      roleRepository.save(new Role("Developer"));
      roleRepository.save(new Role("SuperDeveloper"));

      developerRepository.save(new Developer(roleRepository.findOne(2l), "tes1", "wakowakowakowa",
          "apn@gmail.com", "1234"));
      developerRepository.save(new Developer(roleRepository.findOne(2l), "tes2", "wakowakowakowa2",
          "aldi@gmail.com", "1234"));
      developerRepository.save(new Developer(roleRepository.findOne(2l), "tes3", "wakowakowakowa3",
          "chyrsant@gmail.com", "1234"));

      userRepository.save(new User(developerRepository.findOne(1l),"aldi@gmail.com","aldi","1234","alamat","089660553886","wakowakowakowa"));
      
      kategoriBarangRepository.save(new KategoriBarang("Elektronik"));
      
      barangRepository.save(new Barang(kategoriBarangRepository.findOne(1l),userRepository.findOne(1l),"Iphone 7","loss",1,"https://cnet2.cbsistatic.com/img/CXq-MuOMDvpuhYOfgpmsHWbYdxg=/830x467/2016/09/12/9a3ba000-7c14-4f55-b685-92ffa90f353c/septus-02.jpg"));
    };
  }
}
