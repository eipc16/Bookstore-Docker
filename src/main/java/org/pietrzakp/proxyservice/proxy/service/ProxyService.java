package org.pietrzakp.proxyservice.proxy.service;

import org.pietrzakp.proxyservice.infrastructure.clients.BookServiceClient;
import org.pietrzakp.proxyservice.infrastructure.clients.LibraryServiceClient;
import org.pietrzakp.proxyservice.infrastructure.clients.UserServiceClient;
import org.pietrzakp.proxyservice.infrastructure.dto.BookDTO;
import org.pietrzakp.proxyservice.infrastructure.dto.MessageDTO;
import org.pietrzakp.proxyservice.infrastructure.dto.RentedBooksDTO;
import org.pietrzakp.proxyservice.infrastructure.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProxyService {

    private final BookServiceClient bookServiceClient;
    private final LibraryServiceClient libraryServiceClient;
    private final UserServiceClient userServiceClient;

    @Autowired
    public ProxyService(BookServiceClient bookServiceClient, LibraryServiceClient libraryServiceClient, UserServiceClient userServiceClient) {
        this.bookServiceClient = bookServiceClient;
        this.libraryServiceClient = libraryServiceClient;
        this.userServiceClient = userServiceClient;
    }

    public UserDTO getUser(Long id) {
        return userServiceClient.getUserById(id);
    }

    public MessageDTO addBook(BookDTO bookDTO) {
        return bookServiceClient.putBook(bookDTO);
    }

    public MessageDTO addBookRental(RentedBooksDTO rentedBooksDTO) {
        return libraryServiceClient.postBookRenting(rentedBooksDTO);
    }
}
