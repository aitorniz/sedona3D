## Simple 3D Sedona example

Compile the project:

```bash
mvn clean package -DskipTest
```

and execute the example with Spark:

```bash
./run-spark.sh
```

This example reads a CSV file containing positions (x, y, z) and an ID:

```
x,y,z,ID
0,0,0,a
0,0,10,b
19,4,100,c
0,0,9.5,d
```

It creates a DataFrame of 3D points (`ST_PointZ`), and performs a distance computation with a center `(0, 0, 10)`: 

```scala
// Define a center
sedona
  .sql("SELECT ST_PointZ(0, 0, 10) as coordinates")
  .createOrReplaceTempView("center")

// Compute the distance to the points
dfPoint3D.createOrReplaceTempView("points")
var jointDf = sedona.sql("""
  |SELECT *
  |FROM points, center
  |WHERE ST_3dDistance(points.point, center.coordinates) < 1
""".stripMargin)
```

Finally only points with a distance to the center lower than 1 are returned:

```
+-----------+-----------+-----------+---+
|st_x(point)|st_y(point)|st_z(point)| ID|
+-----------+-----------+-----------+---+
|        0.0|        0.0|       10.0|  b|
|        0.0|        0.0|        9.5|  d|
+-----------+-----------+-----------+---+
```
