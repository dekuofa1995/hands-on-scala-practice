package trie

// 6.2
class Trie {

  case class Node(
      var exist: Boolean,
      children: collection.mutable.Map[Char, Node] = collection.mutable.Map()
  )

  private val root = Node(exist = false)

  def add(s: String): Trie = {
    var node = root
    for (c <- s) node = node.children.getOrElseUpdate(c, Node(exist = false))
    node.exist = true
    this
  }

  /**
    * @param s target string
    * @return check if s exist in Trie
    */
  def contains(s: String): Boolean = {
    var node = Option(root)
    for (c <- s if node.nonEmpty)
      node = node.get.children.get(c)
    node.exists(_.exist)
  }

  def prefixesMatchingString0(s: String): Set[Int] = {
    var node = Option(root)
    val output = Set.newBuilder[Int]
    for ((c, i) <- s.zipWithIndex if node.nonEmpty) {
      if (node.get.exist) output += i
      node = node.get.children.get(c)
    }
    output.result()
  }

  def prefixesMatchingString(s: String): Set[String] = {
    prefixesMatchingString0(s).map(s.substring(0, _))
  }

  def stringsMatchingPrefix(prefix: String): Set[String] = {
    def loop(node: Node, prefix: String): Set[String] = {
      val sub = (for ((c, node) <- node.children) yield {
        loop(node, prefix + c)
      }).flatten.toSet
      if (node.exist) sub + prefix
      else sub
    }
    var node = Option(root)
    for (c <- prefix if node.nonEmpty) node = node.get.children.get(c)
    node match {
      case None       => Set()
      case Some(node) => loop(node, prefix)
    }
  }

}
