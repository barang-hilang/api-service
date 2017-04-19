/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.controller;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import plbtw.klmpk.barang.hilang.entity.User;
import plbtw.klmpk.barang.hilang.entity.form.request.UserRequest;
import plbtw.klmpk.barang.hilang.message.CustomResponseMessage;
import plbtw.klmpk.barang.hilang.service.RoleService;
import plbtw.klmpk.barang.hilang.service.UserService;

/**
 *
 * @author ALz
 */
@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {
  @Autowired
  UserService userService;
 

  @RequestMapping(method = RequestMethod.GET, produces = "application/json")
  public Collection<User> getAllUsers() {
    List<User> allUsers = (List<User>) userService.getAllUsers();
    for (User user : allUsers) {
      Link selfLink = linkTo(UserController.class).withSelfRel();
      user.add(selfLink);
    }
    return allUsers;
  }

  @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = "application/json")
  public User getUser(@PathVariable("id") long id) {
    User user = userService.getUser(id);
    Link selfLink = linkTo(UserController.class).withSelfRel();
    user.add(selfLink);
    return user;
  }

  @RequestMapping(value = "/test/", method = RequestMethod.GET, produces = "application/json")
  public CustomResponseMessage showMessage() {
    return new CustomResponseMessage(1, "try message");
  }

  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public CustomResponseMessage addUser(@RequestBody UserRequest userRequest) {
    try {
      User user = new User();
      user.setUsername(userRequest.getUsername());
      user.setEmail(userRequest.getEmail());
      user.setPassword(userRequest.getPassword());
      user.setAlamat(userRequest.getAlamat());
      user.setNoHp(userRequest.getNoHp());
      userService.addUser(user);
      return new CustomResponseMessage(200, "User Has Been Created");
    } catch (Exception ex) {
      return new CustomResponseMessage(201, ex.toString());
    }
  }

  @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
  public CustomResponseMessage updateUser(@RequestBody UserRequest userRequest) {
    try {
      User userUpdate = userService.getUser(userRequest.getId());
      userUpdate.setUsername(userRequest.getUsername());
      userUpdate.setAlamat(userRequest.getAlamat());
      userUpdate.setEmail(userRequest.getEmail());
      userUpdate.setNoHp(userRequest.getNoHp());
      userUpdate.setPassword(userRequest.getPassword());
      userService.updateUser(userUpdate);
      return new CustomResponseMessage(200, "Update User successfuly");
    } catch (Exception ex) {
      return new CustomResponseMessage(201, ex.toString());
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
  public CustomResponseMessage deleteUser(@RequestBody UserRequest userRequest) {
    try {
      userService.deleteUser(userRequest.getId());
      return new CustomResponseMessage(200, "Delete Successful");
    } catch (Exception ex) {
      return new CustomResponseMessage(201, ex.toString());
    }
  }
}
