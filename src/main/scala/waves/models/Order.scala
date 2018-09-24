package waves.models

/**
  * @param name         - orders id
  * @param dealType     - what type of deal, buy or sell
  * @param currencyType - what type currency used in this deal
  * @param cost         - cost for one share
  * @param count        - numbers of shares
  */
case class Order(name: String, dealType: String, currencyType: String, cost: Int, count: Int) {
  def this(array: Array[String]) = {
    this(array.head, array(1), array(2), array(3).toInt, array(4).toInt)
  }
}


