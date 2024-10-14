package org.acme

import org.hibernate.dialect.function.StandardSQLFunction
import org.hibernate.query.ReturnableType
import org.hibernate.sql.ast.SqlAstTranslator
import org.hibernate.sql.ast.spi.SqlAppender
import org.hibernate.sql.ast.tree.SqlAstNode
import org.hibernate.type.BasicTypeReference
import org.hibernate.type.SqlTypes


class ArrayContainsFunction(name: String) : StandardSQLFunction(name, true, RETURN_TYPE){

    companion object {
        private val RETURN_TYPE = BasicTypeReference("boolean", Boolean::class.java, SqlTypes.BOOLEAN)
    }

    override fun render(
        sqlAppender: SqlAppender?,
        sqlAstArguments: MutableList<out SqlAstNode>?,
        returnType: ReturnableType<*>?,
        translator: SqlAstTranslator<*>?
    ) {
        if (sqlAstArguments?.size != 2) {
            throw IllegalArgumentException("Function '${name}' requires exactly 2 arguments");
        }

        sqlAppender?.append("(");
        sqlAstArguments[0].accept(translator);
        sqlAppender?.append(" @> ARRAY[");
        sqlAstArguments[1].accept(translator);
        sqlAppender?.append("]::color[])");
    }
}