package trie.immutable

class Trie(words: Seq[String]) {
  sealed case class Node(index: Int, words: Seq[String]) {
    val exist: Boolean = words.exists(_.length == index)
    val children: Map[Char, Node] = {
      words
        .filter(_.length > index)
        .groupBy(_.charAt(index))
        .mapValues(subWords => Node(index + 1, subWords))
    }
  }
  private val root = Node(index = 0, words)

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
      case None => Set()
      case Some(node) => loop(node, prefix)
    }
  }
}
