package plbtw.klmpk.barang.hilang;

import java.util.Date;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import plbtw.klmpk.barang.hilang.entity.Developer;
import plbtw.klmpk.barang.hilang.entity.Role;
import plbtw.klmpk.barang.hilang.entity.User;
import plbtw.klmpk.barang.hilang.entity.KategoriBarang;
import plbtw.klmpk.barang.hilang.entity.Barang;
import plbtw.klmpk.barang.hilang.entity.Pelaporan;
import plbtw.klmpk.barang.hilang.repository.BarangRepository;
import plbtw.klmpk.barang.hilang.repository.DeveloperRepository;
import plbtw.klmpk.barang.hilang.repository.KategoriBarangRepository;
import plbtw.klmpk.barang.hilang.repository.PelaporanRepository;
import plbtw.klmpk.barang.hilang.repository.RoleRepository;
import plbtw.klmpk.barang.hilang.repository.UserRepository;

@SpringBootApplication
public class ApiServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(RoleRepository roleRepository,
            DeveloperRepository developerRepository,
            UserRepository userRepository,
            KategoriBarangRepository kategoriBarangRepository,
            BarangRepository barangRepository,
            PelaporanRepository pelaporanRepository) {
        return (args) -> {
//            roleRepository.save(new Role("Admin"));
//            roleRepository.save(new Role("Developer"));
//            roleRepository.save(new Role("SuperDeveloper"));
//
//            developerRepository.save(new Developer(roleRepository.findOne(2l), "tes1", "wakowakowakowa",
//                    "apn@gmail.com", "1234"));
//            developerRepository.save(new Developer(roleRepository.findOne(2l), "tes2", "wakowakowakowa2",
//                    "aldi@gmail.com", "1234"));
//            developerRepository.save(new Developer(roleRepository.findOne(2l), "tes3", "wakowakowakowa3",
//                    "chyrsant@gmail.com", "1234"));
//
//            userRepository.save(new User(developerRepository.findOne(1l), "aldi@gmail.com", "aldi", "1234", "alamat", "089660553886", "wakowakowakowa"));
//
//            kategoriBarangRepository.save(new KategoriBarang("Elektronik"));
//            kategoriBarangRepository.save(new KategoriBarang("Keperluan Pribadi"));
//            kategoriBarangRepository.save(new KategoriBarang("Uang"));
//            kategoriBarangRepository.save(new KategoriBarang("Surat"));
//            kategoriBarangRepository.save(new KategoriBarang("Kendaraan"));
//
//            barangRepository.save(new Barang(kategoriBarangRepository.findOne(1l), userRepository.findOne(1l), "Iphone 7", "lost", 1, "https://cnet2.cbsistatic.com/img/CXq-MuOMDvpuhYOfgpmsHWbYdxg=/830x467/2016/09/12/9a3ba000-7c14-4f55-b685-92ffa90f353c/septus-02.jpg"));
//            barangRepository.save(new Barang(kategoriBarangRepository.findOne(2l), userRepository.findOne(1l), "Dompet", "lost", 2, "http://id-live-03.slatic.net/p/7/blackkelly-mens-wallet-dompet-pria-magnum-lcp-567-brown-6482-2528209-62c0917126ac62374beb48df14d2eed2.jpg"));
//            barangRepository.save(new Barang(kategoriBarangRepository.findOne(1l), userRepository.findOne(1l), "Honda Beat", "lost", 5, "http://s.kaskus.id/images/2016/04/24/2229201_20160424010013.jpg"));
//
//            pelaporanRepository.save(new Pelaporan(barangRepository.findOne(1l),userRepository.findOne(1l),"Bagi yang menemukan akan dapat hadiah","Jalan Babarsari No.46, Yogyakarta",new Date()));
//            pelaporanRepository.save(new Pelaporan(barangRepository.findOne(2l),userRepository.findOne(1l),"Bagi yang menemukan akan dapat hadiah","Jalan Babarsari No.46, Yogyakarta",new Date()));
//            pelaporanRepository.save(new Pelaporan(barangRepository.findOne(3l),userRepository.findOne(1l),"Bagi yang menemukan akan dapat hadiah","Jalan Babarsari No.46, Yogyakarta",new Date()));
        };
    }
}
