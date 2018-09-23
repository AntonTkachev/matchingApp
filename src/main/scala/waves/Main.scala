package waves

import waves.models.{ClientsInfo, OrderInfo}
import waves.utils.{Record, Write}

import scala.io.Source

object Main {

  private def record = new Record()

  private def write = new Write()

  private val ResultFile = "/record.txt"

  private def parseFiles(pathToFile: String): List[String] = {
    val source = Source.fromURL(getClass.getResource(pathToFile))
    val lines = source.getLines().toList
    source.close()
    lines
  }

  private def fileToClients(lines: List[String]) = {
    val map = scala.collection.mutable.Map.empty[String, ClientsInfo]
    lines.foreach { line =>
      val array = line.split("\t")
      map += (array.head -> new ClientsInfo(array.tail))
    }
    map
  }

  private def fileToOrders(lines: List[String]): List[OrderInfo] =
    lines.map(line => new OrderInfo(line.split("\t")))

  def main(args: Array[String]): Unit = {
    val oldClients = fileToClients(parseFiles("/clients.txt"))
    val clients = fileToClients(parseFiles("/clients.txt"))
    val orders = fileToOrders(parseFiles("/orders.txt"))
    orders.foreach { order =>
      clients.filter(el => el._1 == order.name)
        .foreach { case (name, clientToChange) =>
          val clientAfterChange = order.transactionsType match {
            case "b" => clientToChange.buy(order)
            case "s" => clientToChange.sell(order)
          }
          clients.update(name, clientAfterChange)
        }
    }
    record.toFile(ResultFile, clients.toMap)
  }
}
