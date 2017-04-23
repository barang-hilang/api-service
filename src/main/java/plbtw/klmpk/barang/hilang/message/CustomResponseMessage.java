/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.message;

import java.util.Collection;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;

/**
 *
 * @author ALz
 */
public class CustomResponseMessage extends ResourceSupport {

  private String message;

  private HttpStatus HttpStatus;

  private Collection<?> result;

  public CustomResponseMessage() {}

  public CustomResponseMessage(HttpStatus httpStatus, String message) {
    this.HttpStatus = httpStatus;
    this.message = message;
  }

  public CustomResponseMessage(HttpStatus httpStatus, String message, Collection<?> objek) {
    this.HttpStatus = httpStatus;
    this.message = message;
    this.result = objek;
  }


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public HttpStatus getHttpStatus() {
    return HttpStatus;
  }

  public void setHttpStatus(HttpStatus HttpStatus) {
    this.HttpStatus = HttpStatus;
  }

  public Collection<?> getResult() {
    return result;
  }

  public void setResult(Collection<?> objek) {
    this.result = objek;
  }

}
