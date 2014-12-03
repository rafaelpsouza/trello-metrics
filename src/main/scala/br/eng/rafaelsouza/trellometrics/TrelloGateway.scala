package br.eng.rafaelsouza.trellometrics

import scalaj.http.Http
import scalaj.http.HttpResponse

object TrelloGateway {
  
  def listCardActions(cardId: String, key: String, token: String): String = {
    val requestURL = s"https://trello.com/1/cards/${cardId}/actions/"
    val response: HttpResponse[String] = Http(requestURL).params(
      ("key",key), ("token", token),
      ("filter", "updateCard:idList,createCard"),
      ("fields","id,data,type,date"),
      ("memberCreator","false")).asString
    response.body
  }
  
}