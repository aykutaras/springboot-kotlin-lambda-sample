package net.aykutaras.repository

import net.aykutaras.domain.ComparableData
import spock.lang.Specification

class CacheStoreSpec extends Specification {
  def "store simple string to left side"() {
    given:
    def store = new CacheStore()
    def id = UUID.randomUUID().toString()
    def data = "Some Simple Data"
    def comparableData = new ComparableData()
    comparableData.addDataToLeft data

//    when:
    store.put id, comparableData
  }

  def "store simple string to right side"() {
    given:
    def store = new CacheStore()
    def id = UUID.randomUUID().toString()
    def data = "Some Simple Data"
    def comparableData = new ComparableData()
    comparableData.addDataToRight data

//    when:
    store.put id, comparableData
  }

  def "retrieve only left filled item"() {
    given:
    def store = new CacheStore()
    def id = UUID.randomUUID().toString()
    def data = "Some Simple Data"
    def comparableData = new ComparableData()
    comparableData.addDataToLeft data
    store.put id, comparableData

    when:
    def storedData = store.get id

    then:
    storedData.left == data
    storedData.right == null
  }

  def "retrieve both filled item"() {
    given:
    def store = new CacheStore()
    def id = UUID.randomUUID() toString()
    def data = "Some Simple Data"
    def comparableData = new ComparableData()
    comparableData.addDataToLeft data
    store.put id, comparableData

    comparableData.addDataToRight data
    store.put id, comparableData

    when:
    def storedData = store.get id

    then:
    storedData.left == data
    storedData.right == data
  }

  def "retrieve empty item for not existing item"() {
    given:
    def store = new CacheStore()
    def id = UUID.randomUUID() toString()

    when:
    def storedData = store.get id

    then:
    storedData.getLeft() == null
    storedData.getRight() == null
  }
}
