package com.pqitech.mapper

import com.pqitech.model.UserInfo
import io.vertx.ext.sql.SQLClient
import io.vertx.ext.sql.SQLOperations
import io.vertx.ext.sql.assist.core.CommonSQL
import io.vertx.ext.sql.assist.core.SQLExecute

class UserInfoMapper(client: SQLExecute<SQLOperations>): CommonSQL<UserInfo, SQLOperations>(client) {
}
