/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.controller;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import plbtw.klmpk.barang.hilang.entity.Developer;
import plbtw.klmpk.barang.hilang.entity.form.request.DeveloperRequest;
import plbtw.klmpk.barang.hilang.entity.form.request.LoginAuthRequest;
import plbtw.klmpk.barang.hilang.message.CustomResponseMessage;
import plbtw.klmpk.barang.hilang.service.RoleService;
import plbtw.klmpk.barang.hilang.service.DeveloperService;
import plbtw.klmpk.barang.hilang.service.LogService;
import plbtw.klmpk.barang.hilang.service.UserService;

/**
 *
 * @author CMDDJ
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/developers")
public class DeveloperController {

    @Autowired
    DeveloperService developerService;

    @Autowired
    RoleService roleService;
    
    @Autowired
    LogService logService;
    
    @Autowired
    UserService userService;

    //Login
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/auth", method = RequestMethod.POST, produces = "application/json")
    public CustomResponseMessage login(@RequestBody LoginAuthRequest req) {
        Developer loggedDeveloper = developerService.getDeveloperByEmailAndPassword(req.getEmail(), req.getPassword());
        CustomResponseMessage resultMessage = new CustomResponseMessage();
        if (loggedDeveloper == null) {
            resultMessage.setResult(null);
            resultMessage.setMessage("Failed");
            resultMessage.setHttpStatus(HttpStatus.NOT_FOUND);
        } else {
            List<Developer> result = new ArrayList<Developer>();
            result.add(loggedDeveloper);
            resultMessage.setResult(result);
            resultMessage.setHttpStatus(HttpStatus.FOUND);
            resultMessage.setMessage("Success");
        }
        return resultMessage;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public CustomResponseMessage getAllDevelopers() {
        List<Developer> allDevelopers = (List<Developer>) developerService.getAllDevelopers();
        for (int i = 0; i < allDevelopers.size(); i++) {
            Link selfLink = linkTo(
                    methodOn(DeveloperController.class).getDeveloper(allDevelopers.get(i).getIdDeveloper()))
                    .withSelfRel();
            allDevelopers.get(i).add(selfLink);
            Link linkRole
                    = linkTo(methodOn(RoleController.class).find(allDevelopers.get(i).getRole().getIdRole()))
                            .withRel("role");
            allDevelopers.get(i).add(linkRole);
            allDevelopers.get(i).getRole().removeLinks();
        }
        CustomResponseMessage result = new CustomResponseMessage();
        result.add(linkTo(DeveloperController.class).withSelfRel());
        result.setHttpStatus(HttpStatus.OK);
        result.setMessage("Success");
        result.setResult(allDevelopers);
        return result;
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    CustomResponseMessage getDeveloper(@PathVariable("id") long id) {
        Developer developer = developerService.getDeveloper(id);
        // Untuk HATEOAS
        Link selfLink = linkTo(DeveloperController.class).withSelfRel();
        developer.getRole().add(
                linkTo(methodOn(RoleController.class).find(developer.getRole().getIdRole())).withSelfRel());
        developer.add(selfLink);
        CustomResponseMessage result = new CustomResponseMessage();
        result.add(linkTo(DeveloperController.class).withSelfRel());
        result.setHttpStatus(HttpStatus.OK);
        result.setMessage("Success");
        List<Developer> tempCollectionDev = new ArrayList<>();
        tempCollectionDev.add(developer);
        result.setResult(tempCollectionDev);
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public CustomResponseMessage addDeveloper(@RequestBody DeveloperRequest developerRequest) {
        try {
            Developer developer = new Developer();
            developer.setRole(roleService.getRole(developerRequest.getIdrole()));
                SecureRandom random = new SecureRandom();
                byte bytes[] = new byte[20];
                random.nextBytes(bytes);
                String token = bytes.toString();
            developer.setToken(token);
            developer.setSecretKey(token);
            developer.setEmail(developerRequest.getEmail());
            developer.setPassword(developerRequest.getPassword());
            developerService.addDeveloper(developer);
            List<Developer> result = new ArrayList<>();
            result.add(developer);
            return new CustomResponseMessage(HttpStatus.CREATED, "Developer Has Been Created",result);
        } catch (Exception ex) {
            return new CustomResponseMessage(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public CustomResponseMessage updateDeveloper(@RequestBody DeveloperRequest developerRequest) {
        try {
            Developer developerUpdate = developerService.getDeveloper(developerRequest.getIdDeveloper());
            SecureRandom random = new SecureRandom();
            byte bytes[] = new byte[20];
            random.nextBytes(bytes);
            String token = bytes.toString();
            developerUpdate.setToken(token);
            developerUpdate.setEmail(developerRequest.getEmail());
            developerUpdate.setPassword(developerRequest.getPassword());
            developerService.updateDeveloper(developerUpdate);
            return new CustomResponseMessage(HttpStatus.CREATED, "Update Developer successfuly");
        } catch (Exception ex) {
            return new CustomResponseMessage(HttpStatus.BAD_REQUEST, ex.toString());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
    public CustomResponseMessage deleteDeveloper(@RequestBody DeveloperRequest developerRequest) {
        try {
            developerService.deleteDeveloper(developerRequest.getIdDeveloper());
            return new CustomResponseMessage(HttpStatus.CREATED, "Delete Successful");
        } catch (Exception ex) {
            return new CustomResponseMessage(HttpStatus.BAD_REQUEST, ex.toString());
        }
    }
    
    @RequestMapping(method = RequestMethod.GET,value = "/get/request",produces = "application/json")
    public CustomResponseMessage getTotalRequest(@RequestHeader String apiKey){
        long totalHit = logService.countByApiKey(apiKey);
        List<Long> result = new ArrayList<>();
        result.add(totalHit);
        return new CustomResponseMessage(HttpStatus.OK, "Success", result);
    }
    
    @RequestMapping(method = RequestMethod.GET,value = "/get/user",produces = "application/json")
    public CustomResponseMessage getTotalUser(@RequestHeader String apiKey){
        Long idTemp = userService.countUserByDeveloper(developerService.getDeveloperByApiKey(apiKey).getIdDeveloper());
        List<Long> result = new ArrayList<>();
        result.add(idTemp);
        return new CustomResponseMessage(HttpStatus.OK, "Success", result);
    }
}
