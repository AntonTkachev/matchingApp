package waves.models

case class OrderInfo(name: String,
                     transactionsType: String,
                     nameCurrency: String,
                     priceForOne: Int,
                     count: Int) {
  def this(arr: Array[String]) = {
    this(arr.head, arr(1), arr(2), arr(3).toInt, arr(4).toInt)
  }

/*  private val minus: (Int, Int) => Int = (a: Int, b: Int) => a - b
  private val plus: (Int, Int) => Int = (a: Int, b: Int) => a + b

  def doMinus(client: ClientsInfo): ClientsInfo = change(client, minus)

  def doPlus(client: ClientsInfo): ClientsInfo = change(client, plus)

  private def change(client: ClientsInfo, fun: ((Int, Int) => Int)): ClientsInfo = {
    this.transactionsType match {
      case "A" => client.copy(countA = fun(client.countA, this.count))
      case "B" => client.copy(countB = client.countB + this.count)
      case "C" => client.copy(countC = client.countC + this.count)
      case "D" => client.copy(countD = client.countD + this.count)
    }
  }*/
}


