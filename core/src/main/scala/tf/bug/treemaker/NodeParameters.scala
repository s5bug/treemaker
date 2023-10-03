package tf.bug.treemaker

sealed trait NodeParameters {

  type Values

}

object NodeParameters {

  final case class WithValues(p: NodeParameters, v: p.Values)

  final case class Simple[T]() extends NodeParameters {
    override type Values = T
  }

}
