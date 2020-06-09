package sort

import scala.collection.mutable

/**
  * 6.1
  * 归并排序
  * 1. 判断数组长度是否小于等于 1
  *  1.1 如果小于等于 1 则数组已有序，直接返回
  *  1.2 反之
  *  1.2.1 从中拆分数组，分别对拆分后的数组进行归并排序
  *  1.2.2 对已排序的两部分数组进行合并
  */
object MergeSort extends Sort {

  override def sort[T: Ordering](arr: IndexedSeq[T]): IndexedSeq[T] = {
    if (arr.length <= 1) arr
    else {
      val (left, right) = arr.splitAt(arr.length / 2)
      val (sortedLeft, sortedRight) = (sort(left), sort(right))
      var (leftIdx, rightIdx) = (0, 0)
      val output = mutable.IndexedSeq.newBuilder[T]
      while (leftIdx < sortedLeft.length || rightIdx < sortedRight.length) {
        val takeLeft =
          (leftIdx < sortedLeft.length, rightIdx < sortedRight.length) match {
            case (false, true) => false
            case (true, false) => true
            case (true, true) =>
              Ordering[T].lt(sortedLeft(leftIdx), sortedRight(rightIdx))
          }
        if (takeLeft) {
          output += sortedLeft(leftIdx)
          leftIdx += 1
        } else {
          output += sortedRight(rightIdx)
          rightIdx += 1
        }
      }
      output.result()
    }
  }
}
