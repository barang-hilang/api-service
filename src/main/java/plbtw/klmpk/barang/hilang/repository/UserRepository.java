/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import plbtw.klmpk.barang.hilang.entity.User;

/**
 *
 * @author ALz
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findOne(Long id);
    User getUserByEmailAndPassword(String email,String password);
}
