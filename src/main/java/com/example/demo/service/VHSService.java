package com.example.demo.service;

import com.example.demo.domain.VHS;
import com.example.demo.rest.VHSDTO;

import java.util.List;

public interface VHSService {
    List<VHS> listAll();
    VHS createVhs(VHSDTO VhsDTO);
    VHS findByTitle(String title);
}
