package ziggurat

import org.specs2.Specification

class ExtractorSpec extends Specification {


  def is = s2"""
      None if the input is not a git repo                 $NoneIfNotRepo
      Collection of Commits if the input is a git repo    $CommitsIfRepo
    """

  def NoneIfNotRepo = {
    val nonGitRepo = "./src/test/resources/noGitRepo"
    val gitRepoDoesntExists: Object = Extractor.extract(nonGitRepo).getOrElse(None)
    (Extractor.extract("/I/don't/exists") must_== None) and (gitRepoDoesntExists must_== None)
  }


  def CommitsIfRepo = {
    val properGitRepo = "./src/test/resources/gitSampleRepo"
    val gitRepoExists: Object = Extractor.extract(properGitRepo).getOrElse(None)
    gitRepoExists must_== Commit

  }

}
