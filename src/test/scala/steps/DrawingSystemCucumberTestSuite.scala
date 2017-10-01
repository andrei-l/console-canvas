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
    val expectedPixels = rows.asScala.map(_.split("""_""").filter(_.nonEmpty).map(_ (0))).toArray
    val expectedCanvas = new Canvas(expectedPixels)

    assert(actualCanvas === expectedCanvas, s"$actualCanvas ${System.lineSeparator()} must be equal to $expectedCanvas")
  }

}
