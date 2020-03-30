package org.pietrzakp.proxyservice.customizers;

import com.github.tomakehurst.wiremock.WireMockServer;
import ru.lanwen.wiremock.config.WiremockCustomizer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class LibraryServiceCustomizer implements WiremockCustomizer {

    @Override
    public void customize(WireMockServer server) {
        server.stubFor(post("/rentals")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n" +
                                "          \"serviceName\": \"library-service (POST)\",\n" +
                                "          \"result\": \"SUCCESS\",\n" +
                                "          \"message\": \"Successfully added new book rental!\"\n" +
                                "        }")));
        server.stubFor(post("/rentals")
                .withRequestBody(equalToJson("{\n    \"bookId\": 5\n}", true, true))
                .willReturn(aResponse()
                        .withStatus(412)
                        .withHeader("Content-Type", "application/json")
                        .withBody("There is no book with id: 5")));
        server.stubFor(post("/rentals")
                .withRequestBody(equalToJson("{\n    \"userId\": 1,\n    \"bookId\": 1\n}", true, true))
                .willReturn(aResponse()
                        .withStatus(412)
                        .withHeader("Content-Type", "application/json")
                        .withBody("User with id: 1 already rented a book with id 1")));
    }
}
