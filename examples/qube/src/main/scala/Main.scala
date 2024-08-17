
import org.apache.log4j.{Level, Logger}
import org.apache.sedona.spark.SedonaContext
import org.apache.sedona.viz.core.Serde.SedonaVizKryoRegistrator
import org.apache.sedona.viz.sql.utils.SedonaVizRegistrator
import org.apache.spark.textFile

import GeometryType3D._
import Point3DFormatMapper._

object Main extends App {
  Logger.getRootLogger().setLevel(Level.WARN)

  val config = SedonaContext.builder().appName("Sedona3D ----- demo")
    .master("local[*]") // Please comment out this when use it on a cluster
    .config("spark.kryo.registrator", classOf[SedonaVizKryoRegistrator].getName)
    .getOrCreate()
  val sedona = SedonaContext.create(config)

  //call the main operation
  Point3DRDD()
}
