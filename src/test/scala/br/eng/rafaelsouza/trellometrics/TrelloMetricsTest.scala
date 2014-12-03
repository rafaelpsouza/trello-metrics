package br.eng.rafaelsouza.trellometrics

import br.eng.rafaelsouza.trellometrics.model.Interval
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import scala.io.Source

@RunWith(classOf[JUnitRunner])
class TrelloMetricsTest extends FunSuite {

  test("Actions to intervals") {
   val expected = Seq(Interval("547e81a54059a489de64d5e9", "Backlog", formatDate("2014-12-03T03:21:30.631Z"), Some(formatDate("2014-12-03T03:21:37.955Z"))),
   Interval("547e81a72b6eea39db472e27", "Doing", formatDate("2014-12-03T03:21:37.955Z"), Some(formatDate("2014-12-03T03:22:54.499Z"))),
   Interval("547e81a82ecc159e130892ec", "Done", formatDate("2014-12-03T03:22:54.499Z"), None))
   val trelloMetrics = new TrelloMetrics(new FakeGateway())    
   assert(expected == trelloMetrics.actionsToIntervals("","",""))
  }
  
  def formatDate(date: String):DateTime ={
    ISODateTimeFormat.dateTime().withZoneUTC.parseDateTime(date)
  }
}

class FakeGateway extends TrelloGateway{
  override def listCardActions(cardId: String, key: String, token: String): String = {
     FakeResponse.card5Response
   }
}  

object FakeResponse {

  val card5Response: String = loadFakeJson("/card5.json")

  private def loadFakeJson(fileName: String): String = {
    Source.fromFile(getClass.getResource(fileName).getFile).getLines.mkString
  }

}
