package ziggurat

import java.text.{SimpleDateFormat, DateFormat}
import java.util.Date

import org.specs2.Specification

class RepositorySpec extends Specification {
  def is = s2"""
        Must be able to return a Stream of Commits from a git repo  $returnStreamOfCommitsFromRepo
        Returns the number of files commited per user $listOfFilesCommited
    """

  def returnStreamOfCommitsFromRepo = {
    val date: Date = new SimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy").parse("Thu Feb 25 09:37:12 GMT 2016")
    val repo : Repository = Directory.fromString("./src/test/resources/gitSampleRepo/", "git").get
    repo.extract.next.id must_== "264ac213c1ecf105e39c6e39aed93358ee0620c6"
  }

  def listOfFilesCommited = {
    val repo : Repository = Directory.fromString("./src/test/resources/gitSampleRepo", "git").get
    repo.filesPerCommit(1) must_== "Jose Talens" :: 2 :: Nil
  }


}
