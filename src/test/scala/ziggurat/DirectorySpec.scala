package ziggurat

import org.specs2.Specification
import scala.reflect._

class DirectorySpec extends Specification{
  def is = s2"""
    Must fail if the provided directory is not a git dir  $failsIfNotGitDir
    Must return a Repo if a git dir is provided           $repoIfGitDir
    """

  def failsIfNotGitDir = Directory.fromString("adfasdf", "git") must_== None

  def repoIfGitDir = {
    val path: String = "./src/test/resources/gitSampleRepo/"
    val repo = Directory.fromString(path, "git").getOrElse(None)

    repo must haveClass(classTag[RepositoryImp])
  }

}
