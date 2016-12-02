package com.github.pikasan;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.objectweb.asm.ClassReader;

public class MethodExtractor {
    private Path path;

    public MethodExtractor(Path path){
        this.path = path;
    }
    
    public Results extract(){
        try{ return extractImpl(new Results(), path); }
        catch(IOException e){
            throw new InternalError(e);
        }
    }

    private Results extractImpl(Results results, Path path) throws IOException{
        JarFile file = new JarFile(path.toFile());
        file.stream()
        .filter(entry -> entry.getName().endsWith(".class"))
        .map(entry -> extractEntry(file, entry))
        .forEach(result -> results.add(result));
        return results;
    }

    private Result[] extractEntry(JarFile jarfile, JarEntry entry){
        try{
            return extractEntryImpl(jarfile, entry);
        } catch(IOException e){
            throw new InternalError(e);
        }
    }

    private Result[] extractEntryImpl(JarFile jarfile, JarEntry entry) throws IOException{
        InputStream in = jarfile.getInputStream(entry);
        ClassReader reader = new ClassReader(in);
        return extractClassFile(reader);
    }

    private Result[] extractClassFile(ClassReader reader){
        PikasanClassVisitor visitor = new PikasanClassVisitor();
        reader.accept(visitor, ClassReader.SKIP_DEBUG);
        return visitor.results();
    }
}
