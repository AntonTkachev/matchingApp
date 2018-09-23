package waves.utils

import scala.io.Source

class Write {
  private def parseFiles(pathToFile: String): List[String] = {
    val source = Source.fromURL(getClass.getResource(pathToFile))
    val lines = source.getLines().toList
    source.close()
    lines
  }
}
