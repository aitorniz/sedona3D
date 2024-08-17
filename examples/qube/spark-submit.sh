#!/bin/bash

$SPARK_HOME/bin/spark-submit \
  --master local[2]\
  --driver-memory=2g\
  --executor-memory=2g\
  --packages org.apache.sedona:sedona-spark-shaded-3.0_2.12:1.6.0,org.datasyslab:geotools-wrapper:1.6.0-28.2\
  --class Main original-sedona-spark-example-1.0.0.jar
