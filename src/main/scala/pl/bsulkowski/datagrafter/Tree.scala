package pl.bsulkowski.datagrafter

/** Contains data and computations organized in a mutable directed tree structure.
  *
  * Branch names allow unique identification of any tree element by path from root.
  * Allows use of references between tree elements, that work like symbolic links for files.
  * Tree branches and references together must form a DAG structure.
  *
  * Computations are represented by nodes with defined function and arguments passed in branches.
  *
  * Primitive data values can be stored only in leafs.
  */
abstract class Tree {

  type Element <: ElementView

  /**
    *
    */
  trait ElementView {

    /** Returns handle to this or None in case of a root.
      * 
      * Handle consists of parent Element and branch name.
      * For elements that define a Graft it is the parent of corresponding graft Element.
      */
    def handle: Option[(Element, String)]

    /** Returns the Content of the Element eventually resolved from this Element.
      */
    def content: Content

    /** Returns Graft used to define this graft Element.
      */
    def graft: Option[Graft]
  }

  abstract class Content
  case class NodeContent(branches: scala.collection.mutable.Map[String, Element]) extends Content
  case class DataContent(data: String) extends Content
  case class FunctionContent(function: (Element => Element)) extends Content
  case class ErrorContent(error: String) extends Content

  abstract class Graft
  case class ReferenceGraft(target: Path) extends Graft
  case class ApplicationGraft(function: Element, arguments: Element) extends Graft
  case class FunctionGraft(arguments: Element, result: Element) extends Graft

  /** The root Element of this Tree.
    *
    * There may exist roots of other subtrees not connected to the main tree,
    * but they are not tracked explicitly.
    */
  val root: Element

  def createNodeElement(): Element
  def createDataElement(data: String): Element
  def createGraftElement(graft: Graft): Element
}
