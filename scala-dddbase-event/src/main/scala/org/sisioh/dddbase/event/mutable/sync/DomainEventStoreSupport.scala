package org.sisioh.dddbase.event.mutable.sync

import org.sisioh.dddbase.core.lifecycle.{EntityIOContext, Repository}
import org.sisioh.dddbase.core.model.Identifier
import org.sisioh.dddbase.event.sync.SyncDomainEventSubscriber
import org.sisioh.dddbase.event.{DomainEventStore, DomainEvent}
import scala.util.Try

/**
 * [[org.sisioh.dddbase.event.DomainEventStore]]のための骨格実装。
 *
 * @tparam R リポジトリの型
 * @tparam ID エンティティの識別子の型
 * @tparam T エンティティの型
 */
trait DomainEventStoreSupport
[+R <: Repository[ID, T, Try],
ID <: Identifier[_],
T <: DomainEvent[ID]]
  extends DomainEventStore[R, ID, T, Try, Unit]
  with SyncDomainEventSubscriber[T, Unit] {

  protected val eventRepository: R

  def handleEvent(event: T)(implicit ctx: EntityIOContext[Try]): Try[Unit] =
    eventRepository.store(event).map(_ => ())

}
