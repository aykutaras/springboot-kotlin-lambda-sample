package net.aykutaras.domain

import spock.lang.Specification

class ComparableDataSpec extends Specification {
  def "Should add data to left and right"() {
    given:
    def data = new ComparableData()

    when:
    data.addDataToLeft("Left Data")
        .addDataToRight("Right Data")

    then:
    data.left == "Left Data"
    data.right == "Right Data"
  }

  def "Should throw exception when adding locked data"() {
    given:
    def data = new ComparableData()
    data.lock()

    when:
    data.addDataToLeft("Left Data")

    then:
    thrown IllegalStateException
  }

  def "Should add data locked data when unlocked"() {
    given:
    def data = new ComparableData()
    data.addDataToRight("Sample Data")
    .lock()
    .unlock()

    when:
    data.addDataToLeft("Sample Data")

    then:
    data.left == "Sample Data"
    data.right == "Sample Data"
  }
}
