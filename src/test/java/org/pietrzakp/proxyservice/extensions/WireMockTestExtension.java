package org.pietrzakp.proxyservice.extensions;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanwen.wiremock.config.WiremockCustomizer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.text.MessageFormat;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class WireMockTestExtension implements BeforeEachCallback, AfterEachCallback {

    private static final Logger LOGGER = LoggerFactory.getLogger(WireMockTestExtension.class);

    private WireMockServer server;

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        LOGGER.info("Started test: {}", extensionContext.getDisplayName());
        LOGGER.getName();
        validateNotRunning(this.server);
         extensionContext.getTestMethod()
                .map(method -> method.getDeclaredAnnotation(WireMockTestExtension.Wiremock.class))
                .ifPresent(this::startServerFromAnnotationConfiguration);
    }

    private void startServerFromAnnotationConfiguration(Wiremock wiremockAnnotation) {
        this.server = new WireMockServer(options()
                .port(wiremockAnnotation.port())
                .extensions(new ResponseTemplateTransformer(false))
                .notifier(new ConsoleNotifier(wiremockAnnotation.verbose())));
        this.server.start();
        try {
            wiremockAnnotation.customizer().getDeclaredConstructor().newInstance()
                    .customize(this.server);
        } catch (Exception ex) {
            throw new ParameterResolutionException(MessageFormat.format("Cannot customize server with customizer: {0}", wiremockAnnotation.customizer()), ex);
        }
        LOGGER.info("Started WireMock server with localhost:{}", this.server.port());
    }

    private static void validateNotRunning(WireMockServer wireMockServer) {
        if(wireMockServer != null && wireMockServer.isRunning()) {
            throw new RuntimeException("Cannot inject more than one server");
        }
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        if(this.server != null && this.server.isRunning()) {
            this.server.resetRequests();
            this.server.resetToDefaultMappings();
            LOGGER.info("Stopping wiremock server on localhost:{}", this.server.port());
            this.server.stop();
        }
        LOGGER.info("Finished test: {}.", extensionContext.getDisplayName());
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Wiremock {
        Class<? extends WiremockCustomizer> customizer() default WiremockCustomizer.NoopWiremockCustomizer.class;
        int port() default 8080;
        boolean verbose() default false;
    }
}
