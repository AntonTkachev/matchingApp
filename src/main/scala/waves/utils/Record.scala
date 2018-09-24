package waves.utils

import java.io.{File, PrintWriter}

import waves.models.Client

class Record {
  /**
    * @param fileName - name file where write result clients info
    * @param clients  - list with [[Client's]] where each element transform to string
    */
  def writeToFile(fileName: String, clients: List[Client]): Unit = {
    val line = clients.sortBy(_.name)
      .map(client => s"${client.name} ${client.dollarsBalance} ${client.A} ${client.B} ${client.C} ${client.D}")
    val pw = new PrintWriter(new File(fileName))

    pw.write(line.mkString("\n"))
    pw.close()
  }
}
