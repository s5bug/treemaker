# use cases

- source-to-source (testing)
- program synthesis (copilot etc)

in this essence we are looking to generate **and recognize(?)** terms that fulfill constraints

- generating more efficient expressions (reduce w/ rewrites)

# i/o

user provides:
- list of paths
- function of how to process those paths

multiple of these! run ~~the most specific (first?) that matches~~ based on weight, discounting those that don't match

program:
- runs function on all incomplete nodes

# current idea

three languages:

a. specifying node types

```
Lambda:
  - Arity: 1
  - Allowed Children: expressions
  - Introduces [current path] as an element into "bindings" distribution pool with weight 1
And:
  - Arity: 2
  - Allowed Children: expressions
  - Forbids left and right side from being equivalent
  - Forbids (not left) and right ^
  - Forbids left and (not right) ^ [TODO: possibly unneeded?]
Binding:
  - Arity: 0
  - Parameters: sample from "bindings" distribution pool
```

keyed distribution pools are used to control introduction of arbitrary random data

b. specifying input tree

`λx . (x && (λy . x))`
```
Lambda
  And
    Binding(Root)
    Lambda
      Binding(Root)
```

c. some way of specifying "rewrite" rules, i.e. DeMorgan simplifications?

# current structure

`NodeType` is meant to express elements of language `a`.

`Tree` is meant to express elements of language `b`.

language `c` is yet unsolved.

# current things missing

- How to express language `c`?
- It's easy to manage nodes pointing to each-other (see `Tree`) but the associated data is a little bit harder
  - Perhaps a `Node` class that contains a `NodeType` and that type's `.parameters.Values`?
- Using this keyed distribution pool method, are we able to get away with _only_ downwards propagation of the pools?
- How do we select distribution of allowed children?
  - schrodinger's changes have completely thrown me off in terms of being able to just store/generate a distribution in
    a context somewhere and use that
