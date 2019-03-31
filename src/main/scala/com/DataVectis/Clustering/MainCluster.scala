package com.DataVectis.Clustering

import java.io.File
import org.apache.spark.sql.{SaveMode, SparkSession}
import java.util.regex.Pattern
import scala.util.control.Breaks.{break, breakable}


object MainCluster {

  def main(args: Array[String]) {

    // Initializing SparkContext

    val master, appName, hdfsPath, outPutData = new prop

    val spark = SparkSession.builder.
      master(master.getProp("master"))
      .appName(appName.getProp("appName"))
      .getOrCreate()


    // Importing data

    val files = new ListFilesOrDirectory().getListFileNames(hdfsPath.getProp("hdfsPath"))

    breakable {
      var i = 1
      for (inputData <- files) {
        // Looking for Data path
        val patternFile = Pattern.compile("(.+?)Brisbane_CityBike.json")
        // matching
        val matchingFile = patternFile.matcher(inputData)

        if (matchingFile.find()) {

        //Get the appropriate path
        val  BrisbaneCityBike = spark.read.json(inputData.replaceAll("\\\\","/"))

        // Creating an instance from "Classify.scala" Class to get the model of clustering
        val mod = new Clustering

        //Creating Model
        val modelToCluster = mod.getModel(BrisbaneCityBike)

        // Adding Cluster Labels to our data
        val clusters = modelToCluster.transform(BrisbaneCityBike)

        // Displaying Clustered Data
        clusters.show()

        // Ploting Individuals according to Clusters laels
        val plot = new DataViz
        plot.View(clusters.toDF())

        //Checking if output exists
        val checkOutputDataPath = new File(outPutData.getProp("outPutData")).mkdir()

        if (checkOutputDataPath) {
          // Saving the BrisbaneCityBike with labels

        clusters
          .repartition(1)
          .write
          .mode(SaveMode.Overwrite)
          .format("com.databricks.spark.csv")
          .option("header", "true")
          .option("delimiter", ";")
          .save(outPutData.getProp("outPutData"))

          println("Data saved in : "+outPutData.getProp("outPutData"))
          } else{

          println("OutPut Data Path Not Found")
          println("Creatin Data Path Out Put")
            new File(outPutData.getProp("outPutData"))

          clusters
            .repartition(1)
            .write
            .mode(SaveMode.Overwrite)
            .format("com.databricks.spark.csv")
            .option("header", "true")
            .option("delimiter", ";")
            .save(outPutData.getProp("outPutData"))

          println("Data saved in : "+outPutData.getProp("outPutData"))
          }
          break()
        }else if(files.size == i){
          println("No Data Found")
        }
        i = i+1
      }


    }
  }
}
