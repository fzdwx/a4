package org.atomic.infrastructure.collection;

import java.util.Iterator;

/**
 * Description: 背包  <br>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-07-31 20:12:07
 * @see Iterable
 */
public class Bag<Item> implements Iterable<Item>, Collection<Item> {

    private final Stack<Item> stack = new LinkedStack<>();

    public void add(Item item) {
        stack.push(item);
    }

    @Override
    public Iterator<Item> iterator() {
        return stack.iterator();
    }

    @Override
    public int size() {
        return stack.size();
    }
}
