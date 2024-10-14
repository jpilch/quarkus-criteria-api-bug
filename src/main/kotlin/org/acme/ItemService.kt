package org.acme

import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.metamodel.SingularAttribute
import org.hibernate.reactive.mutiny.Mutiny.SessionFactory

typealias ColorAttribute = SingularAttribute<Item, Array<Color>>;

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
                        "array_contains",
                        Boolean::class.java,
                        root.get<ColorAttribute>("colors"),
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
