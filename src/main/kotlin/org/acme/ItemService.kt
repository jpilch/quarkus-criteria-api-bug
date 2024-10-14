package org.acme

import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.criteria.Predicate
import org.hibernate.reactive.mutiny.Mutiny.SessionFactory

@ApplicationScoped
class ItemService(private val sessionFactory: SessionFactory) {

    fun findBy(color: Color): Uni<List<Item>> {
        return sessionFactory.withSession { session ->
            val cb = sessionFactory.criteriaBuilder
            val query = cb.createQuery(Item::class.java)
            val root = query.from(Item::class.java)

            val predicate = cb.equal(root.get<Color>("color"), color)

            query.where(predicate)

            session.createQuery(query).resultList
        }
    }

    fun findBy(colors: List<Color>): Uni<List<Item>> {
        return sessionFactory.withSession { session ->
            val cb = sessionFactory.criteriaBuilder
            val query = cb.createQuery(Item::class.java)
            val root = query.from(Item::class.java)

            val predicates = colors.takeIf { it.isNotEmpty() }?.let {
                val toPredicate: (Color) -> Predicate = { color ->
                    val expr = cb.function(
                        "custom_function_xd",
                        Boolean::class.java,
                        root.get<Array<Color>>("colors"),
                        cb.literal(color.name))

                    cb.isTrue(expr)
                }

                it.map(toPredicate);
            }.orEmpty()

            query.where(*predicates.toTypedArray())

            session.createQuery(query).resultList
        }
    }
}
