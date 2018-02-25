package net.aykutaras.controller

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

  def "testing api testing"() {
    given:
    def a = 1

    when:
    restTemplate.getForObject("http://localhost:$port/v1/diff/left", String.class)

    then:
    1==1
  }
}
