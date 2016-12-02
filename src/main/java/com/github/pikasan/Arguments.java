package com.github.pikasan;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Arguments{
    private List<String> arguments;

    public Arguments(List<String> list){
        arguments = new ArrayList<>();
        arguments.addAll(list);
    }

    public Stream<String> stream(){
        return arguments.stream();
    }
}
