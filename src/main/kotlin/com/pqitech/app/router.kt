package com.pqitech.app

import com.pqitech.example.HelloContorller
import com.pqitech.example.helloWord
import com.pqitech.vertx.addRoute
import io.vertx.ext.web.Router

fun addRouter(router: Router)
{
  router.addRoute(HelloContorller())
  router.get("/").handler(::helloWord)
}
