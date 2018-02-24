package net.aykutaras.service

import net.aykutaras.domain.ComparableData
import spock.lang.Shared
import spock.lang.Specification

class ComparerServiceImplSpec extends Specification {
  @Shared ComparerServiceImpl comparer

  def setupSpec() {
    comparer = new ComparerServiceImpl()
  }

  def "compare simple base64 strings"(String left, String right, String result) {
    given:
    def data = prepareData(left, right)

    expect:
    comparer.compare(data) == result

    where:
    left               | right              | result
    "SampleString"     | "SampleString"     | "Sides are same"
    "Bigger String"    | "Min String"       | "Sides are different length"
    "Sample String 1"  | "Sample String 2"  | "Offset: 19, Length: 1"
    "Rample String 1"  | "Sample String 2"  | "Offset: 1, Length: 1; Offset: 19, Length: 1"
    "Totally String 1" | "Samplee String 2" | "Offset: 0, Length: 6; Offset: 21, Length: 1; Offset: 7, Length: 2"
    null               | "Sample String"    | "One of the sides are null"
    "Sample String"    | null               | "One of the sides are null"
    null               | null               | "One of the sides are null"
  }


  def prepareData(String left, String right) {
    def data = new ComparableData()

    if (left != null) {
      def leftBase64 = Base64.encoder.encodeToString(left.bytes)
      data.addDataToLeft(leftBase64)
    }

    if (right != null) {
      def rightBase64 = Base64.encoder.encodeToString(right.bytes)
      data.addDataToRight(rightBase64)
    }

    data.lock()
  }
}
