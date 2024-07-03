# Write a Dockerfile

# Download sedona examples

# Into each examples run `mvn clean package`

# Write a spark-submit.sh and run it

# Create your own example directory
## Copy a pom.xml from sedona examples
## copy the directory src/main/scala


# Start modifying Sedona's geometries
In order to modify the geometries, we started
working in the following directory of Sedona's repo:
```
sedona/spark/common/src/main/java/org/apache/sedona/core/spatialRDD
```
## Overiding points
[java doc for points](https://sedona.apache.org/latest-snapshot/api/javadoc/spark/)

In fact, you should rather go to:
```
sedona/common/src/main/java/org/apache/sedona/common/utils/FormatUtils.java
```
Then, I look for the splitter

## Adding OctTree
In order to add and OctTree option, we may modify
SpatialRDD.java file and more precisely
`calc_partitioner` method by adding an OctTree option at
line 234.

## Modify geometry point
###How does it work ?
SpatialRDD is bascily compound of three operations:
1/spark.textFIle which read the WKT format file,
2/FormatMapper which takes in account the binary result
of 1/ and build many partitions of the fist document
3/ Geometry which precises the data type in order to
build objects based on what FormatMapper did.
### What have I to do?
Modify GeometryType, either by adding a Point3D class and recompiling it
or if POINT3D exists in Java, just make an heritage in the POINT part.

