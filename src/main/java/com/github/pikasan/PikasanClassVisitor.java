package com.github.pikasan;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class PikasanClassVisitor extends ClassVisitor {
    private List<Result> list = new ArrayList<>();
    private String className;

    public PikasanClassVisitor() {
        super(Opcodes.ASM5);
    }

    public Result[] results(){
        return list.toArray(new Result[list.size()]);
    }

    @Override
    public void visit(int version, int access, String name, String signature,
            String superName, String[] interfaces){
        this.className = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature,
            String[] exceptions){
        Result result = new Result(className + "##" + name + "," + desc);
        list.add(result);
        MethodVisitor visitor = super.visitMethod(access, name, desc, signature, exceptions);
        return new PikasanMethodVisitor(result, visitor);
    }
}
