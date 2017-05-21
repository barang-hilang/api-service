/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.service.impl;

import java.util.Date;
import plbtw.klmpk.barang.hilang.entity.form.request.LogRequest;

/**
 *
 * @author D
 */
public class DependencyFactory {
    public static LogRequest createLog(String apiKey,String status){
        LogRequest temp = new LogRequest();
        temp.setApiKey(apiKey);
        temp.setStatus(status);
        temp.setTime_request(new Date());
        return temp;
    }
}
