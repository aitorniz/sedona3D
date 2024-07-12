package org.apache.sedona.core.enums;

import java.io.Serializable;

// TODO: Auto-generated Javadoc

/** The Enum IndexType. */
public enum IndexType3D implements Serializable {

  /** The quadtree. */
  QUADTREE,

  /** The rtree. */
  RTREE,

  OCTTREE;

  /**
   * Gets the index type.
   *
   * @param str the str
   * @return the index type
   */
  public static IndexType3D getIndexType(String str) {
    for (IndexType3D me : IndexType3D.values()) {
      if (me.name().equalsIgnoreCase(str)) {
        return me;
      }
    }
    return null;
  }
}
