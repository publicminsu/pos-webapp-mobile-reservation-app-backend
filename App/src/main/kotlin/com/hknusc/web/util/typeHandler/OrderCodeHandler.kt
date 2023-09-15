package com.hknusc.web.util.typeHandler

import com.hknusc.web.util.type.OrderCode
import org.apache.ibatis.type.JdbcType
import org.apache.ibatis.type.MappedJdbcTypes
import org.apache.ibatis.type.MappedTypes
import org.apache.ibatis.type.TypeHandler
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet

@MappedTypes(OrderCode::class)
class OrderCodeHandler : TypeHandler<OrderCode> {
    override fun setParameter(ps: PreparedStatement, i: Int, parameter: OrderCode, jdbcType: JdbcType?) {
        ps.setShort(i, parameter.code)
    }

    override fun getResult(rs: ResultSet, columnName: String): OrderCode? {
        return OrderCode.find(rs.getShort(columnName))
    }

    override fun getResult(rs: ResultSet, columnIndex: Int): OrderCode? {
        return OrderCode.find(rs.getShort(columnIndex))
    }

    override fun getResult(cs: CallableStatement, columnIndex: Int): OrderCode? {
        return OrderCode.find(cs.getShort(columnIndex))
    }
}