/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plbtw.klmpk.barang.hilang.entity.User;
import plbtw.klmpk.barang.hilang.repository.UserRepository;
import plbtw.klmpk.barang.hilang.service.UserService;

/**
 *
 * @author ALz
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Override
  public Collection getAllUsers() {
    List<User> result = new ArrayList<User>();
    result = userRepository.findAll();
    return result;
  }

  @Override
  public void addUser(User user) {
    userRepository.save(user);
  }

  @Override
  public User getUser(long id) {
    return userRepository.findOne(id);
  }

  @Override
  public void updateUser(User userBaru) {
    userRepository.save(userBaru);
  }

  @Override
  public void deleteUser(long id) {
    userRepository.delete(id);
  }

    @Override
    public User authLoginUser(String email, String password) {
        return userRepository.getUserByEmailAndPassword(email, password);
    }

    @Override
    public Long countUserByDeveloper(Long id) {
        return userRepository.countByDev(id);
    }

    @Override
    public User checkUserExist(String email) {
        return userRepository.findByEmail(email);
    }

}
