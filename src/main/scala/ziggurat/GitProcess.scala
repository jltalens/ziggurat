package ziggurat

import java.io.File
import sys.process._

case class GitProcess(gitDir: File) {
  final val commitList = "git --git-dir=%s log --reverse --topo-order --no-merges --format=%%H#%%cn#%%cd"
  def commits : Iterator[String] = {
    Process(commitList.format(gitDir.getAbsolutePath)).lineStream.to[Iterator]
  }
}