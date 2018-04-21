package chapter_2

import org.scalatest.{FlatSpec, Matchers}

class PaymentTest extends FlatSpec with Matchers {
  it should "return a*a*3 of a given value a" in {
    val payment = new Payment()

    assert(payment.compose.apply(payment.square).apply(payment.triple).apply(2) == 36)
    assert(payment.higherCompose().apply(payment.square).apply(payment.triple).apply(2) == 36)
    assert(payment.higherAndThen().apply(payment.square).apply(payment.triple).apply(2) == 12)
  }

  it should "return a factorial value" in {
    val payment = new Payment()

    assert(payment.factorial.apply(3) == 6)
  }


}
