package waves.utils

import org.scalatest.{FlatSpec, Matchers}
import waves.models._

class WriteSpec extends FlatSpec with Matchers {

  val write = new Write()

  "getClients" should "correct parse clients.txt" in {
    val clients = write.getClients()
    clients.length shouldEqual 9
    clients.head shouldEqual Client("C1", 1000, 130, 240, 760, 320)
  }

  "getOrders" should "correct parse orders.txt" in {
    val orders = write.getOrders()
    orders.length shouldEqual 9
    orders.head shouldEqual Order("C8", "b", "C", 15, 4)
  }
}
