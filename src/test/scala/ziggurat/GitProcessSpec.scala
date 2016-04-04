package ziggurat

import java.io.File

import org.specs2.Specification

class GitProcessSpec extends Specification {
  def is =
    s2"""
          Get commit list from a repository                       $commitListFromGitRepo
          Get a file list from a commit hash                      $fileListFromCommit
          Get the content of file from a commit hash and its name $contentFileFromCommit
    """

  def commitListFromGitRepo = {
    val gitDir: File = new File("./src/test/resources/gitSampleRepo/git")
    val gitProc = new GitProcess(gitDir)
    val expectedList = gitProc.commits.toList
    expectedList.take(1) must_== List("264ac213c1ecf105e39c6e39aed93358ee0620c6#Jose Talens#Thu Feb 25 09:37:12 2016 +0000")
  }

  def fileListFromCommit = {
    val gitDir: File = new File("./src/test/resources/gitSampleRepo/git")
    val gitProc = new GitProcess(gitDir)
    val singleFile = gitProc.filesFromCommits("264ac213c1ecf105e39c6e39aed93358ee0620c6").toList
    val multipleFiles = gitProc.filesFromCommits("e1d460f1ad8a0fdb831e656cfbdb2d7409d89f75").toList
    (singleFile must_== List("README.md")) and (multipleFiles must_== List("andAnotherOne.txt", "anotherfile.txt"))
  }

  def contentFileFromCommit = {
    val gitDir: File = new File("./src/test/resources/gitSampleRepo/git")
    val gitProc = new GitProcess(gitDir)
    val files = gitProc.filesFromCommits("e1d460f1ad8a0fdb831e656cfbdb2d7409d89f75").toList
    val content = gitProc.fileContent("e1d460f1ad8a0fdb831e656cfbdb2d7409d89f75", "anotherfile.txt")
    val contentFromNextCommit = gitProc.fileContent("8688b1463e0e072ba56905bd5cf4c480afd3f918", "anotherfile.txt")
    (content.mkString("\n") must_== "1") and (contentFromNextCommit.mkString("\n") must_== "1\n2")
  }

}
