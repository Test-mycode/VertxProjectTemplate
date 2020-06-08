package com.pqitech.app

import com.pqitech.example.helloWord
import io.vertx.ext.web.Router


fun addRouter(router: Router)
{

  router.get("/").handler(::helloWord)
}
