package ziggurat

import java.io.File

trait Directory {

  def fromString(pathAsString: String, gitDir: String = ".git"): Option[Repository] = {
    val path = new File(pathAsString)
    path.isDirectory && path.list.contains(gitDir) match {
      case false => None
      case _     => Some(Repository(new File(path, gitDir)))
    }
  }
}

object Directory extends Directory