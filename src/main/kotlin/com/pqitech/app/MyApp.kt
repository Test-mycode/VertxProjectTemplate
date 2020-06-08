package com.pqitech.app

import com.pqitech.vertx.VertxApp
import io.vertx.ext.asyncsql.PostgreSQLClient
import io.vertx.ext.sql.SQLClient
import io.vertx.ext.sql.mapper.SQLExecute
import io.vertx.ext.sql.mapper.SQLStatement
import io.vertx.ext.sql.mapper.sql.PostgreSQLStatementSQL
//import io.vertx.ext.jdbc.JDBCClient
//import io.vertx.ext.mongo.MongoClient
import io.vertx.kotlin.core.closeAwait
//import io.vertx.kotlin.ext.sql.getConnectionAwait
//import io.vertx.redis.client.Redis
//import io.vertx.redis.client.RedisAPI
//import io.vertx.redis.client.RedisOptions
import org.apache.logging.log4j.LogManager

object MyApp : VertxApp() {
  val log = LogManager.getLogger(MyApp.javaClass)

  //  private lateinit var redis_ : Redis
//  private lateinit var mongo_ : MongoClient
  private lateinit var _jdbc: SQLClient
  private lateinit var _sqlExecute: SQLExecute<SQLClient>

  //
//  val redisAPI get() = RedisAPI.api(redis_)
//  val mongoClient get()= mongo_
  val jdbc get() = _jdbc
  val sqlExecute get() = _sqlExecute;
  override suspend fun doStart() {
    kotlin.runCatching {
      log.warn("test")
//      redis_ = Redis.createClient(vertx, RedisOptions(startOptions.getJsonObject("redis")))
//      mongo_ = MongoClient.createShared(vertx, startOptions.getJsonObject("monogo"))
      _jdbc = PostgreSQLClient.createShared(vertx, startOptions.getJsonObject("jasync"))
      _sqlExecute = SQLExecute.create(_jdbc);
      SQLStatement.register(PostgreSQLStatementSQL::class.java)

      this.deployVerticleConfigAwait(eventLoopSize) {
        WebVerticle()
      }
    }.run {
      if (this.isFailure) {
        log.error(this.exceptionOrNull())
        vertx.closeAwait()
      }
    }
  }
}
