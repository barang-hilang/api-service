/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import plbtw.klmpk.barang.hilang.entity.KategoriBarang;
import plbtw.klmpk.barang.hilang.entity.form.request.KategoriBarangRequest;
import plbtw.klmpk.barang.hilang.message.CustomResponseMessage;
import plbtw.klmpk.barang.hilang.service.KategoriBarangService;

/**
 *
 * @author ALz
 */
@RestController
@RequestMapping(value = "/api/v1/kategori/barang")
public class KategoriBarangController {
  @Autowired
  private KategoriBarangService kategoriBarangService;

  @RequestMapping(method = RequestMethod.GET, produces = "application/json")
  public Collection<KategoriBarang> getAllKategoriBarang() {
    return kategoriBarangService.getAllKategoriBarang();
  }

  @RequestMapping(method = RequestMethod.GET, value = "/find/{id}", produces = "application/json")
  public KategoriBarang getKategoriBarang(@PathVariable("id") long id) {
    KategoriBarang kategoriBarang = kategoriBarangService.getKategoriBarang(id);
    Link selfLink = linkTo(UserController.class).withSelfRel();
    kategoriBarang.add(selfLink);
    return kategoriBarang;
  }

  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public CustomResponseMessage addKategoriBarang(
      @RequestBody KategoriBarangRequest kategoriBarangRequest) {
    try {
      KategoriBarang kategoriBarang = new KategoriBarang();
      kategoriBarang.setJenis(kategoriBarangRequest.getJenis());
      kategoriBarangService.addKategoriBarang(kategoriBarang);
      return new CustomResponseMessage(HttpStatus.CREATED, "Kategori Barang berhasil di tambahkan");
    } catch (Exception ex) {
      return new CustomResponseMessage(HttpStatus.BAD_REQUEST, ex.toString());
    }

  }

  @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
  public CustomResponseMessage updateKategoriBarang(
      @RequestBody KategoriBarangRequest kategoriBarangRequest) {
    try {
      KategoriBarang kategoriBarang =
          kategoriBarangService.getKategoriBarang(kategoriBarangRequest.getId());
      kategoriBarang.setJenis(kategoriBarangRequest.getJenis());
      kategoriBarangService.updateKategoriBarang(kategoriBarang);
      return new CustomResponseMessage(HttpStatus.CREATED, "Update Successfull");
    } catch (Exception ex) {
      return new CustomResponseMessage(HttpStatus.BAD_REQUEST, ex.toString());
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
  public CustomResponseMessage deleteKategoriBarang(
      @RequestBody KategoriBarangRequest kategoriBarangRequest) {
    try {
      kategoriBarangService.deleteKategoriBarang(kategoriBarangRequest.getId());
      return new CustomResponseMessage(HttpStatus.CREATED, "Delete Kategori Barang Succesfull");
    } catch (Exception ex) {
      return new CustomResponseMessage(HttpStatus.BAD_REQUEST, ex.toString());
    }
  }
}
