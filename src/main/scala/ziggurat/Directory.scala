package ziggurat

import java.io.File

trait GitDirectory {

  def isGitRepo(dir: File): Boolean = {
    dir.list.contains(".git")
  }

  def fromString(pathAsString: String): Option[GitRepository] = {
    val path = new File(pathAsString)
    path.isDirectory && isGitRepo(path) match {
      case false => None
      case _ => Some(GitRepository)
    }
  }
}

object GitDirectory extends GitDirectory