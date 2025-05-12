//
//
//public class Stack {
//    node top;
//
//    class node {
//        String data;
//        node next;
//        public node(String data) {
//            this.data = data;
//        }
//    }
//
//    public void push(String data) {
//        node newnode = new node(data);
//        if (top == null) {
//            top = newnode;
//        } else {
//            newnode.next = top;
//            top = newnode;
//        }
//    }
//
//    public String pop() {
//        if (top == null) {
//            return null;
//        } else {
//            System.out.println("popped: " + top.data);
//            String data = top.data;
//            top = top.next;
//            return data;
//        }
//    }
//}