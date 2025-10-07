/**
 * This program creates a doubly linked list data structure
 * <p>
 * Date Last Modified: 3-29-24
 *
 * @author Alec Hungerford and Constantine Zafiris
 * <p>
 * CS1122, Spring 2024
 * Lab Section 5
 */

import java.util.*;


public class DoublyLinkedList implements SimpleList {
    public class Node {
        Integer value = null;
        Node next = null;
        Node previous = null;

        public Node(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrevious() {

            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }
    }


    // The beginning of the list
    private Node head = null;
    // The number of elements in the list
    private int size = 0;

    /**
     * 37 * Removes the element at the specified *position in this list.
     * 38 *Shifts any subsequent elements to the left ( * subtracts one from
     * 39 * their indices). Returns the *value that was removed from the list.
     * 40 *@param index - the index of the element to *be removed @return
     * 41 * the value previously at the
     * 42 * specified position
     * 43 * @throwsIndexOutOfBoundsException
     * 44 *- if the index is out of range
     * 45 * (index < 0 || index >= size())
     * 46
     */

    @Override
    public Integer remove(int index) throws
            IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } // checks to see if the index given is greater than zero and exists within the size of the list
        if (index == 0) {
            // remove from front
            int temp = head.getValue();
            if (head.getNext() == null){
                head = null;
                size--;
                return temp;
            }
            head = head.getNext();
            head.setPrevious(null);
            size--;
            return temp;
        } else {
            // remove after node at index - 1
            Node currentNode = head;
            for (int i = 1; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            int temp = currentNode.getNext().getValue();
            currentNode.setNext(currentNode.getNext().getNext());
            if (currentNode.getNext() != null) {
                currentNode.getNext().setPrevious(currentNode);
            }
                size--;
            return temp;

        }
    }

    /**
     * 54 * Print the values in the list in order from the front.
     * 55 * For example: [ 1, 2, 4, 8, 16 ]
     * 56
     */


    public void printList() {
        Node currentNode = head;
        String str = "";
        for (int i = 0; i < size; i++) {
            str += currentNode.value;
            currentNode = currentNode.getNext();
        }
        System.out.println(str);
    }

    /**
     * 64 * prints the entire list starting from the * tail of the list
     * 65 * and working
     * 66 *backwards through the list by following the previous
     * 67 * node references.
     * 68
     */
    public void printReverse() {
        Node currentNode = head;
        while (currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
        }
        String str = "";
        while (currentNode != null) {
            str += currentNode.value;
            currentNode = currentNode.getPrevious();
        }
        System.out.println(str);
    }

    /**
     * 76 * Inserts the specified element at the specified position in this list.
     * 77 * Shifts the element currently at that position * (if any) and any
     * 78 *subsequent
     * 79 * elements to the right (adds one to their * indices).
     * 80 * Prohibits the insertion of a null value into * the list.
     * 81 *
     * 82 * @param index - index at which the specified element is to be
     * 83 * inserted
     * 84 * @param value - value to be inserted
     * 85 * @throws IndexOutOfBoundsException - if the index * is out of
     * 86 * range (index < 0 || index >
     * 87 * size())
     * 88 * @throws IllegalArgumentException - if the value specified is null
     * 89
     */

    @Override
    public void add(int index, Integer value)
            throws IndexOutOfBoundsException, IllegalArgumentException {
        if (value == null) {
            throw new IllegalArgumentException();
        } // checks to see if legal value is given
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } // checks to see if the index given is greater than zero and exists within the size of the list
        Node newNode = new Node(value);
        if (index == 0) {
            // add to front
            if (head == null) {
                newNode.setNext(head);
                head = newNode;
                head.setPrevious(null);
            } else { // checking whether or not we are adding to a null list
                newNode.setNext(head);
                head = newNode;
                newNode.getNext().setPrevious(head);
            }
        } else {
            // add after node at index - 1
            Node currentNode = head;
            for (int i = 1; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            newNode.setNext(currentNode.getNext());
            currentNode.setNext(newNode); // changed currentNode to newNode
            if (newNode.getNext() != null) {
                newNode.getNext().setPrevious(newNode);
            }
            newNode.setPrevious(currentNode); //needed so that newNode points to the one before it (currentNode)
        }
        size++;
    }

    /**
     * 116 * Returns the value at the specified position in this list.
     * 117 *
     * 118 * @param index - index of the element to return
     * 119 * @return the value at the specified position in this list or null if
     * 120 * empty
     * 121 * @throws IndexOutOfBoundsException - if the index is out of
     * 122 * range (index < 0 || index >= size())
     * 123
     */
    public Integer get(int index) throws
            IndexOutOfBoundsException {
        // validate index argument
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index Out Of Bounds: " + index);
        }
        // Check if list is empty
        if (isEmpty()) {
            return null;
        }
        Node currentNode = find(index);
        return currentNode.getValue();
    }

    private Node find(int index) throws IndexOutOfBoundsException {
        // validate index argument
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index Out Of Bounds: " + index);
        }
        Node currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }

    /**
     * 153 * Returns true if this collection contains no elements.
     * 154 *
     * 155 *@return true if this collection contains no elements
     * 156
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * 164 * Returns the number of elements in this collection.
     * 165 * If this collection
     * 166 * contains more than Integer. MAX_VALUE *elements , returns Integer.
     * 167 * MAX_VALUE.
     * 168 *
     * 169 * @return the number of elements in this collection
     * 170
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 176 * Retrieves the node at index
     * 177 * @param index
     * 178 * @return the node at index
     * 179
     */
    public Node getNode(int index) {
        Node currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }

    public static void main(String[] args) {
        DoublyLinkedList smth = new DoublyLinkedList();
        smth.add(0, 1);
        smth.add(1, 2);
        smth.add(2, 3);
        smth.add(3, 4);
        smth.add(4, 5);
        smth.add(5, 6);
        smth.printList();
        smth.printReverse();
    }

}
