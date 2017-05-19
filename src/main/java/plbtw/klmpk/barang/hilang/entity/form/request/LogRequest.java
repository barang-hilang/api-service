/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.entity.form.request;

import java.sql.Date;
import plbtw.klmpk.barang.hilang.entity.Developer;

/**
 *
 * @author CMDDJ
 */
public class LogRequest {
    private long idLog;
    private long idDev;
    private Date time_request;
    private String status;
    
      public long getIdLog() {
    return idLog;
  }

  public void setId(long id) {
    this.idLog = idLog;
  }
  
   public long getIdDev() {
    return idDev;
  }

  public void setDev(long idDev) {
    this.idDev = idDev;
  }
  
   public Date getTime_request() {
    return time_request;
  }

  public void setTime_request(Date time_request) {
    this.time_request = time_request;
  }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
