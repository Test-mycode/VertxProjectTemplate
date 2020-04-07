@file:JvmName("AppMain")
package com.pqitech.app

import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.core.json.JsonObject
import com.pqitech.utils.launch
import io.vertx.kotlin.core.deployVerticleAwait
import com.pqitech.utils.parseArgs
//import io.vertx.ext.jdbc.JDBCClient
import io.vertx.kotlin.core.closeAwait
//import io.vertx.kotlin.ext.sql.getConnectionAwait
import org.apache.logging.log4j.LogManager


fun main(args : Array<String>) {
  val option = parseArgs(args, "Jxc GateWay")
  val logger = LogManager.getLogger("com.pqitech.app.AppMain")
  logger.trace("read options: ${logger}")
  val optionVertx = VertxOptions(option.getJsonObject("vertx", JsonObject()))
  val vertx = Vertx.vertx(optionVertx)
  vertx.launch {
    try {
//      logger.debug("start run init sql")
//      initDb(JDBCClient.create(vertx, option.getJsonObject("jdbc")).getConnectionAwait())
      logger.debug("start run deployVerticle")
      for (i in 0..optionVertx.eventLoopPoolSize) {
        vertx.deployVerticleAwait(WebVerticle(option))
      }
    } catch (e : Throwable){
      logger.error("error : ",e)
      vertx.closeAwait()
    }
  }
}

