package pl.bsulkowski.datagrafter

abstract class DataGrafterTree {
  abstract class Element {
    def parent: Option[Element]
    def sourceBranchName: Option[String]

    def branches: Map[String, Element]
    def addBranch(branchName: String, element: Element): Option[Element]
    def removeBranch(branchName: String): Option[Element]

    def data: Option[Value]
    def expandReference: Option[ReferenceDefinition]
    def expandComputation: Option[ComputationDefinition]

    def copy(): Element
    def delete(): Unit
  }
  abstract class ReferenceDefinition(val path: Path) extends Element
  abstract class ComputationDefinition(val function: Function) extends Element

  def root: Element
  def createNode(): Element
  def createData(value: Value): Element
  def createReference(path: Path): Element
  def createComputation(function: Function): Element

  type Value = String

  abstract class Function {
    def compute(arguments: Map[String, Element]): Element
  }
  def standardFunction(functionName: String): Function

  abstract class Path
  case class AbsolutePath(branchNames: List[String]) extends Path
  case class RelativePath(parentCount: Int, branchNames: List[String]) extends Path
}
