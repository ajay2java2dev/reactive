package one.aviato.sample.reactive.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

public class FluxCore {

    @Test
    void fluxMergeWithFlux () {
        //flux of values1
        var listOfValues = new ArrayList<Integer>();
        listOfValues.add(1);
        listOfValues.add(2);
        listOfValues.add(3);
        listOfValues.add(4);
        Flux<Integer> integerFlux1 = Flux.fromIterable(listOfValues);
        Flux<Integer> integerFlux2 = Flux.fromIterable(listOfValues);
        var finalFlux = integerFlux1.mergeWith(integerFlux2);
        var listValues = finalFlux.flatMap(integer -> {
            System.out.println(integer);
            return Mono.just(integer);
        }).collectList();

        var finalList = listValues.block();
        Assertions.assertTrue(finalList.size()==8);

    }
}
