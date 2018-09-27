package waves

import waves.utils.{Constants, FileUtils}

object Main {
  private val utils = new FileUtils()

  def main(args: Array[String]): Unit = {
    val ordersByClient = utils.getOrders().groupBy(_.name)
    val newClients = utils.getClients().map(client =>
      ordersByClient(client.name)
        .foldLeft(client) { (resultClient, order) => resultClient.transformByOrder(order) })
    utils.writeToFile(Constants.Paths.DefaultResultStore, newClients)
  }
}
