package com.hknusc.web.util.typeHandler

import com.hknusc.web.util.type.StoreCategory
import org.apache.ibatis.type.JdbcType
import org.apache.ibatis.type.MappedTypes
import org.apache.ibatis.type.TypeHandler
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet

@MappedTypes(StoreCategory::class)
class StoreCategoryHandler : TypeHandler<StoreCategory> {
    override fun setParameter(ps: PreparedStatement, i: Int, parameter: StoreCategory, jdbcType: JdbcType?) {
        ps.setShort(i, parameter.code)
    }

    override fun getResult(rs: ResultSet, columnName: String): StoreCategory? {
        return StoreCategory.find(rs.getShort(columnName))
    }

    override fun getResult(rs: ResultSet, columnIndex: Int): StoreCategory? {
        return StoreCategory.find(rs.getShort(columnIndex))
    }

    override fun getResult(cs: CallableStatement, columnIndex: Int): StoreCategory? {
        return StoreCategory.find(cs.getShort(columnIndex))
    }
}
