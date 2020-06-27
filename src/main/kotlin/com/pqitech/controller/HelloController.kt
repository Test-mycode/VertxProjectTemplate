package com.pqitech.controller

import com.pqitech.anno.*
import com.pqitech.app.MyApp
import com.pqitech.app.queryPageWithParams
import com.pqitech.core.RouteController
import com.pqitech.mapper.UserInfoMapper
import com.pqitech.model.UserInfo
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.core.json.jsonArrayOf
import io.vertx.kotlin.coroutines.await
import io.vertx.kotlin.ext.sql.queryWithParamsAwait

@RouteMapping("/test")
class HelloController : RouteController() {
  private val userInfoMapper: UserInfoMapper = UserInfoMapper(MyApp.sqlExecute)

  //  @Produces(value = "text/plain; charset=utf-8")
  @RouteMapping(value = "/test/:id")
  suspend fun test(routingContext: RoutingContext, @RoutePathValue("id") id: Int): JsonObject? {
    return this.userInfoMapper.selectById(id).await()
  }

  @ExecuteBlock
//  @Produces(value = "text/html; charset=utf-8")
  @RouteMapping(value = "/test2", method = ["POST"])
  fun test2(routingContext: RoutingContext, @RouteBody(valid = false) user: UserInfo): UserInfo {
    return user
  }

  @RouteMapping(value = "/test3")
  suspend fun test3(routingContext: RoutingContext, @RouteParam("id") id: Int): JsonObject? {
    return this.userInfoMapper.selectById(id).await()
  }

  @RouteMapping("/test4")
  fun test4(routingContext: RoutingContext): String {
    return "hello world"
  }
}
