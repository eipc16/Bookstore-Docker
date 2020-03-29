package org.pietrzakp.proxyservice.proxy.controller;

import org.pietrzakp.proxyservice.infrastructure.dto.BookDTO;
import org.pietrzakp.proxyservice.infrastructure.dto.MessageDTO;
import org.pietrzakp.proxyservice.infrastructure.dto.RentedBooksDTO;
import org.pietrzakp.proxyservice.infrastructure.dto.UserDTO;
import org.pietrzakp.proxyservice.proxy.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library")
public class ProxyController {

    private final ProxyService proxyService;

    @Autowired
    public ProxyController(ProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @GetMapping
    public UserDTO getUser(@RequestParam Long id) {
        return proxyService.getUser(id);
    }

    @PutMapping
    private MessageDTO addBook(@RequestBody BookDTO bookDTO) {
        return proxyService.addBook(bookDTO);
    }

    @PostMapping
    private MessageDTO addBookRental(@RequestBody RentedBooksDTO rentedBooksDTO) {
        return proxyService.addBookRental(rentedBooksDTO);
    }
}
