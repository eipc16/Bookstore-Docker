package org.pietrzakp.proxyservice.proxy.adapters;

import org.pietrzakp.proxyservice.infrastructure.dto.BookDTO;
import org.pietrzakp.proxyservice.infrastructure.dto.MessageDTO;
import org.pietrzakp.proxyservice.infrastructure.dto.RentedBooksDTO;
import org.pietrzakp.proxyservice.infrastructure.dto.UserDTO;


public interface ProxyClientAdapter {
    UserDTO getUser(Long id);
    MessageDTO addBook(BookDTO bookDTO);
    MessageDTO addRentedBook(RentedBooksDTO rentedBooksDTO);
}
