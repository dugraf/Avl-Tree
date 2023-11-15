package entities;

public class AvlTree<E> {
    private Node<E> raiz;

    public AvlTree() {
        raiz = null;
    }

    public Node<E> getRaiz() {
        return raiz;
    }

    // SEARCH AN ELEMENT

    public Node<E> search(int key) {
        return searchRecursive(key, raiz);
    }

    private Node<E> searchRecursive(int key, Node<E> node) {
        if(node == null) {
            return null;
        }
        if(key == node.getRaiz()) {
            return node;
        } else if(key < node.getRaiz()) {
            return searchRecursive(key, node.getEsquerda());
        } else {
            return searchRecursive(key, node.getDireita());
        }
    }

    // INSERT AN ELEMENT

    public void insert(int key) {
        raiz = insertRecursive(key, raiz);
    }

    private Node<E> insertRecursive(int key, Node<E> node) {
        if(node == null) {
            node = new Node(key);
        } else if(key < node.getRaiz()) {
            node.setEsquerda(insertRecursive(key, node.getEsquerda()));
        } else if(key > node.getRaiz()) {
            node.setDireita(insertRecursive(key, node.getDireita()));
        } else {
            System.out.println("Já existe um elemento com esse número na árvore!");
        }

        updateHeight(node);
        return balance(node);
    }

    // DELETE AN ELEMENT

    public void delete(int key) {
        raiz = deleteRecursive(key, raiz);
    }

    private Node deleteRecursive(int key, Node node) {
        if(node == null) {
            return null;
        }

        if(key < node.getRaiz()) {
            node.setEsquerda(deleteRecursive(key, node.getEsquerda()));
        } else if(key > node.getRaiz()) {
            node.setDireita(deleteRecursive(key, node.getDireita()));
        }

        // NO CHILDREN
        else if(node.getEsquerda() == null && node.getDireita() == null) {
            node = null;
        }
        // ONE CHILDREN
        else if(node.getEsquerda() == null) {
            node.setRaiz(node.getDireita().getRaiz());
            node.setDireita(null);
        } else if(node.getDireita() == null) {
            node.setRaiz(node.getEsquerda().getRaiz());
            node.setEsquerda(null);
        }
        // TWO CHILDREN
        else {
            Node minimumRight = findMinimum(node.getDireita());
            node.setRaiz(minimumRight.getRaiz());
            node.setDireita(deleteRecursive(minimumRight.getRaiz(), node.getDireita()));
        }

        updateHeight(node);
        return balance(node);
    }

    private Node findMinimum(Node node) {
        while(node.getEsquerda() != null) {
            node = node.getEsquerda();
        }
        return node;
    }

    // BALANCE TREE

    private int calculateHeight(Node node) {
        return node != null ? node.getAltura() : -1;
    }

    private void updateHeight(Node node) {
        if(node != null) {
            int leftHeight = calculateHeight(node.getEsquerda());
            int rightHeight = calculateHeight(node.getDireita());
            node.setAltura(1 + Math.max(leftHeight, rightHeight));
        }
    }

    private int balanceFactor(Node node) {
        if(node == null) {
            return 0;
        } else {
            return calculateHeight(node.getEsquerda()) - calculateHeight(node.getDireita());
        }
    }

    private Node rotateRight(Node node) {
        Node leftChild = node.getEsquerda();

        node.setEsquerda(leftChild.getDireita());
        leftChild.setDireita(node);

        updateHeight(node);
        updateHeight(leftChild);

        return leftChild;
    }

    private Node rotateLeft(Node node) {
        Node rightChild = node.getDireita();

        node.setDireita(rightChild.getEsquerda());
        rightChild.setEsquerda(node);

        updateHeight(node);
        updateHeight(rightChild);

        return rightChild;
    }

    private boolean simpleRotateRight(Node node) {
        return calculateHeight(node.getEsquerda().getEsquerda()) > calculateHeight(node.getEsquerda().getDireita());
    }

    private boolean simpleRotateLeft(Node node) {
        return calculateHeight(node.getDireita().getDireita()) > calculateHeight(node.getDireita().getEsquerda());
    }

    private Node balance(Node node) {
        updateHeight(node);
        if(balanceFactor(node) > 1) {
            if(!simpleRotateRight(node)) {
                node.setEsquerda(rotateLeft(node.getEsquerda()));
            }
            node = rotateRight(node);
        }
        else if(balanceFactor(node) < -1) {
            if(!simpleRotateLeft(node)) {
                node.setDireita(rotateRight(node.getDireita()));
            }
            node = rotateLeft(node);
        }
        return node;
    }

    // ORDERS

    public void preOrder(Node node) {
        if(node == null) {
            return;
        }
        System.out.print(node.getRaiz() + " ");

        preOrder(node.getEsquerda());
        preOrder(node.getDireita());
    }

    public void postOrder(Node node) {
        if(node == null) {
            return;
        }
        postOrder(node.getEsquerda());
        postOrder(node.getDireita());

        System.out.print(node.getRaiz() + " ");
    }

    public void inOrder(Node node) {
        if(node == null) {
            return;
        }
        inOrder(node.getEsquerda());
        System.out.print(node.getRaiz() + " ");
        inOrder(node.getDireita());
    }
}