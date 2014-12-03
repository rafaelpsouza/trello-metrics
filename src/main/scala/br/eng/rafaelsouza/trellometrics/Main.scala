package br.eng.rafaelsouza.trellometrics

object Main extends App{
   
  if(args.length == 3){
    runApp(args(0), args(1), args(2))
  }else{
    printUsage
  }

  def runApp(token: String, key: String, cardId: String) = {
    println("--------------------------------------------------------------")
    println(s"Card: ${cardId}")
    println("Spent time per list: ")
    val intervals = TrelloMetrics.actionsToIntervals(TrelloGateway.listCardActions(cardId, key, token))
    intervals.map(i => println(s"${i.listName}: ${msToHour(i.period)} hours"))    
    println("--------------------------------------------------------------")  
  }
  
  def msToHour(ms: Option[Long]): Option[Long] = {
    ms.map(f => f / 3600000)
  } 
  
  def printUsage() = {
    println("java -jar xxx.jar token key cardId")
  }
  
}
