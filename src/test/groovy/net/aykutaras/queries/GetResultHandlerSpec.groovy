package net.aykutaras.queries

import net.aykutaras.commands.StoreLeftHandler
import net.aykutaras.commands.StoreRightHandler
import net.aykutaras.messages.commands.LeftStore
import net.aykutaras.messages.commands.RightStore
import net.aykutaras.messages.queries.GetResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class GetResultHandlerSpec extends Specification {
  @Autowired
  private GetResultHandler handler

  @Autowired
  private StoreRightHandler storeRightHandler

  @Autowired
  private StoreLeftHandler storeLeftHandler

  def "Should find diffs of different strings with same length"() {
    given:
    storeLeftHandler.handle(new LeftStore("ID", "Sample String 1"))
    storeRightHandler.handle(new RightStore("ID", "Sample String 2"))

    when:
    def result = handler.handle(new GetResult("ID"))

    then:
    result.response == "Offset: 14, Length: 1"
  }

  def "Should return same for equal strings"() {
    given:
    storeLeftHandler.handle(new LeftStore("ID", "Sample String"))
    storeRightHandler.handle(new RightStore("ID", "Sample String"))

    when:
    def result = handler.handle(new GetResult("ID"))

    then:
    result.response == "Sides are same"
  }

  def "Should return error response for null side"() {
    given:
    storeLeftHandler.handle(new LeftStore("ID", "Sample String"))

    when:
    def result = handler.handle(new GetResult("ID"))

    then:
    result.response == "One of the sides are null"
  }
}
