/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import plbtw.klmpk.barang.hilang.entity.Developer;

/**
 *
 * @author CMDDJ
 */
@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
  Developer findOne(Long id);
  Developer findByToken(String token);
  Developer findByEmailAndPassword(String email,String password);
  Developer findByEmail(String email);
}
