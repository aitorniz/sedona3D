
package Point3DRDD

import org.apache.sedona.core.spatialRDD.SpatialRDD
import FormatMapper.Point3DFormatMapper
import org.apache.spark.sql.SparkSession
import org.apache.sedona.common.enums.FileDataSplitter
import org.locationtech.jts.geom.Geometry

case class Point3DRDD()

object Point3DRDD{

  val spark = SparkSession.builder()
      .appName("Read csv example")
      .master("local[*]") // Use local[*] to run Spark locally with as many worker threads as logical cores on your machine
      .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer") // Example of setting a configuration property
      .getOrCreate()
  /*
  val filePath = "./data/testCSV3D.csv"
  println("filePathb has been defined")
  val carryInputData = false
  println("carryInputData has been defined")

  //read the file and convert it into binary
  val point3Dcsv = spark.read.option("lineSep", "\n").option("delimiter", ",").csv("./data/testCSV3D.csv")
  point3Dcsv.show() 
  
  //determine how to separate elements
  val splitter = FileDataSplitter.CSV                           
  
  //create objects based on extracted data            
  val point3DFormatMapper = new Point3DFormatMapper(0, splitter, carryInputData)

  //map into an instance of spatialRDD
  val point3DRDD = new SpatialRDD[Geometry]
  point3DRDD.rawSpatialRDD = point3Dcsv.mapPartitions(point3DFormatMapper.call(_))

  //visualize the output
  point3DRDD.collect().foreach(println)
  */
  spark.stop()
      }

