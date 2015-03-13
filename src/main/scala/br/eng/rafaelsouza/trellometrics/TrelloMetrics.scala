package br.eng.rafaelsouza.trellometrics

import br.eng.rafaelsouza.trellometrics.model.{TrelloList, Action, Card, Interval}
import br.eng.rafaelsouza.trellometrics.parser.TrelloMetricsParser
import org.joda.time.DateTime

class TrelloMetrics(trelloGateway: TrelloGateway = new TrelloGateway()) {

  def actionsToIntervals(cardId: String) = {
    val actions = sortActionsByDate(trelloGateway.listCardActions(cardId))
    val created = takeCreatedAction(actions)
    val firstInterval = Interval(created.data.list.get.id, created.data.list.get.name, created.date)
    actions.drop(1).foldLeft(Seq(firstInterval)){
      (intervals: Seq[Interval], currentAction: Action) => {
        updateLastInterval(intervals, currentAction.date) :+ Interval(currentAction.data.listAfter.id, currentAction.data.listAfter.name, currentAction.date)
      }
    }
  }

  def getCard(cardId: String): Card = {
    TrelloMetricsParser.parseCard(trelloGateway.getCard(cardId))
  }

  def getLists(): List[TrelloList] = {
    TrelloMetricsParser.parseBoardLists(trelloGateway.getBoardLists())
  }

  def getListCards(listId: String): List[Card] = {
    TrelloMetricsParser.parseListCards(trelloGateway.getListCards(listId))
  }

  private def updateLastInterval(intervals: Seq[Interval], endDate: DateTime): Seq[Interval] = {
    val lastInterval = intervals.last.copy(endDate = Some(endDate))
    intervals.take(intervals.length - 1) :+ lastInterval
  }

  private def takeCreatedAction(actions: Seq[Action]):Action = actions.head

  private def sortActionsByDate(actions: String):Seq[Action] ={
    TrelloMetricsParser.parseActions(actions).sortBy(_.date.getMillis)
  }

}