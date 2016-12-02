package com.github.pikasan;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ResultPrinter {
    private PrintWriter out;

    public ResultPrinter() throws IOException{
        this(new PrintWriter(new OutputStreamWriter(System.out, "utf-8")));
    }

    public ResultPrinter(PrintWriter out) throws IOException{
        this.out = out;
    }

    public void print(Results results){
        results.forEach(item -> print(item));
        out.flush();
    }

    public void print(Result item){
        out.println(item);
    }
}
