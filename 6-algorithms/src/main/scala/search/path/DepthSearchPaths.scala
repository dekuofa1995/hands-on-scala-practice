package search.path

import scala.util.Try

object DepthSearchPaths extends SearchPaths {
  override def searchPaths[T](
    start: T,
    graph: Map[T, Seq[T]]
  ): collection.immutable.Map[T, List[T]] = {
    val seen = collection.mutable.Map[T, List[T]](start -> List(start))
    val pathLengths = collection.mutable.Map[T, Int](start -> 0)
    val stack = collection.mutable.Stack((start, List(start), 1)) // (node, path, pathLength)
    while (stack.nonEmpty) {
      val (node, path, pathLength) = stack.pop()
      for (next <- graph.withDefaultValue(Seq())(node)
           if pathLength + 1 < pathLengths.withDefaultValue(Int.MaxValue)(next)) {
        val nPath = next :: path
        stack.push((next, nPath, pathLength + 1))
        pathLengths(next) = pathLength + 1
        seen(next) = nPath
      }
    }
    seen.toMap
  }

  override def shortestPath[T](start: T, dest: T, graph: Map[T, Seq[T]]): Option[List[T]] = {
    Try(searchPaths(start, graph)(dest)).toOption.map(_.reverse)
  }
}
