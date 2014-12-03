package br.eng.rafaelsouza.trellometrics.parser

import br.eng.rafaelsouza.trellometrics.model.Action

object TrelloMetricsParser {
  
  def parseActions(json: String):Seq[Action] = JsonJacksonSerialization.fromJson[Seq[Action]](json) 
  
}

