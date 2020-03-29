package org.pietrzakp.proxyservice.proxy.controller;

import org.pietrzakp.proxyservice.proxy.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyController {

    private final ProxyService proxyService;

    @Autowired
    public ProxyController(ProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @GetMapping(path = "/")
    public ResponseEntity<?> getRequest() {
        return ResponseEntity.ok(proxyService.get());
    }
}
