package net.andreaskluth.micrometersample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class SampleController {

    @GetMapping("/")
    public Mono<String> hello() {
        return Mono.just("Hallo");
    }

}
