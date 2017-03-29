/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import plbtw.klmpk.barang.hilang.entity.Barang;
import plbtw.klmpk.barang.hilang.entity.form.request.BarangRequest;
import plbtw.klmpk.barang.hilang.message.CustomResponseMessage;
import plbtw.klmpk.barang.hilang.service.BarangService;
import plbtw.klmpk.barang.hilang.service.KategoriBarangService;
import plbtw.klmpk.barang.hilang.service.UserService;

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

  @RequestMapping(method = RequestMethod.GET, produces = "application/json")
  public Collection<Barang> getAllBarang() {
    return barangService.getAllBarang();
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
      return new CustomResponseMessage(200, "Add Barang berhasil");
    } catch (Exception ex) {
      return new CustomResponseMessage(201, ex.toString());
    }

  }

}
