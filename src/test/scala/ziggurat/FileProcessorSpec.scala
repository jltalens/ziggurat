package ziggurat

import org.specs2.Specification


class FileProcessorSpec extends Specification {

  def is = s2"""
      Instantiable with file and lang type  $getInstance
    """

  def getInstance = {
    val content = scala.io.Source.fromFile("./src/test/resources/gitSampleRepo/exampleInJS.js").getLines
    val fp = FileProcessor(content, "js")
    fp.methods.length must_== 2
  }
}
