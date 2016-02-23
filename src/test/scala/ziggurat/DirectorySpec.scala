package ziggurat

import org.specs2.Specification

class DirectorySpec extends Specification {
  def is = s2"""
    Must fail if the provided directory is not a git dir  $failsIfNotGitDir
    Must return a Repo if a git dir is provided           $repoIfGitDir
    """

  def failsIfNotGitDir = Directory.fromString("adfasdf") must_== None

  def repoIfGitDir = {
    val path: String = "/Users/JoseTalens/Applications/ziggurat/src/test/resources/gitSampleRepo"
    val repo = Directory.fromString(path)

    Directory.fromString(path).getOrElse(None) must beTypedEqualTo(Repository)
  }

}