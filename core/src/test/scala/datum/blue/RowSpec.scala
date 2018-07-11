package datum.blue

import datum.blue.data.DataF
import datum.blue.ops.Corresponds
import datum.blue.schema.{IntegerType, SchemaF, TextType}
import org.scalatest.{Matchers, WordSpec}
import turtles.data.Fix

class RowSpec extends WordSpec with Matchers {

  val fixD = data.Specialize[Fix[DataF]]

  val fixS = schema.Specialize[Fix[SchemaF]]

  "list schemas" should {
    "be able to check correspondence of a simple list schema" in {

      val person = fixS.row(
        fixS.value(TextType),
        fixS.value(IntegerType)
      )()

      val p = fixD.row(
        Vector(
          fixD.text("bob"),
          fixD.integer(42)
        )
      )

      Corresponds(person)(p) shouldBe true
    }

    "be able to check correspondence with optional values (if the number of columns match)" in {
      val person = fixS.row(
        fixS.value(TextType),
        fixS.value(IntegerType, Map(attributes.common.optional -> attributes.property(true)))
      )()

      val p = fixD.row(Vector(fixD.text("wat"), fixD.text("?")))

      Corresponds(person)(p) shouldBe true
    }
  }
}