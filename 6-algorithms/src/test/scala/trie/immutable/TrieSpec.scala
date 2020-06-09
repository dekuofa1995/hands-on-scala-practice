package trie.immutable

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TrieSpec extends AnyWordSpec with Matchers {

  "empty trie" should {
    "no elements" in {
      val emptyTrie = new Trie(Seq())
      assert(!emptyTrie.contains("t"))
      assert(!emptyTrie.contains("te"))
      assert(!emptyTrie.contains("tes"))
      assert(!emptyTrie.contains("test1"))
      assert(!emptyTrie.contains("tesn"))
    }
  }

  "trie(mandarin, map, mandarin, man, mango)" should {
    val trie = new Trie(Seq("mandarin", "map", "mandarin", "man", "mango"))

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
    val trie = new Trie(Seq("mandarin", "map", "mandarin", "man", "mango"))

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
