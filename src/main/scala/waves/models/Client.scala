package waves.models

import waves.utils.Constants

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
    * @param order            - order for this client
    * @param actionByCurrency - how should we change the amount of currency (it may be [[minus]] or [[plus]], but actionByDollars should be revers)
    * @param actionByDollars  - how should we change the amount of dollars (it may be [[minus]] or [[plus]], but actionByCurrency should be revers)
    * @return [[Client]] after all transformations
    */
  private def changeClientCurrencyType(order: Order, actionByCurrency: (Int, Int) => Int, actionByDollars: (Int, Int) => Int): Client = {
    (order.currencyType match {
      case Constants.CurrencyA => this.copy(A = actionByCurrency(this.A, order.count))
      case Constants.CurrencyB => this.copy(B = actionByCurrency(this.B, order.count))
      case Constants.CurrencyC => this.copy(C = actionByCurrency(this.C, order.count))
      case Constants.CurrencyD => this.copy(D = actionByCurrency(this.D, order.count))
    }).copy(dollarsBalance = actionByDollars(this.dollarsBalance, order.cost * order.count))
  }

  /**
    * @param order - order for this client
    * @return [[Client]] after all transformations
    */
  def transformByOrder(order: Order): Client = {
    if (order.count > 0) {
      order.dealType match {
        case Constants.Buy => changeClientCurrencyType(order, plus, minus)
        case Constants.Sell => changeClientCurrencyType(order, minus, plus)
      }
    } else this
  }
}
