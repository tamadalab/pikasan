package com.github.pikasan;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandLine{
    private Arguments arguments;
    private Options options;

    public CommandLine(){
    }

    public boolean printHelpIfSpecified(){
        if(options.hasOption("--help")){
            printHelp();
            return true;
        }
        return false;
    }

    private void printHelp(){
        System.out.println("java pikasan-1.0.jar [OPTIONS] <JARS...>");
        System.out.println("OPTIONS");
        System.out.println("    --help:  print this message and exit.");
        System.out.println("JARS");
        System.out.println("    targets.");
    }

    public void arguments(Consumer<Path> consumer){
        arguments.stream()
        // .peek(item -> System.out.println("target: " + item))
        .map(item -> Paths.get(item))
        .forEach(consumer);
    }

    public Stream<String> options(){
        return options.stream();
    }

    public static CommandLine parse(String[] args){
        CommandLine line = new CommandLine();
        line.parseArgs(args);
        return line;
    }

    private void parseArgs(String[] args){
        Predicate<String> predicate = (item) -> item.startsWith("-");
        arguments = parseArguments(Arrays.stream(args), predicate.negate());
        options = parseOptions(Arrays.stream(args), predicate);
    }

    private Arguments parseArguments(Stream<String> stream, Predicate<String> predicate){
        List<String> list = stream.filter(predicate).collect(Collectors.toList());
        return new Arguments(list);
    }

    private Options parseOptions(Stream<String> stream, Predicate<String> predicate){
        List<String> list = stream.filter(predicate).collect(Collectors.toList());
        return new Options(list);
    }
}
