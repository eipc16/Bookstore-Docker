package org.pietrzakp.proxyservice.customizers;

import com.github.tomakehurst.wiremock.WireMockServer;
import ru.lanwen.wiremock.config.WiremockCustomizer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class UserNotFoundUserServiceCustomizer implements WiremockCustomizer {
    @Override
    public void customize(WireMockServer server) {
        server.stubFor(get(urlMatching("/users/([0-9]+)"))
                .willReturn(aResponse()
                        .withStatus(412)
                        .withBody("User with id: {{request.path.[1]}}does not exist")));
    }
}
