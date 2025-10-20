package com.example.demo.service.impl;

import com.example.demo.dao.VHSRepository;
import com.example.demo.domain.VHS;
import com.example.demo.rest.VHSDTO;
import com.example.demo.service.VHSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
        Assert.notNull(VhsDTO, "VHS object must be given");
        Assert.notNull(VhsDTO.getTitle(), "VHS title must not be null");
        boolean alreadyExists = VHSRepo.existsByTitle(VhsDTO.getTitle());
        Assert.isTrue(!alreadyExists, "This VHS already exists in database");
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
