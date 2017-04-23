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

  @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = "application/json")
  public Pelaporan getPelaporan(@PathVariable("id") long id) {
    Pelaporan pelaporan = pelaporanService.getPelaporan(id);
    Link selfLink = linkTo(UserController.class).withSelfRel();
    pelaporan.add(selfLink);
    return pelaporan;
  }

  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public CustomResponseMessage addPelaporan(@RequestBody PelaporanRequest pelaporanRequest) {
    try {
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
  public CustomResponseMessage updatePelaporan(@RequestBody PelaporanRequest pelaporanRequest) {
    try {
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
  public CustomResponseMessage deletePelaporan(@RequestBody PelaporanRequest pelaporanRequest) {
    try {
      pelaporanService.deletePelaporan(pelaporanRequest.getIdPelaporan());
      return new CustomResponseMessage(HttpStatus.CREATED, "Pelaporan berhasil dihapus");
    } catch (Exception ex) {
      return new CustomResponseMessage(HttpStatus.CREATED, ex.toString());
    }
  }

}
