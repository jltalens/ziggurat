package ziggurat

import java.io.File
import sys.process._

case class GitProcess(gitDir: File) {

  val absPath = gitDir.getAbsolutePath
  final val commitList = "git --git-dir=%s log --reverse --topo-order --no-merges --format=%%H#%%cn#%%cd"
  final val fileFromCommit = "git --git-dir=%s show %s --name-only --oneline"
  final val contentFile = "git --git-dir=%s show %s:%s"

  def filesFromCommits(commitHash: String): Iterator[String] =
    Process(fileFromCommit.format(absPath, commitHash)).lineStream.to[Iterator].drop(1)

  def commits: Iterator[String] =
    Process(commitList.format(absPath)).lineStream.to[Iterator]

  def fileContent(commitHash: String, file: String): Iterator[String] =
    Process(contentFile.format(absPath, commitHash, file)).lineStream.to[Iterator]

}