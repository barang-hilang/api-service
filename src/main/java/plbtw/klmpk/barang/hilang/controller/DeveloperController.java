/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import plbtw.klmpk.barang.hilang.entity.Developer;
import plbtw.klmpk.barang.hilang.entity.form.request.DeveloperRequest;
import plbtw.klmpk.barang.hilang.message.CustomResponseMessage;
import plbtw.klmpk.barang.hilang.service.RoleService;
import plbtw.klmpk.barang.hilang.service.DeveloperService;

/**
 *
 * @author CMDDJ
 */
@RestController
@RequestMapping(value = "/api/v1/developers")
public class DeveloperController {
  @Autowired
  DeveloperService developerService;
   @Autowired
  RoleService roleService;
 

  @RequestMapping(method = RequestMethod.GET, produces = "application/json")
  public Collection<Developer> getAllDevelopers() {
    List<Developer> allDevelopers = (List<Developer>) developerService.getAllDevelopers();
    for (Developer developer : allDevelopers) {
      Link selfLink = linkTo(DeveloperController.class).withSelfRel();
      developer.add(selfLink);
    }
    return allDevelopers;
  }

  @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = "application/json")
  public Developer getDeveloper(@PathVariable("id") long id) {
    Developer developer = developerService.getDeveloper(id);
    Link selfLink = linkTo(DeveloperController.class).withSelfRel();
    developer.add(selfLink);
    return developer;
  }

  @RequestMapping(value = "/test/", method = RequestMethod.GET, produces = "application/json")
  public CustomResponseMessage showMessage() {
    return new CustomResponseMessage(1, "try message");
  }

  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public CustomResponseMessage addDeveloper(@RequestBody DeveloperRequest developerRequest) {
    try {
      Developer developer = new Developer();
      developer.setRole(roleService.getRole(developerRequest.getIdrole()));
      developer.setSecretKey(developerRequest.getSecretKey());
      developer.setToken(developerRequest.getToken());
      developer.setEmail(developerRequest.getEmail());
      developer.setPassword(developerRequest.getPassword());
      developerService.addDeveloper(developer);
      return new CustomResponseMessage(200, "Developer Has Been Created");
    } catch (Exception ex) {
      return new CustomResponseMessage(201, ex.toString());
    }
  }

  @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
  public CustomResponseMessage updateDeveloper(@RequestBody DeveloperRequest developerRequest) {
    try {
      Developer developerUpdate = developerService.getDeveloper(developerRequest.getIdDeveloper());
      developerUpdate.setSecretKey(developerRequest.getSecretKey());
      developerUpdate.setToken(developerRequest.getToken());
      developerUpdate.setEmail(developerRequest.getEmail());
      developerUpdate.setPassword(developerRequest.getPassword());
      developerService.updateDeveloper(developerUpdate);
      return new CustomResponseMessage(200, "Update Developer successfuly");
    } catch (Exception ex) {
      return new CustomResponseMessage(201, ex.toString());
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
  public CustomResponseMessage deleteDeveloper(@RequestBody DeveloperRequest developerRequest) {
    try {
      developerService.deleteDeveloper(developerRequest.getIdDeveloper());
      return new CustomResponseMessage(200, "Delete Successful");
    } catch (Exception ex) {
      return new CustomResponseMessage(201, ex.toString());
    }
  }
}
