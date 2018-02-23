package net.aykutaras.repository

import spock.lang.Specification

class CacheStoreSpec extends Specification {
  def "store simple string to left side"() {
    given:
    def store = new CacheStore()
    def id = UUID.randomUUID().toString()
    def data = "Some Simple Data"

    when:
    def isDataSet = store.setLeft(id, data)

    then:
    isDataSet
  }

  def "store simple string to right side"() {
    given:
    def store = new CacheStore()
    def id = UUID.randomUUID().toString()
    def data = "Some Simple Data"

    when:
    def isDataSet = store.setRight(id, data)

    then:
    isDataSet
  }

  def "retrieve only left filled item"() {
    given:
    def store = new CacheStore()
    def id = UUID.randomUUID().toString()
    def data = "Some Simple Data"
    store.setLeft(id, data)

    when:
    def comparableData = store.get(id)

    then:
    comparableData.left == data
    comparableData.right == null
  }

  def "retrieve both filled item"() {
    given:
    def store = new CacheStore()
    def id = UUID.randomUUID().toString()
    def data = "Some Simple Data"
    store.setLeft(id, data)
    store.setRight(id, data)

    when:
    def comparableData = store.get(id)

    then:
    comparableData.left == data
    comparableData.right == data
  }

  def "retrieve not available item"() {
    given:
    def store = new CacheStore()
    def id = UUID.randomUUID().toString()

    when:
    store.get id

    then:
    thrown NoSuchElementException
  }
}
