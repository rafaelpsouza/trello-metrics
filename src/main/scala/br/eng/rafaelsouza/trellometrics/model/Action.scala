package br.eng.rafaelsouza.trellometrics.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.joda.time.DateTime

case class Action(id: String, date: DateTime, @JsonProperty("type") typee:String, data: Data)
case class Data(board: Board, card: Card, listAfter: TrelloList, listBefore: TrelloList, list: Option[TrelloList])
case class TrelloList(id: String, name: String)
case class Board(id: String, name: String)
case class Card(id: String, name: String)

case class Interval(listId: String, listName: String, startDate: DateTime, endDate: Option[DateTime] = None) {
  def period: Option[Long] = endDate.map(end => end.getMillis - startDate.getMillis)
}