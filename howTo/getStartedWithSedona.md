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
### Where I am?
I'm in
```
sedona/spark/common/src/main/java/org/apache/sedona/core/formatMapper/PointFormatMapper.java
```
at the end of the document. Now I go to `Functions.java` where GeometryType is defined.
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

In facts, the class GeometryType inherites from Java native Geometry class. 
In the case of Points, it precisely concern the `Point3D.java` file in
the [Java Geometry repo](https://github.com/dlegland/javaGeom/blob/master/src/main/java/net/javageom/geom3d/Point3D.java).

That's why we're making a heritage from `GeometryType` and `PointFormatMapper` in order
to modify this 3D aspect by calling `javaGeom` POINT3D. After that, an example -qube
in our case- will call these two modified classes: `GeometryType3D` and `Point3DFormatMapper`.
The aim is to read a csv file which contains an X, a Y and a Z.

I have to go trhough the git-clone I made of Sedona's git-hub repo. There, I copy and paste
the modified class I created (`Point3DFormatMapper.java` and `GeometryType3D.java`).
I re-compile Sedona by running into a terminal opened in this directory a
```
mvn clean package -DskipTests
```
And after that, the Sedona code will modified so I can use it in one home made example
to see if I can open an `.csv` of XYZ coordinates.
