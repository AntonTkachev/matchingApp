package waves.utils

import java.io.{File, PrintWriter}

import waves.models.ClientsInfo

class Record {
  def toFile(fileName: String, accounts: Map[String, ClientsInfo]): Unit = {
    val strings = accounts.map { case (name, client) =>
      s"$name ${client.dollarsBalance} ${client.countA} ${client.countB} ${client.countC} ${client.countB}"
    }

    val pw = new PrintWriter(new File(fileName))

    pw.write(strings mkString "\n")
    pw.close()
  }
}
