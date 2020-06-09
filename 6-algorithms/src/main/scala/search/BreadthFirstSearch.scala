package search

import scala.util.Try

// 6.3 & 6.4
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

  def searchPaths[T](start: T, graph: Map[T, Seq[T]]): collection.immutable.Map[T, List[T]] = {
    val paths = collection.mutable.Map[T, List[T]](start -> List(start))
    val queue = collection.mutable.Queue(start -> List(start))
    while (queue.nonEmpty) {
      val (current, path) = queue.dequeue()
      for (next <- graph.withDefaultValue(Seq())(current)
           if !paths.contains(next)) {
        val newPath = next :: path
        queue += (next -> newPath)
        paths += (next -> newPath)
      }
    }
    collection.immutable.Map() ++ paths
  }

  def shortestPath[T](start: T, dest: T, graph: Map[T, Seq[T]]): Option[List[T]] = {
    Try(searchPaths(start, graph)(dest)).toOption.map(_.reverse)
  }
}
