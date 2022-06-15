package com.techzealot.mop.ast

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

/**
 * 需要添加全限定名到META-INF/services/org.codehaus.groovy.transform.ASTTransformation文件
 */
@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
class CodeCheck implements ASTTransformation {

    @Override
    void visit(ASTNode[] nodes, SourceUnit source) {
        source.AST.classes.each { classNode ->
            classNode.visitContents(new OurClassVisitor(source))
        }
    }
}