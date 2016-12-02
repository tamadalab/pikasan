package com.github.pikasan;

import java.io.IOException;
import java.nio.file.Path;

public class Main{
    private ResultPrinter printer;

    public Main(String[] args){
        initialize();
        CommandLine commandline = CommandLine.parse(args);
        if(!commandline.printHelpIfSpecified())
            perform(commandline);
    }

    private void initialize(){
        try{
            printer = new ResultPrinter();        
        } catch(IOException e){
            throw new InternalError();
        }
    }

    private void perform(CommandLine line){
        line.arguments(jar -> performEachJar(jar));
    }

    private void performEachJar(Path jar){
        MethodExtractor extractor = new MethodExtractor(jar);
        Results result = extractor.extract();
        printer.print(result);
    }

    public static void main(String[] args){
        new Main(args);
    }
}
