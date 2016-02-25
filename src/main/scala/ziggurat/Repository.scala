package ziggurat
import java.io.File
import sys.process._

abstract class Repository {
  def extract: Stream[Commit]
}

object Repository {
  def apply(dir: File): Repository =
    new RepositoryImp(dir)
}

class RepositoryImp(dir: File) extends Repository {
  override def extract: Stream[Commit] = {
    val x = Process(s"git --git-dir=${dir.getAbsolutePath} log --reverse --topo-order --no-merges --format=%H").lineStream
    x.map(new Commit(_))
  }
}
