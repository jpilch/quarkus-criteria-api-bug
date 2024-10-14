package org.acme

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import org.hibernate.annotations.Type

@Entity(name = "item")
class Item (
    @Type(value = ColorType::class)
    @Column(columnDefinition = "color[]")
    val colors: MutableList<Color> = mutableListOf()
) {
    @Id
    @SequenceGenerator(name = "item_seq", sequenceName = "item_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    var id: Long? = null
}