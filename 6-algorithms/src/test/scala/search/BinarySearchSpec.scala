package search

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class BinarySearchSpec extends AnyWordSpec with Matchers {

  import BinarySearch._
  "binary search" should {

    "search(Array(), 3) === None" in {
      assert(indexOf(Array(), 3) === None)
    }

    "search(Array(1, 3, 7, 9, 13), 3) === Some(1)" in {
      assert(indexOf(Array(1, 3, 7, 9, 13), 3) === Some(1))
    }

    "search(Array(1, 3, 7, 9, 13), 9) === Some(3)" in {
      assert(indexOf(Array(1, 3, 7, 9, 13), 9) === Some(3))
    }
    "search(Array(1, 3, 7, 9, 13), 7) === Some(2)" in {
      assert(indexOf(Array(1, 3, 7, 9, 13), 7) === Some(2))
    }
    "search(Array(1, 3, 7, 9, 13), 8) === None" in {
      assert(indexOf(Array(1, 3, 7, 9, 13), 8) === None)
    }
    "search(Array(1, 3, 7, 9, 13), 100) === None" in {
      assert(indexOf(Array(1, 3, 7, 9, 13), 100) === None)
    }

    """search(Vector("i", "am", "cow", "hear", "me", "moo"), cow) === Some(2)""" in {
      assert(indexOf(Array("i", "am", "cow", "hear", "me", "moo"), "cow") === Some(2))
    }
    """search(Vector("i", "am", "cow", "hear", "me", "moo"), moo) === Some(5)""" in {
      assert(indexOf(Array("i", "am", "cow", "hear", "me", "moo"), "moo") === Some(5))
    }
    """search(Vector("i", "am", "cow", "hear", "me", "moo"), horse) === None""" in {
      assert(indexOf(Array("i", "am", "cow", "hear", "me", "moo"), "horse") === None)
    }
  }

}
