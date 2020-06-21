/**
  * 同步 src 与 dest 路径下的文件 <br/>
  * 路径代表三种情况：<br/>
  *  1. 文件 <br/>
  *  2. 目录 <br/>
  *  3. 不存在 <br/>
  * 由于目的地（dest）路径下不存在的情况可以直接被源路径文件覆盖
  * 所以一共有 7 种情况：
  *  1. 源 文件   => 目的地 文件     覆写 <br/>
  *  2. 源 目录   => 目的地 文件     替换 <br/>
  *  3. 源 不存在 => 目的地 文件   删除 <br/>
  *  4. 源 文件   => 目的地 目录     覆写 <br/>
  *  5. 源 目录   => 目的地 目录     忽略 <br/>
  *  6. 源 不存在 => 目的地 目录   删除 <br/>
  *  7. 源 *     => 目的地 不存在     复制 <br/>
  *  ps: 当路径不存在时 os.isDir 返回 false
  */
object FolderSyncing {

  def syncDelete(src: os.Path, dest: os.Path) = {
    dest match {
      case path if os.isDir(path) =>
        (os.list(dest).map(_.relativeTo(dest)).toSet -- os
          .list(src)
          .map(_.relativeTo(src))
          .toSet).foreach(path => os.remove.all(dest / path))
      case _ =>
    }
  }
  def sync(src: os.Path, dest: os.Path) = {
    syncDelete(src, dest)
    for (srcSubPath <- os.walk(src)) {
      val subPath = srcSubPath.subRelativeTo(src)
      val destSubPath = dest / subPath
      syncDelete(srcSubPath, destSubPath)
      (os.isDir(srcSubPath), os.isDir(destSubPath)) match {
        case (false, true) | (true, false) =>
          // 2、4、5、7
          os.copy.over(srcSubPath, destSubPath, createFolders = true)
        case (false, false)
            if !os.exists(destSubPath)
              || !os.read.bytes(srcSubPath).sameElements(os.read.bytes(destSubPath)) =>
          // 1
          os.copy.over(srcSubPath, destSubPath, createFolders = true)
        case _ =>
      }
      //os.copy(src / subPath, destSubPath)
    }
  }

}
