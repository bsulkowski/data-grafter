# Data Model

* Data is organized as a tree with root
* Branches are labelled with alphanumeric strings
* Labels are unique between branches from given node
* Leafs can contain:
  * Simple data -- number, text, etc.
  * References to other tree elements
* Nodes can contain functions
* Children of a function node act as its parameters
* Function node is visible for user and for other elements from parent side of a tree as a result of calculations
* Reference is visible for user and for other elements from parent side of a tree as a referenced elements

## References

* can be defined via text expression describing path in tree
* can reference one or more elements
* tree branches and references together have to be a DAG
* a path is defined by literal values of branch labels
* special expressions can be used to reference:
  * any child
  * any descendant
  * parent

## Available Functions

* equality
* arithmetic operations
* comparisons
* logical operators
* text
* database
  * join
  * filter
  * map
  * group by
  * sort
  * aggregate
  * set theory
* structure comparison

# User Interface

* display chosen tree fragment
* switch function node view between input parameters and results
* modify structure and content of a tree
* results presented as a reference (editable) where possible
* branch labels added on default as consecutive numbers
* define reference by pointing element visually 
* copy and paste whole subtrees
* mass operations on sets of elements
