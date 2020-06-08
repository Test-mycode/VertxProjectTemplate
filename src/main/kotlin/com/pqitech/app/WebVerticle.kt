package com.pqitech.app

import com.pqitech.router.addRouter
import com.pqitech.utils.bodyHandle
import com.pqitech.utils.enableCors
import com.pqitech.vertx.AbstractWebVerticle
import io.vertx.ext.web.RoutingContext
import org.apache.logging.log4j.LogManager


class WebVerticle : AbstractWebVerticle() {
  val log = LogManager.getLogger("com.pqitech.app.WebVerticle")

  override suspend fun  doInit()
  {
    httpRouter.bodyHandle(16 * 1024 * 1024,false)
    httpRouter.enableCors() //开启跨域
    //ws设置
//    httpServer.webSocketHandler {
//      if(it.path() == "/push"){
//
//      } else {
//        it.reject(302);
//      }
//    }
    addRouter(vertx, httpRouter) // 添加router
  }
  // 自定义处理时抛异常的处理
  override fun doFailureHandle(context: RoutingContext) {
    super.doFailureHandle(context)
  }
}
