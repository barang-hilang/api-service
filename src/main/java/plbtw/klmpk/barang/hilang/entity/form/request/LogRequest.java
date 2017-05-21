/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.entity.form.request;

import java.util.Date;
import plbtw.klmpk.barang.hilang.entity.Developer;

/**
 *
 * @author CMDDJ
 */
public class LogRequest {
    private Long idLog;
    private Date time_request;
    private String status;
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
      public Long getIdLog() {
    return idLog;
  }

  public void setId(Long id) {
    this.idLog = id;
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
