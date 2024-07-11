# What has to change to make Sedona ready for 3D ?
## Geometries
### 3D points
`spark/common/src/main/java/org/apache/sedona/core/spatialRDD/PointRDD.java`
in order to build 3D RDD by adding an import from JavaGeom
`common/src/main/java/org/apache/sedona/common/enums/GeometryType.java`
In order to add 3D objects like cubes, tetraedrons and so on.

## Spatial partitionning
`spark/common/src/main/java/org/apache/sedona/core/spatialRDD/SpatialRDD.java` line 235
To add an OCTTREE to make a 3D-compatible paritionning

## Algorithms
