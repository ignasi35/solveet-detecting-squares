package com.marimon.scalatest

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import scala.math.sqrt
import scala.math.abs

@RunWith(classOf[JUnitRunner])
class SquareDetectorTest extends FlatSpec with ShouldMatchers {

  "A Square Detector" should "return false when input is not square" in {
    assert(List(Point(0, 0), Point(0, 3), Point(3, 0), Point(1, 2)), false)
  }

  it should "return true when input is ((0,0),(0,1),(1,0),(1,1)) or some other square" in {
    assert(List(Point(0, 0), Point(0, 1), Point(1, 0), Point(1, 1)), true)
    assert(List(Point(0, 0), Point(2, 1), Point(3, -1), Point(1, -2)), true)
    assert(List(Point(0, 0), Point(1, 1), Point(0, 1), Point(1, 0)), true)
  }

  def assert(points : List[Point], expected : Boolean) {
    SquareDetector.detect(points) should be(expected)
  }

}

case class Point(x : Int, y : Int)

object SquareDetector {
  /**
   * Computes all possible distances between the points provided. A square will comply with the assertion:
   * 4 distances are value 'a' and two are value 'b', where b^2=2*a^2
   */
  def detect(points : List[Point]) : Boolean = {
    val distancesMap = distances(points.head, points.tail) groupBy (d => d) mapValues (v => v.length)

    (distancesMap.keys.toList.sorted match {
      case List(c, h) => abs(square(h) - 2 * (square(c))) < 0.000000001
      case _          => false
    }) && distancesMap.values.toList.sorted == List(2, 4)
  }

  def dist(a : Point, b : Point) = sqrt(square(b.x - a.x) + square(b.y - a.y))

  def square(x : Int) : Int = x * x
  def square(x : Double) : Double = x * x

  def distances(source : Point, points : List[Point]) : List[Double] = {
    points match {
      case Nil => Nil
      case _ => {
        {
          for (p <- points) yield dist(source, p)
        } ::: distances(points.head, points.tail)
      }
    }
  }

}
