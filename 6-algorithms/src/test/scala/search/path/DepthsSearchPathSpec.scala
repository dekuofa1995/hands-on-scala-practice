package search.path

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DepthsSearchPathSpec extends AnyWordSpec with Matchers {

  import DepthSearchPaths._
  "searchPaths" should {
    "empty graph return start -> List(start)" in {
      val paths = searchPaths("a", Map())
      assert(paths === Map("a" -> List("a")))
    }

    "start node with empty path return start -> List(start) " in {
      val paths = searchPaths("a", Map("a" -> List("a")))
      assert(paths === Map("a" -> List("a")))
    }

    "return all paths 1" in {
      val paths = searchPaths("a", Map("a" -> List("a", "b"), "b" -> List("a")))
      assert(paths === Map("a" -> List("a"), "b" -> List("b", "a")))
    }

    "return all paths 2" in {
      val paths = searchPaths("a", Map("a" -> List("b"), "b" -> List()))
      assert(paths === Map("a" -> List("a"), "b" -> List("b", "a")))
    }

    "return all paths 3" in {
      val paths =
        searchPaths("a", Map("a" -> List("b"), "b" -> List("c"), "c" -> List("d"), "d" -> List()))
      assert(
        paths === Map(
          "a" -> List("a"),
          "b" -> List("b", "a"),
          "c" -> List("c", "b", "a"),
          "d" -> List("d", "c", "b", "a")
        )
      )
    }

    "return all paths 4" in {
      val paths = searchPaths(
        "a",
        Map("a" -> List("b", "c"), "b" -> List("c", "d"), "c" -> List("d"), "d" -> List())
      )
      assert(
        paths === Map(
          "a" -> List("a"),
          "b" -> List("b", "a"),
          "c" -> List("c", "a"),
          "d" -> List("d", "c", "a")
        )
      )
    }
  }

}
