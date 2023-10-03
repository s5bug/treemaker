package tf.bug.treemaker

sealed trait TreePath

object TreePath {
  // TODO better interface for this
  case class Down(from: TreePath, childIdx: Int) extends TreePath
  case object Root extends TreePath
}
