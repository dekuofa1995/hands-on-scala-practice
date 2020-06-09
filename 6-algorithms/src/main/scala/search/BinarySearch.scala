package search
// 6.6
trait SortedArraySearch {
  def indexOf[T: Ordering](array: Array[T], t: T): Option[Int]
}
object BinarySearch extends SortedArraySearch {
  override def indexOf[T: Ordering](array: Array[T], t: T): Option[Int] = {
    @scala.annotation.tailrec
    def loop(left: Int, right: Int): Option[Int] = {
      if (left > right) None
      else {
        val mid = left + (right - left) / 2
        val midValue = array(mid)
        midValue match {
          case x if Ordering[T].equiv(x, t) => Some(mid) // mid == target
          case x if Ordering[T].gt(x, t)    => loop(left, mid - 1)
          case x if Ordering[T].lt(x, t)    => loop(mid + 1, right)
        }
      }
    }
    loop(left = 0, right = array.length - 1)
  }
}
