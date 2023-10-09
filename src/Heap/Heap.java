/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Heap;

import java.util.ArrayList;
import java.util.Collections;

public class Heap<T extends Comparable<T>> implements IHeap<T> {
    private ArrayList<T> heap;

    public Heap() {
        heap = new ArrayList<>();
    }

    @Override
    public void add(T elemento) {
        heap.add(elemento);
        siftUp();
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        T max = heap.get(0);
        Collections.swap(heap, 0, size() - 1);
        heap.remove(size() - 1);
        siftDown();
        return max;
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public void siftUp() {
    int index = size() - 1;
    while (index > 0) {
        int parentIndex = (index - 1) / 2;
        T current = heap.get(index);
        T parent = heap.get(parentIndex);
        if (current.compareTo(parent) < 0) { // Cambio el signo de comparación aquí
            Collections.swap(heap, index, parentIndex);
            index = parentIndex;
        } else {
            break;
        }
    }
}

    @Override
    public void siftDown() {
    int index = 0;
    int minChild;
    while (true) {
        int leftChildIdx = 2 * index + 1;
        int rightChildIdx = 2 * index + 2;

        if (leftChildIdx < size()) {
            minChild = leftChildIdx;

            if (rightChildIdx < size() && heap.get(rightChildIdx).compareTo(heap.get(leftChildIdx)) < 0) {
                minChild = rightChildIdx;
            }

            if (heap.get(index).compareTo(heap.get(minChild)) > 0) {
                Collections.swap(heap, index, minChild);
                index = minChild;
            } else {
                break;
            }
        } else {
            break;
        }
    }
    }
}