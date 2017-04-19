/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.entity;

import java.sql.Date;
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
  private long id;

   @ManyToOne(cascade = CascadeType.MERGE)
  private Developer dev;
  
  private Date time_request;  
  
  
   public long getIdLog() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
  
   public Developer dev() {
    return dev;
  }

  public void setDev(Developer dev) {
    this.dev = dev;
  }
  
   public Date getTime_request() {
    return time_request;
  }

  public void setTime_request(Date time_request) {
    this.time_request = time_request;
  }
}
