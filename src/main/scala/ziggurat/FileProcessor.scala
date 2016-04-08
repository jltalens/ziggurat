package ziggurat

import scala.util.matching.Regex


case class Method(name: String, start: (Int, Int), end: (Int, Int))

abstract class FileProcessor(fileContent: Iterator[String], ext: String) {
  def methods: Iterator[Method]
}

object FileProcessor {
  def apply(fileContent: Iterator[String], ext: String): FileProcessor = new FileProcessorImp(fileContent, ext)
}

class FileProcessorImp(fileContent: Iterator[String], ext: String = "") extends FileProcessor(fileContent, ext) {

  def fp : FileProcessor = ext match {
    case "js" => new JSProcessor(fileContent)
  }

  override def methods: Iterator[Method] = fp.methods

}

class JSProcessor(fileContent: Iterator[String]) extends FileProcessor(fileContent, "js") {

  private val f: Regex = """.*function *([a-zA-Z0-9_]*) *\(.*"""r

  override def methods: Iterator[Method] = {
    fileContent.collect {
      case f(funcName) => new Method(funcName, (0,0), (0,0))
    }
  }

}