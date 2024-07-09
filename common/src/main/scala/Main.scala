
import org.apache.log4j.{Level, Logger}
import org.apache.sedona.spark.SedonaContext
import org.apache.sedona.viz.core.Serde.SedonaVizKryoRegistrator
import org.apache.sedona.viz.sql.utils.SedonaVizRegistrator
import org.apache.spark.sql.SparkSession

import Point3DRDD.Point3DRDD


object Main extends App {
  Logger.getRootLogger().setLevel(Level.WARN)

  //val config = SedonaContext.builder().appName("Sedona3D ----- demo")
  //  .config("spark.kryo.registrator", classOf[SedonaVizKryoRegistrator].getName)
  //  .getOrCreate()
  //val sedona = SedonaContext.create(config)

  val spark = SparkSession.builder()
      .appName("Read csv example")
      .getOrCreate()

  val Point3Dcsv = spark.read.option("lineSep", "\n").option("delimiter", ",").csv("./data/testCSV3D.csv")

  Point3Dcsv.show()
  

  //call the main operation
  //Point3DRDD()
}
