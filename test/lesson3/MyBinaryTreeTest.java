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
        SortedSet<Integer> head = intTree.headSet(70);
        intTree.add(5);
        assertEquals(head, intTree.headSet(70));
        head.add(3);
        assertTrue(intTree.contains(3));
        intTree.add(120);
        assertTrue(intTree.contains(120));
        assertFalse(head.contains(120));
    }
}