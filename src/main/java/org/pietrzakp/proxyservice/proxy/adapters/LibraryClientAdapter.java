package org.pietrzakp.proxyservice.proxy.adapters;

import org.pietrzakp.proxyservice.infrastructure.clients.BookServiceClient;
import org.pietrzakp.proxyservice.infrastructure.clients.LibraryServiceClient;
import org.pietrzakp.proxyservice.infrastructure.clients.UserServiceClient;
import org.pietrzakp.proxyservice.infrastructure.dto.BookDTO;
import org.pietrzakp.proxyservice.infrastructure.dto.MessageDTO;
import org.pietrzakp.proxyservice.infrastructure.dto.RentedBooksDTO;
import org.pietrzakp.proxyservice.infrastructure.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Resource(name = LibraryClientAdapter.RESOURCE_NAME)
public class LibraryClientAdapter implements ProxyClientAdapter {

    public static final String RESOURCE_NAME = "libraryClientAdapter";

    private final BookServiceClient bookServiceClient;
    private final LibraryServiceClient libraryServiceClient;
    private final UserServiceClient userServiceClient;

    @Autowired
    public LibraryClientAdapter(BookServiceClient bookServiceClient, LibraryServiceClient libraryServiceClient, UserServiceClient userServiceClient) {
        this.bookServiceClient = bookServiceClient;
        this.libraryServiceClient = libraryServiceClient;
        this.userServiceClient = userServiceClient;
    }

    @Override
    public UserDTO getUser(Long id) {
        return userServiceClient.getUserById(id);
    }

    @Override
    public MessageDTO addBook(BookDTO bookDTO) {
        return bookServiceClient.putBook(bookDTO);
    }

    @Override
    public MessageDTO addRentedBook(RentedBooksDTO rentedBooksDTO) {
        return libraryServiceClient.postBookRenting(rentedBooksDTO);
    }
}
