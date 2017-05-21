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
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import plbtw.klmpk.barang.hilang.entity.Log;
import plbtw.klmpk.barang.hilang.entity.User;
import plbtw.klmpk.barang.hilang.entity.form.request.ApiKeyRequest;
import plbtw.klmpk.barang.hilang.entity.form.request.LogRequest;
import plbtw.klmpk.barang.hilang.entity.form.request.UserAuthRequest;
import plbtw.klmpk.barang.hilang.entity.form.request.UserRequest;
import plbtw.klmpk.barang.hilang.message.CustomResponseMessage;
import plbtw.klmpk.barang.hilang.service.DeveloperService;
import plbtw.klmpk.barang.hilang.service.LogService;
import plbtw.klmpk.barang.hilang.service.RoleService;
import plbtw.klmpk.barang.hilang.service.UserService;
import plbtw.klmpk.barang.hilang.service.impl.DependencyFactory;

/**
 *
 * @author ALz
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final int RATE_LIMIT = 10;
    @Autowired
    UserService userService;
    @Autowired
    DeveloperService developerService;
    @Autowired
    LogService logService;

    private boolean checkRateLimit(int rateLimit, String apiKey) {
        return (logService.rateLimit(apiKey) >= rateLimit) ? true : false;
    }

    private boolean authApiKey(String token) {
        if (developerService.getDeveloperByApiKey(token) == null || token.equalsIgnoreCase("") || token == null) {
            return false;
        }
        return true;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public CustomResponseMessage getAllUsers(@RequestHeader String apiKey) {
        try {
            if (!authApiKey(apiKey)) {
                return new CustomResponseMessage(HttpStatus.FORBIDDEN,
                        "Please use your api key to authentication");
            }

            if (checkRateLimit(RATE_LIMIT, apiKey)) {
                return new CustomResponseMessage(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED,
                        "Please wait a while, you have reached your rate limit");
            }

            LogRequest temp = DependencyFactory.createLog(apiKey, "Get");

            Log log = new Log();
            log.setApiKey(temp.getApiKey());
            log.setStatus(temp.getStatus());
            log.setTimeRequest(temp.getTime_request());
            logService.addLog(log);

            List<User> allUsers = (List<User>) userService.getAllUsers();
            for (User user : allUsers) {
                Link selfLink = linkTo(UserController.class).withSelfRel();
                user.add(selfLink);
            }
            CustomResponseMessage result = new CustomResponseMessage();
            result.add(linkTo(UserController.class).withSelfRel());
            result.setHttpStatus(HttpStatus.ACCEPTED);
            result.setMessage("Success");
            result.setResult(allUsers);
            return result;
        } catch (Exception ex) {
            return new CustomResponseMessage(HttpStatus.FORBIDDEN,
                    "Please use your api key to authentication");
        }
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = "application/json")
    public CustomResponseMessage getUser(@RequestHeader String apiKey, @PathVariable("id") long id) {
        try {

            if (!authApiKey(apiKey)) {
                return new CustomResponseMessage(HttpStatus.FORBIDDEN,
                        "Please use your api key to authentication");
            }

            if (checkRateLimit(RATE_LIMIT, apiKey)) {
                return new CustomResponseMessage(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED,
                        "Please wait a while, you have reached your rate limit");
            }

            LogRequest temp = DependencyFactory.createLog(apiKey, "Get");

            Log log = new Log();
            log.setApiKey(temp.getApiKey());
            log.setStatus(temp.getStatus());
            log.setTimeRequest(temp.getTime_request());
            logService.addLog(log);

            User user = userService.getUser(id);
            Link selfLink = linkTo(UserController.class).withSelfRel();
            user.add(selfLink);
            List<User> listUser = new ArrayList<User>();
            listUser.add(user);
            CustomResponseMessage result = new CustomResponseMessage();
            result.add(linkTo(UserController.class).withSelfRel());
            result.setHttpStatus(HttpStatus.ACCEPTED);
            result.setMessage("Success");
            result.setResult(listUser);
            return result;
        } catch (Exception ex) {
            return new CustomResponseMessage(HttpStatus.FORBIDDEN,
                    "Please use your api key to authentication");
        }
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST, produces = "application/json")
    public CustomResponseMessage authLogin(@RequestHeader String apiKey, @RequestBody UserAuthRequest userAuthRequest) {
        try {
            if (!authApiKey(apiKey)) {
                return new CustomResponseMessage(HttpStatus.FORBIDDEN,
                        "Please use your api key to authentication");
            }

            if (checkRateLimit(RATE_LIMIT, apiKey)) {
                return new CustomResponseMessage(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED,
                        "Please wait a while, you have reached your rate limit");
            }
            LogRequest temp = DependencyFactory.createLog(apiKey, "Post");

            Log log = new Log();
            log.setApiKey(temp.getApiKey());
            log.setStatus(temp.getStatus());
            log.setTimeRequest(temp.getTime_request());
            logService.addLog(log);

            User user = userService.authLoginUser(userAuthRequest.getEmail(), userAuthRequest.getPassword());
            List<User> listUser = new ArrayList<User>();
            if (user == null) {
                return new CustomResponseMessage(HttpStatus.NOT_FOUND, "Login Failed", listUser);
            }
            listUser.add(user);
            CustomResponseMessage result = new CustomResponseMessage();
            result.setResult(listUser);
            result.setHttpStatus(HttpStatus.ACCEPTED);
            result.setMessage("Auth Success");
            return result;
        } catch (Exception ex) {
            return new CustomResponseMessage(HttpStatus.BAD_REQUEST, "Please use your api key to authentication");
        }
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public CustomResponseMessage addUser(@RequestHeader String apiKey,
            @RequestBody UserRequest userRequest) {
        try {
            if (!authApiKey(apiKey)) {
                return new CustomResponseMessage(HttpStatus.FORBIDDEN,
                        "Please use your api key to authentication");
            }

            if (checkRateLimit(RATE_LIMIT, apiKey)) {
                return new CustomResponseMessage(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED,
                        "Please wait a while, you have reached your rate limit");
            }

            LogRequest temp = DependencyFactory.createLog(apiKey, "Post");

            Log log = new Log();
            log.setApiKey(temp.getApiKey());
            log.setStatus(temp.getStatus());
            log.setTimeRequest(temp.getTime_request());
            logService.addLog(log);

            if (userService.checkUserExist(userRequest.getEmail()) != null) {
                return new CustomResponseMessage(HttpStatus.METHOD_NOT_ALLOWED, "Email Already Used");
            }

            User user = new User();
            user.setUsername(userRequest.getUsername());
            user.setEmail(userRequest.getEmail());
            user.setPassword(userRequest.getPassword());
            user.setAlamat(userRequest.getAlamat());
            user.setNoHp(userRequest.getNoHp());
            userService.addUser(user);
            return new CustomResponseMessage(HttpStatus.CREATED, "User Has Been Created");
        } catch (Exception ex) {
            return new CustomResponseMessage(HttpStatus.BAD_REQUEST, ex.toString());
        }
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public CustomResponseMessage updateUser(@RequestHeader String apiKey,
            @RequestBody UserRequest userRequest) {
        try {
            if (!authApiKey(apiKey)) {
                return new CustomResponseMessage(HttpStatus.FORBIDDEN,
                        "Please use your api key to authentication");
            }

            if (checkRateLimit(RATE_LIMIT, apiKey)) {
                return new CustomResponseMessage(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED,
                        "Please wait a while, you have reached your rate limit");
            }

            LogRequest temp = DependencyFactory.createLog(apiKey, "Put");

            Log log = new Log();
            log.setApiKey(temp.getApiKey());
            log.setStatus(temp.getStatus());
            log.setTimeRequest(temp.getTime_request());
            logService.addLog(log);

            User userUpdate = userService.getUser(userRequest.getId());
            userUpdate.setUsername(userRequest.getUsername());
            userUpdate.setAlamat(userRequest.getAlamat());
            userUpdate.setEmail(userRequest.getEmail());
            userUpdate.setNoHp(userRequest.getNoHp());
            userUpdate.setPassword(userRequest.getPassword());
            userService.updateUser(userUpdate);
            return new CustomResponseMessage(HttpStatus.CREATED, "Update User successfuly");
        } catch (Exception ex) {
            return new CustomResponseMessage(HttpStatus.BAD_REQUEST, ex.toString());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
    public CustomResponseMessage deleteUser(@RequestHeader String apiKey,
            @RequestBody UserRequest userRequest) {
        try {
            if (!authApiKey(apiKey)) {
                return new CustomResponseMessage(HttpStatus.FORBIDDEN,
                        "Please use your api key to authentication");
            }
            LogRequest temp = DependencyFactory.createLog(apiKey, "Delete");
            
            if (checkRateLimit(RATE_LIMIT, apiKey)) {
                return new CustomResponseMessage(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED,
                        "Please wait a while, you have reached your rate limit");
            }
            Log log = new Log();
            log.setApiKey(temp.getApiKey());
            log.setStatus(temp.getStatus());
            log.setTimeRequest(temp.getTime_request());
            logService.addLog(log);

            userService.deleteUser(userRequest.getId());
            return new CustomResponseMessage(HttpStatus.CREATED, "Delete Successful");
        } catch (Exception ex) {
            return new CustomResponseMessage(HttpStatus.BAD_REQUEST, ex.toString());
        }
    }
}
