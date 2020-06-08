package com.pqitech.router

import com.pqitech.controller.HelloController
import io.vertx.core.Vertx
import io.vertx.ext.web.Router

fun addRouter(vertx: Vertx, router: Router)
{
  HelloController().addRoute(vertx,router)
}
