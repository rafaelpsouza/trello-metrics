package br.eng.rafaelsouza.trellometrics

import scalaj.http.Http
import scalaj.http.HttpResponse

class TrelloGateway {
  
  def listCardActions(cardId: String, key: String, token: String): String = {
    val requestURL = s"https://trello.com/1/cards/${cardId}/actions/"
    val response: HttpResponse[String] = Http(requestURL).params(
      ("key", key), ("token", token),
      ("filter", "updateCard:idList,createCard"),
      ("fields","id,data,type,date"),
      ("memberCreator","false")).asString
    response.body
  }
  
  def getCard(cardId: String, key: String, token: String): String = {
    val requestURL = s"https://trello.com/1/cards/${cardId}/"
    val response: HttpResponse[String] = Http(requestURL).params(
      ("key", key), ("token", token),
      ("fields","id,name")).asString
    response.body
  }
  
}