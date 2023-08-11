import cats.Eval
import cats.syntax.all.*

sealed trait Tree {

  def complete: Eval[Either[Tree, CompleteTree]]

}

case class IncompleteTree(introduces: Set[Int], children: List[Option[Tree]]) extends Tree {

  override def complete: Eval[Either[Tree, CompleteTree]] = {
    val emptyChildren: Eval[Either[List[Option[Tree]], List[CompleteTree]]] = Eval.now(Right(Nil))
    val processedChildren: Eval[Either[List[Option[Tree]], List[CompleteTree]]] = children.foldRightDefer(emptyChildren) {
      case (None, accumEval) => accumEval.map {
        case Left(incompleteTrees) => Left(None :: incompleteTrees)
        case Right(completeTrees) => Left(None :: completeTrees.map(Some(_)))
      }
      case (Some(child), accumEval) => child.complete.flatMap {
        case Left(incompleteTree) => accumEval.map {
          case Left(incompleteTrees) => Left(Some(incompleteTree) :: incompleteTrees)
          case Right(completeTrees) => Left(Some(incompleteTree) :: completeTrees.map(Some(_)))
        }
        case Right(completeTree) => accumEval.map(_.bimap(Some(completeTree) :: _, completeTree :: _))
      }
    }
    processedChildren.map(_.bimap(
      incompleteTrees => IncompleteTree(introduces, incompleteTrees),
      completeTrees => CompleteTree(introduces, completeTrees)
    ))
  }

}

case class CompleteTree(introduces: Set[Int], children: List[CompleteTree]) extends Tree {

  override def complete: Eval[Right[Tree, CompleteTree]] = Eval.now(Right(this))

}
