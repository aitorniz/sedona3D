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
Then, I look for the splitter.
