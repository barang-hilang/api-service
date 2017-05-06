/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package plbtw.klmpk.barang.hilang.service.impl;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plbtw.klmpk.barang.hilang.entity.Developer;
import plbtw.klmpk.barang.hilang.repository.DeveloperRepository;
import plbtw.klmpk.barang.hilang.service.DeveloperService;

/**
 *
 * @author CMDDJ
 */
@Service
public class DeveloperServicImpl implements DeveloperService {

    @Autowired
    private DeveloperRepository devRepository;

    @Override
    public Collection getAllDevelopers() {
        List<Developer> result = new ArrayList<Developer>();
        result = devRepository.findAll();
        return result;
    }

    @Override
    public void addDeveloper(Developer dev) {
        devRepository.save(dev);
    }

    @Override
    public Developer getDeveloper(long id) {
        return devRepository.findOne(id);
    }

    @Override
    public void updateDeveloper(Developer devBaru) {
        devRepository.save(devBaru);
    }

    @Override
    public void deleteDeveloper(long id) {
        devRepository.delete(id);
    }

    @Override
    public Developer getDeveloperByApiKey(String token) {
        return devRepository.findByToken(token);
    }

    @Override
    public Developer getDeveloperByEmailAndPassword(String email, String password) {
        return devRepository.findByEmailAndPassword(email,password);
    }
}
