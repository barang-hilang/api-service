/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.service;

import java.util.Collection;
import org.springframework.stereotype.Service;
import plbtw.klmpk.barang.hilang.entity.User;

/**
 *
 * @author ALz
 */
public interface UserService {
  Collection getAllUsers();

  User checkUserExist(String email);
  
  User getUser(long id);

  void addUser(User user);

  void updateUser(User userBaru);

  void deleteUser(long id);
  
  User authLoginUser(String email,String password);
  
  Long countUserByDeveloper(Long id);
}
