package search

// 6.3
object BreadthFirstSearch {

  def search[T](start: T, graph: Map[T, Seq[T]]): collection.immutable.Set[T] = {
    val seen = collection.mutable.Set[T](start)
    val queue = collection.mutable.Queue(start)

    while (queue.nonEmpty) {
      val current = queue.dequeue()
      for (next <- graph.withDefaultValue(Seq())(current)
           if !seen.contains(next)) {
        queue += next
        seen += next
      }
    }
    collection.immutable.Set.empty ++ seen
  }

}
