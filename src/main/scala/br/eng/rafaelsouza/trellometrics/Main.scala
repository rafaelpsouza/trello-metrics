package br.eng.rafaelsouza.trellometrics


object Main extends App {

  val trelloMetrics = new TrelloMetrics()

  if(args.length == 4){
    runApp(args(0), args(1), args(2), args(3))
  }else{
    printUsage
  }

  def runApp(boardId: String, listName: String, token: String, key: String) = {
    val doneList = trelloMetrics.getLists(boardId, token, key).filter(list => list.name.equalsIgnoreCase(listName))(0)
    val cards = trelloMetrics.getListCards(doneList.id, token, key)
    cards.foreach(card => showTimeSpent(card.id, token, key))
  }

  def showTimeSpent(cardId : String, token: String, key: String): Unit = {
    println("--------------------------------------------------------------")
    val card = trelloMetrics.getCard(cardId, token, key)
    println(s"Card: ${card.name}")
    println("Spent time per list: ")
    val intervals = trelloMetrics.actionsToIntervals(cardId, token, key)
    intervals.map(i => println(s"${i.listName}: ${msToHour(i.period)} hours"))
    println("--------------------------------------------------------------")
  }

  def msToHour(ms: Option[Long]): Option[Long] = {
    ms.map(f => f / 3600000)
  }

  def printUsage() = {
    println("./trello-metrics boardId listName token key")
  }

}