package com.example.demo.service.impl;

import com.example.demo.dao.VHSRepository;
import com.example.demo.domain.VHS;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.rest.VHSDTO;
import com.example.demo.service.VHSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VHSServiceImpl implements VHSService {

    @Autowired
    private VHSRepository VHSRepo;

    @Override
    public List<VHS> listAll() {
        return VHSRepo.findAll();
    }

    @Override
    public VHS createVhs(VHSDTO VhsDTO) {
        boolean alreadyExists = VHSRepo.existsByTitle(VhsDTO.getTitle());
        if (alreadyExists) {
            throw new DuplicateResourceException("VHS tape " + VhsDTO.getTitle() + " already exists!");
        }
        VHS newVHS = new VHS();
        newVHS.setTitle(VhsDTO.getTitle());
        newVHS.setGenre(VhsDTO.getGenre());
        newVHS.setReleaseYear(VhsDTO.getReleaseYear());
        VHSRepo.save(newVHS);
        return newVHS;
    }

    @Override
    public VHS findByTitle(String title) {
        return VHSRepo.findByTitle(title);
    }
}
