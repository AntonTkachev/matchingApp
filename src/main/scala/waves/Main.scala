package waves

import waves.utils.{Meta, Record, Write}

object Main {
  private val record = new Record()
  private val write = new Write()

  def main(args: Array[String]): Unit = {
    val ordersByClient = write.getOrders().groupBy(_.name)
    val newClients = write.getClients().map(client =>
      ordersByClient(client.name)
        .foldLeft(client) { (resultClient, order) => resultClient.transformByOrder(order) })
    record.writeToFile(Meta.resultFilesName, newClients)
  }
}
