package br.eng.rafaelsouza.trellometrics

import br.eng.rafaelsouza.trellometrics.model.Interval
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.ISODateTimeFormat
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import scala.io.Source

@RunWith(classOf[JUnitRunner])
class TrelloMetricsTest extends FunSuite {

  test("Actions to interval") {
   val expected = Seq(Interval("547e81a54059a489de64d5e9", formatDate("2014-12-03T03:21:30.631Z"), Some(formatDate("2014-12-03T03:21:37.955Z"))),
   Interval("547e81a72b6eea39db472e27", formatDate("2014-12-03T03:21:37.955Z"), Some(formatDate("2014-12-03T03:22:54.499Z"))),
   Interval("547e81a82ecc159e130892ec", formatDate("2014-12-03T03:22:54.499Z"), None))
   assert(expected == TrelloMetrics.actionsToIntervals(FakeResponse.card5Response))
  }
  
  test("Print times") {
    println("--------------------------------------------------------------")
    println("Time in each list")
    val intervals = TrelloMetrics.actionsToIntervals(FakeResponse.card5Response)
    intervals.map(i => println(s"List: ${i.listId} time: ${msToHour(i.period)} ms"))    
    println("--------------------------------------------------------------")  
  }
  
  def formatDate(date: String):DateTime ={
    ISODateTimeFormat.dateTime().withZoneUTC.parseDateTime(date)
  }
  
  def msToHour(ms: Option[Long]): Option[Long] = {
    ms.map(f => f / 3600000)
  }
  
}

object FakeResponse {

  val card5Response: String = loadFakeJson("/card5.json")

  private def loadFakeJson(fileName: String): String = {
    Source.fromFile(getClass.getResource(fileName).getFile).getLines.mkString
  }

}
