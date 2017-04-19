/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.service;
import plbtw.klmpk.barang.hilang.entity.Log;

/**
 *
 * @author CMDDJ
 */
public interface LogService {
  Log getLog(long id);

  void addLog(Log log);

  void deleteLog(long id);
}
