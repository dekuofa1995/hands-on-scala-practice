package search

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class BreadthFirstSearchSpec extends AnyWordSpec with Matchers {

  import BreadthFirstSearch._
  "search" should {
    "return start by empty graph" in {
      assert(search(1, Map()) === Set(1))
    }

    "return start by empty next path" in {
      assert(search(1, Map(1 -> Seq())) === Set(1))
    }

    "return all reachable nodes 1" in {
      val reachable =
        search("a", Map("a" -> Seq("b", "c"), "b" -> Seq("a", "c"), "c" -> Seq()))
      assert(reachable === Set("a", "b", "c"))
    }

    "return all reachable nodes 2" in {
      val reachable =
        search("a", Map("a" -> Seq("b", "c"), "b" -> Seq("a", "c"), "c" -> Seq("d"), "d" -> Seq()))
      assert(reachable === Set("a", "b", "c", "d"))
    }

    "return all reachable nodes 3" in {
      val reachable =
        search("c", Map("a" -> Seq("b", "c"), "b" -> Seq("a", "c"), "c" -> Seq("d"), "d" -> Seq()))
      assert(reachable === Set("c", "d"))
    }
  }

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
          "d" -> List("d", "b", "a")
        )
      )
    }
  }

}
