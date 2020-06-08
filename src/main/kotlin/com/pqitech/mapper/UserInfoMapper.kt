package com.pqitech.mapper

import com.pqitech.model.UserInfo
import io.vertx.ext.sql.SQLClient
import io.vertx.ext.sql.mapper.CommonSQL
import io.vertx.ext.sql.mapper.SQLExecute

class UserInfoMapper(client: SQLExecute<SQLClient>): CommonSQL<UserInfo, SQLClient>(client) {
}
