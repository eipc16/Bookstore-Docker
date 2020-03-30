package org.pietrzakp.proxyservice.customizers;

import com.github.tomakehurst.wiremock.WireMockServer;
import ru.lanwen.wiremock.config.WiremockCustomizer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class BookServiceCustomizer implements WiremockCustomizer {

    @Override
    public void customize(WireMockServer server) {
        server.stubFor(put(urlMatching("/books/([0-9]+)"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withTransformers("response-template")
                        .withBody("{\n" +
                                "          \"serviceName\": \"book-service (PUT)\",\n" +
                                "          \"result\": \"SUCCESS\",\n" +
                                "          \"message\": \"Successfully put book with id: {{request.path.[1]}}!\"\n" +
                                "        }")));
        server.stubFor(put(urlMatching("/books/-([0-9]+)"))
                .willReturn(aResponse()
                        .withStatus(412)
                        .withHeader("Content-Type", "application/json")
                        .withTransformers("response-template")
                        .withBody("Book id cannot be less than zero!")));
    }
}
