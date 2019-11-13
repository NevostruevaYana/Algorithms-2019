package lesson3;

import org.junit.jupiter.api.Test;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class MyBinaryTreeTest {

    @Test
    void testRemoveAndSomeOtherMethods() {
        BinaryTree<Integer> intTree = new BinaryTree<>();
        intTree.add(50);
        intTree.add(17);
        intTree.add(70);
        intTree.add(19);
        intTree.add(18);
        intTree.add(8);
        intTree.add(10);
        intTree.add(1);
        intTree.add(68);
        intTree.add(63);
        intTree.add(100);
        assertTrue(intTree.contains(19));
        intTree.remove(19);
        assertEquals(4, intTree.height());
        assertFalse(intTree.contains(19));
        assertEquals(10, intTree.size());
        intTree.remove(1);
        intTree.remove(10);
        intTree.remove(63);
        assertEquals(3, intTree.height());
        assertEquals(7, intTree.size());
        System.out.println(intTree.checkInvariant());
    }

    @Test
    void testTailSetHeadSetSubSet() {
        BinaryTree<Integer> intTree = new BinaryTree<>();
        intTree.add(50);
        intTree.add(17);
        intTree.add(70);
        intTree.add(19);
        intTree.add(18);
        intTree.add(8);
        intTree.add(10);
        intTree.add(1);
        intTree.add(68);
        intTree.add(63);
        intTree.add(100);
        SortedSet<Integer> tail = new TreeSet<>();
        assertEquals(tail, intTree.tailSet(1));
        tail.add(1);
        assertEquals(tail, intTree.tailSet(8));
        tail.add(8);
        tail.add(10);
        assertEquals(tail, intTree.tailSet(17));
        tail.add(17);
        tail.add(18);
        tail.add(19);
        assertEquals(tail, intTree.tailSet(50));
        SortedSet<Integer> head = new TreeSet<>();
        head.add(70);
        head.add(100);
        assertEquals(head, intTree.headSet(70));
        head.add(50);
        head.add(68);
        head.add(63);
        assertEquals(head, intTree.headSet(50));
        head.add(17);
        head.add(18);
        head.add(19);
        assertEquals(head, intTree.headSet(17));
        head.remove(70);
        head.remove(100);
        assertEquals(head, intTree.subSet(17, 70));
        head.add(8);
        head.add(1);
        head.add(10);
        assertEquals(head, intTree.subSet(1, 70));
    }
}