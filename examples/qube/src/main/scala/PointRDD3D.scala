import org.apache.sedona.code.spatialRDD.PointRDD
import org.apache.spark.api.java.JavaRDD<org.locationtech.jts.geom.Point> rawSpatialRDD

class PointRDD3D(
  sparkContext: JavaSparkContext,
  inputLocation: String,
  offset: Integer,
  splitter: FileDataSplitter,
  carryInputData: Boolean,
  geometryFactory: GeometryFactory
) extends PointRDD(sparkContext, inputLocation, offset, splitter, carryInputData){
  /* A constructor for new RDD
   */
  def this(){
}
  def this(3DRaw : rawSpatialRDD){

  }



