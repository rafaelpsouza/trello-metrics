package br.eng.rafaelsouza.trellometrics

import scalaj.http.Http
import scalaj.http.HttpResponse

class TrelloGateway {

  def listCardActions(cardId: String, token: String, key: String): String = {
    val requestURL = s"https://trello.com/1/cards/${cardId}/actions/"
    val response: HttpResponse[String] = Http(requestURL).params(
      ("key", key), ("token", token),
      ("filter", "updateCard:idList"),
      ("fields","id,data,type,date"),
      ("memberCreator","false")).asString
    response.body
  }

  def getCard(cardId: String, token: String, key: String): String = {
    val requestURL = s"https://trello.com/1/cards/${cardId}/"
    val response: HttpResponse[String] = Http(requestURL).params(
      ("key", key), ("token", token),
      ("fields","id,name")).asString
    response.body
  }

  def getBoardLists(boardId: String, token: String, key: String): String = {
    val requestURL = s"https://trello.com/1/boards/${boardId}/lists"
    val response: HttpResponse[String] = Http(requestURL).params(
      ("key", key), ("token", token),
      ("fields","id,name")).asString
    response.body
  }

  def getListCards(listId: String, token: String, key: String): String = {
    val requestURL = s"https://trello.com/1/lists/${listId}/cards"
    val response: HttpResponse[String] = Http(requestURL).params(
      ("key", key), ("token", token),
      ("fields","id,name,desc,date")).asString
    response.body
  }

}