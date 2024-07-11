
import org.apache.log4j.{Level, Logger}
import org.apache.sedona.spark.SedonaContext
import org.apache.sedona.viz.core.Serde.SedonaVizKryoRegistrator
import org.apache.sedona.sql.utils.Adapter

import org.apache.spark.sql.sedona_sql.expressions.st_constructors._
import org.apache.spark.sql.sedona_sql.expressions.st_functions._
import org.apache.spark.sql.sedona_sql.expressions.st_predicates._
import org.apache.spark.sql.sedona_sql.expressions.st_aggregates._


object Main extends App {
  Logger.getRootLogger().setLevel(Level.INFO)

  // Set up the context
  val config = SedonaContext.builder().appName("3D tests")
    .config(
      "spark.kryo.registrator", 
      classOf[SedonaVizKryoRegistrator].getName)
    .getOrCreate()

  val sedona = SedonaContext.create(config)

  // Read data
  val df = sedona
    .read
    .option("lineSep", "\n")
    .option("delimiter", ",")
    .option("header", "true")
    .csv("./data/testCSV3D.csv")

  // Cast in 3D points
  var dfPoint3D = df.select(
    ST_PointZ("x", "y", "z").alias("point"), 
    df("ID")
  )

  // A simple show will hide the Z column
  // TODO: why so?
  //dfPoint3D.show()

  // Check we have the Z column though
  // dfPoint3D.select(ST_Z("point")).show()


  // Define a center
  sedona
    .sql("SELECT ST_PointZ(0, 0, 10) as coordinates")
    .createOrReplaceTempView("center")

  // Compute the distance to the points
  dfPoint3D.createOrReplaceTempView("points")
  var jointDf = sedona.sql("""
    |SELECT *
    |FROM points, center
    |WHERE ST_3dDistance(points.point, center.coordinates) < 1
  """.stripMargin)

  // Show results
  jointDf.select(
    ST_X("point"), 
    ST_Y("point"), 
    ST_Z("point"), 
    df("ID")
  ).show()

}
