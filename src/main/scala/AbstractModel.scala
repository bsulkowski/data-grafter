package pl.bsulkowski.datagrafter

abstract class DataGrafterTree {
  abstract class Element {
    def parent: Option[Element]
    def parentBranch: Option[String]

    def branches: Map[String, Element]
    def addBranch(branch: String, element: Element): Unit
    def removeBranch(branch: String): Unit

    def data: Option[Int]
    def expandReference: Option[ReferenceDefinitionElement]
    def expandFunction: Option[FunctionDefinitionElement]

    def copy(): Element
    def delete(): Unit
  }
  abstract class ReferenceDefinitionElement extends Element {
    def path: Path
    def follow: Element
  }
  abstract class FunctionDefinitionElement extends Element {
    def function: String
    def evaluate: Element
  }

  def root: Element
  def createNode(): Element
  def createData(value: Int): Element
  def createReference(path: Path): Element
  def createFunction(function: String): Element

  abstract class Path{
    def stepDown(branch: String): Path
    def stepBack: Path
  }

  def rootAbsolutePath: Path
  def ancestorRelativePath(levels: Int): Path
}
