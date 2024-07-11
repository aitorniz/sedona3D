package GeometryType3D;

import java.io.Serializable;

// TODO: Auto-generated Javadoc

/** The Enum GeometryType. */
public enum GeometryType3D implements Serializable {
  POINT,
  POINT3D,
  POLYGON,
  LINESTRING,
  MULTIPOINT,
  MULTIPOLYGON,
  MULTILINESTRING,
  GEOMETRYCOLLECTION,
  CIRCLE,
  RECTANGLE;

  /**
   * Gets the GeometryType.
   *
   * @param str the str
   * @return the GeometryType
   */
  public static GeometryType3D getGeometryType3D(String str) {
    for (GeometryType3D me : GeometryType3D.values()) {
      if (me.name().equalsIgnoreCase(str)) {
        return me;
      }
    }
    throw new IllegalArgumentException(
        "[" + GeometryType3D.class + "] Unsupported geometry type:" + str);
  }
}
