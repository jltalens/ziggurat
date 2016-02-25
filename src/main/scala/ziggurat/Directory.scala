package ziggurat

import java.io.File

trait Directory {

  def isGitRepo(dir: File): Boolean = {
    dir.list.contains(".git")
  }

  def fromString(pathAsString: String): Option[Repository] = {
    val path = new File(pathAsString)
    path.isDirectory && isGitRepo(path) match {
      case false => None
      case _     => Some(Repository(new File(path, ".git")))
    }
  }
}

object Directory extends Directory