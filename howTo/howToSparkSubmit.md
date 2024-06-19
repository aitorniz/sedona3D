# How to make a Spark-submit easily

You must make your own spark-submit.sh in order which seems like this theoretically:
```
./bin/spark-submit \
  --master <master-url> \
  --deploy-mode <deploy-mode> \
  --conf <key<=<value> \
  --driver-memory <value>g \
  --executor-memory <value>g \
  --executor-cores <number of cores>  \
  --jars  <comma separated dependencies>
  --class <main-class> \
  <application-jar> \
  [application-arguments]
```
## Firstly, the options
Manage the dependencies and resources
### Deployment Modes --deploy-mode
In the case you choose cluster, then the parallelization will emphasize
on management flexibility.Although, the secund option client must protect
your calculus letting less options.
### Cluster Managers (-master)
Choose who will be the conductor of your spark-orchestra.
There are several possibilities available: \
The more standard seems to be Standalone but Mesos ans Kubernetes are also
very used. Evenmore knowing that Kubernetes design makes it quite
useful for managing containers.
According your choice:\
- for Mesos: mesos://HOST:PORT
- for Kubernetes: k8s://HOST:PORT or k8s://https://HOST:PORT
- for Standalone: spark://HOST:PORT

# What I made to run Sedona-Spark example:
```
/root/projects/boussole/ \
  --deploy-mode cluster\
  --driver-memory=2g\
  --executor-memory=2g\
  --executor-cores=4\
  --num_executor=3\
  --deploy-mode cluster\
  --driver-memory=2g\
  --executor-memory=2g\
  --executor-cores=4\
  --num_executor=3\
  --jars ./target/original-sedona-spark-example-1.0.0.jar\
  --class com.examples.MainClass
```
