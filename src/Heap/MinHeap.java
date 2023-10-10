/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MinHeap<T> implements IHeap<T> {
    private ArrayList<T> heap;
    private Comparator<T> comparator;

    public MinHeap(Comparator<T> customComparator) {
        heap = new ArrayList<>();
        comparator = customComparator;
    }

    public void add(T elemento) {
        heap.add(elemento);
        siftUp();
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }
        T min = heap.get(0);
        swap(0, size() - 1);
        heap.remove(size() - 1);
        siftDown();
        return min;
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void siftUp() {
        int index = size() - 1;
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            T current = heap.get(index);
            T parent = heap.get(parentIndex);
            if (comparator.compare(current, parent) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    public void siftDown() {
        int index = 0;
        while (true) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int smallestChildIndex = leftChildIndex;

            if (leftChildIndex >= size()) {
                break;
            }

            if (rightChildIndex < size() && comparator.compare(heap.get(rightChildIndex), heap.get(leftChildIndex)) < 0) {
                smallestChildIndex = rightChildIndex;
            }

            if (comparator.compare(heap.get(smallestChildIndex), heap.get(index)) < 0) {
                swap(index, smallestChildIndex);
                index = smallestChildIndex;
            } else {
                break;
            }
        }
    }

    public List<T> toList() {
        return new ArrayList<>(heap);
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}