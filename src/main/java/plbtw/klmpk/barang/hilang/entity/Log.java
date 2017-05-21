/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author CMDDJ
 */
@Entity
@Table(name = "barang_hilang_log")
public class Log extends ResourceSupport {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idLog;

  private Date timeRequest;
  
  private String status;

    public Date getTimeRequest() {
        return timeRequest;
    }

    public void setTimeRequest(Date timeRequest) {
        this.timeRequest = timeRequest;
    }

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
