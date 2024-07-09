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
