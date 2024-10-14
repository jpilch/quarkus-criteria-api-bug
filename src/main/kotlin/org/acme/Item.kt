package org.acme

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.annotations.Type
import org.hibernate.type.SqlTypes

@Entity
class Item(
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    val color: Color = Color.RED
) {
    @Id
    @SequenceGenerator(name = "item_seq", sequenceName = "item_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    val id: Long? = null

    @Type(value = ColorType::class)
    @Column(columnDefinition = "color[]")
    val colors: Array<Color> = arrayOf()
}
