package lesson3;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Iterator;
import java.util.SortedSet;

/**
 * @author Tatarin Esli Che
 */


public class BinaryTreeJavaTest {
    @Test
    public void testRemove() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        for (int i = 1; i <= 10; i++) {
            tree.add(i);
        }
        Assert.assertFalse(tree.remove(0));
        Assert.assertTrue(tree.remove(4));
        Assert.assertFalse(tree.contains(4));
        for (int i = 1; i <= 10; i++) {
            if (i != 4) Assert.assertTrue(tree.contains(i));
        }
        Assert.assertEquals(9, tree.size());

        Assertions.assertThrows(NullPointerException.class, () -> tree.remove(null));
        Assertions.assertThrows(ClassCastException.class, () -> tree.remove("000"));
    }

    @Test
    public void testIterator() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        Iterator iter = tree.iterator();
        //Проверяю итератор от пустого дерева
        Assert.assertFalse(iter.hasNext());
        for (int i = 1; i <= 10; i++) {
            tree.add(i);
        }
        iter = tree.iterator();
        int s = tree.size();
        //Проверяю, пройдет ли итератор по всем элементам
        while (iter.hasNext()) {
            iter.next();
            iter.remove();
            Assert.assertEquals(--s, tree.size());
        }
        Assert.assertEquals(0, s);
    }

    @Test
    public void testSubTailHeadTree() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        for (int i = 0; i <= 100; i+=2) {
            tree.add(i);
        }
        SortedSet<Integer> head = tree.headSet(41);
        SortedSet<Integer> tail = tree.tailSet(70);
        SortedSet<Integer> sub = tree.subSet(31, 80);

        //Проверяю размеры
        Assert.assertEquals(21, head.size());
        Assert.assertEquals(16, tail.size());
        Assert.assertEquals(24, sub.size());

        //В множестве должны быть только четные элементы из диапазона
        for (int i = 0; i <= 41; i+= 2) {
            Assert.assertTrue(head.contains(i));
        }
        for (int i = 1; i <= 41; i+= 2) {
            Assert.assertFalse(head.contains(i));
        }
        Assert.assertFalse(head.contains(42));

        for (int i = 70; i <= 100; i+= 2) {
            Assert.assertTrue(tail.contains(i));
        }
        for (int i = 71; i <= 100; i+= 2) {
            Assert.assertFalse(tail.contains(i));
        }
        Assert.assertFalse(tail.contains(68));

        //Показываю зависимость head/tail/sub от tree
        tree.add(35);
        Assert.assertTrue(head.contains(35));
        Assert.assertEquals(25, sub.size());

        tree.remove(72);
        Assert.assertFalse(tail.contains(72));
        Assert.assertEquals(24, sub.size());

        Assert.assertFalse(sub.remove(2));
        sub.remove(78);
        Assert.assertFalse(tree.contains(78));
    }
}
