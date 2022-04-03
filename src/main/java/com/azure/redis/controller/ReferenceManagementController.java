package com.azure.redis.controller;

import com.azure.redis.dto.ReferenceType;
import com.azure.redis.service.ReferenceManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("reference-management")
@RequiredArgsConstructor
public class ReferenceManagementController {

    private final ReferenceManagementService referenceManagementService;

    @PostMapping("primary")
    public ResponseEntity<Object> addPrimaryRefType(@RequestBody ReferenceType referenceType) {
        var response = referenceManagementService.addPrimaryReferenceType(referenceType);
        return ResponseEntity.status(CREATED).body(response);
    }

    @GetMapping("primary")
    public ResponseEntity<Object> getPrimaryRefType() {
        var response = referenceManagementService.getPrimaryRefTypes();
        return ResponseEntity.status(OK).body(response);
    }
}
