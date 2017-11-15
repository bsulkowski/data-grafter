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
  /** 
    * 
    */
  abstract class Element {
    /** Parent Element of this or None in case of a root.
      * 
      * For elements that define a Graft it is the parent of corresponding graft Element.
      */
    def parent: Option[Element]
    /** Branch name pointing to this from the parent Element or None in case of a root.
      * 
      * For elements that define a Graft it is the parentBranch of corresponding graft Element.
      */
    def parentBranch: Option[String]

    /** Returns all branches of the node Element eventually resolved from this Element.
      * 
      * Branches for given node Element has unique names and map to direct child Elements.
      */
    def branches: Map[String, Element]
    /** Attempts to add a branch for the node Element eventually resolved from this Element.
      * 
      * Returns None in case of success or that Element in case of failure.
      * Parent information of that Element is updated accordingly.
      */
    def addBranch(name: String, that: Element): Option[Element]
    /** Attempts to remove a branch for the node Element eventually resolved from this Element.
      * 
      * Returns detached Element in case of success or None in case of failure.
      * Parent information of detached Element is updated accordingly.
      */
    def removeBranch(name: String): Option[Element]

    /** Returns Data contained in the data Element eventually resolved from this Element.
      */
    def data: Option[Data]
    /** Returns Graft used to define this graft Element.
      */
    def graft: Option[Graft]
  }

  /** The root Element of this Tree.
    *
    * There may exist roots of other subtrees not connected to the main tree,
    * but they are not tracked explicitly.
    */
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
