package ziggurat

import scopt.OptionParser

object Ziggurat {
  case class Params(gitRepo: String = null, filesPerCommit: Boolean = false, methodsPerCommit: Boolean = false, gitDir: String = ".git")

  def main(args: Array[String]): Unit = {

    val parser = new OptionParser[Params]("ziggurat") {
      head("Ziggurat: stats for git commits")
      arg[String]("<git repo>")
        .required()
        .action((gitRepo, config) => config.copy(gitRepo = gitRepo))
      opt[Unit]('f', "files-per-commit")
        .action((_, config) => config.copy(filesPerCommit = true))
      opt[Unit]('m', "methods-per-commit")
        .action((_, config) => config.copy(methodsPerCommit = true))
      opt[String]("git-dir")
        .action((gitDir, config) => config.copy(gitDir = gitDir))
    }

    parser.parse(args, Params()) match {
      case Some(params) => run(params)
      case None         => sys.exit(1)
    }
  }

  def run(params: Params): Unit = {
    val d: Repository = Directory.fromString(params.gitRepo, params.gitDir).get
    if (params.filesPerCommit)
      println(d.filesPerCommit)
    if (params.methodsPerCommit)
      println(d.methodsPerCommit)
  }
}
