package com.pqitech.app

import com.pqitech.vertx.VertxApp
//import io.vertx.ext.jdbc.JDBCClient
//import io.vertx.ext.mongo.MongoClient
import io.vertx.kotlin.core.closeAwait
//import io.vertx.kotlin.ext.sql.getConnectionAwait
//import io.vertx.redis.client.Redis
//import io.vertx.redis.client.RedisAPI
//import io.vertx.redis.client.RedisOptions
import org.apache.logging.log4j.LogManager

object MyApp : VertxApp() {
  val log = LogManager.getLogger("com.pqitech.app.PLApp")
//  private lateinit var redis_ : Redis
//  private lateinit var mongo_ : MongoClient
//  private lateinit var jdbcClient_ : JDBCClient
//
//  val redisAPI get() = RedisAPI.api(redis_)
//  val mongoClient get()= mongo_
//  val jdbc get() = jdbcClient_

  override suspend fun doStart() {
    kotlin.runCatching {
//      redis_ = Redis.createClient(vertx, RedisOptions(startOptions.getJsonObject("redis")))
//      mongo_ = MongoClient.createShared(vertx, startOptions.getJsonObject("monogo"))
//      jdbcClient_ = JDBCClient.create(vertx, startOptions.getJsonObject("jdbc"))
//      initDB()
      this.deployVerticleConfigAwait(eventLoopSize) {
        WebVerticle()
      }
    }.run {
      if(this.isFailure){
        log.error(this.exceptionOrNull())
        vertx.closeAwait()
      }
    }
  }

//  suspend  fun initDB() = jdbc.getConnectionAwait().use {
//    // todo : 初始化DataBase
//  }
}
