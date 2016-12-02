package com.github.pikasan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Results {
    private List<Result> results = new ArrayList<>();

    public void add(Result[] result){
        Arrays.stream(result)
        .forEach(r -> results.add(r));
    }

    public void forEach(Consumer<Result> consumer){
        results.forEach(consumer);
    }
}
