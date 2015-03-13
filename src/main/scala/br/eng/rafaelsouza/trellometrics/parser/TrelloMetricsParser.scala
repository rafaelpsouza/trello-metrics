package br.eng.rafaelsouza.trellometrics.parser

import br.eng.rafaelsouza.trellometrics.model.{TrelloList, Action, Card}

object TrelloMetricsParser {

  def parseActions(json: String):Seq[Action] = JsonJacksonSerialization.fromJson[Seq[Action]](json)
  def parseCard(json: String):Card = JsonJacksonSerialization.fromJson[Card](json)
  def parseBoardLists(json: String):List[TrelloList] = JsonJacksonSerialization.fromJson[List[TrelloList]](json)
  def parseListCards(json: String):List[Card] = JsonJacksonSerialization.fromJson[List[Card]](json)

}
