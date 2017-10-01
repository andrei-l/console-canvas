package steps

import al.challenge.drawing.command.{DrawingCommand, StringCommandResolver}
import al.challenge.drawing.system.Canvas
import com.waioeka.sbt.runner.CucumberSpec
import cucumber.api.scala.{EN, ScalaDsl}
import org.scalatest.Assertions

import scala.collection.JavaConverters.collectionAsScalaIterableConverter
import scala.language.implicitConversions


class DrawingSystemCucumberTestSuite extends CucumberSpec

class StepDefinitions extends ScalaDsl with EN with Assertions {
  private val commandResolver = new StringCommandResolver

  var canvas: Option[Canvas] = _
  var command: Option[DrawingCommand] = _


  Given("""^no canvas$""") {
    canvas = None
  }

  Given("""^empty canvas (\d+)x(\d+)$""") { (width: Int, height: Int) =>
    canvas = Some(new Canvas(width, height))
  }

  When("""^a drawing command (.+) is issued$""") { (commandString: String) =>
    command = commandResolver.resolveCommand(commandString)
    canvas = command.flatMap(_.drawOnCanvas(canvas))
  }

  Then("""^canvas will look like$""") { (rows: java.util.List[String]) =>
    val actualCanvas = canvas.getOrElse(throw new AssertionError("Canvas must be present"))
    val readPixels = rows.asScala.map(_.replace('_', ' ').split("").map(_ (0))).toArray
    val height = readPixels.length
    val width = readPixels(0).length
    val expectedPixels = Array.ofDim[Char](width, height)
    for {
      j <- 0 until height
      i <- 0 until width
    } yield expectedPixels(i)(j) = readPixels(j)(i)

    val expectedCanvas = new Canvas(expectedPixels)

    assert(
      deep(actualCanvas.pixels) == deep(expectedCanvas.pixels),
      s"actual canvas $actualCanvas must be equal to $expectedCanvas"
    )
  }

  private def deep(arr: Array[Array[Char]]) = arr.map(_.deep).deep

}
