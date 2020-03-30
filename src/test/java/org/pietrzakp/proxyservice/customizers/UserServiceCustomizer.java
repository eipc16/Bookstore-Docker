package org.pietrzakp.proxyservice.customizers;

import com.github.tomakehurst.wiremock.WireMockServer;
import ru.lanwen.wiremock.config.WiremockCustomizer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class UserServiceCustomizer implements WiremockCustomizer {
    @Override
    public void customize(WireMockServer server) {
        server.stubFor(get(urlMatching("/users/([0-9]+)"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withTransformers("response-template")
                        .withBody("{\n" +
                                "          \"id\": {{request.path.[1]}},\n" +
                                "          \"firstName\": \"Przemysław\",\n" +
                                "          \"lastName\": \"Pietrzak\",\n" +
                                "          \"address\": {\n" +
                                "            \"country\": \"Polska\",\n" +
                                "            \"city\": \"Wrocław\",\n" +
                                "            \"street\": \"Ulicowa\",\n" +
                                "            \"streetNumber\": 5\n" +
                                "          }\n" +
                                "        }")));
    }
}
