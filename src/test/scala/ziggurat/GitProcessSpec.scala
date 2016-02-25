package ziggurat

import java.io.File

import org.specs2.Specification


class GitProcessSpec extends Specification {
  def is =
    s2"""
          Get commit list from a repository   $commitListFromGitRepo
          Get a file list from a commit hash  $fileListFromCommit
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

}
