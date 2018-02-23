package net.aykutaras.commands

import net.aykutaras.domain.ComparableData
import net.aykutaras.domain.ComparableDataStore
import net.aykutaras.messages.commands.LeftStore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class StoreLeftHandlerSpec extends Specification {
  @Autowired
  private StoreLeftHandler handler

  @Autowired
  private ComparableDataStore store

  def "send simple data to handler"() {
    given:
    def request = new LeftStore(UUID.randomUUID().toString(), "Simple Data")

    when:
    def response = handler.handle request

    then:
    response.status == "SUCCESS"
  }

  def "try to set same data over and over again"() {
    given:
    def request = new LeftStore(UUID.randomUUID().toString(), "")

    when:
    handler.handle request
    handler.handle request
    handler.handle request
    handler.handle request
    def response = handler.handle request

    then:
    response.status == "SUCCESS"
  }

  def "try to set locked data and get exception"() {
    given:
    def id = UUID.randomUUID().toString();
    def data = new ComparableData("Left Data", "Right Data", true)
    store.put id, data
    def request = new LeftStore(id, "New Data")

    when:
    handler.handle request

    then:
    thrown IllegalStateException
  }
}
