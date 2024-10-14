package org.acme

import org.hibernate.boot.model.FunctionContributions
import org.hibernate.boot.model.FunctionContributor


class ArrayContainsFunctionContributor : FunctionContributor {
    override fun contributeFunctions(functionContributions: FunctionContributions) {
        functionContributions.functionRegistry
            .register("custom_function_xd", ArrayContainsFunction("array_contains"))
    }
}