package com.techzealot.mop.ast

import org.codehaus.groovy.ast.*
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.syntax.SyntaxException

class OurClassVisitor implements GroovyClassVisitor {
    SourceUnit sourceUnit

    OurClassVisitor(SourceUnit sourceUnit) {
        this.sourceUnit = sourceUnit
    }

    @Override
    void visitClass(ClassNode node) {

    }

    @Override
    void visitConstructor(ConstructorNode node) {

    }

    private reportError(message, lineNumber, columnNumber) {
        sourceUnit.addError(new SyntaxException(message, lineNumber, columnNumber))
    }

    @Override
    void visitMethod(MethodNode node) {
        if (node.name.size() == 1) {
            reportError("avoid single letter name", node.lineNumber, node.columnNumber)
        }
        node.parameters.each { parameter ->
            if (parameter.name.size() == 1) {
                reportError("single letter parameter name is wrong", parameter.lineNumber, parameter.columnNumber)
            }
        }
    }

    @Override
    void visitField(FieldNode node) {

    }

    @Override
    void visitProperty(PropertyNode node) {

    }
}
