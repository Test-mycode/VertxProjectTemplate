package com.pqitech.app

import com.pqitech.exception.ErrorCodeException
import com.pqitech.utils.bodyHandle
import com.pqitech.utils.enableCors
import com.pqitech.utils.endJson
import io.vertx.core.http.HttpServer
import io.vertx.core.http.HttpServerOptions
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await
import org.apache.logging.log4j.LogManager

class WebVerticle(val verticleConfig : JsonObject) : CoroutineVerticle() {
  val log = LogManager.getLogger("com.pqitech.app.WebVerticle")
  private lateinit var server : HttpServer
  private lateinit var router : Router

  override suspend fun start() {
    server = vertx.createHttpServer(httpOptions())
//    redis = Redis.createClient(vertx, RedisOptions(verticleConfig.getJsonObject("redis")))
//    jdbc = JDBCClient.create(vertx,verticleConfig.getJsonObject("jdbc"))
    router = Router.router(vertx)
    doInit()
    server.requestHandler(router).listen().await()
  }

  private suspend fun  doInit()
  {
    router.bodyHandle(16 * 1024 * 1024,false)
    router.enableCors()
  }

  override suspend fun stop() {
    server.close().await()
  }

  private fun httpOptions() : HttpServerOptions {
    val options = HttpServerOptions(verticleConfig.getJsonObject("web", JsonObject()))
    options.setReuseAddress(true).isReusePort = true
    return  options
  }

  private fun failureHandler(context: RoutingContext)
  {
    handleException(context,context.failure(),404)
  }

  fun handleException(context: RoutingContext, e : Throwable?, status : Int = 500, msg : String = "")
  {
    val ret = JsonObject()
    ret.put("status",status);
    ret.put("error", status)
    if(msg.isEmpty())
      ret.put("message", e?.message ?: "未知错误")
    else
      ret.put("message", msg)
    ret.put("data",JsonObject())
    if(e != null){
      if(e is ErrorCodeException){
        log.info("catch ErrorCodeException : ",e);
        ret.put("status", e.errorCode)
      } else {
        e.printStackTrace()
        log.warn("catch error exception : ",e);
      }
    }
    context.response().setStatusCode(200).endJson(ret)
  }
}
