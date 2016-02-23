package ziggurat

import org.specs2.Specification

class GitExtractorSpec extends Specification {


  def is = s2"""
      None if the input is not a git repo                 $NoneIfNotRepo
      Collection of Commits if the input is a git repo    $CommitsIfRepo
    """

  def NoneIfNotRepo = {
    val nonGitRepo = "/Users/JoseTalens/Applications/ziggurat/src/test/resources/noGitRepo"
    val gitRepoDoesntExists: Object = GitExtractor.extract(nonGitRepo).getOrElse(None)
    (GitExtractor.extract("/I/don't/exists") must_== None) and (gitRepoDoesntExists must_== None)
  }


  def CommitsIfRepo = {
    val properGitRepo = "/Users/JoseTalens/Applications/ziggurat/src/test/resources/gitSampleRepo"
    val gitRepoExists: Object = GitExtractor.extract(properGitRepo).getOrElse(None)
    gitRepoExists must_== Commits

  }

}
