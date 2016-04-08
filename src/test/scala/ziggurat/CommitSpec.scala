package ziggurat

import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

import org.specs2.Specification


class CommitSpec extends Specification{
  def is = s2"""
    Gets the methods commited on a specific hash and file $methodsInFile
    """

  def methodsInFile = {
    val date: Date = new SimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy").parse("Thu Feb 25 09:37:12 GMT 2016")
    val gitDir: File = new File("./src/test/resources/gitSampleRepo/git")
    val gitProc = new GitProcess(gitDir)
    val commit = Commit("ec8aaa1404b40c80cd061a9ca555dc6f660c5596", "Jose Talens", date , gitProc)
    val c = commit.methods.toArray
    c.length must_== 3
    c(0) must_== Method("someOtherFunction", (0,0), (0,0))
    c(2) must_== Method("m", (0,0), (0,0))
  }
}
