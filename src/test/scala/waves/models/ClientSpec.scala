package waves.models

import org.scalatest.{FlatSpec, Matchers}
import waves.utils.Write

class ClientSpec extends FlatSpec with Matchers {
  private val write = new Write()

  it should "process order which nothing change" in {
    val ordersByClient = write.getOrders(Option("/orderForOneClient.txt")).groupBy(_.name)
    val client = List(Client("C1", 0, 0, 0, 0, 0))
    val newClients = client.map(client =>
      ordersByClient(client.name)
        .foldLeft(client) { (resultClient, order) => resultClient.transformByOrder(order) })
    newClients.head shouldEqual client.head
  }
}