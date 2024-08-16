Some bugs I had working on this projects and the way I fixed them (when I fixed then :) )

# How to push when it looks like your connection is not very good
## Warning: don't do multiple commits
If, like me, you try many ways to achieve pushing, you will got a problem.
In facts, while you haven't push, the commits stack. Therefore, each time
you try to push, git tryes to push every commit you did, including the wrong ones
that you tryed to fix doing other commits.
## What can I do if I have done many commits ?
### Easy way: clone, copy and push
First, create another directory:
```
cd path/to/the/directory
```
Then, clone there the Sedona3D repo:
```
git clone git@github.com:aitorniz/sedona3D.git
```
We must stop there to make sure that you understood 
what you're doing.
You have your most recent repo which is in your machine. You 
can't push it. And you have your less recent repo, in Git hub.
What we are doing is to clone again the older repo. Then, we
will add manually the created/modified/compressed files there. So
your older work will be updated by yourself. And, we will
push this to the Git hub repo.
Finally and only when you will have checked that the update
and the push are complete, you delete the newest repo.

Practically, I renamed the newer repo as `sedona3Dcrashed`
to avoid conflicts.
Therefore, you can copy the files:
```
cp -r path/to/the/sedona3Dcrashed/file/you/want/to/save path/to/sedona3D
```
Finally, you can just commit and push
```
git add fileYouWantToPush
git commit -m "A very clear message to explain what you're doing accurately"
git push
```

### Advanced way: modify the commit itself
I didn't try it

I got and error of size because I tryed to also push the fat jar of sedona's examples.
When 
In fact, I tryed many ways to fix the problem. Firstly, I compress it

# How to modify Sedona
## How to modify what you need to modify
Like "Le Petit poucet", a classic french tail, you have to yield little rocks.
In the code, you must identify function that seem to be related to what you want
to change. Then, double click on them to go to the file where the function is defined and so on.
After identifying the block of code who makes what you want to change, you could
modify it, compile and run your code until the next bug.

## Running modifyied examples
### Check if normal example runs
When I source my `spark-submit`, I got the following:
```
bash: /home/aitor/spark-3.4.3-bin-hadoop3/bin/spark-submit: No such file or directory
```

## Error compiling a modified version of Sedona
After copy and paste modified version of two files into Sedona source code and
running the following command in the highest directory:
```
mvn clean package -DskipTests
```
I got the following error:
```
 Compilation failure: 
[ERROR] /home/aitor/projects/sedonaCodeSource/common/src/main/java/org/apache/sedona/common/enums/GeometryType3D.java:[1,43] ';' expected
[ERROR] /home/aitor/projects/sedonaCodeSource/common/src/main/java/org/apache/sedona/common/enums/GeometryType3D.java:[3,27] '{' expected
[ERROR] /home/aitor/projects/sedonaCodeSource/common/src/main/java/org/apache/sedona/common/enums/GeometryType3D.java:[3,28] ',', '}', or ';' expected
```
Then I add the necessary symbols. Note that `enums` seems to work better with `implements` than `extends`.

Therefore, I got the following:
```
Compilation failure: 
[ERROR] /home/aitor/projects/sedonaCodeSource/common/src/main/java/org/apache/sedona/common/enums
/GeometryType3D.java:[1,30] package org.apache.sedona.core does not exist
[ERROR] /home/aitor/projects/sedonaCodeSource/common/src/main/java/org/apache/sedona/common/enums/
GeometryType3D.java:[3,39] cannot find symbol
[ERROR]   symbol: class GeometryType
```
Ahah, I modified the path by adding the good path into Sedona repo and got the following:
```i
Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.10.1:compile (default-compile) on project sedona-common:
 Compilation failure
[ERROR] /home/aitor/projects/sedonaCodeSource/common/src/main/java/org/apache/sedona/common/enums/
GeometryType3D.java:[3,39] interface expected here
```
So I deleted GeometryType3D.java in order to directly add a `POINT3D` imported from JavaGeom into `GeometryType.java`
and I got the following:
```
/home/aitor/projects/sedonaCodeSource/common/src/main/java/org/apache/sedona/common/enums/
GeometryType.java:[22,40] package org.locationtech.jts.geom.geom3d does not exist
```
Therefore, I modify the path by deleting `geom3d` and I got the following error:
```
/home/aitor/projects/sedonaCodeSource/common/src/main/java/org/apache/sedona/common/enums/
GeometryType.java:[22,33] cannot find symbol
[ERROR]   symbol:   class Point3D
[ERROR]   location: package org.locationtech.jts.geom
```
So I changed Point3D class for `Geometry` and it seems to work.
After that, I got the following error:
```
/home/aitor/projects/sedonaCodeSource/spark/common/src/main/java/org/apache/sedona/core/formatMapper/
Point3DFormatMapper.java:3: error: `;' expected but `import' found.
```
Then I got:
```
/home/aitor/projects/sedonaCodeSource/spark/common/src/main/java/org/apache/sedona/core/formatMapper/
Point3DFormatMapper.java:10: error: `}' expected but eof found.
```
After correcting that, the following error was raised:
```
Compilation failure
[ERROR] /home/aitor/projects/sedonaCodeSource/spark/common/src/main/java/org/apache/sedona/core/formatMapper/
Point3DFormatMapper.java:[5,16] invalid method declaration; return type required
```
Then I try to delete `@override` because it seems like it is not a method but I got this:
```
 Compilation failure
[ERROR] /home/aitor/projects/sedonaCodeSource/spark/common/src/main/java/org/apache/sedona/core/formatMapper/
Point3DFormatMapper.java:[4,16] invalid method declaration; return type required
```
There was a `3D` missing in the constructor name in order to have the same name than the class name. After that:
```
Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.10.1:
compile (default-compile) on project sedona-spark-common-3.0_2.12: Compilation failure: Compilation failure: 
[ERROR] /home/aitor/projects/sedonaCodeSource/spark/common/src/main/java/org/apache/sedona/core/formatMapper/
Point3DFormatMapper.java:[6,57] cannot find symbol
[ERROR]   symbol:   class FileDataSplitter
[ERROR]   location: class org.apache.sedona.core.formatMapper3D.Point3DFormatMapper
[ERROR] /home/aitor/projects/sedonaCodeSource/spark/common/src/main/java/org/apache/sedona/core/formatMapper/
Point3DFormatMapper.java:[7,75] cannot find symbol
[ERROR]   symbol:   variable GeometryType
[ERROR]   location: class org.apache.sedona.core.formatMapper3D.Point3DFormatMapper
```
After adding the right import for FileDataSplitter I got:
```
Compilation failure
[ERROR] /home/aitor/projects/sedonaCodeSource/spark/common/src/main/java/org/apache/sedona/core/formatMapper/
Point3DFormatMapper.java:[8,75] cannot find symbol
[ERROR]   symbol:   variable GeometryType
[ERROR]   location: class org.apache.sedona.core.formatMapper3D.Point3DFormatMapper
```
So after giving the good import into GeometryType, I got:
```
Compilation failure
[ERROR] /home/aitor/projects/sedonaCodeSource/spark/common/src/main/java/org/apache/sedona/core/formatMapper/
Point3DFormatMapper.java:[8,13] no suitable constructor found for 
PointFormatMapper(java.lang.Integer,int,org.apache.sedona.common.enums.FileDataSplitter,
boolean,org.apache.sedona.common.enums.GeometryType)
[ERROR]     constructor org.apache.sedona.core.formatMapper.PointFormatMapper.
PointFormatMapper(org.apache.sedona.common.enums.FileDataSplitter,boolean) is not applicable
[ERROR]       (actual and formal argument lists differ in length)
[ERROR]     constructor org.apache.sedona.core.formatMapper.PointFormatMapper.
PointFormatMapper(java.lang.Integer,org.apache.sedona.common.enums.FileDataSplitter,boolean) is not applicable
[ERROR]       (actual and formal argument lists differ in length)

```
Next day, I got the following:
```
Compilation failure
[ERROR] /home/aitor/projects/sedonaCodeSource/spark/common/src/main/java/org/apache/sedona/core/formatMapper/
Point3DFormatMapper.java:[8,13] no suitable constructor found for 
PointFormatMapper(java.lang.Integer,int,org.apache.sedona.common.enums.FileDataSplitter,boolean,org.apache.sedona.common.enums.GeometryType)
[ERROR]     constructor org.apache.sedona.core.formatMapper.PointFormatMapper.
PointFormatMapper(org.apache.sedona.common.enums.FileDataSplitter,boolean) is not applicable
[ERROR]       (actual and formal argument lists differ in length)
[ERROR]     constructor org.apache.sedona.core.formatMapper.PointFormatMapper.
PointFormatMapper(java.lang.Integer,org.apache.sedona.common.enums.FileDataSplitter,boolean) is not applicable
[ERROR]       (actual and formal argument lists differ in length)
```

In facts, the repo I forked is too much old in comparison with the actual. SO I made a `git clone` of the actual version
and tried to compile it but it didn't work. I got the following:
```
Failed to execute goal org.apache.maven.plugins:maven-javadoc-plugin:2.10.4:jar
 (attach-javadocs) on project sedona-common: MavenReportException:
Error while generating Javadoc: Unable to find javadoc command: 
The environment variable JAVA_HOME is not correctly set. 
```

Then, I have many problems. First of all, the GeometryType.java file isn't the same than the original Sedona repo one.
Also, I tried to git clone again the original Sedona repo, run it and got an error message which said
that is a compiler without. So I retry to fix the problem of Point3DFormatMapper which hasn't constructor.
Then I git the following:
```
Failed to execute goal org.apache.maven.plugins:maven-clean-plugin:3.1.0:clean (default-clean) 
on project sedona-parent: Failed to clean project:Failed to delete /home/aitor/projects/sedonaCodeSource/target/resolved-pom.xml 
```
So this was an error of rights. You can check your rights on `resolved-pom.xml` by running:
```
cd /home/aitor/projects/sedonaCodeSource/target/
ls -lth
```
where -lth will give you a result like this:
```
-rw-r--r-- 1 root root  32K juil.  4 10:16 resolved-pom.xml
```
As you can read, rights are off for this file. So you can turn on sudo rights all the Sedona cloned repo with:
```
sudo chown -R user:user path/to/sedona
```
### Kind of half-debrief
[a very clear schema](https://excalidraw.com/#json=-5neyDrMFmMc-tVTE2CzA,WgJkZ0gLG66_vc99K1H5Cw)
### bugs still there

## Compile the basic modication on Points
Then I compile it after removing all modifications to test if the repo itself works
### Adding POINT3D in `GeometryType`: OK
After adding POINT3D in `GeometryType` it compiles without failure
### Adding Point3DFormatMapper.java: not OK
I got the following:
```
Compilation failure
[ERROR] /home/aitor/projects/sedonaCodeSource/spark/common/src/main/java/org/apache/sedona/core/formatMapper/
Point3DFormatMapper.java:[11,13] no suitable constructor found for 
PointFormatMapper(java.lang.Integer,int,org.apache.sedona.common.enums.FileDataSplitter,boolean,org.apache.sedona.common.enums.GeometryType)
[ERROR]     constructor org.apache.sedona.core.formatMapper.PointFormatMapper.
PointFormatMapper(org.apache.sedona.common.enums.FileDataSplitter,boolean) is not applicable
[ERROR]       (actual and formal argument lists differ in length)
[ERROR]     constructor org.apache.sedona.core.formatMapper.PointFormatMapper.
PointFormatMapper(java.lang.Integer,org.apache.sedona.common.enums.FileDataSplitter,boolean) is not applicable
[ERROR]       (actual and formal argument lists differ in length)
```
The constructor of PointFormatMapper has only 2 arguments unlike FormatMapper's constructor which has 4. In our case, we inherite
PointFormatMapper into `Point3DFormatMapper` file. That's why we only have to specify two parameters: Splitter and CarryInputData.
So I changed the class code like this:
```
//make a package of this class in order
//to export and import it easier

package org.apache.sedona.core.formatMapper3D;

import org.apache.sedona.core.formatMapper.PointFormatMapper;
import org.apache.sedona.common.enums.FileDataSplitter;
import org.apache.sedona.common.enums.GeometryType;

public class Point3DFormatMapper extends PointFormatMapper{
        public Point3DFormatMapper(Integer startOffset, FileDataSplitter Splitter, boolean carryInputData) {
            super(Splitter, carryInputData);

        }
}
```
##Test these basic modified geometries
In order to test them, I made few hypothesis. One of them was to install the modified version of Sedona in my computer.
Thanks to this, the example will probably call it. But how I could do that?
```
```

## Unable to clean class directory
After writting a basic code to inherite sedona, modify it and test it, I got the following error:
```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-clean-plugin:2.5:clean (default-clean) on project sedona-spark-example: 
Failed to clean project: 
Failed to delete /home/aitor/projects/sedona3D/examples/qube/target/classes
```
Knowing that the `classes` folder is empty into `qube` example.
Nota bene: don't hesitate to use:
```
mvn clean package **-e** **-X**-DskipTests
```
In order to get the full trace of error

You must delete by yourself the classes located at `classes` using:
```
sudo rm -rf path/to/classes
```
In my case after that it works and show me my code's bugs

## Import errors:
```
/home/aitor/projects/sedona3D/common/src/main/scala/Main.scala:6: error: object textFile is not a member of package org.apache.spark
[ERROR] import org.apache.spark.textFile
[ERROR]        ^
[ERROR] /home/aitor/projects/sedona3D/common/src/main/scala/Main.scala:8: error: not found: object GeometryType3D
[ERROR] import GeometryType3D._
[ERROR]        ^
[ERROR] /home/aitor/projects/sedona3D/common/src/main/scala/Main.scala:9: error: not found: object Point3DFormatMapper
[ERROR] import Point3DFormatMapper._
[ERROR]        ^
[ERROR] /home/aitor/projects/sedona3D/common/src/main/scala/Main.scala:21: error: not found: value Point3DRDD
[ERROR]   Point3DRDD()
[ERROR]   ^
[ERROR] /home/aitor/projects/sedona3D/common/src/main/scala/Point3D.scala:1: error: object enum is not a member of package org.apache.sedona.core
[ERROR] import org.apache.sedona.core.enum.spatialRDD;
[ERROR]                               ^
[ERROR] /home/aitor/projects/sedona3D/common/src/main/scala/Point3D.scala:2: error: object Geometry is not a member of package org.apache.sedona.core
[ERROR] import org.apache.sedona.core.Geometry;
[ERROR]        ^
[ERROR] /home/aitor/projects/sedona3D/common/src/main/scala/Point3D.scala:3: error: object TextFile is not a member of package org.apache.sedona
[ERROR] import org.apache.sedona.TextFile;
[ERROR]        ^
[ERROR] /home/aitor/projects/sedona3D/common/src/main/scala/Point3D.scala:9: error: not found: value spark
[ERROR]   val Point3Dcsv = spark.read.TextFile("./data/testCSV3D.csv")
[ERROR]                    ^
[ERROR] /home/aitor/projects/sedona3D/common/src/main/scala/Point3D.scala:11: error: not found: value point3DFormatMapper
[ERROR]   val Point3D = point3DFormatMapper(Point3Dcsv)
```
Then, I modified some imports removing any unnecessary thing and after creating a Spark session, I got this:
```
[ERROR]   /workspace/src/main/scala/Point3DRDD.scala:19: error: value option is not a member of org.apache.spark.sql.DataFrame
[ERROR]   val Point3Dcsv = spark.read.csv("./data/testCSV3D.csv").option("lineSep", ",")
```
The problem was you need to create a Spark context. After that use it in the Main. At the end, it works.

## My `mvn clean package` don't take the good directory
My command create a jar based on the pom.xml i copied from spark-example 
You must change the name of the artefactID inside the .xml and put the name
of your current example

## Import problems with my home made example
While importing classes I created to make 3D RDD, I got the following:
```
Compiling 2 source files to /home/aitor/projects/sedona3D/common/target/classes at 1720596047114
[ERROR] /home/aitor/projects/sedona3D/common/src/main/scala/Point3DRDD.scala:7: error: not found: object Point3DFormatMapper
[ERROR] import Point3DFormatMapper.Point3DFormatMapper._
[ERROR]        ^
[ERROR] /home/aitor/projects/sedona3D/common/src/main/scala/Point3DRDD.scala:30: error: not found: value Point3DFormatMapper
[ERROR]   val Point3D = Point3DFormatMapper(0, FileSplitter, Point3Dcsv)
[ERROR]                 ^
[ERROR] two errors found
```
So I try to change the import syntax. But it doesn't change anything.
You should go into your pom.xml and check in the `<\build>` if the path are corrects.
You can add a build. Copy and paste a build topic and add the path you want. Try and retry
until you got the right paths messages during compilation.

##Problem whil instantiating a point using `PointFormatMapper`
When I run this inside `Point3DRDD.scala`
```
val Point3D = Point3DFormatMapper(0, FileSplitter, true)
```
It raises me the following error:
```
 error: class FormatMapper.Point3DFormatMapper is not a value
```
Yes, it's an object but how could I instantiate a point so ?
The solution was `new` because it's an object so you must create
a new instance by using the key-word `new`.
To avoid problems while importing, take care of the difference
between the package you're calling, the class/object and the method.
They probably have similar names, but still different names. Confusing
may raise errors.

## Argument for call
I defined the map to use inside a spatial RDD I created thanks to:
```
val point3DRDD = new SpatialRDD[Geometry]
point3DRDD.rawSpatialRDD = point3Dcsv.mapPartitions(point3DFormatMapper.call)
```
Consequently, I got the error above:
```
/home/aitor/projects/sedona3D/common/src/main/scala/Point3DRDD.scala:35: error: missing argument list for method call in class FormatMapper
[ERROR] Unapplied methods are only converted to functions when a function type is expected.
[ERROR] You can make this conversion explicit by writing `call _` or `call(_)` instead of `call`.
[ERROR]   point3DRDD.rawSpatialRDD = point3Dcsv.mapPartitions(point3DFormatMapper.call)
```
After modifying `call` to `call(_)` It raised the following error:
```
Compiling 4 source files to /home/aitor/projects/sedona3D/common/target/classes at 1720604584858
[ERROR] /home/aitor/projects/sedona3D/common/src/main/scala/Point3DRDD.scala:35: error: overloaded method value mapPartitions with alternatives:
[ERROR]   [U](f: org.apache.spark.api.java.function.MapPartitionsFunction[org.apache.spark.sql.Row,U], encoder: org.apache.spark.sql.Encoder[U])org.apache.spark.sql.Dataset[U] <and>
[ERROR]   [U](func: Iterator[org.apache.spark.sql.Row] => Iterator[U])(implicit evidence$7: org.apache.spark.sql.Encoder[U])org.apache.spark.sql.Dataset[U]
[ERROR]  cannot be applied to (java.util.Iterator[String] => java.util.Iterator[_ <: org.locationtech.jts.geom.Geometry])
[ERROR]   point3DRDD.rawSpatialRDD = point3Dcsv.mapPartitions(point3DFormatMapper.call(\_))
```
It just mean that I'm trying to iterate over Geometry object with a iterator which is designed for sql rows. Everything is ok.

After modiyfing `Point3DFormatMapper` in order to import correctly GeometryType3D (I wrote GeometryType3D.GeometryType3D) I got:
```
Compilation failure: 
[ERROR] /home/aitor/projects/sedona3D/common/src/main/java/org/apache/sedona/common/enums/GeometryType3D.java:[27,30] cannot find symbol
[ERROR]   symbol:   variable GeometryType
[ERROR]   location: class GeometryType3D.GeometryType3D
[ERROR] /home/aitor/projects/sedona3D/common/src/main/java/org/apache/sedona/core/formatMapper/Point3DFormatMapper.java:[16,13] no suitable constructor found for PointFormatMapper(int,int,org.apache.sedona.common.enums.FileDataSplitter,boolean,GeometryType3D.GeometryType3D)
[ERROR]     constructor org.apache.sedona.core.formatMapper.PointFormatMapper.PointFormatMapper(org.apache.sedona.common.enums.FileDataSplitter,boolean) is not applicable
[ERROR]       (actual and formal argument lists differ in length)
[ERROR]     constructor org.apache.sedona.core.formatMapper.PointFormatMapper.PointFormatMapper(java.lang.Integer,org.apache.sedona.common.enums.FileDataSplitter,boolean) is not applicable
[ERROR]       (actual and formal argument lists differ in length)
[ERROR] /home/aitor/projects/sedona3D/common/src/main/java/org/apache/sedona/core/formatMapper/Point3DFormatMapper.java:[27,13] no suitable constructor found for PointFormatMapper(java.lang.Integer,int,org.apache.sedona.common.enums.FileDataSplitter,boolean,GeometryType3D.GeometryType3D)
[ERROR]     constructor org.apache.sedona.core.formatMapper.PointFormatMapper.PointFormatMapper(org.apache.sedona.common.enums.FileDataSplitter,boolean) is not applicable
[ERROR]       (actual and formal argument lists differ in length)
[ERROR]     constructor org.apache.sedona.core.formatMapper.PointFormatMapper.PointFormatMapper(java.lang.Integer,org.apache.sedona.common.enums.FileDataSplitter,boolean) is not applicable
[ERROR]       (actual and formal argument lists differ in length)
```
## How to import JavaGeom library ?
Looking for a way to easily determine how to import a java class.
In this case is the [JavaGeom](https://github.com/dlegland/javaGeom/blob/master/README.md) library.

I try adding the following dependency inside the `pom.xml`:
```
	<dependency>
            <groupId>fr.lgi2p</groupId>
            <artifactId>javaGeom</artifactId>
            <version>0.4.3</version>
        </dependency>
```
And got the following:
```
Failed to execute goal on project customExample3D: Could not resolve dependencies for project 
org.apache.sedona:customExample3D:jar:1.0.0: 
Could not find artifact fr.lgi2p:javaGeom:jar:0.4.3 in maven-central (https://repo.maven.apache.org/maven2/)
```
### Problem while using spatialPartitionning
I'm working on a 3D partitionner based on sedona's 2D partitionner. 
I modified a code to read and partition some 3D points. I got the following error:
```
```
Compiling 3 source files to /workspace/target/classes at 1720775620615
[ERROR] /workspace/src/main/scala/Main.scala:37: error: value spatialPartitioning is not a member of org.apache.spark.rdd.RDD[org.apache.spark.sql.Row]
[ERROR]   test3Drdd.spatialPartitioning(GridType3D.KDBTREE)
[ERROR]             ^
[ERROR] /workspace/src/main/scala/Main.scala:38: error: value buildIndex is not a member of org.apache.spark.rdd.RDD[org.apache.spark.sql.Row]
[ERROR]   test3Drdd.buildIndex(IndexType3D.OCTTREE, true)
[ERROR]             ^
[ERROR] /workspace/src/main/scala/Main.scala:39: error: value indexedRDD is not a member of org.apache.spark.rdd.RDD[org.apache.spark.sql.Row]
[ERROR]   test3Drdd.indexedRDD = test3Drdd.indexedRDD.cache()
```
Then I try to use Adapter.toSpatialRDD as an alternative to `.rdd` and it raised the following:
```
Compiling 3 source files to /workspace/target/classes at 1720777110942
[ERROR] /workspace/src/main/scala/Main.scala:36: error: overloaded method value toSpatialRdd with alternatives:
[ERROR]   (dataFrame: org.apache.spark.sql.DataFrame,geometryColId: Int)org.apache.sedona.core.spatialRDD.SpatialRDD[org.locationtech.jts.geom.Geometry] <and>
[ERROR]   (dataFrame: org.apache.spark.sql.DataFrame,geometryColId: Int,fieldNames: Seq[String])org.apache.sedona.core.spatialRDD.SpatialRDD[org.locationtech.jts.geom.Geometry] <and>
[ERROR]   (dataFrame: org.apache.spark.sql.DataFrame,geometryFieldName: String,fieldNames: Seq[String])org.apache.sedona.core.spatialRDD.SpatialRDD[org.locationtech.jts.geom.Geometry] <and>
[ERROR]   (dataFrame: org.apache.spark.sql.DataFrame,geometryFieldName: String)org.apache.sedona.core.spatialRDD.SpatialRDD[org.locationtech.jts.geom.Geometry]
[ERROR]  cannot be applied to (org.apache.spark.sql.DataFrame)
[ERROR]   val test3Drdd = Adapter.toSpatialRdd(df)
```
Then I tried with `.collect()` and it needs sparkcontext.
Finally, `toJavaRDD` works but raising th following:
```
Compiling 3 source files to /workspace/target/classes at 1720777741486
[ERROR] /workspace/src/main/scala/Main.scala:38: error: value spatialPartitioning is not a member of org.apache.spark.api.java.JavaRDD[org.apache.spark.sql.Row]
[ERROR]   test3Drdd.spatialPartitioning(GridType3D.KDBTREE)
[ERROR]             ^
[ERROR] /workspace/src/main/scala/Main.scala:39: error: value buildIndex is not a member of org.apache.spark.api.java.JavaRDD[org.apache.spark.sql.Row]
[ERROR]   test3Drdd.buildIndex(IndexType3D.OCTTREE, true)
[ERROR]             ^
[ERROR] /workspace/src/main/scala/Main.scala:40: error: value indexedRDD is not a member of org.apache.spark.api.java.JavaRDD[org.apache.spark.sql.Row]
[ERROR]   test3Drdd.indexedRDD = test3Drdd.indexedRDD.cache()
```
After modifying the line who build the RDD this way:
```
val test3Drdd = Adapter.toSpatialRdd(sedona.sql("select df (\_c0,\_c1,\_c2) as X, Y, Z limit 2"))
```
I got the following error:
```
Compiling 3 source files to /workspace/target/classes at 1721027167619
[ERROR] /workspace/src/main/scala/Main.scala:36: error: overloaded method value toSpatialRdd with alternatives:
[ERROR]   (dataFrame: org.apache.spark.sql.DataFrame,geometryColId: Int)org.apache.sedona.core.spatialRDD.SpatialRDD[org.locationtech.jts.geom.Geometry] <and>
[ERROR]   (dataFrame: org.apache.spark.sql.DataFrame,geometryColId: Int,fieldNames: Seq[String])org.apache.sedona.core.spatialRDD.SpatialRDD[org.locationtech.jts.geom.Geometry] <and>
[ERROR]   (dataFrame: org.apache.spark.sql.DataFrame,geometryFieldName: String,fieldNames: Seq[String])org.apache.sedona.core.spatialRDD.SpatialRDD[org.locationtech.jts.geom.Geometry] <and>
[ERROR]   (dataFrame: org.apache.spark.sql.DataFrame,geometryFieldName: String)org.apache.sedona.core.spatialRDD.SpatialRDD[org.locationtech.jts.geom.Geometry]
[ERROR]  cannot be applied to (org.apache.spark.sql.DataFrame)
[ERROR]   val test3Drdd = Adapter.toSpatialRdd(sedona.sql("select df (\_c0, \_c1,\_c2) as X, Y, Z limit 2"))
```
Then, I got an error because inside the Dockerfile I installed `open-java-jre-headless` that is a jre without compiler.

```
```
/workspace/src/main/scala/Main.scala:44: error: overloaded method value spatialPartitioning with alternatives:
[ERROR]   (x$1: org.apache.sedona.core.spatialPartitioning.quadtree.StandardQuadTree[\_])Boolean <and>
[ERROR]   (x$1: java.util.List[org.locationtech.jts.geom.Envelope])Boolean <and>
[ERROR]   (x$1: org.apache.sedona.core.spatialPartitioning.SpatialPartitioner)Unit <and>
[ERROR]   (x$1: org.apache.sedona.core.enums.GridType)Boolean
[ERROR]  cannot be applied to (org.apache.sedona.core.enums.GridType3D)
[ERROR]   test3Drdd.spatialPartitioning(GridType3D.KDBTREE)
```
## "Orphan case here"
It seems like a very tricky error. I started by reviewing all the files i created for 3D partitionning.
But the error was easier and [this article](https://stackoverflow.com/questions/23887097/orphaned-case-error-in-a-switch-statments) helped me a lot. You just need to check that you closed
all your brackets.

##Make a 3D partitionner
### Recreate quadtree-like architecture
FIrst of all, I copy all this architecture:
```
|-QuadTreePartitioner.java
|-QuadTreePartitioning.java
|-quadtree/
          |-QuadNode.java
          |-QuadRectangle.java
          |-StandardQuadTree.java
```
With the following architecture:
```
|-OctTreePartitioner.java
|-OctTreePartitioning.java
|-octtree/
          |-OctNode.java
          |-OctRectangle.java
          |-StandardOctTree.java
```
And inside the files, I replaced any "quad" with "oct" and add a Z or depth where it was necessary.
Also, I mentionned that there are modifications to make inside the Envelope class which is highly used by
the quadtree.

Therefore, I ran all this and got several bugs that I will fix step by step.
First of all, I got this:
```
/home/aitor/projects/sedonaCodeSource/spark/common/src/main/java/org/apache/sedona/core/spatialRDD/SpatialRDD.java:[30,57] package org.apache.sedona.core.spatialPartitioning.octree does not exist
```
Inside `SpatialRDD` there was a misstake in the octtree import which was written as `octree` with only one t.

After that, I got this:
```
/home/aitor/projects/sedonaCodeSource/spark/common/src/main/java/org/apache/sedona/core/spatialPartitioning/octtree/StandardOctTree.java:[43,19] cannot find symbol
[ERROR]   symbol:   class List
[ERROR]   location: class org.apache.sedona.core.spatialPartitioning.octtree.StandardOctTree<T>
```
After asking to chatGPT, I added both `ArrayList` and `List` imports 
from the Java.util class:
```
import java.util.List;
import java.util.ArraList;
```
It works even it don't explain why this works in the original code without being imported.
Now, we have this bug to fix:
```
/home/aitor/projects/sedonaCodeSource/spark/common/src/main/java/org/apache/sedona/core/spatialPartitioning/octtree/StandardOctTree.java:[56,28] cannot find symbol
[ERROR]   symbol:   class OctRectangle
[ERROR]   location: class org.apache.sedona.core.spatialPartitioning.octtree.StandardOctTree<T>
```
This is easy. It's just a misstake in `OctRectangle` which is `OctBox`.

### cannot find symbol class Visitor
After that, we have the next bug:
```
/home/aitor/projects/sedonaCodeSource/spark/common/src/main/java/org/apache/sedona/core/spatialPartitioning/octtree/StandardOctTree.java:[270,27] cannot find symbol
[ERROR]   symbol:   class Visitor
[ERROR]   location: class org.apache.sedona.core.spatialPartitioning.octtree.StandardOctTree<T>
```
`Visitor` was defined in another class. Let's verify it and import it if it's true.
Visitor is a Java design pattern used to apply many different methods to a objects without modifying it.

### Cannot find symbol OctBox
I got this error:
```
/home/aitor/projects/sedonaCodeSource/spark/common/src/main/java/org/apache/sedona/core/spatialPartitioning/OcttreePartitioning.java:[22,59] cannot find symbol
[ERROR]   symbol:   class OctBox
[ERROR]   location: package org.apache.sedona.core.spatialPartitioning.quadtree
```
Because of a misstake in the OctBox import. I put `quadtree` instead of `octtree`.
Now, I got another syntax error with:
```
 /home/aitor/projects/sedonaCodeSource/spark/common/src/main/java/org/apache/sedona/core/spatialPartitioning/OctTreePartitioner.java:[24,59] cannot find symbol
[ERROR]   symbol:   class StandardOctTree
[ERROR]   location: package org.apache.sedona.core.spatialPartitioning.quadtree
```
I wrote `quadtree` instead of `octtree`. Now it's fixed.

### ReferencedEnvelope3D imported in the wrong way
In order to fix it, the good import syntax is the following:
```
import org.geotools.geometry.jts.ReferencedEnvelope3D;
```
### Wrong syntax again
```
 /home/aitor/projects/sedonaCodeSource/spark/common/src/main/scala/org/apache/sedona/viz/showcase/ScalaExample.scala:196: error: value boundaryEnvelope is not a member of org.apache.sedona.core.spatialRDD.PointRDD
```
Replace boundaryEnvelope with boundaryEnvelope3D


## Compile example using modified sources
I want to compile an example which import modified sedona classes. So,
I looked for the path of code to compile in `pom.xml`in line 527.
It looks like the `</scope>` which is the way to process the code (compile, test, runtime and so on). And this parameter could be changed onto `provided` in order to give the modified source code for the compilation of the example. Now, in th example's `pom.xml` this parameter is a a reference thanks to `${sedona.scope}`. So there must be another file in what it is defined or another way to change this parameter.
Another possibility will be `</artefactID>`as we modified this 
parameter in our example and it changed the printed name at the start 
of the compilation.

Therefore, we have the next bug:
```
/workspace/spark/common/src/main/java/org/apache/sedona/core/
spatialRDD/SpatialRDD.java:[217,60] incompatible types: 
org.locationtech.jts.geom.Envelope cannot be converted to 
org.geotools.geometry.jts.ReferencedEnvelope3D
```
Once the file opened, it has to be the getEnvelopeInternal() method 
applied to geometry which try to make an impossible convertion.

###Recreate a git repo with sedona
My sedonaCodeSource repo was corrupted. So, I created a new repo
in my computer, copy and paste thanks to the GUI the files. And
manage the exception. The exception was `Permission denied`. So
I used `sudo`. And finally I got the same errors as the initial
situation and could continue fixing them.
The exceptions were the following:
```
/home/aitor/projects/sedonaCodeSource/target/maven-shared-archive-resources: Permission denied

 /home/aitor/projects/sedonaCodeSource/target/.plxarc: Permission denied

/home/aitor/projects/sedonaCodeSource/target/resolved-pom.xml: Permission denied
```
### How to switch a git repo from https to ssh
1. Open a terminal on this repo \n
2. Delete the https link between remote and local
```
git remote rm origin
```
3. Add a ssh connexion process
```
git remote add origin git@github.com:user/repo.git
```
4. See remote repo's updates without removing your work

```
git fetch origin branchName
```
### How to check size of files when you get 
`No space left on device`
Fist, type this:
```
du -d 1 -h .
```
In order to get informations about where is the big thing
which take all your memory.
Also type the following to remove temporary docker things:
```
docker system prune
```
### How to fix test bug about knn algo
I got the following:
```
Failed tests:
  ShapefileReaderTest.testLoadDbfFile:283 
  expected:<...0000US72087	72087	Lo[??]za	
  13	50159459	11975...> 
  but was:<...0000US72087	72087	Lo[??]za	
  13	50159459	11975...>
  PointKnnTest.testSpatialKNNCorrectness:192 null
```
In the same error message, there was also this:
```
FAILURE! - in org.apache.sedona.core.formatMapper.shapefileParser.shapes.ShapefileReaderTest
testLoadDbfFile(org.apache.sedona.core.formatMapper.shapefileParser.shapes.ShapefileReaderTest)  Time elapsed: 0.39 sec  <<< FAILURE!
org.junit.ComparisonFailure: expected:<...0000US72087	72087	Lo[??]za13	50159459	11975...> but was:<...0000US72087	72087	Lo[??]za	13	50159459	11975...>
	at org.junit.Assert.assertEquals(Assert.java:117)
	at org.junit.Assert.assertEquals(Assert.java:146)
	at org.apache.sedona.core.formatMapper.shapefileParser.shapes.ShapefileReaderTest.testLoadDbfFile(ShapefileReaderTest.java:283)
```
### How to look for a word into Vim
To make a simple search, press `Esc` and then:
```
/wordImLookingFor
```
For a whole word, type:
```
/\<wordImLookingFor\>
```
### How to fix "unable ro resolve ssh port"
While trying to copy and paste my modification from my computer
to the almalinux virtual machine, I got the following:

### How to fix "unable ro resolve ssh port"
While trying to copy and paste my modification from my computer
to the almalinux virtual machine, I got the following:
```
scp aitor@oracle:/projects/sedona/spark/common/src/main/java/
org/apache/sedona/core/spatialRDD/ 
almalinux@dev-sedona:sedona-laurent/spark/common/src/main/java/
org/apache/sedona/core/spatialRDDssh: 
connect to host oracle port 22: Connection refused
ssh: Could not resolve hostname dev-sedona: Temporary failure 
in name resolution
```
Then, I used [this](https://www.howtouselinux.com/post/fix-ssh-connect-to-host-port-22-connection-refused)

### How to create a ssh key for git hub
I used [this](https://docs.github.com/en/authentication/connecting-to-github-with-ssh/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent)
But [this](https://stackoverflow.com/questions/24392657/adding-an-rsa-key-without-overwriting) helped so much.

1. Generate new one
```
ssh-keygen -t rsa
```
2. Do not overwrite existing key
3. Create a `config` file to manage both keys
```
Host * (asterisk for all hosts or add specific host)
  AddKeysToAgent yes
  UseKeychain yes
  IdentityFile <key> (e.g. ~/.ssh/yourKey)
```
NB: you can use many keys with the following example:
```
Host *
  AddKeysToAgent yes
  UseKeychain yes
  IdentityFile ~/.ssh/id_rsa_private_server
  IdentityFile ~/.ssh/id_rsa_github
  IdentityFile ~/.ssh/id_rsa_work_server
```

bug:
```
Cloning into 'sedona'...
git@github.com: Permission denied (publickey).
fatal: Could not read from remote repository.

Please make sure you have the correct access rights
and the repository exists.
```
### ssh config error
```
Could not open a connection to your authentication agent.
```

