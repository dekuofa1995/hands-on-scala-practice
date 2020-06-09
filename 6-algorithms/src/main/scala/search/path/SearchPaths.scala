package search.path

trait SearchPaths {
  def searchPaths[T](start: T, graph: Map[T, Seq[T]]): collection.immutable.Map[T, List[T]]

  def shortestPath[T](start: T, dest: T, graph: Map[T, Seq[T]]): Option[List[T]]
}
