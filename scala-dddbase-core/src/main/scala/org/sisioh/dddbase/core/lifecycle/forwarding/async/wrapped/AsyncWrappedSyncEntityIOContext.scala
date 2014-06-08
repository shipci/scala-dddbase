/*
 * Copyright 2011-2013 Sisioh Project and others. (http://www.sisioh.org/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.sisioh.dddbase.core.lifecycle.forwarding.async.wrapped

import org.sisioh.dddbase.core.lifecycle.async.AsyncEntityIOContext
import org.sisioh.dddbase.core.lifecycle.sync.SyncEntityIOContext
import scala.concurrent.ExecutionContext

/**
 * [[org.sisioh.dddbase.core.lifecycle.async.AsyncEntityIOContext]]のラッパー版。
 */
trait AsyncWrappedSyncEntityIOContext extends AsyncEntityIOContext {

  /**
   * [[org.sisioh.dddbase.core.lifecycle.sync.SyncEntityIOContext]]
   */
  val syncEntityIOContext: SyncEntityIOContext

}

/**
 * コンパニオンオブジェクト。
 */
object AsyncWrappedSyncEntityIOContext {

  /**
   * ファクトリメソッド。
   *
   * @param syncEntityIOContext [[org.sisioh.dddbase.core.lifecycle.sync.SyncEntityIOContext]]
   * @param executor [[scala.concurrent.ExecutionContext]]
   * @return [[org.sisioh.dddbase.core.lifecycle.forwarding.async.wrapped.AsyncWrappedSyncEntityIOContext]]
   */
  def apply(syncEntityIOContext: SyncEntityIOContext = SyncEntityIOContext)
           (implicit executor: ExecutionContext): AsyncWrappedSyncEntityIOContext =
    new AsyncWrappedSyncEntityIOContextImpl(syncEntityIOContext)

  /**
   * エクストラクタメソッド。
   *
   * @param asyncWrappedEntityIOContext [[org.sisioh.dddbase.core.lifecycle.forwarding.async.wrapped.AsyncWrappedSyncEntityIOContext]]
   * @return 構成要素
   */
  def unapply(asyncWrappedEntityIOContext: AsyncWrappedSyncEntityIOContext): Option[(SyncEntityIOContext)] =
    Some(asyncWrappedEntityIOContext.syncEntityIOContext)

}

private[wrapped]
case class AsyncWrappedSyncEntityIOContextImpl
(syncEntityIOContext: SyncEntityIOContext = SyncEntityIOContext)
(implicit val executor: ExecutionContext)
  extends AsyncWrappedSyncEntityIOContext

