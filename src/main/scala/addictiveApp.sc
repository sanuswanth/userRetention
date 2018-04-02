import java.io.{File, PrintWriter}

import org.joda.time.DateTime

import scala.io.Source

case class Data(day: Int, user: String)

object addictiveApp extends App {

  override def main(args: Array[String]): Unit = {

    if (args.length < 2) {
      System.err.println("Please set argument for the input Path & outPut path")
      System.exit(1)
    }
    toFileOutput(args(0), args(1))

  }

  def toFileOutput(input: String, output: String) = {
    var i = 1
    val res = process(readTxt(input)).zipWithIndex.map{case(el, i) => s"$i,"+ el.mkString(", ") }.mkString("\n")
    println("Last step: Writing to File ...")
    val pw = new PrintWriter(new File(output))
    pw.write(res)
    pw.close
    println(" ✓ Done")

  }



  def printOutput(input: String) = {
    var i = 1
    val res = process(readTxt(input))

    println("-------- Output ---------")
    res.foreach{
      r=>
        println(s"$i  :  ${r.mkString("  ,")}")
        i = i+1
    }
  }


  def readTxt(input: String): List[Data] = {
    print("Step 1: Reading & preparing the text file ...")
    val bufferedSource = Source.fromFile(input)
    val data = bufferedSource.getLines.map{
      line =>
        val cols = line.split(",").map(_.trim)
        val day = new DateTime(cols(0).toLong*1000L).getDayOfMonth
        Data(day,cols(1))
    }.toList
    bufferedSource.close
    println(" ✓ Done")
    data
  }



  def process(list: List[Data]) = {
    print("Step 2: Getting the users ...")

    val listUser: Seq[(String, List[Int])] = list
      .groupBy(_.user)
      .toSeq
      .sortBy(_._1)
      .map(el => (el._1, el._2.map(_.day)))



    println(" ✓ Done")

    print("Step 3: Getting the days ...")
    val numberOfDays = list.groupBy(_.day).toList.length
    println(" ✓ Done")

    print("Step 4: Doing the magic ...")
    val res : Seq[List[Option[(Int, Int)]]] = listUser.map { el =>
      val days = el._2
      val userId = el._1

      var j = 1
      var c = 0


      (1 to numberOfDays).map {
        i =>
          if (c < days.length && i == days(c)) {
            val toReturn = Some((i, j))
            j = j + 1
            c = c + 1
            toReturn
          } else {
            j = 1
            None
          }
      }.toList
    }

    println(" ✓ Done")

    val output = Array.ofDim[Int](numberOfDays, numberOfDays)
    print("Step 5: Preparing the Output ...")
    res.map(_.filter(_.isDefined).map(_.get)).flatten.groupBy(r => r).map {
      r =>
        val x = r._1._1-1
        val y = r._1._2-1
        output(x)(y) = r._2.length
    }
    println(" ✓ Done")
    output
  }



}
