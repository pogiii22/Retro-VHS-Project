package com.example.demo.rest;

import com.example.demo.domain.VHS;
import com.example.demo.service.VHSService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vhs")
public class VHSController {
    private static final Logger log = LoggerFactory.getLogger(VHSController.class);

    @Autowired
    private VHSService VhsService;

    @GetMapping("")
    public List<VHS> listVHSs(HttpServletRequest request) {
        log.info( "[CONTROLLER] GET /api/vhs - request {}",  request.getRemoteAddr());
        return VhsService.listAll();
    }

    @GetMapping("find/{title}")
        public ResponseEntity<List<VHS>> findByTitle(@PathVariable String title, HttpServletRequest request){
        log.info("[CONTROLLER] GET /api/vhs/find/{} -RequestBody: {} request from {}",title, title, request.getRemoteAddr());
        List<VHS> vhs = VhsService.findByTitle(title);
        log.info("[CONTROLLER] Successfully found vhs Tape {} (HTTP {})",vhs, HttpStatus.OK.value());
        return ResponseEntity.ok(vhs);
    }

    @PostMapping("addvhs")
    public ResponseEntity<VHS> createVHS(@Valid @RequestBody VHSDTO VhsDto, HttpServletRequest request){
        log.info("[CONTROLLER] POST /api/vhs/addvhs -RequestBody: {} request from {}", VhsDto, request.getRemoteAddr());
        VHS saved = VhsService.createVhs(VhsDto);
        log.info("[CONTROLLER] Successfully created vhs Tape {} (HTTP {})",saved, HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
