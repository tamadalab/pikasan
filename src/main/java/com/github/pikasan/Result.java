package com.github.pikasan;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Result {
    private String className;
    private List<String> list = new ArrayList<>();

    public Result(String className){
        this.className = className;
    }

    public String className(){
        return className;
    }

    public void add(String item){
        list.add(item);
    }

    public String toString(){
        StringJoiner joiner = new StringJoiner(",");
        list.stream().forEach(item -> joiner.add(item));
        return String.format("%s,%d,%s", className, list.size(), joiner.toString());
    }
}
