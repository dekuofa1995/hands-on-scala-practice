package sort

trait Sort {
  def sort[T: Ordering](arr: IndexedSeq[T]): IndexedSeq[T]
}
