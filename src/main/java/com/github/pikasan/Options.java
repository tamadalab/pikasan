package com.github.pikasan;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Options{
    private List<String> options;

    public Options(List<String> list){
        options = new ArrayList<>();
        options.addAll(list);
    }

    public boolean hasOption(String option){
        return stream()
                .filter(item -> item.equals(option))
                .count() > 0;
    }

    public Stream<String> stream(){
        return options.stream();
    }
}
