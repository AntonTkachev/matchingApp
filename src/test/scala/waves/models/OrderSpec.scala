package waves.models

import org.scalatest.{FlatSpec, Matchers}

class OrderSpec extends FlatSpec with Matchers {

  "second constructor" should "correct parse array to Order" in {
    val name = "C8"
    val dealType = "b"
    val currencyType = "C"
    val cost = 15
    val count = 4

    val order = new Order(Array(name, dealType, currencyType, cost.toString, count.toString))
    order.name shouldEqual name
    order.dealType shouldEqual dealType
    order.currencyType shouldEqual currencyType
    order.cost shouldEqual cost
    order.count shouldEqual count
  }
}
