package pl.bsulkowski.datagrafter

abstract class Path
case class AbsolutePath(branchNames: List[String]) extends Path
case class RelativePath(parentCount: Int, branchNames: List[String]) extends Path
case class SystemPath(branchNames: List[String]) extends Path
case class ArgumentPath(argumentName: String, branchNames: List[String]) extends Path
