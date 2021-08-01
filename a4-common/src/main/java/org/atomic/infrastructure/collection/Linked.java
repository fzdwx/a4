package org.atomic.infrastructure.collection;

import java.util.Iterator;
import java.util.function.Function;

/**
 * Description: 链 <br>
 *
 * @author <a href="mailto:likelovec@gmail.com">like</a>
 * @date 2021-07-31 19:55:55
 * @see Iterable
 */
public abstract class Linked<Item> implements Iterable<Item> {

    protected Function<Iterator<Item>, String> print = (it) -> {
        if (!it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (; ; ) {
            Item e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (!it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    };

    @Override
    public String toString() {
        return print.apply(iterator());
    }

}
