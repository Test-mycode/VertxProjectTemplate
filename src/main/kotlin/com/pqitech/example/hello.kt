package com.pqitech.example

import io.vertx.ext.web.RoutingContext


fun helloWord(context: RoutingContext)
{
  context.response().end("hello world")
}


