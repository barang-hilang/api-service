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
import plbtw.klmpk.barang.hilang.entity.Pelaporan;
import plbtw.klmpk.barang.hilang.entity.form.request.LogRequest;
import plbtw.klmpk.barang.hilang.entity.form.request.PelaporanRequest;
import plbtw.klmpk.barang.hilang.message.CustomResponseMessage;
import plbtw.klmpk.barang.hilang.service.BarangService;
import plbtw.klmpk.barang.hilang.service.DeveloperService;
import plbtw.klmpk.barang.hilang.service.LogService;
import plbtw.klmpk.barang.hilang.service.PelaporanService;
import plbtw.klmpk.barang.hilang.service.UserService;
import plbtw.klmpk.barang.hilang.service.impl.DependencyFactory;

/**
 *
 * @author ALz
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/pelaporan")
public class PelaporanController {

    private final int RATE_LIMIT = 10;
    @Autowired
    PelaporanService pelaporanService;
    @Autowired
    BarangService barangService;
    @Autowired
    UserService userService;
    @Autowired
    DeveloperService developerService;
    @Autowired
    LogService logService;

    private boolean authApiKey(String token) {
        if (developerService.getDeveloperByApiKey(token) == null || token.equalsIgnoreCase("") || token == null) {
            return false;
        }
        return true;
    }

    private boolean checkRateLimit(int rateLimit, String apiKey) {
        return (logService.rateLimit(apiKey) >= rateLimit) ? true : false;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public CustomResponseMessage getAllPelaporan(@RequestHeader String apiKey) {

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

        CustomResponseMessage result = new CustomResponseMessage();
        result.add(linkTo(PelaporanController.class).withSelfRel());
        result.setHttpStatus(HttpStatus.ACCEPTED);
        result.setMessage("Success");
        result.setResult(pelaporanService.getAllPelaporan());
        return result;

    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = "application/json")
    public CustomResponseMessage getPelaporan(@PathVariable("id") long id, @RequestHeader String apiKey) {
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

        Pelaporan pelaporan = pelaporanService.getPelaporan(id);
        Link selfLink = linkTo(UserController.class).withSelfRel();
        pelaporan.add(selfLink);

        List<Pelaporan> laporan = new ArrayList<>();
        laporan.add(pelaporan);

        CustomResponseMessage result = new CustomResponseMessage();
        result.add(linkTo(PelaporanController.class).withSelfRel());
        result.setHttpStatus(HttpStatus.ACCEPTED);
        result.setMessage("Success");
        result.setResult(laporan);

        return result;
    }

    @RequestMapping(value = "/pelapor/{id}", method = RequestMethod.GET, produces = "application/json")
    public CustomResponseMessage getPelaporanByPelapor(@PathVariable("id") long id, @RequestHeader String apiKey) {
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

        Pelaporan pelaporan = pelaporanService.getPelaporanByPelapor(id);
        Link selfLink = linkTo(UserController.class).withSelfRel();
        pelaporan.add(selfLink);

        List<Pelaporan> laporan = new ArrayList<>();
        laporan.add(pelaporan);

        CustomResponseMessage result = new CustomResponseMessage();
        result.add(linkTo(PelaporanController.class).withSelfRel());
        result.setHttpStatus(HttpStatus.ACCEPTED);
        result.setMessage("Success");
        result.setResult(laporan);

        return result;
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public CustomResponseMessage addPelaporan(@RequestBody PelaporanRequest pelaporanRequest, @RequestHeader String apiKey) {
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

            Pelaporan pelaporan = new Pelaporan();
            pelaporan.setBarang(barangService.getBarang(pelaporanRequest.getIdBarang()));
            pelaporan.setTanggalHilang(pelaporanRequest.getTanggalHilang());
            pelaporan.setTempatHilang(pelaporanRequest.getTempatHilang());
            pelaporanService.addPelaporan(pelaporan);

            return new CustomResponseMessage(HttpStatus.CREATED, "Pelaporan has been added");
        } catch (Exception ex) {
            return new CustomResponseMessage(HttpStatus.BAD_REQUEST, ex.toString());
        }
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public CustomResponseMessage updatePelaporan(@RequestBody PelaporanRequest pelaporanRequest, @RequestHeader String apiKey) {
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

            Pelaporan pelaporan = pelaporanService.getPelaporan(pelaporanRequest.getIdPelaporan());
            pelaporan.setBarang(barangService.getBarang(pelaporanRequest.getIdBarang()));
            pelaporan.setKeterangan(pelaporanRequest.getKeterangan());
            pelaporan.setPelapor(userService.getUser(pelaporanRequest.getIdPelapor()));
            pelaporan.setTanggalDitemukan(pelaporanRequest.getTanggalDitemukan());
            pelaporan.setTanggalHilang(pelaporanRequest.getTanggalHilang());
            pelaporan.setTempatDitemukan(pelaporanRequest.getTempatDitemukan());
            pelaporan.setTempatHilang(pelaporanRequest.getTempatHilang());
            pelaporanService.updatePelaporan(pelaporan);
            return new CustomResponseMessage(HttpStatus.CREATED, "Pelaporan berhasil di buat");
        } catch (Exception ex) {
            return new CustomResponseMessage(HttpStatus.BAD_REQUEST, ex.toString());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = "applicaiton/json")
    public CustomResponseMessage deletePelaporan(@RequestBody PelaporanRequest pelaporanRequest, @RequestHeader String apiKey) {
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
            pelaporanService.deletePelaporan(pelaporanRequest.getIdPelaporan());
            return new CustomResponseMessage(HttpStatus.CREATED, "Pelaporan berhasil dihapus");
        } catch (Exception ex) {
            return new CustomResponseMessage(HttpStatus.CREATED, ex.toString());
        }
    }

}
