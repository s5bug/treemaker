package tf.bug.treemaker

case class NodeType(
  name: String,
  arity: Int,
  // TODO probably a tag system instead
  allowedChildren: Set[String],
  parameters: NodeParameters,

  // TODO what does a node need to know to fill itself?
  generateParameters: (TreePath, ???) => parameters.Values,
  // TODO how does a node affect the context below it?
  affectContext: (TreePath, parameters.Values, ???) => ???
)
