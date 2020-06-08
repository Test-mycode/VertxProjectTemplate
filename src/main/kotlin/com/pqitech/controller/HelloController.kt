package com.pqitech.controller

import com.pqitech.anno.*
import com.pqitech.app.MyApp
import com.pqitech.core.RouteController
import com.pqitech.mapper.UserInfoMapper
import com.pqitech.model.UserInfo
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.await
import io.vertx.kotlin.ext.sql.*
import java.lang.Exception

@RouteMapping("/test")
class HelloController : RouteController() {
  private val userInfoMapper: UserInfoMapper = UserInfoMapper(MyApp.sqlExecute);

  //  @Produces(value = "text/plain; charset=utf-8")
  @RouteMapping(value = "/test/:id")
  fun test(
    routingContext: RoutingContext,
    @RoutePathValue("id") id: Int,
    @RouteParam("test") test: String,
    @RouteHeader("cookie") cookie: String): String {
    return """
      select * from user_info
      left join user_role ur on user_info.id = ur.user_id
      left join role r on ur.role_id = r.id
      left join role_permission rp on r.id = rp.role_id
      left join permission p on rp.permission_id = p.id
      where user_info.id = ?
    """.trimIndent()
  }

  @ExecuteBlock
//  @Produces(value = "text/html; charset=utf-8")
  @RouteMapping(value = "/test2", method = ["POST"])
  fun test2(routingContext: RoutingContext, @RouteBody(valid = false) user: UserInfo): UserInfo {
    return user
  }

  @RouteMapping(value = "/test3")
  suspend fun test3(routingContext: RoutingContext, @RouteParam("id") id: Int): JsonObject? {
    val con = MyApp.jdbc.getConnectionAwait();
    try {
      con.setAutoCommitAwait(false);

      con.commitAwait();
    } catch (e: Exception) {
      e.printStackTrace()
      con.rollbackAwait();
    } finally {
      con.closeAwait()
    }
    return this.userInfoMapper.selectById(id).await()
  }

  @RouteMapping("/test4")
  fun test4(routingContext: RoutingContext) {
    println("hello world")
  }
}
