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
import plbtw.klmpk.barang.hilang.entity.Pelaporan;
import plbtw.klmpk.barang.hilang.entity.form.request.PelaporanRequest;
import plbtw.klmpk.barang.hilang.message.CustomResponseMessage;
import plbtw.klmpk.barang.hilang.service.BarangService;
import plbtw.klmpk.barang.hilang.service.PelaporanService;
import plbtw.klmpk.barang.hilang.service.UserService;

/**
 *
 * @author ALz
 */
@RestController
@RequestMapping(value = "/api/v1/pelaporan")
public class PelaporanController {
  @Autowired
  PelaporanService pelaporanService;
  @Autowired
  BarangService barangService;
  @Autowired
  UserService userService;

  @RequestMapping(method = RequestMethod.GET, produces = "application/json")
  public Collection<Pelaporan> getAllPelaporan() {
    return pelaporanService.getAllPelaporan();
  }

  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public CustomResponseMessage addPelaporan(@RequestBody PelaporanRequest pelaporanRequest) {
    try {
      Pelaporan pelaporan = new Pelaporan();
      pelaporan.setBarang(barangService.getBarang(pelaporanRequest.getIdBarang()));
      pelaporan.setTanggalHilang(pelaporanRequest.getTanggalHilang());
      pelaporan.setTempatHilang(pelaporanRequest.getTempatHilang());
      pelaporanService.addPelaporan(pelaporan);
      return new CustomResponseMessage(200, "Pelaporan has been added");
    } catch (Exception ex) {
      return new CustomResponseMessage(201, ex.toString());
    }
  }


}
