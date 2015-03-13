package br.eng.rafaelsouza.trellometrics

import scalaj.http.Http
import scalaj.http.HttpResponse

class TrelloGateway {
  val key = "500883312ee38a44d357b1ac15a17524"
  val token = "4e78fd23dc573ab48081031048be2544414748c5b707ba2721a33d291ce750c3"
  val boardId = "dZDQVhM8"

  def listCardActions(cardId: String): String = {
    val requestURL = s"https://trello.com/1/cards/${cardId}/actions/"
    val response: HttpResponse[String] = Http(requestURL).params(
      ("key", key), ("token", token),
      ("filter", "updateCard:idList,createCard"),
      ("fields","id,data,type,date"),
      ("memberCreator","false")).asString
    response.body
  }

  def getCard(cardId: String): String = {
    val requestURL = s"https://trello.com/1/cards/${cardId}/"
    val response: HttpResponse[String] = Http(requestURL).params(
      ("key", key), ("token", token),
      ("fields","id,name")).asString
    response.body
  }

  def getBoardLists(): String = {
    val requestURL = s"https://trello.com/1/boards/${boardId}/lists"
    val response: HttpResponse[String] = Http(requestURL).params(
      ("key", key), ("token", token),
      ("fields","id,name")).asString
    response.body
  }

  def getListCards(listId: String ): String = {
    val requestURL = s"https://trello.com/1/lists/${listId}/cards"
    val response: HttpResponse[String] = Http(requestURL).params(
      ("key", key), ("token", token),
      ("fields","id,name,desc,date")).asString
    response.body
  }

}