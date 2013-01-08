package com.marimon.scalatest

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SillySuite extends FunSuite {

  case class CaseClass(name: String, surname: String)

  test("A case class should be usable from Java code") {
    new JavaCode().invokeToString(new CaseClass("name", "surname"))
  }
  test("A case class should be usable from Java code") {
    new JavaCode().someCoolStuff("foobar")
  }

}
