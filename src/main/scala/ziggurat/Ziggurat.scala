package ziggurat

import scopt.OptionParser

object Ziggurat {
  case class Params(gitRepo: String = null, filesPerCommit: Boolean = false)

  def main(args: Array[String]): Unit = {

    val parser = new OptionParser[Params]("ziggurat") {
      head("Ziggurat: stats for git commits")
      arg[String]("<git repo>")
        .required()
        .action((gitRepo, config) => config.copy(gitRepo = gitRepo))
      opt[Unit]('f', "files-per-commit")
        .action((_, config) => config.copy(filesPerCommit = true))
    }

    parser.parse(args, Params()) match {
      case Some(params) => run(params)
      case None         => sys.exit(1)
    }
  }

  def run(params: Params): Unit = {
    val d: Repository = Directory.fromString(params.gitRepo).get
    if (params.filesPerCommit)
      println(d.filesPerCommit)
  }
}
