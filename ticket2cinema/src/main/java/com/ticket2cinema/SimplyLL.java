package com.ticket2cinema;

class SimplyLL {
    class Node {
        String data;
        Node next;

        Node(String d) {
            this.data = d;
            this.next = null;
        }
    }

    Node first = null;

    void insertLast(String y) {
        Node n = new Node(y);
        if (first == null) {
            first = n;
        } else {
            Node temp = first;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = n;
        }
    }

    void display() {
        System.out.println("============================");
        System.out.println();
        System.out.println("-----Theatre Nearest You-----");
        Node temp = first;
        if (first == null) {
            System.out.println("LinkedList is Empty");
        } else {
            while (temp != null) {
                System.out.println(temp.data);
                temp = temp.next;
            }
            System.out.println();
        }
        System.out.println("============================");
    }

    boolean search(String x) {
        Node temp = first;
        while (temp != null) {
            if (temp.data.equalsIgnoreCase(x)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

}
