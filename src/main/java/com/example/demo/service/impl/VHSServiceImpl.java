package com.example.demo.service.impl;

import com.example.demo.dao.VHSRepository;
import com.example.demo.domain.VHS;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.rest.VHSDTO;
import com.example.demo.service.VHSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VHSServiceImpl implements VHSService {

    @Autowired
    private VHSRepository vhsRepo;

    @Override
    public List<VHS> listAll() {
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
        return newVHS;
    }

    @Override
    public VHS findByTitle(String title) {
        return vhsRepo.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("VHS with title '" + title + "' not found"));
    }
}
