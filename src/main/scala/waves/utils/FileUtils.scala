package waves.utils

import java.io.File

import org.apache.commons.io.FileUtils

import scala.io.Source
import waves.models.Order
import waves.models.Client

import scala.util.{Failure, Success, Try}

class FileUtils {
  /**
    * @param path - to file for parse
    * @return list where each element [[Client]] or [[Order]]
    */
  private def parseFile(path: String): List[String] = {
    Try {
      val source = Source.fromURL(getClass.getResource(path)) //TODO add Try (recoverWith)
      val lines = source.getLines().toList
      source.close()
      lines
    } match {
      case Success(lines) => lines
      case Failure(ex) => throw ex
    }
  }

  /**
    * @return all clients for precessing
    */
  def getClients(pathToFile: String = Constants.Paths.DefaultClientStore): List[Client] =
    parseFile(pathToFile).map(line => new Client(line.split(Constants.SplitBy)))

  /**
    * @return all orders for precessing
    */
  def getOrders(pathToFile: String = Constants.Paths.DefaultOrderStore): List[Order] =
    parseFile(pathToFile).map(line => new Order(line.split(Constants.SplitBy)))

  /**
    * @param fileName - name file where write result clients info
    * @param clients  - list with [[Client's]] where each element transform to string
    */
  def writeToFile(fileName: String, clients: List[Client]): Unit = {
    val line = clients.sortBy(_.name)
      .map(client => s"${client.name} ${client.dollarsBalance} ${client.A} ${client.B} ${client.C} ${client.D}")
    FileUtils.writeStringToFile(new File(fileName), line.mkString("\n"))
  }
}
