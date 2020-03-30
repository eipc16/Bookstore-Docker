package org.pietrzakp.proxyservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pietrzakp.proxyservice.customizers.BookServiceCustomizer;
import org.pietrzakp.proxyservice.extensions.WireMockTestExtension;
import org.pietrzakp.proxyservice.infrastructure.clients.BookServiceClient;
import org.pietrzakp.proxyservice.infrastructure.dto.BookDTO;
import org.pietrzakp.proxyservice.infrastructure.dto.MessageDTO;
import org.pietrzakp.proxyservice.infrastructure.exceptions.ExternalServiceException;
import org.pietrzakp.proxyservice.proxy.service.ProxyService;

@ExtendWith(WireMockTestExtension.class)
class ProxyBookServiceTests {

    private static final int SERVICE_PORT = 8012;
    private static final String SERVICE_HOST = "http://localhost";

    private ProxyService proxyService = new ProxyService(new BookServiceClient(SERVICE_HOST + ":" + SERVICE_PORT), null, null);

    @Test
    @WireMockTestExtension.Wiremock(customizer = BookServiceCustomizer.class, port = SERVICE_PORT)
    void assertThatBookWasPutCorrectly() {
        BookDTO book = BookDTO.builder()
                .withId(1L)
                .withAuthorName("New book")
                .withBookName("Example author")
                .build();
        MessageDTO message = proxyService.addBook(book);
        Assertions.assertEquals("SUCCESS", message.getResult(), "Successful PUT request");
    }

    @Test
    @WireMockTestExtension.Wiremock(customizer = BookServiceCustomizer.class, port = SERVICE_PORT)
    void assertThatNegativeIdThrows412Exception() {
        BookDTO book = BookDTO.builder()
                .withId(-1L)
                .withAuthorName("New book")
                .withBookName("Example author")
                .build();
        ExternalServiceException ex = Assertions.assertThrows(ExternalServiceException.class, () -> proxyService.addBook(book), "Negative id returns exception");
        Assertions.assertEquals(412, ex.getStatus().value(), "Exception with status 412 thrown");
    }

    @Test
    @WireMockTestExtension.Wiremock(customizer = BookServiceCustomizer.class, port = SERVICE_PORT)
    void assertThatNegativeIdThrowsExceptionFromBookService() {
        BookDTO book = BookDTO.builder()
                .withId(-1L)
                .withAuthorName("New book")
                .withBookName("Example author")
                .build();
        ExternalServiceException ex = Assertions.assertThrows(ExternalServiceException.class, () -> proxyService.addBook(book), "Negative id returns exception");
        Assertions.assertEquals("book-service", ex.getServiceName(), "Exception thrown in book-service");
    }
}
