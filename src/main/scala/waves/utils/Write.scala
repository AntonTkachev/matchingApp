package waves.utils

import scala.io.Source
import waves.models.Order
import waves.models.Client

class Write {
  /**
    * @param pathToFile - to file for parse
    * @return list where each element [[Client]] or [[Order]]
    */
  private def parseFiles(pathToFile: String): List[String] = {
    val source = Source.fromURL(getClass.getResource(pathToFile))
    val lines = source.getLines().toList
    source.close()
    lines
  }

  /**
    * @return all clients for precessing
    */
  def getClients: List[Client] = parseFiles(Meta.clientsFilesName).map(line => new Client(line.split("\t")))

  /**
    * @return all orders for precessing
    */
  def getOrders: List[Order] = parseFiles(Meta.ordersFilesName).map(line => new Order(line.split("\t")))
}
