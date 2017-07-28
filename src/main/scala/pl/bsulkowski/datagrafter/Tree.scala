package pl.bsulkowski.datagrafter

abstract class Tree {
  abstract class Element {
    def parent: Option[Element]
    def sourceBranchName: Option[String]

    def branches: Map[String, Element]
    def addBranch(branchName: String, element: Element): Option[Element]
    def removeBranch(branchName: String): Option[Element]

    def data: Option[Value]
    def definition: Option[(Path, Element)]

    def copy(): Element
    def delete(): Unit
  }

  def root: Element
  def createNode(): Element
  def createData(value: Value): Element
  def createComputationNode(function: Path): Element

  type Value = String
}
