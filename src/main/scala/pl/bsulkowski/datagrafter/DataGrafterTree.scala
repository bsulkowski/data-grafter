package pl.bsulkowski.datagrafter

/** Contains data and computations organized in a mutable directed tree structure.
 *  
 *  Branch names allow unique identification of any tree element by path from root.
 *  Allows use of references beetwen tree elements, that work like symbolic links for files.
 *  Tree branches and references together must form a DAG structure.
 *  
 *  Computations are represented by nodes with defined function and arguments passed in branches.
 *  
 *  Primitive data values can be stored only in leafs.
 */
abstract class DataGrafterTree {
  abstract class Element {
    def parent: Option[Element]
    def branchName: Option[String]

    def branches: Map[String, Element]
    def addBranch(branchName: String, element: Element): Option[Element]
    def removeBranch(branchName: String): Option[Element]

    def data: Option[Value]
    def expandReference: Option[ReferenceDefinition]
    def expandComputation: Option[ComputationDefinition]

    def copy(): Element
    def delete(): Unit
  }
  abstract class ReferenceDefinition extends Element {
    def path: Path
    def follow: Element
  }
  abstract class ComputationDefinition extends Element {
    def function: Function
    def compute: Element
  }

  def root: Element
  def createNode(): Element
  def createData(value: Value): Element
  def createReference(path: Path): Element
  def createComputation(function: Function): Element

  type Value = String

  abstract class Function

  abstract class Path {
    def stepDown(branchName: String): Path
    def stepBack: Path
  }

  def rootAbsolutePath: Path
  def ancestorRelativePath(levels: Int): Path
}
