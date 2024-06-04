package ar.edu.itba.pod.tpe2.query1;

import ar.edu.itba.pod.tpe2.models.Infraction;
import com.hazelcast.mapreduce.Collator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Q1Collator implements Collator<Map.Entry<String, Integer>, Map<String, Integer>> {
    private final Map<String, Infraction> infractions;

    public Q1Collator(Map<String, Infraction> infractions) {
        this.infractions = infractions;
    }

    @Override
    public Map<String, Integer> collate(Iterable<Map.Entry<String, Integer>> values) {
        return StreamSupport.stream(values.spliterator(), false)
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
                        .thenComparing(entry -> infractions.get(entry.getKey()).getDescription()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}