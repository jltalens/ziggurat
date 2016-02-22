package ziggurat

import scopt.OptionParser

object Ziggurat {
  case class Params(gitRepo: String = null, orderTypes: Seq[String] = null)

  def main(args: Array[String]): Unit = {

    val parser = new OptionParser[Params]("ziggurat") {
      head("Ziggurat: stats for git commits")
      arg[String]("<git repo>")
        .required()
        .action((gitRepo, config) => config.copy(gitRepo = gitRepo))
      opt[Seq[String]]('e', "extract")
        .valueName("<order1>, <order2>, ...")
        .action((orderTypes, config) => config.copy(orderTypes = orderTypes))
    }

    parser.parse(args, Params()) match {
      case Some(params) => run(params)
      case None => sys.exit(1)
    }
  }

  def run(params: Params): Unit = {
    println(params.gitRepo)
    println(params.orderTypes)
  }
}
