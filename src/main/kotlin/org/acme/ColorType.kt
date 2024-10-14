package org.acme

import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.usertype.UserType
import java.sql.ResultSet
import java.sql.Types
import java.io.Serializable
import java.sql.PreparedStatement

class ColorType : UserType<List<Color>> {
    override fun equals(x: List<Color>?, y: List<Color>?): Boolean =
        if (x is List<Color> && y is List<Color>) {
            x == y
        } else {
            false
        }

    override fun hashCode(x: List<Color>?): Int {
        if (x == null) return 0

        var hashCode = 1
        for (element in x) {
            hashCode = 31 * hashCode + (element.hashCode())
        }

        return hashCode
    }

    override fun getSqlType(): Int = Types.ARRAY

    override fun returnedClass(): Class<List<Color>> = List::class.java as Class<List<Color>>

    override fun nullSafeGet(rs: ResultSet, position: Int, session: SharedSessionContractImplementor?, owner: Any?): List<Color>? {
        val array = rs.getObject(position) as? Array<String> ?: return null
        val listOfBrands = array.toList()
        return listOfBrands.map { Color.valueOf(it) }
    }

    override fun isMutable(): Boolean = false

    override fun assemble(cached: Serializable?, owner: Any?): List<Color> =
        throw UnsupportedOperationException("Unable to assemble cached data")

    override fun disassemble(value: List<Color>?): Serializable {
        if (value != null) {
            return ArrayList(value)
        } else {
            throw UnsupportedOperationException("Unable to disassemble null value")
        }
    }

    override fun deepCopy(value: List<Color>?): List<Color> {
        if (value != null) {
            return ArrayList(value)
        } else {
            throw UnsupportedOperationException("Unable to deep copy null value")
        }
    }

    override fun nullSafeSet(st: PreparedStatement?, value: List<Color>?, index: Int, session: SharedSessionContractImplementor?) {
        if (value == null) {
            st!!.setNull(index, Types.ARRAY)
        } else {
            val array = value.toTypedArray()
            st!!.setObject(index, array)
        }
    }
}