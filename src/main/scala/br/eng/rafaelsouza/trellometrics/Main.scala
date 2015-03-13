package br.eng.rafaelsouza.trellometrics


object Main extends App{

  val trelloMetrics = new TrelloMetrics()

  runApp()

  def runApp() = {
    val doneList = trelloMetrics.getLists().filter(list => list.name.equalsIgnoreCase("DONE"))(0)
    val cards = trelloMetrics.getListCards(doneList.id)
    cards.foreach(card => showTimeSpent(card.id))
  }

  def showTimeSpent(cardId : String): Unit = {
    println("--------------------------------------------------------------")
    val card = trelloMetrics.getCard(cardId)
    println(s"Card: ${card.name}")
    println("Spent time per list: ")
    val intervals = trelloMetrics.actionsToIntervals(cardId)
    intervals.map(i => println(s"${i.listName}: ${msToHour(i.period)} hours"))
    println("--------------------------------------------------------------")
  }

  def msToHour(ms: Option[Long]): Option[Long] = {
    ms.map(f => f / 3600000)
  }

  def printUsage() = {
    println("java -jar xxx.jar token")
  }

}