package pl.bsulkowski.datagrafter

abstract class Tree {
  abstract class Element {
    def parent: Option[Element]
    def sourceBranchName: Option[String]

    def branches: Map[String, Element]
    def addBranch(branchName: String, element: Element): Option[Element]
    def removeBranch(branchName: String): Option[Element]

    def data: Option[Value]
    def expandReference: Option[ReferenceDefinition]
    def expandComputation: Option[ComputationDefinition]
    def expandFunction: Option[FunctionDefinition]

    def copy(): Element
    def delete(): Unit
  }
  abstract class ReferenceDefinition(val target: Path) extends Element
  abstract class ComputationDefinition(val function: Path) extends Element
  abstract class FunctionDefinition(val argumentNames: List[String]) extends Element

  def root: Element
  def createNode(): Element
  def createData(value: Value): Element
  def createReference(target: Path): Element
  def createComputation(function: Path): Element
  def createFunction(argumentNames: List[String]): Element

  type Value = String
}
