package com.example.demo.service.impl;

import com.example.demo.dao.VHSRepository;
import com.example.demo.domain.VHS;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.rest.VHSDTO;
import com.example.demo.service.VHSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VHSServiceImpl implements VHSService {

    private static final Logger log = LoggerFactory.getLogger(VHSServiceImpl.class);


    @Autowired
    private VHSRepository vhsRepo;

    @Override
    public List<VHS> listAll() {
        log.info("[SERVICE] Listed all VHS tapes");
        return vhsRepo.findAll();
    }

    @Override
    public VHS createVhs(VHSDTO VhsDTO) {
        boolean alreadyExists = vhsRepo.existsByTitle(VhsDTO.getTitle());
        if (alreadyExists) {
            throw new DuplicateResourceException("VHS tape " + VhsDTO.getTitle() + " already exists!");
        }
        VHS newVHS = new VHS();
        newVHS.setTitle(VhsDTO.getTitle());
        newVHS.setGenre(VhsDTO.getGenre());
        newVHS.setReleaseYear(VhsDTO.getReleaseYear());
        vhsRepo.save(newVHS);
        log.info("[SERVICE] Added new VHS tape with title {}", VhsDTO.getTitle());
        return newVHS;
    }

    @Override
    public VHS findByTitle(String title) {
        log.info("[SERVICE] VHS tape with title {} searched for", title);
        return vhsRepo.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("VHS with title '" + title + "' not found"));
    }
}
