package br.eng.rafaelsouza.trellometrics.parser

import br.eng.rafaelsouza.trellometrics.model.Action
import br.eng.rafaelsouza.trellometrics.model.Card

object TrelloMetricsParser {
  
  def parseActions(json: String):Seq[Action] = JsonJacksonSerialization.fromJson[Seq[Action]](json)
  def parseCard(json: String):Card = JsonJacksonSerialization.fromJson[Card](json)
  
}

