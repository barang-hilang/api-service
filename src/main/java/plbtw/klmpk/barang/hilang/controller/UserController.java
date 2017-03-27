/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import plbtw.klmpk.barang.hilang.entity.User;
import plbtw.klmpk.barang.hilang.entity.form.request.UserRequest;
import plbtw.klmpk.barang.hilang.message.CustomResponseMessage;
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

  @RequestMapping(value = "/test/", method = RequestMethod.GET, produces = "application/json")
  public CustomResponseMessage showMessage() {
    return new CustomResponseMessage(1, "try message");
  }

  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public CustomResponseMessage addUser(@RequestBody UserRequest userRequest) {
    try {
      User user = new User();
      user.setEmail(userRequest.getEmail());
      user.setPassword(userRequest.getPassword());
      user.setAlamat(userRequest.getAlamat());
      user.setNoHp(userRequest.getNoHp());
      userService.addUser(user);
      return new CustomResponseMessage(201, "User Has Been Created");
    } catch (Exception ex) {
      return new CustomResponseMessage(400, ex.toString());
    }
  }
}
