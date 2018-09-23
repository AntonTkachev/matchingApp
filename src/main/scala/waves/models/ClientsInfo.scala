package waves.models

case class ClientsInfo(dollarsBalance: Int,
                       countA: Int,
                       countB: Int,
                       countC: Int,
                       countD: Int) {
  def this(arr: Array[String]) = this(arr(0).toInt, arr(1).toInt, arr(2).toInt, arr(3).toInt, arr(4).toInt)

  private val minus: (Int, Int) => Int = (a: Int, b: Int) => a - b
  private val plus: (Int, Int) => Int = (a: Int, b: Int) => a + b

  def sell(order: OrderInfo): ClientsInfo = changeWithFunction(order, minus).changeDollarsBalance(order, minus)

  def buy(order: OrderInfo): ClientsInfo = changeWithFunction(order, plus).changeDollarsBalance(order, plus)

  private def changeWithFunction(order: OrderInfo, fun: ((Int, Int) => Int)): ClientsInfo = {
    order.nameCurrency match {
      case "A" => this.copy(countA = fun(this.countA, order.count))
      case "B" => this.copy(countB = fun(this.countB, order.count))
      case "C" => this.copy(countC = fun(this.countC, order.count))
      case "D" => this.copy(countD = fun(this.countD, order.count))
    }
  }

  private def changeDollarsBalance(order: OrderInfo, fun: ((Int, Int) => Int)): ClientsInfo =
    this.copy(dollarsBalance = fun(this.dollarsBalance, order.count * order.priceForOne))
}