package tf.bug.treemaker

import schrodinger.kernel
import java.util.UUID

trait Tree {

  def root: UUID

  def children: Map[UUID, Vector[UUID]]

  // TODO schrodinger seems to have changed??
  def distributionPoolAt(poolName: String, path: TreePath): Distribution[???, ???, ???]

}
