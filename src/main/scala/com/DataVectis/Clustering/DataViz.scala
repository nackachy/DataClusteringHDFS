package com.DataVectis.Clustering

import org.apache.spark.sql.DataFrame
import vegas._
import vegas.sparkExt._

class DataViz{

  def View(dataFrame: DataFrame) : String ={

    val titleOfDataPlot,stepsOfAxis,nameOfColumnCluster,heightOfPlot,widthOfPlot = new prop

    val plot = Vegas(titleOfDataPlot.getProp("titleOfDataPlot"),
      width = widthOfPlot.getProp("widthOfPlot").toDouble,
      height = heightOfPlot.getProp("heightOfPlot").toDouble).
      withDataFrame(dataFrame).
      mark(Point).
      encodeX("longitude", Quantitative ,bin = Bin(step = stepsOfAxis.getProp("stepsOfAxis").toDouble) ,enableBin=false).
      encodeY("latitude", Quantitative ,bin = Bin(step = stepsOfAxis.getProp("stepsOfAxis").toDouble) ,enableBin=false).
      encodeColor(
        field= nameOfColumnCluster.getProp("nameOfColumnCluster"),
        dataType=Nominal,
        legend=Legend(orient="left", title="Clusters"))

    plot.show

    return  plot.html.plotHTML()
  }
}