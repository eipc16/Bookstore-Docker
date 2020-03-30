package org.pietrzakp.proxyservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pietrzakp.proxyservice.customizers.LibraryServiceCustomizer;
import org.pietrzakp.proxyservice.extensions.WireMockTestExtension;
import org.pietrzakp.proxyservice.infrastructure.clients.LibraryServiceClient;
import org.pietrzakp.proxyservice.infrastructure.dto.MessageDTO;
import org.pietrzakp.proxyservice.infrastructure.dto.RentedBooksDTO;
import org.pietrzakp.proxyservice.infrastructure.exceptions.ExternalServiceException;
import org.pietrzakp.proxyservice.proxy.service.ProxyService;

@ExtendWith(WireMockTestExtension.class)
class ProxyLibraryServiceTests {

    private static final int SERVICE_PORT = 8011;
    private static final String SERVICE_HOST = "http://localhost";

    private ProxyService proxyService = new ProxyService(null, new LibraryServiceClient(SERVICE_HOST + ":" + SERVICE_PORT), null);

    @Test
    @WireMockTestExtension.Wiremock(customizer = LibraryServiceCustomizer.class, port = SERVICE_PORT)
    void assertThanRentalWasCorrectlySaved() {
        RentedBooksDTO rental = RentedBooksDTO.builder()
                .withBookId(10L)
                .withUserId(10L)
                .build();
        MessageDTO response = proxyService.addBookRental(rental);
        Assertions.assertEquals("SUCCESS", response.getResult(), "POST request was a success");
    }

    @Test
    @WireMockTestExtension.Wiremock(customizer = LibraryServiceCustomizer.class, port = SERVICE_PORT)
    void assertThatBookWithId5Throws412Exception() {
        RentedBooksDTO rental = RentedBooksDTO.builder()
                .withBookId(5L)
                .withUserId(10L)
                .build();
        ExternalServiceException ex = Assertions.assertThrows(ExternalServiceException.class,
                () -> proxyService.addBookRental(rental), "Throws external service exception");
        Assertions.assertEquals(412, ex.getStatus().value(), "Return 412 status code");
    }

    @Test
    @WireMockTestExtension.Wiremock(customizer = LibraryServiceCustomizer.class, port = SERVICE_PORT)
    void assertThatBookWithId5ReturnsCorrectMessage() {
        RentedBooksDTO rental = RentedBooksDTO.builder()
                .withBookId(5L)
                .withUserId(10L)
                .build();
        ExternalServiceException ex = Assertions.assertThrows(ExternalServiceException.class,
                () -> proxyService.addBookRental(rental), "Throws external service exception");
        Assertions.assertEquals("There is no book with id: 5", ex.getMessage(), "Correct exception message");
    }

    @Test
    @WireMockTestExtension.Wiremock(customizer = LibraryServiceCustomizer.class, port = SERVICE_PORT)
    void assertThatBookWithId1AndUserId1ReturnsCorrectMessage() {
        RentedBooksDTO rental = RentedBooksDTO.builder()
                .withBookId(1L)
                .withUserId(1L)
                .build();
        ExternalServiceException ex = Assertions.assertThrows(ExternalServiceException.class,
                () -> proxyService.addBookRental(rental), "Throws external service exception");
        Assertions.assertEquals("User with id: 1 already rented a book with id 1", ex.getMessage(), "Correct exception message");
    }
}
