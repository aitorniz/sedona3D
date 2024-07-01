
import org.apache.sedona.PointRDD
import org.locationtech.jts.geom.{Coordinate, GeometryFactory, Point}

/**
 * A class representing 3-dimensional points
 *
 * @param x is the x-coordinate and so on for y and z
 * @param geometryFactory is the way used to create the point
 */

class Point3D extends Point(new Coordinate(x, y, z), geometryFactory) {
  /*
   * Gets the z-coordinate of a 3D point
   *
   * @return the z-coordinate
   */
  def getZ: Double = getCoordinate.getZ
  /* A way to print 3D point's coodinates
   *
   * @return x, y and z coordinates
   */
  override def toString: String = s"Point3D(${getX}, ${getY}, ${getZ})"
  
  /* A method to know if 2 3D-points are exactly the same or not
   *
   * @param pt is the the other 3D point with what we compare our own 3D point
   * @return true or false accordingly to identity of the points
   */
  override def equals(pt: Point3D){
    if (pt.getX == this.getX || pt.getY == this.getY || pt.getZ == this.getZ){
      return true
  } else{
      return false
  }


     
}
