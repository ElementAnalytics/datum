package datum.patterns

import datum.patterns.attributes.{Attribute, AttributeKey}
import qq.droste.data.Fix

import scala.collection.immutable.SortedMap

package object schemas {
  type Schema = Fix[SchemaF]

  def struct(attributes: Map[AttributeKey, Attribute] = Map.empty)(fields: (String, Schema)*): Schema = {
    Fix(ObjF(SortedMap(fields: _*), attributes))
  }

  def row(attributes: Map[AttributeKey, Attribute] = Map.empty)(elements: Column[Schema]*): Schema = {
    Fix(RowF(Vector(elements: _*)))
  }

  def value(
    tpe: Type,
    attributes: Map[AttributeKey, Attribute] = Map.empty
  ): Schema = {
    Fix.apply[SchemaF](ValueF(tpe, attributes))
  }
}
