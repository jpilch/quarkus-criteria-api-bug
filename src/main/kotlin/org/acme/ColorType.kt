package org.acme

import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.usertype.UserType
import java.sql.ResultSet
import java.sql.Types
import java.io.Serializable
import java.sql.PreparedStatement

class ColorType : UserType<Array<Color>> {
    override fun equals(x: Array<Color>?, y: Array<Color>?): Boolean =
        if (x is Array<Color> && y is Array<Color>) {
            x == y
        } else {
            false
        }

    override fun hashCode(x: Array<Color>?): Int {
        if (x == null) return 0

        var hashCode = 1
        for (element in x) {
            hashCode = 31 * hashCode + (element.hashCode())
        }

        return hashCode
    }

    override fun getSqlType(): Int = Types.ARRAY

    override fun returnedClass(): Class<Array<Color>> = Array<Color>::class.java as Class<Array<Color>>

    override fun nullSafeGet(rs: ResultSet, position: Int, session: SharedSessionContractImplementor?, owner: Any?): Array<Color>? {
        val array = rs.getObject(position) as? Array<String> ?: return null
        val listOfBrands = array.toList()
        return listOfBrands.map { Color.valueOf(it) }.toTypedArray()
    }

    override fun isMutable(): Boolean = false

    override fun assemble(cached: Serializable?, owner: Any?): Array<Color> =
        throw UnsupportedOperationException("Unable to assemble cached data")

    override fun disassemble(value: Array<Color>?): Serializable {
        if (value != null) {
            return (value)
        } else {
            throw UnsupportedOperationException("Unable to disassemble null value")
        }
    }

    override fun deepCopy(value: Array<Color>?): Array<Color> {
        if (value != null) {
            return (value)
        } else {
            throw UnsupportedOperationException("Unable to deep copy null value")
        }
    }

    override fun nullSafeSet(st: PreparedStatement?, value: Array<Color>?, index: Int, session: SharedSessionContractImplementor?) {
        if (value == null) {
            st!!.setNull(index, Types.ARRAY)
        } else {
            st!!.setObject(index, value)
        }
    }
}
