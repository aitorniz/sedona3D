package org.apache.sedona.core.enums;

import java.io.Serializable;
import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc

/** The Enum GridType. */
public enum GridType3D implements Serializable {

  /** Partition the space to uniform grids */
  EQUALGRID,
  /** The Quad-Tree partitioning. */
  QUADTREE, 
  
  /** A 3D tree */
  OCTTREE,

  /** K-D-B-tree partitioning (k-dimensional B-tree) */
  KDBTREE;

  /**
   * Gets the grid type.
   *
   * @param str the str
   * @return the grid type
   */
  public static GridType3D getGridType(String str) {
    final Logger logger = Logger.getLogger(GridType3D.class);
    for (GridType3D me : GridType3D.values()) {
      if (me.name().equalsIgnoreCase(str)) {
        return me;
      }
    }
    logger.error(
        "[Sedona] Choose quadtree or kdbtree instead. This grid type is not supported: " + str);
    return null;
  }
}
