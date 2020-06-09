package trie.mutable

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TrieSpec extends AnyWordSpec with Matchers {

  "trie add" should {
    "add element to empty" in {
      val s = "test"
      val emptyTrie = new Trie()
      assert(!emptyTrie.contains(s))
      emptyTrie.add(s)
      assert(emptyTrie.contains(s))
      assert(!emptyTrie.contains("t"))
      assert(!emptyTrie.contains("te"))
      assert(!emptyTrie.contains("tes"))
      assert(emptyTrie.contains("test"))
      assert(!emptyTrie.contains("test1"))
      assert(!emptyTrie.contains("tesn"))
    }
  }

  "trie(mandarin, map, mandarin, man, mango)" should {
    val trie = new Trie()
      .add("mandarin")
      .add("map")
      .add("mandarin")
      .add("man")
      .add("mango")

    "contains all add element" in {
      assert(trie.contains("mandarin"))
      assert(trie.contains("map"))
      assert(trie.contains("man"))
      assert(trie.contains("mango"))
    }

    "prefixesMatchingString(manible) == Set(man)" in {
      assert(trie.prefixesMatchingString("manible") === Set("man"))
    }
    "prefixesMatchingString(mangosteen) == Set(man, mango)" in {
      assert(trie.prefixesMatchingString("mangosteen") === Set("man", "mango"))
    }
  }

  "stringsMatchingPrefix with trie(mandarin, map, mandarin, man, mango)" should {
    val trie = new Trie()
      .add("mandarin")
      .add("map")
      .add("mandarin")
      .add("man")
      .add("mango")

    "stringsMatchingPrefix(man) == Set(man, mandarin, mango)" in {
      assert(trie.stringsMatchingPrefix("man") === Set("man", "mandarin", "mango"))
    }
    "stringsMatchingPrefix(ma) == Set(map, man, mandarin, mango)" in {
      assert(
        trie
          .stringsMatchingPrefix("ma") === Set("map", "man", "mandarin", "mango")
      )
    }
  }

}
