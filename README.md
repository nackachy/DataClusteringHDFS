# DataClusteringHDFS

# DataClustering 

## Description

The purpose of this project is to cluster Brisbane_CityBike based on longitude and latitude. 

## Requirements

* [Java 8](https://www.java.com/fr/download/faq/java8.xml)
* [Scala 2.11.7](https://www.scala-lang.org/download/2.11.7.html)
* [SBT 1.2.8](https://piccolo.link/sbt-1.2.8.zip)
* [Spark 2.1.0](https://spark.apache.org/releases/spark-release-2-1-0.html)

## Steps

* Initializing Spark Session
* Importing Data
* Building the Pipeline
  * Assembling Vectors
  * Training Model
* Applying model to data
* View clustering

## Configuration

Before executing the code, you have to change the parameters existing in application.properties.

    hdfsPath = C:/Users/Lenovo/Desktop/emotion/DataClusteringHDFS
    inputClusterNumber = 4
    master = local
    appName = DataClustering
    nameOfColumnCluster = Clusters
    titleOfDataPlot = Data Plot
    stepsOfAxis = 0.00001
    heightOfPlot = 400.0
    widthOfPlot = 600.0
    outPutData = C:/Users/Lenovo/Desktop/emotion/DataClusteringHDFS/output
    testLocalData = "src/main/data/Brisbane_CityBike.json"

* **hdfsPath** path of the project.
* **inputClusterNumber** is the number of clusters chosen to cluster the data.
* **nameOfColumnCluster** is the name of the column of the clusters added to data.
* **outPutData** is the path where the clustered data is going to be saved.

## Running project on Spark

To build the project, run : 

    sbt clean assembly
    
This will produce a jar containing the compiled project

Then you can submit the job using **spark-submit** :

    ./bin/spark-submit --class "com.DataVectis.clustering.Run"
              --master local 
              path/to/your/jar.jar

## Results

In this project, we've chosen to cluster our data in 3 clusters. The plot provided will show how the work was done ! 

This is how the result would look like.

***Data with Clusters***

![Data Clustered](https://github.com/nackachy/DataClustering/blob/master/dataWithClusters.PNG)

***Data Plot***

![Data Plot](https://github.com/nackachy/DataClustering/blob/master/clusters.PNG)




