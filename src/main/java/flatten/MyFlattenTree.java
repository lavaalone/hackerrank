package flatten;

import java.util.*;

public class MyFlattenTree<T> implements FlattenTree<T> {
    @Override
    public List<T> flattenInOrder(Tree<T> tree)   {
        if (tree == null)
                throw new IllegalArgumentException("Tree is null.");

        if (tree.get().isLeft()) {
            return Arrays.asList(tree.get().<T>ifLeft(p -> p));
        }

        return tree.get().ifRight(p -> {
            final List<T> result = new ArrayList<T>();
            result.addAll(flattenInOrder(p.left()));
            result.addAll(flattenInOrder(p.middle()));
            result.addAll(flattenInOrder(p.right()));
            return result;
        });
//        Stack<Tree<T>> stack = new Stack<Tree<T>>();
//        stack.push(tree);
//        while (!stack.isEmpty()) {
//            Tree<T> inner_tree = stack.pop();
//            if (inner_tree.get().isLeft()) {
//                T value = inner_tree.get().ifLeft(p -> {
//                    return p;
//                });
////                T value = inner_tree.get().ifLeft(new Function<T, T>() {
////                    @Override
////                    public T apply(T t) {
////                        return t;
////                    }
////                });
//                System.out.println("T := " + value + ", LEFT");
//            } else {
//                inner_tree.get().ifRight(p -> {
//                    System.out.println("p.left := " + p.left().get().);
//                    System.out.println("p.middle := " + p.middle());
//                    System.out.println("p.right := " + p.right());
//                    return p;
//                });
//            }
//        }

//            if (tree.get().isLeft()) {
//                return Arrays.asList(tree.get().ifLeft( new Function<T, T>() {
//
//                    public T apply(T p) {
//                        return p;
//                    }
//
//                } ));
//
//            } else {
//                return tree.get().ifRight( new Function<Triple<Tree<T>>, List<T>>() {
//
//                            public List<T> apply(Triple<Tree<T>> p) {
//
//                                List<T> nodes = new ArrayList<T>();
//                                nodes.addAll(flattenInOrder(p.left()));
//                                nodes.addAll(flattenInOrder(p.middle()));
//                                nodes.addAll(flattenInOrder(p.right()));
//
//                                return nodes; //return all fetched nodes
//                            }
//                    } );
//
//            } //end if

//        if (tree.get().isLeft()) {
//            // Java 8:
//            return Arrays.asList(tree.get().<T> ifLeft(p -> p));
//        }
//        return tree.get().ifRight(p -> {
//
//            final List<T> result = new LinkedList<T>();
//            result.addAll(flattenInOrder(p.left()));
//            result.addAll(flattenInOrder(p.middle()));
//            result.addAll(flattenInOrder(p.right()));
//
//            return result; // return all fetched nodes
//        });

    } //end function


    public static void main(String[] args)
    {
        Tree<Integer> nodes = Tree.Node.tree(5, 4, 9);
        Tree<Integer> root = new Tree.Node<Integer>(Tree.Leaf.leaf(1), nodes, Tree.Leaf.leaf(6));
        MyFlattenTree<Integer> myFlattenTree = new MyFlattenTree<Integer>();

        System.out.println("Flattened tree: " + myFlattenTree.flattenInOrder(root));
    }
}