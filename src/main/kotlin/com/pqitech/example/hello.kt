package com.pqitech.example

import com.pqitech.vertx.RouteBaseController
import com.pqitech.vertx.RouteInfo
import io.vertx.ext.web.RoutingContext


fun helloWord(context: RoutingContext)
{
  context.response().end("hello world")
}


class HelloContorller : RouteBaseController
{
  @RouteInfo("/hello","GET")
  fun hello(context: RoutingContext)
  {
    context.response().end("hello:  ${context.request().query()}")
  }

  @RouteInfo("/world","POST")
  fun world(context: RoutingContext)
  {
    context.response().end("Word :  ${context.bodyAsString}")
  }
}

