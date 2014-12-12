import java.io.File

import net.sourceforge.tess4j.Tesseract

object MainApp extends App {

  //Dir where we store png-files for recognition
  val dirName = "images"
  val libDirName = "c:/devtools/tesseract/"

  //Load library
  try {
    System.load(libDirName + "liblept168.dll")
    System.load(libDirName + "libtesseract302.dll")
  } catch {
    case e: Exception => println("Native code library failed to load.\n" + e)
  }

  def ocr(filename: File) = {
    println("\n*** Trying to recognize " + filename.getName + " ***")
    val instance = Tesseract.getInstance()

    try {
      val result = instance.doOCR(filename)
      println(result)
    } catch {
      case e: Exception => println(e.getMessage)
    }
    println("*** End of recognition ***\n")
  }

  val source = new java.io.File(dirName).listFiles.filter(_.getName.endsWith(".png"))

  source.foreach(ocr)
}