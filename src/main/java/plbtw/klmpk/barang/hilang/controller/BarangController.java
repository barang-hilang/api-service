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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import plbtw.klmpk.barang.hilang.entity.Barang;
import plbtw.klmpk.barang.hilang.entity.form.request.BarangRequest;
import plbtw.klmpk.barang.hilang.message.CustomResponseMessage;
import plbtw.klmpk.barang.hilang.service.BarangService;
import plbtw.klmpk.barang.hilang.service.KategoriBarangService;
import plbtw.klmpk.barang.hilang.service.UserService;
import plbtw.klmpk.barang.hilang.service.DeveloperService;

/**
 *
 * @author ALz
 */
@RestController
@RequestMapping(value = "/api/v1/barang")
public class BarangController {
  @Autowired
  private BarangService barangService;
  @Autowired
  private KategoriBarangService kategoriBarangService;
  @Autowired
  private UserService userService;
  @Autowired
  private DeveloperService developerService;
  
  private boolean authApiKey(String token) {
    if (developerService.getDeveloperByApiKey(token) == null || token.equalsIgnoreCase("") || token==null)
        return false;
    return true;
  }

  @RequestMapping(method = RequestMethod.GET, produces = "application/json")
  public CustomResponseMessage /*Collection<Barang>*/ getAllBarang(@RequestHeader String apiKey) {
      try {
      
        if (!authApiKey(apiKey)) {
          return new CustomResponseMessage(HttpStatus.FORBIDDEN,
              "Please use your api key to authentication");
        }
        List<Barang> allBarang=(List<Barang>) barangService.getAllBarang();
        for(Barang barang : allBarang){
            Link selfLink=linkTo(BarangController.class).withSelfRel();
            barang.add(selfLink);
        }
        CustomResponseMessage result=new CustomResponseMessage();
        result.add(linkTo(BarangController.class).withSelfRel());
        result.setHttpStatus(HttpStatus.ACCEPTED);
        result.setMessage("Success");
        result.setResult(allBarang);
        return result;
    } catch (Exception ex) {
      return new CustomResponseMessage(HttpStatus.FORBIDDEN,
          "Please use your api key to authentication");
    }
  }

  @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = "application/json")
  public Barang findBarang(@PathVariable("id") long id) {
    Barang barang = barangService.getBarang(id);
    Link selfLink = linkTo(UserController.class).withSelfRel();
    barang.add(selfLink);
    return barang;
  }

  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public CustomResponseMessage addBarang(@RequestBody BarangRequest barangRequest) {
    try {
      Barang barang = new Barang();
      barang.setJumlah(barangRequest.getJumlahBarang());
      barang.setKategoriBarang(
          kategoriBarangService.getKategoriBarang(barangRequest.getIdKategoriBarang()));
      barang.setNama(barangRequest.getNama());
      barang.setStatus(barangRequest.getStatus());
      barang.setUrl_image(barangRequest.getUrl_image());
      barang.setUser(userService.getUser(barangRequest.getIdUserPemilik()));
      return new CustomResponseMessage(HttpStatus.CREATED, "Add Barang berhasil");
    } catch (Exception ex) {
      return new CustomResponseMessage(HttpStatus.BAD_REQUEST, ex.toString());
    }

  }

  @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
  public CustomResponseMessage updateBarang(@RequestBody BarangRequest barangRequest) {
    try {
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
    } catch (Exception ex) {
      return new CustomResponseMessage(HttpStatus.BAD_REQUEST, ex.toString());
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
  public CustomResponseMessage deleteBarang(@RequestBody BarangRequest barangRequest) {
    try {
      barangService.deleteBarang(barangRequest.getId());
      return new CustomResponseMessage(HttpStatus.CREATED, "Delete Barang successfull");
    } catch (Exception ex) {
      return new CustomResponseMessage(HttpStatus.BAD_REQUEST, ex.toString());
    }
  }

}
