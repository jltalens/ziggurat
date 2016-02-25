package ziggurat
import java.io.File
import java.text.{SimpleDateFormat, DateFormat}

import scala.sys.process._
import scala.util.matching.Regex

abstract class Repository {
  def extract: Iterator[Commit]
}

object Repository {
  def apply(dir: File): Repository =
    new RepositoryImp(dir)
}

class RepositoryImp(dir: File) extends Repository {

  final val RCommitFormat: Regex = """(.*)#(.*)#(.*)""".r
  final val dateParser: SimpleDateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z")

  override def extract: Iterator[Commit] = {
    val x = Process(s"git --git-dir=${dir.getAbsolutePath} log --reverse --topo-order --no-merges --format=%H#%cn#%cd").lineStream.to[Iterator]
    x.collect { case RCommitFormat(id, contributor, date) => Commit(id, contributor, dateParser.parse(date)) }
  }
}
