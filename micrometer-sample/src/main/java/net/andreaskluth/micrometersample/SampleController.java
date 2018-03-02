package net.andreaskluth.micrometersample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import reactor.core.publisher.Mono;

@RestController
public class SampleController {

    private final Counter counter = Metrics.counter("handler.calls", "uri", "/");

    @GetMapping("/")
    public Mono<String> hello() {
        return Mono.just("Hallo").map(__ -> {
            counter.increment();
            return __;
        });
    }

}
