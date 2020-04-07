package com.pqitech.app
/*
import io.vertx.ext.sql.SQLConnection
import io.vertx.kotlin.ext.sql.updateAwait
import org.apache.logging.log4j.LogManager

suspend fun initDb(con : SQLConnection) = con.use {
  val logger = LogManager.getLogger("com.pqitech.app.initdb")
  val sql = "CREATE TABLE if not exists public.usermapper (\n" +
    "\tid serial8 NOT NULL,\n" +
    "\tinserttime timestamp NOT NULL DEFAULT now()\n" +
    ");"
  logger.debug("run sql1 : ${sql}")
  con.updateAwait(sql)
  val sql2 = "CREATE UNIQUE INDEX  if not exists  usermapper_user_idx ON public.usermapper (\"user\");"
  logger.debug("run sql1 : ${sql2}")
  con.updateAwait(sql2)
}
*/
