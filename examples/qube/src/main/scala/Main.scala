object Main{

  def main(args: Array[String]): Unit = {
         println("Hello from the futur where Sedona3D is fully operational")
         val spark = SparkSession
          .builder()
          .master("local[*]")
          .appName("Testtouille")
          .getOrCreate();
  }
}
