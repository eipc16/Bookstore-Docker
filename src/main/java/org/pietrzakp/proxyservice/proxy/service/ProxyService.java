package org.pietrzakp.proxyservice.proxy.service;

import org.pietrzakp.proxyservice.infrastructure.dto.BookDTO;
import org.pietrzakp.proxyservice.infrastructure.dto.MessageDTO;
import org.pietrzakp.proxyservice.infrastructure.dto.RentedBooksDTO;
import org.pietrzakp.proxyservice.infrastructure.dto.UserDTO;
import org.pietrzakp.proxyservice.proxy.adapters.LibraryClientAdapter;
import org.pietrzakp.proxyservice.proxy.adapters.ProxyClientAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProxyService {

    private ProxyClientAdapter proxyClientAdapter;

    public UserDTO getUser(Long id) {
        return proxyClientAdapter.getUser(id);
    }

    public MessageDTO addBook(BookDTO bookDTO) {
        return proxyClientAdapter.addBook(bookDTO);
    }

    public MessageDTO addBookRental(RentedBooksDTO rentedBooksDTO) {
        return proxyClientAdapter.addRentedBook(rentedBooksDTO);
    }

    @Autowired
    @Qualifier(LibraryClientAdapter.RESOURCE_NAME)
    public void setProxyClientAdapter(ProxyClientAdapter proxyClientAdapter) {
        this.proxyClientAdapter = proxyClientAdapter;
    }
}
