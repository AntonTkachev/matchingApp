package waves.utils

import org.scalatest.{FlatSpec, Matchers}
import waves.models._

class FileUtilsSpec extends FlatSpec with Matchers {

  val utils = new FileUtils()

  "getClients" should "correct parse clients.txt" in {
    val clients = utils.getClients()
    clients.length shouldEqual 9
    clients.head shouldEqual Client("C1", 1000, 130, 240, 760, 320)
  }

  "getOrders" should "correct parse orders.txt" in {
    val orders = utils.getOrders()
    orders.length shouldEqual 9
    orders.head shouldEqual Order("C8", "b", "C", 15, 4)
  }
}
