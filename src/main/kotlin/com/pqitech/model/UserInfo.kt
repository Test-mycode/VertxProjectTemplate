package com.pqitech.model
import io.vertx.ext.sql.assist.anno.Table
import io.vertx.ext.sql.assist.anno.TableColumn
import io.vertx.ext.sql.assist.anno.TableId
import java.sql.Timestamp
import java.util.*

/**
 *
 *
 *
 *
 *
 * @author test
 * @since 2020-02-13
 */
@Table("user_info")
class UserInfo {
  @TableId("id")
  var id: Int? = null
  @TableColumn("username")
  var username: String? = null
  @TableColumn("password")
  var password: String? = null
  @TableColumn("avatar")
  var avatar: String? = null
  @TableColumn("phone")
  var phone: String? = null
  @TableColumn("email")
  var email: String? = null
  @TableColumn("resume")
  var resume: String? = null
  @TableColumn("config")
  var config: String? = null
  @TableColumn("time_created")
  var timeCreated: Timestamp? = null
  @TableColumn("time_updated")
  var timeUpdated: Timestamp? = null
  @TableColumn("deleted")
  var deleted: Boolean? = null

  constructor() {}
  constructor(id: Int?, username: String?, password: String?, avatar: String?, phone: String?, email: String?, resume: String?, config: String?, timeCreated: Timestamp?, timeUpdated: Timestamp?, deleted: Boolean?) {
    this.id = id
    this.username = username
    this.password = password
    this.avatar = avatar
    this.phone = phone
    this.email = email
    this.resume = resume
    this.config = config
    this.timeCreated = timeCreated
    this.timeUpdated = timeUpdated
    this.deleted = deleted
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is UserInfo) return false
    return id == other.id &&
      username == other.username &&
      password == other.password &&
      avatar == other.avatar &&
      phone == other.phone &&
      email == other.email &&
      resume == other.resume &&
      config == other.config &&
      timeCreated == other.timeCreated &&
      timeUpdated == other.timeUpdated &&
      deleted == other.deleted
  }

  override fun hashCode(): Int {
    return Objects.hash(id, username, password, avatar, phone, email, resume, config, timeCreated, timeUpdated, deleted)
  }
}
