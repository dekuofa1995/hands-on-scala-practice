package sort

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MergeSortSpec extends AnyWordSpec with Matchers {

  import MergeSort._

  "merge sort" should {
    "sort empty array without exception" in {
      val empty = IndexedSeq[Int]()
      assert(sort(empty) == empty)
    }

    "sort one element array without exception" in {
      val oneElement = IndexedSeq[Int](1)
      assert(sort(oneElement) == oneElement)
    }

    "sort([2,1]) == [1,2]" in {
      val oneElement = IndexedSeq[Int](2, 1)
      assert(sort(oneElement) == IndexedSeq(1, 2))
    }

    "sort([4,0,1,5,2,3]) == [0,1,2,3,4,5]" in {
      val oneElement = IndexedSeq[Int](4, 0, 1, 5, 2, 3)
      assert(sort(oneElement) == IndexedSeq(0, 1, 2, 3, 4, 5))
    }
  }

}
