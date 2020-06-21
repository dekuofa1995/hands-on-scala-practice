import org.scalatest.BeforeAndAfter
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FolderSyncingSpec extends AnyWordSpec with Matchers with BeforeAndAfter {

  import FolderSyncing._
  val src = os.pwd / "posts"
  val dest = os.pwd / "posts-copy"

  before {
    if (os.exists(src)) {
      throw new Exception(s"$src is exist, please change path!")
    }
    if (os.exists(dest)) {
      throw new Exception(s"$dest is exist, please change path!")
    }
    println(s"mkdir to src: $src")
    os.makeDir(src)
    os.write(src / "Text.txt", "Hello World")
    os.makeDir(src / "dir")
    os.makeDir(src / "inner")
    os.write(src / "inner" / "inner-file.txt", "inner")
  }

  after {
    if (os.exists(src)) os.remove.all(src)
    if (os.exists(dest)) os.remove.all(dest)
  }

  def checkSrcFile(): Unit = {
    // src:
    // ├── Text.txt
    // ├── dir
    // └── inner
    //     └── inner-file.txt
    assert(os.isDir(dest / "dir"))
    assert(os.isDir(dest / "inner"))
    assert(os.isFile(dest / "Text.txt"))
    assert(os.isFile(dest / "inner" / "inner-file.txt"))

    assert(os.read(dest / "Text.txt") === "Hello World")
    assert(os.read(dest / "inner" / "inner-file.txt") === "inner")
  }

  "sync" should {

    "copy all src content to dest" in {
      sync(src, dest)
      checkSrcFile()
    }

    "over write content" in {
      os.makeDir(dest)
      os.makeDir(dest / "inner")
      os.write(dest / "inner" / "inner-file.txt", "test")
      os.write(dest / "Text.txt", "test")

      sync(src, dest)

      checkSrcFile()
    }

    "delete content which not exist in src" in {
      os.makeDir(dest)
      os.makeDir(dest / "not-exist-dir")
      os.write(dest / "not-exist.file", "")
      os.makeDir(dest / "inner")
      os.write(dest / "inner" / "not-exist.file", "")

      sync(src, dest)
      checkSrcFile()
      assert(!os.exists(dest / "inner" / "not-exist.file"))
      assert(!os.exists(dest / "not-exist.file"))
      assert(!os.exists(dest / "not-exist-dir"))
    }
  }

}
