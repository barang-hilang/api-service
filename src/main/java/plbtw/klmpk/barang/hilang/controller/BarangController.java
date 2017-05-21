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
import plbtw.klmpk.barang.hilang.entity.Barang;
import plbtw.klmpk.barang.hilang.entity.Log;
import plbtw.klmpk.barang.hilang.entity.form.request.BarangRequest;
import plbtw.klmpk.barang.hilang.entity.form.request.LogRequest;
import plbtw.klmpk.barang.hilang.message.CustomResponseMessage;
import plbtw.klmpk.barang.hilang.service.BarangService;
import plbtw.klmpk.barang.hilang.service.KategoriBarangService;
import plbtw.klmpk.barang.hilang.service.UserService;
import plbtw.klmpk.barang.hilang.service.DeveloperService;
import plbtw.klmpk.barang.hilang.service.LogService;
import plbtw.klmpk.barang.hilang.service.impl.DependencyFactory;

/**
 *
 * @author ALz
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/barang")
public class BarangController {

    private final int RATE_LIMIT = 10;
    @Autowired
    private BarangService barangService;
    @Autowired
    private KategoriBarangService kategoriBarangService;
    @Autowired
    private UserService userService;
    @Autowired
    private DeveloperService developerService;
    @Autowired
    private LogService logService;

    private boolean authApiKey(String token) {
        if (developerService.getDeveloperByApiKey(token) == null) {
            return false;
        }
        return true;
    }

    private boolean checkRateLimit(int rateLimit, String apiKey) {
        return (logService.rateLimit(apiKey) >= rateLimit) ? true : false;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public CustomResponseMessage /*Collection<Barang>*/ getAllBarang(@RequestHeader String apiKey) {
        try {

            if (!authApiKey(apiKey)) {
                return new CustomResponseMessage(HttpStatus.FORBIDDEN,
                        "Please use your api key to authentication");
            }
            LogRequest temp = DependencyFactory.createLog(apiKey, "Get");

            if (checkRateLimit(RATE_LIMIT, apiKey)) {
                return new CustomResponseMessage(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED,
                        "Please wait a while, you have reached your rate limit");
            }
            Log log = new Log();
            log.setApiKey(temp.getApiKey());
            log.setStatus(temp.getStatus());
            log.setTimeRequest(temp.getTime_request());
            logService.addLog(log);

            List<Barang> allBarang = (List<Barang>) barangService.getAllBarang();
            for (Barang barang : allBarang) {
                Link selfLink = linkTo(BarangController.class).withSelfRel();
                barang.add(selfLink);
            }
            CustomResponseMessage result = new CustomResponseMessage();
            result.add(linkTo(BarangController.class).withSelfRel());
            result.setHttpStatus(HttpStatus.FOUND);
            result.setMessage("Success");
            result.setResult(allBarang);
            return result;
        } catch (NullPointerException ex) {
            return new CustomResponseMessage(HttpStatus.NOT_FOUND,
                    "Data not found");
        } catch (Exception ex) {
            return new CustomResponseMessage(HttpStatus.FORBIDDEN,
                    ex.getMessage());
        }
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = "application/json")
    public CustomResponseMessage findBarang(@RequestHeader String apiKey, @PathVariable("id") long id) {
        try {
            if (!authApiKey(apiKey)) {
                return new CustomResponseMessage(HttpStatus.FORBIDDEN,
                        "Please use your api key to authentication");
            }
            LogRequest temp = DependencyFactory.createLog(apiKey, "Get");

            if (checkRateLimit(RATE_LIMIT, apiKey)) {
                return new CustomResponseMessage(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED,
                        "Please wait a while, you have reached your rate limit");
            }

            Log log = new Log();
            log.setApiKey(temp.getApiKey());
            log.setStatus(temp.getStatus());
            log.setTimeRequest(temp.getTime_request());
            logService.addLog(log);

            List<Barang> rsBarang = new ArrayList<>();
            Barang barang = barangService.getBarang(id);
            Link selfLink = linkTo(UserController.class).withSelfRel();
            barang.add(selfLink);
            rsBarang.add(barang);
            CustomResponseMessage result = new CustomResponseMessage();
            result.add(linkTo(BarangController.class).withSelfRel());
            result.setHttpStatus(HttpStatus.FOUND);
            result.setMessage("Success");
            result.setResult(rsBarang);
            return result;
        } catch (NullPointerException ex) {
            return new CustomResponseMessage(HttpStatus.NOT_FOUND,
                    "Data not found");
        } catch (Exception ex) {
            return new CustomResponseMessage(HttpStatus.BAD_REQUEST,
                    ex.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public CustomResponseMessage addBarang(@RequestHeader String apiKey, @RequestBody BarangRequest barangRequest) {
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

            Barang barang = new Barang();
            barang.setJumlah(barangRequest.getJumlahBarang());
            barang.setKategoriBarang(
                    kategoriBarangService.getKategoriBarang(barangRequest.getIdKategoriBarang()));
            barang.setNama(barangRequest.getNama());
            barang.setStatus(barangRequest.getStatus());
            barang.setUrl_image(barangRequest.getUrl_image());
            barang.setUser(userService.getUser(barangRequest.getIdUserPemilik()));
            barangService.addBarang(barang);
            return new CustomResponseMessage(HttpStatus.CREATED, "Insert Success");
        } catch (Exception ex) {
            return new CustomResponseMessage(HttpStatus.BAD_REQUEST, ex.toString());
        }

    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public CustomResponseMessage updateBarang(@RequestHeader String apiKey, @RequestBody BarangRequest barangRequest) {
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

            Barang barang = barangService.getBarang(barangRequest.getId());
            barang.setJumlah(barangRequest.getJumlahBarang());
            barang.setKategoriBarang(
                    kategoriBarangService.getKategoriBarang(barangRequest.getIdKategoriBarang()));
            barang.setNama(barangRequest.getNama());
            barang.setStatus(barangRequest.getStatus());
            barang.setUrl_image(barangRequest.getUrl_image());
            barang.setUser(userService.getUser(barangRequest.getIdUserPemilik()));
            barangService.updateBarang(barang);
            return new CustomResponseMessage(HttpStatus.CREATED, "Update Barang Successfull");
        } catch (NullPointerException ex) {
            return new CustomResponseMessage(HttpStatus.NOT_FOUND,
                    "Data not found");
        } catch (Exception ex) {
            return new CustomResponseMessage(HttpStatus.BAD_REQUEST, ex.toString());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
    public CustomResponseMessage deleteBarang(@RequestHeader String apiKey, @RequestBody BarangRequest barangRequest) {
        try {
            if (!authApiKey(apiKey)) {
                return new CustomResponseMessage(HttpStatus.FORBIDDEN,
                        "Please use your api key to authentication");
            }

            if (checkRateLimit(RATE_LIMIT, apiKey)) {
                return new CustomResponseMessage(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED,
                        "Please wait a while, you have reached your rate limit");
            }

            LogRequest temp = DependencyFactory.createLog(apiKey, "Delete");

            Log log = new Log();
            log.setApiKey(temp.getApiKey());
            log.setStatus(temp.getStatus());
            log.setTimeRequest(temp.getTime_request());
            logService.addLog(log);
            barangService.deleteBarang(barangRequest.getId());
            return new CustomResponseMessage(HttpStatus.CREATED, "Delete Barang successfull");
        } catch (NullPointerException ex) {
            return new CustomResponseMessage(HttpStatus.NOT_FOUND,
                    "Data not found");
        } catch (Exception ex) {
            return new CustomResponseMessage(HttpStatus.BAD_REQUEST, ex.toString());
        }
    }

}
