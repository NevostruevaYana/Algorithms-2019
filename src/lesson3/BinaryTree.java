package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLOutput;
import java.util.*;

// Attention: comparable supported but comparator is not
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    // для контроля добавления элементов в последних задачах
    private T topLine = null;
    private T bottomLine = null;

    BinaryTree(){}

    BinaryTree(T bottomLine, T topLine) {
        this.bottomLine = bottomLine;
        this.topLine = topLine;
    }

    private boolean inRange(T t) {
        if (bottomLine == null && topLine == null)
            return true;
        if (bottomLine == null)
            return t.compareTo(topLine) < 0;
        if (topLine == null)
            return t.compareTo(bottomLine) >= 0;
        return t.compareTo(topLine) < 0 && t.compareTo(bottomLine) >= 0;
    }

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        if (!inRange(t))
            throw new IllegalArgumentException();
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        }
        else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        }
        else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    public int height() {
        return height(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    private int height(Node<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */
    /**
     * Сложность по времени: O(n) (в худшем случае)
     * Сложность по памяти: O(1) (хранение current узла и его родителя parent)
     */
    @Override
    public boolean remove(Object o) {
        T value = (T) o;
        if (!contains(value))
            return false;
        Node<T> current = root;
        Node<T> parent = root;
        while (current.value != value) {
            parent = current;
            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        if (current.left == null || current.right == null)
            if (current.left == null) {
                transfer(current, parent, current.right);
            } else {
                transfer(current, parent, current.left);
            }
        else {
            Node<T> min = current.right;
            Node<T> minParent = current;
            while (min.left != null) {
                minParent = min;
                min = min.left;
            }
            if (min != current.right) {
                minParent.left = min.right;
                min.right = current.right;
            }
            min.left = current.left;
            transfer(current, parent, min);
        }
        size--;
        return true;
    }

    private void transfer(Node<T> current, Node<T> parent, Node<T> node) {
        if (current == root)
            root = node;
        else {
            if (parent.left == current)
                parent.left = node;
            else
                parent.right = node;
        }
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current;
        private Stack<Node<T>> stack;

        private BinaryTreeIterator() {
            stack = new Stack<>();
            push(root);
        }

        // изначально храним только всех левых потомков корня
        private void push(Node<T> root) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        /**
         * Проверка наличия следующего элемента
         * Средняя
         */
        /**
         * Сложность по времени: O(1) (проверка на пустоту)
         * Сложность по памяти: O(1)
         */
        @Override
        public boolean hasNext() {
            return !stack.empty();
        }

        /**
         * Поиск следующего элемента
         * Средняя
         */
        /**
         * Сложность по времени: O(n) (худший случай)
         * Сложность по памяти: O(1)
         */
        @Override
        public T next() {
            current = stack.pop();
            push(current.right);
            return current.value;
        }

        /**
         * Удаление следующего элемента
         * Сложная
         */
        @Override
        public void remove() {
            // TODO
            throw new NotImplementedError();
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Задания немного некорректны, что можно заметить при тестировании последних трех задач
     *
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * (Найти подмножество всех элементов меньше toElement и больших или равных fromElement, в созданное множество
     * в дальнейшем не могут быть добавлены элементы, не входящие в задвнный начальными усливиями диапазон)
     * Очень сложная
     */
    /**
     * Оценка для subSet, headSet и tailSet
     * Сложность по времени: O(n) (итерируемся по всему дереву)
     * Сложность по памяти: O(n) (в худшем случае)
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        BinaryTreeIterator iterator = new BinaryTreeIterator();
        BinaryTree<T> subSet = new BinaryTree<>(fromElement, toElement);
        while (iterator.hasNext()) {
            T value = iterator.next();
            if (value.compareTo(fromElement) >= 0 && value.compareTo(toElement) < 0)
                subSet.add(value);
        }
        return subSet;
    }

    /**
     * Найти множество всех элементов меньше заданного(, в созданное множество
     * в дальнейшем не могут быть добавлены элементы, большие или раные заданному)
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        BinaryTreeIterator iterator = new BinaryTreeIterator();
        BinaryTree<T> tailSet = new BinaryTree<>(null, toElement);
        while (iterator.hasNext()) {
            T value = iterator.next();
            if (value.compareTo(toElement) < 0)
                tailSet.add(value);
        }
        return tailSet;
    }

    /**
     * Найти множество всех элементов больше или равных заданного(, в созданное множество
     * в дальнейшем не могут быть добавлены элементы, меньшие заданного)
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        BinaryTreeIterator iterator = new BinaryTreeIterator();
        BinaryTree<T> tailSet = new BinaryTree<>(fromElement, null);
        while (iterator.hasNext()) {
            T value = iterator.next();
            if (value.compareTo(fromElement) >= 0)
                tailSet.add(value);
        }
        return tailSet;
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
