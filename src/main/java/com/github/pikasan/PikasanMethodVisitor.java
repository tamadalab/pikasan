package com.github.pikasan;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class PikasanMethodVisitor extends MethodVisitor {
    private Result result;

    public PikasanMethodVisitor(Result result, MethodVisitor visitor) {
        super(Opcodes.ASM5, visitor);
        this.result = result;
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf){
        super.visitMethodInsn(opcode, owner, name, desc, itf);
        result.add(owner + "#" + name);
    }
}
