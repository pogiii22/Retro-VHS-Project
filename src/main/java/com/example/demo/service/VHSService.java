package com.example.demo.service;

import com.example.demo.domain.VHS;
import com.example.demo.rest.VHSDTO;

import java.util.List;
import java.util.Optional;

public interface VHSService {
    List<VHS> listAll();
    VHS createVhs(VHSDTO VhsDTO);
    List<VHS> findByTitle(String title);
    void saveVHS(VHS vhs);
    VHS findFirstByTitleAndRentedFalse(String title);
}
