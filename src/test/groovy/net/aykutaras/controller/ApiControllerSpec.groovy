package net.aykutaras.controller

import net.aykutaras.controller.request.DataStoreRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiControllerSpec extends Specification {
  @LocalServerPort
  def port

  @Autowired
  TestRestTemplate restTemplate

  def "api testing sample for diff"() {
    given:
    restTemplate.postForEntity("http://localhost:$port/v1/diff/SOME_ID/left", new DataStoreRequest("Sample String"), String.class)
    restTemplate.postForEntity("http://localhost:$port/v1/diff/SOME_ID/right", new DataStoreRequest("Sample String"), String.class)

    when:
    def response = restTemplate.getForObject("http://localhost:$port/v1/diff/SOME_ID", String.class)

    then:
    response == '{"response":"Sides are same"}'
  }
}
