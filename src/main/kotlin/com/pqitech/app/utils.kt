package com.pqitech.app

import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.sql.SQLOperations
import io.vertx.kotlin.ext.sql.queryAwait
import io.vertx.kotlin.ext.sql.queryWithParamsAwait
import java.lang.IllegalArgumentException


private fun getCountSql(sql: String): String {
  if(sql.isBlank())
    throw IllegalArgumentException("sql不能为空")
  val lowSql = sql.toLowerCase();
  val begin = lowSql.indexOf("from");
  // select * from ...
  if(begin<0)
    throw IllegalArgumentException("分页sql格式不合法")

  // where .... limit ? offset ?
  val end = lowSql.lastIndexOf("limit")

  if(end<0)
    throw IllegalArgumentException("分页sql格式不合法")

  return "select count(*) ${sql.substring(begin,end)}"
}

public suspend fun SQLOperations.queryPageWithParams(sql: String, params: JsonArray): Pair<Long,List<JsonObject>> {
  val paramsSize = params.size()

  if(paramsSize<2)
    throw IllegalArgumentException("参数过少")

  val countParams = if(paramsSize>2) {
    val res = params.copy();
    res.remove(paramsSize-1)
    res.remove(paramsSize-2)
    res
  }else JsonArray()

  val count = if(!countParams.isEmpty)
    this.queryWithParamsAwait(getCountSql(sql),countParams).rows[0].getLong("count")
  else
    this.queryAwait(getCountSql(sql)).rows[0].getLong("count")

  val data = if(count<=0) ArrayList<JsonObject>() else this.queryWithParamsAwait(sql,params).rows

  return count to data
}
