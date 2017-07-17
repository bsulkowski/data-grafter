package pl.bsulkowski.datagrafter

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
