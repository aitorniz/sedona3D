import org.apache.sedona.core.enum.spatialRDD;
import org.apache.sedona.core.Geometry;
import org.apache.sedona.TextFile;
import org.apache.sedona.core.formatMapper.PointFormatMapper;

class Point3DRDD{

  //read the file and convert it into binary
  val Point3Dcsv = spark.read.TextFile("./data/testCSV3D.csv")
  //reshape the extracted data into a precise geometry
  val Point3D = point3DFormatMapper(Point3Dcsv)
  //convert it into rdd
  val Point3DRDD = Point3D.rdd

  //visualize the output
  println(Point3DRDD.take(5).toString())
      }

