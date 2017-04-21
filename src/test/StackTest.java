package test;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

import org.junit.Before;
import org.junit.Test;

import ku.util.Stack;
import ku.util.StackFactory;

public class StackTest {

    private Stack<String> stack;

    @Before
    public void setUp() {
	StackFactory.setStackType(0);
	stack = makeStack(2);
    }

    @Test
    public void test() {

    }

    @Test(expected = IllegalArgumentException.class)
    public void testPushNull() {
	stack.push(null);
    }

    @Test
    public void testIsFull() {
	assertFalse(stack.isFull());
	stack.push("A");
	assertFalse(stack.isFull());
	stack.push("B");
	assertTrue(stack.isFull());
	stack.pop();
	assertFalse(stack.isFull());
	stack.peek();
	assertFalse(stack.isFull());
    }

    @Test
    public void testIsEmpty() {
	assertTrue(stack.isEmpty());
	stack.push("A");
	assertFalse(stack.isEmpty());
	stack.push("B");
	assertFalse(stack.isEmpty());
	stack.pop();
	assertFalse(stack.isEmpty());
	stack.pop();
	assertTrue(stack.isEmpty());
    }

    @Test
    public void testCapacity() {
	assertEquals(2, stack.capacity());
	stack.push("A");
	assertEquals(2, stack.capacity());
	stack.peek();
	assertEquals(2, stack.capacity());
	stack.pop();
	assertEquals(2, stack.capacity());
    }

    @Test
    public void testPop() {
	stack.push("A");
	stack.push("B");
	System.out.println(stack.size());
	stack.pop();
	assertEquals(1, stack.size());

	stack.pop();
	assertEquals(0, stack.size());
    }

    @Test
    public void testPeek() {
	assertNull(stack.peek());
	stack.push("A");
	stack.push("B");
	assertEquals("B", stack.peek());
	assertEquals("B", stack.peek());
	stack.pop();
	assertEquals("A", stack.peek());
	stack.pop();
	assertNull(stack.peek());
    }

    @Test(expected = IllegalStateException.class)
    public void testOverFlow() {
	stack.push("A");
	stack.push("B");
	stack.push("C");
    }

    @Test
    public void testSize() {
	assertEquals(0, stack.size());

	stack.push("A");
	assertEquals(1, stack.size());

	stack.peek();
	assertEquals(1, stack.size());

	stack.push("B");
	assertEquals(2, stack.size());

	stack.pop();
	assertEquals(1, stack.size());

	stack.push("B");
	assertEquals(2, stack.size());
    }

    @Test(expected = java.util.EmptyStackException.class)
    public void testPopEmptyStackThrowsException() {
	assumeTrue(stack.isEmpty());
	stack.pop();
    }

    @Test
    public void newStackIsEmpty() {
	assertTrue(stack.isEmpty());
	assertFalse(stack.isFull());
	assertEquals(0, stack.size());
    }

    private Stack<String> makeStack(int capacity) {
	return StackFactory.makeStack(capacity);
    }

}
