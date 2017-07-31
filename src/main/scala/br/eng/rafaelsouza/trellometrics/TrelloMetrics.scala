package br.eng.rafaelsouza.trellometrics

import br.eng.rafaelsouza.trellometrics.model.{TrelloList, Action, Card, Interval}
import br.eng.rafaelsouza.trellometrics.parser.TrelloMetricsParser
import org.joda.time.DateTime

class TrelloMetrics(trelloGateway: TrelloGateway = new TrelloGateway()) {

  def actionsToIntervals(cardId: String, token: String, key: String) = {
    val actions = sortActionsByDate(trelloGateway.listCardActions(cardId, token, key))
    val firstInterval = Interval(actions.head.data.listBefore.id, actions.head.data.listBefore.name, getCreatedDate(cardId))

    actions.foldLeft(Seq(firstInterval)){
      (intervals: Seq[Interval], currentAction: Action) => {
        updateLastInterval(intervals, currentAction.date) :+ Interval(currentAction.data.listAfter.id, currentAction.data.listAfter.name, currentAction.date)
      }
    }
  }

  def getCard(cardId: String, token: String, key: String): Card = {
    TrelloMetricsParser.parseCard(trelloGateway.getCard(cardId, token, key))
  }

  def getLists(boardId: String, token: String, key: String): List[TrelloList] = {
    TrelloMetricsParser.parseBoardLists(trelloGateway.getBoardLists(boardId, token, key))
  }

  def getListCards(listId: String, token: String, key: String): List[Card] = {
    TrelloMetricsParser.parseListCards(trelloGateway.getListCards(listId, token, key))
  }

  private def updateLastInterval(intervals: Seq[Interval], endDate: DateTime): Seq[Interval] = {
    val lastInterval = intervals.last.copy(endDate = Some(endDate))
    intervals.take(intervals.length - 1) :+ lastInterval
  }

  private def getCreatedDate(cardId: String):DateTime = {
    val unixTimestamp = Integer.parseInt(cardId.substring(0, 8), 16)
    new DateTime(unixTimestamp * 1000L)
  }

  private def sortActionsByDate(actions: String):Seq[Action] ={
    TrelloMetricsParser.parseActions(actions).sortBy(_.date.getMillis)
  }

}