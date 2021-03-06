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
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import plbtw.klmpk.barang.hilang.entity.Role;
import plbtw.klmpk.barang.hilang.entity.form.request.RoleRequest;
import plbtw.klmpk.barang.hilang.message.CustomResponseMessage;
import plbtw.klmpk.barang.hilang.service.RoleService;

/**
 *
 * @author ALz
 */
@RestController
@RequestMapping(value = "/api/v1/roles")
public class RoleController {
  @Autowired
  private RoleService roleService;


  @RequestMapping(method = RequestMethod.GET, produces = "application/json")
  public Collection<Role> getAllUser() {
    List<Role> allRole = (List<Role>) roleService.getAllRole();
    for (Role role : allRole) {
      role.add(linkTo(methodOn(RoleController.class).find(role.getIdRole())).withSelfRel());
    }
    return allRole;
  }

  @RequestMapping(value = "find/{id}", method = RequestMethod.GET, produces = "application/json")
  public Role find(@PathVariable("id") long id) {
    Role role = roleService.getRole(id);
    Link selfLink = linkTo(RoleController.class).withSelfRel();
    role.add(selfLink);
    return role;
  }

  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public CustomResponseMessage addRole(@RequestBody RoleRequest roleRequest) {
    try {
      Role role = new Role();
      role.setRole(roleRequest.getRole());
      roleService.addRole(role);
      return new CustomResponseMessage(HttpStatus.CREATED, "Role Has Been Created");
    } catch (Exception ex) {
      return new CustomResponseMessage(HttpStatus.BAD_REQUEST, ex.toString());
    }
  }

  @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
  public CustomResponseMessage updateRole(@RequestBody RoleRequest roleRequest) {
    try {
      Role role = roleService.getRole(roleRequest.getId());
        System.out.println(role.toString());
      role.setRole(roleRequest.getRole());
      roleService.updateRole(role);
      return new CustomResponseMessage(HttpStatus.CREATED, "Update Role Succesfull");
    } catch (Exception ex) {
      return new CustomResponseMessage(HttpStatus.BAD_REQUEST, ex.toString());
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
  public CustomResponseMessage deleteRole(@RequestBody RoleRequest roleRequest) {
    try {
      roleService.deleteRole(roleRequest.getId());
      return new CustomResponseMessage(HttpStatus.CREATED, "Detele Successfull");
    } catch (Exception ex) {
      return new CustomResponseMessage(HttpStatus.BAD_REQUEST, ex.toString());
    }
  }
}
