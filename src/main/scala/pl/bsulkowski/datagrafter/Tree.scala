package pl.bsulkowski.datagrafter

abstract class Tree {
  abstract class Element {
    def parent: Option[Element]
    def parentBranch: Option[String]

    def branches: Map[String, Element]
    def addBranch(name: String, element: Element): Option[Element]
    def removeBranch(name: String): Option[Element]

    def data: Option[Data]
    def graft: Option[Graft]
  }

  def root: Element
  def createNodeElement(): Element
  def createDataElement(data: Data): Element
  def createGraftElement(graft: Graft): Element

  type Data = String

  abstract class Graft
  case class ReferenceGraft(target: Path) extends Graft
  case class ApplicationGraft(function: Element, arguments: Element) extends Graft
  case class FunctionGraft(arguments: Element, result: Element) extends Graft
}
