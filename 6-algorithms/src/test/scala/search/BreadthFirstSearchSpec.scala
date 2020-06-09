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

}
