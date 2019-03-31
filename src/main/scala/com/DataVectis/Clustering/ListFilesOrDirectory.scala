package com.DataVectis.Clustering

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

class ListFilesOrDirectory {

  // Get files names with path
  def getListFileNames(hdfsPath: String): List[String] = {
    val hdfs = FileSystem.get(new Configuration())

    hdfs
      .listStatus(new Path(hdfsPath))
      .flatMap { status =>
        // If it's a file:
        if (status.isFile)
          List(hdfsPath + "/" + status.getPath.getName)
        // If it's a dir and we're in a recursive option:
        else
          getListFileNames(hdfsPath + "/" + status.getPath.getName)
      }
      .toList
      .sorted
  }



}