package org.pietrzakp.proxyservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pietrzakp.proxyservice.customizers.UserNotFoundUserServiceCustomizer;
import org.pietrzakp.proxyservice.customizers.UserServiceCustomizer;
import org.pietrzakp.proxyservice.extensions.WireMockTestExtension;
import org.pietrzakp.proxyservice.infrastructure.clients.UserServiceClient;
import org.pietrzakp.proxyservice.infrastructure.dto.AddressDTO;
import org.pietrzakp.proxyservice.infrastructure.dto.UserDTO;
import org.pietrzakp.proxyservice.infrastructure.exceptions.ExternalServiceException;
import org.pietrzakp.proxyservice.proxy.service.ProxyService;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@ExtendWith(WireMockTestExtension.class)
class ProxyUserServiceTests {

    private static final int SERVICE_PORT = 8010;
    private static final String SERVICE_HOST = "http://localhost";
    private static final Long NON_EXISTING_USER_ID = 1000L;

    private ProxyService proxyService = new ProxyService(null, null, new UserServiceClient(SERVICE_HOST + ":" + SERVICE_PORT));

    @Test
    @WireMockTestExtension.Wiremock(customizer = UserServiceCustomizer.class, port = SERVICE_PORT)
    void assertThatReturnsCorrectUser() {
        UserDTO expectedUser = UserDTO.builder()
                .withId(5L)
                .withFirstName("Przemysław")
                .withLastName("Pietrzak")
                .withAddress(AddressDTO.builder()
                        .withCountry("Polska")
                        .withCity("Wrocław")
                        .withStreet("Ulicowa")
                        .withStreetNumber(5)
                        .build())
                .build();
        UserDTO foundUser = proxyService.getUser(5L);
        Assertions.assertEquals(expectedUser, foundUser, "Returned user is the same");
    }

    @Test
    @WireMockTestExtension.Wiremock(customizer = UserServiceCustomizer.class, port = SERVICE_PORT)
    void assertThanAlwaysReturnsCorrectId() {
        List<UserDTO> users = LongStream.range(1, 100).boxed()
                .map(id -> UserDTO.builder().withId(id).build())
                .collect(Collectors.toList());
        for (UserDTO user : users) {
            Assertions.assertEquals(user.getId(), proxyService.getUser(user.getId()).getId(), "Returned id is the same");
        }
    }

    @Test
    @WireMockTestExtension.Wiremock(customizer = UserNotFoundUserServiceCustomizer.class, port = SERVICE_PORT)
    void assertThanNonExistingUserThrowsException() {
        Assertions.assertThrows(ExternalServiceException.class, () -> proxyService.getUser(NON_EXISTING_USER_ID),
                MessageFormat.format("Fetching user with {0} throws external service exception", NON_EXISTING_USER_ID));
    }

    @Test
    @WireMockTestExtension.Wiremock(customizer = UserNotFoundUserServiceCustomizer.class, port = SERVICE_PORT)
    void assertThanNonExistingUserThrows412Exception() {
        ExternalServiceException ex = Assertions.assertThrows(ExternalServiceException.class,
                () -> proxyService.getUser(NON_EXISTING_USER_ID), "Throws correct exception");
        Assertions.assertEquals(412, ex.getStatus().value(), "Throws 412 exception");
    }

    @Test
    @WireMockTestExtension.Wiremock(customizer = UserNotFoundUserServiceCustomizer.class, port = SERVICE_PORT)
    void assertThanNonExistingUserThrowsExceptionFromUsersService() {
        ExternalServiceException ex = Assertions.assertThrows(ExternalServiceException.class,
                () -> proxyService.getUser(NON_EXISTING_USER_ID), "Throws correct exception");
        Assertions.assertEquals("users-service", ex.getServiceName(), "Throws users-service exception");
    }
}
