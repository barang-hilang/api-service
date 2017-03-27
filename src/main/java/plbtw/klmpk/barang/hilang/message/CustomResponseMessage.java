/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.message;

/**
 *
 * @author ALz
 */
public class CustomResponseMessage {
  private long HttpStatus;
  private String message;


  public CustomResponseMessage() {}

  public CustomResponseMessage(long HttpStatus, String message) {
    this.HttpStatus = HttpStatus;
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public long getHttpStatus() {
    return HttpStatus;
  }

  public void setHttpStatus(long HttpStatus) {
    this.HttpStatus = HttpStatus;
  }


}
