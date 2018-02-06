import java.util.ArrayList;
import java.util.LinkedList;

public class RandomQueue<E>
{
    private LinkedList<E> queue;

    public RandomQueue() { queue = new LinkedList<E>(); }

    public void add(E e)
    {
        if (Math.random() < 0.5)
            queue.addFirst(e);
        else
            queue.addLast(e);
    }

    public E remove()
    {
        if (queue.size() == 0)
            throw new IllegalArgumentException("There's no element to remove in Random Queue");
        if (Math.random() < 0.5)
            return queue.removeFirst();
        else
            return queue.removeLast();
    }

    public int size() { return queue.size(); }
    public boolean empty() { return size() == 0; }

}
//public class RandomQueue<E>
//{
//    private ArrayList<E> queue;
//
//    public RandomQueue() { queue = new ArrayList<E>(); }
//
//    public void add(E e) { queue.add(e); }
//
//    public E remove()
//    {
//        if (queue.size() == 0)
//            throw new IllegalArgumentException("There's no element to remove in Random Queue");
//        int randIndex = (int)(Math.random() * queue.size());
//        E randElement = queue.get(randIndex);
//        queue.set(randIndex, queue.get(queue.size() - 1));
//        queue.remove(queue.size() - 1);
//        return randElement;
//    }
//
//    public int size() { return queue.size(); }
//    public boolean empty() { return size() == 0; }
//
//}
