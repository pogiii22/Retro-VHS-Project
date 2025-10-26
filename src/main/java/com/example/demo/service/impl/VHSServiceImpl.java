package com.example.demo.service.impl;

import com.example.demo.dao.VHSRepository;
import com.example.demo.domain.VHS;
import com.example.demo.exception.ResourceNotAvailableException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.rest.VHSDTO;
import com.example.demo.service.VHSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VHSServiceImpl implements VHSService {

    private static final Logger log = LoggerFactory.getLogger(VHSServiceImpl.class);

    @Autowired
    private VHSRepository vhsRepo;

    @Override
    @Transactional(readOnly = true)
    public List<VHS> listAll() {
        log.info("[SERVICE] Listed all VHS tapes");
        return vhsRepo.findAll();
    }

    @Override
    @Transactional
    public VHS createVhs(VHSDTO VhsDTO) {
        VHS newVHS = new VHS();
        newVHS.setTitle(VhsDTO.getTitle());
        newVHS.setGenre(VhsDTO.getGenre());
        newVHS.setReleaseYear(VhsDTO.getReleaseYear());
        vhsRepo.save(newVHS);
        log.info("[SERVICE] Added new VHS tape with title {}", VhsDTO.getTitle());
        return newVHS;
    }

    @Override
    @Transactional(readOnly = true)
    public List<VHS> findByTitle(String title) {
        log.info("[SERVICE] VHS tape with title {} searched for", title);
        List<VHS> vhs = vhsRepo.findByTitle(title);
        if(vhs.isEmpty()){
            throw new ResourceNotFoundException("VHS with title '" + title + "' not found");
        } else{
            return vhs;
        }
    }

    @Override
    @Transactional
    public void saveVHS(VHS vhs) {
        log.info("[SERVICE] VHS saved in database: {}", vhs.toString());
        vhsRepo.save(vhs);
    }

    @Override
    @Transactional(readOnly = true)
    public VHS findFirstByTitleAndRentedFalse(String title){
        return vhsRepo.findFirstByTitleAndRentedFalse(title)
                .orElseThrow(() -> new ResourceNotAvailableException(
                        "All VHS tapes with title " + title + " are taken!"
                ));
    }
}
