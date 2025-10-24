package com.example.demo.rest;

import com.example.demo.domain.VHS;
import com.example.demo.service.VHSService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vhs")
public class VHSController {
    @Autowired
    private VHSService VhsService;

    @GetMapping("")
    public List<VHS> listVHSs() {
        return VhsService.listAll();
    }

    @GetMapping("find/{title}")
        public ResponseEntity<List<VHS>> findByTitle(@PathVariable String title){
            List<VHS> vhs = VhsService.findByTitle(title);
            return ResponseEntity.ok(vhs);
        }

    @PostMapping("addvhs")
    public ResponseEntity<VHS> createVHS(@Valid @RequestBody VHSDTO VhsDto){
        VHS saved = VhsService.createVhs(VhsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
