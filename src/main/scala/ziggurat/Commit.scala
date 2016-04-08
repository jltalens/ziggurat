package ziggurat

import java.util.Date
case class Commit(id: String, contributor: String = "", date: Date = new Date, gitProcess: GitProcess) {
  val files: Iterator[String] = gitProcess.filesFromCommits(id)
  val methods: Iterator[Method] = files
    .filter(_ containsSlice ".js")
    .flatMap(file => FileProcessor(gitProcess.fileContent(id, file), "js").methods)
}
