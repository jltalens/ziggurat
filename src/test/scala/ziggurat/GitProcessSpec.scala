package ziggurat

import java.io.File

import org.specs2.Specification


class GitProcessSpec extends Specification{
  def is = s2"""
          Get commit list from a repository $commitListFromGitRepo
    """

  def commitListFromGitRepo = {
    val path: String = "./src/test/resources/gitSampleRepo/.git"
    val gitDir: File = new File(path)
    val gitProc = new GitProcess(gitDir)
    gitProc.commits must_== List("264ac213c1ecf105e39c6e39aed93358ee0620c6#Jose Talens#Thu Feb 25 09:37:12 2016 +0000")
  }



}
