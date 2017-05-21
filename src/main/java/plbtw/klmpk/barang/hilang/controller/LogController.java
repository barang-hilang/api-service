/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import plbtw.klmpk.barang.hilang.entity.Log;
import plbtw.klmpk.barang.hilang.entity.form.request.LogRequest;
import plbtw.klmpk.barang.hilang.message.CustomResponseMessage;
import plbtw.klmpk.barang.hilang.service.DeveloperService;
import plbtw.klmpk.barang.hilang.service.LogService;
import plbtw.klmpk.barang.hilang.service.LogService;

/**
 *
 * @author CMDDJ
 */
@RestController
@RequestMapping(value = "/api/v1/log")
public class LogController {
  @Autowired
  private LogService logService;

  @RequestMapping(method = RequestMethod.GET, produces = "application/json")
  public List<Log> getAllLog() {
    return logService.getAll();
  }
  
  @RequestMapping(value = "/check/rateLimit",method = RequestMethod.GET,produces = "application/json")
  public Long checkTotalReq(@RequestHeader String apiKey){
      return logService.rateLimit(apiKey);
  }
  
  @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = "application/json")
  public Log findLog(@PathVariable("id") long id) {
    Log log = logService.getLog(id);
    Link selfLink = linkTo(LogController.class).withSelfRel();
    log.add(selfLink);
    return log;
  }

  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public CustomResponseMessage addLog(@RequestBody LogRequest logRequest) {
    try {
      Log log = new Log();
      log.setId(logRequest.getIdLog());
      log.setTimeRequest(logRequest.getTime_request());
      log.setStatus("");

      return new CustomResponseMessage(HttpStatus.CREATED, "Add Log berhasil");
    } catch (Exception ex) {
      return new CustomResponseMessage(HttpStatus.BAD_REQUEST, ex.toString());
    }

  }

  @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
  public CustomResponseMessage deleteLog(@RequestBody LogRequest logRequest) {
    try {
      logService.deleteLog(logRequest.getIdLog());
      return new CustomResponseMessage(HttpStatus.CREATED, "Delete log successfull");
    } catch (Exception ex) {
      return new CustomResponseMessage(HttpStatus.BAD_REQUEST, ex.toString());
    }
  }
}
