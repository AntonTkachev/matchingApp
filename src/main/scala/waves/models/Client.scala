package waves.models

import waves.utils.Meta

/**
  * @param name           - clients id
  * @param dollarsBalance - numbers of dollars
  * @param A              - numbers of currency type A
  * @param B              - numbers of currency type B
  * @param C              - numbers of currency type C
  * @param D              - numbers of currency type D
  */
case class Client(name: String, dollarsBalance: Int, A: Int, B: Int, C: Int, D: Int) {
  def this(array: Array[String]) = {
    this(array.head, array(1).toInt, array(2).toInt, array(3).toInt, array(4).toInt, array(5).toInt)
  }

  private def minus(f: Int, s: Int): Int = f - s

  private def plus(f: Int, s: Int): Int = f + s

  /**
    * @param order         - order for this client
    * @param funByCurrency - how should we change the amount of currency (it may be [[minus]] or [[plus]], but funByDollars should be revers)
    * @param funByDollars  - how should we change the amount of dollars (it may be [[minus]] or [[plus]], but funByCurrency should be revers)
    * @return [[Client]] after all transformations
    */
  private def changeClientCurrencyType(order: Order, funByCurrency: (Int, Int) => Int, funByDollars: (Int, Int) => Int): Client = {
    (order.currencyType match {
      case Meta.currencyA => this.copy(A = funByCurrency(this.A, order.count))
      case Meta.currencyB => this.copy(B = funByCurrency(this.B, order.count))
      case Meta.currencyC => this.copy(C = funByCurrency(this.C, order.count))
      case Meta.currencyD => this.copy(D = funByCurrency(this.D, order.count))
    }).copy(dollarsBalance = funByDollars(this.dollarsBalance, order.cost * order.count))
  }

  /**
    * @param order - order for this client
    * @return [[Client]] after all transformations
    */
  def transformByOrder(order: Order): Client = {
    if (order.count > 0) {
      order.dealType match {
        case Meta.Buy => changeClientCurrencyType(order, plus, minus)
        case Meta.Sell => changeClientCurrencyType(order, minus, plus)
      }
    } else this
  }
}