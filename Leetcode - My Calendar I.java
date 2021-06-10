// https://leetcode.com/explore/challenge/card/june-leetcoding-challenge-2021/604/week-2-june-8th-june-14th/3774/

// Brute force: Checking with every event in list of already added events.
class Event implements Comparable<Event>{
    int start;
    int end;
    
    public Event(int start, int end) {
        if (start > end) {
            throw new RuntimeException("invalid start and end values");
        }
        this.start = start;
        this.end = end;
    }
    
    @Override
    public String toString() {
        return "Event start = " + start + " event end = " + end;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Event)) {
            return false;
        }
        return this.compareTo((Event)o) == 0;
    }
    
    @Override
    public int compareTo(Event event) {
        if (event.start >= this.end) {
            return 1;
        } else if (event.end <= this.start) {
            return -1;
        } else { // conflict
            return 0;
        }
    }
    
    
}
class MyCalendar {
    private List<Event> events;
    public MyCalendar() {
        this.events = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        Event newEvent = new Event(start, end);
        for (Event event : events) {
            if (newEvent.equals(event)) {
                return false;
            }
        }
        events.add(newEvent);
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */



// Optimized: Create a BST with Event as Node(brilliant idea!). 
// All events before current node will go on left, and bigger than current will go on right.
// If current and new event is having a conflict, we break recursion and say it's not possible to add.

class Event implements Comparable<Event>{
    int start;
    int end;
    
    Event left; // left subtree
    Event right; // right subtree
    
    public Event(int start, int end) {
        if (start > end) {
            throw new RuntimeException("invalid start and end values");
        }
        this.start = start;
        this.end = end;
    }
    
    @Override
    public String toString() {
        return "Event start = " + start + " event end = " + end;
    }
    
    @Override
    public int compareTo(Event event) {
        if (event.start >= this.end) {
            return 1;
        } else if (event.end <= this.start) {
            return -1;
        } else { // conflict
            return 0;
        }
    }
    
    
}
class MyCalendar {
    private Event root;
    
    public boolean book(int start, int end) {
        Event newEvent = new Event(start, end);
        if (root == null) {
            root = newEvent;
            return true;
        }
        
        return insertEvent(root, newEvent);
    }
    
    private boolean insertEvent(Event root, Event newEvent) {
        // conflict 
        if (root.compareTo(newEvent) == 0) {
            return false;
        }
        
        if (root.compareTo(newEvent) == 1) {
            if (root.right == null) {
                root.right = newEvent;
                return true;
            } else {
                return insertEvent(root.right, newEvent);
            }
        } else {
            if (root.left == null) {
                root.left = newEvent;
                return true;
            } else {
                return insertEvent(root.left, newEvent);
            }
        }
        
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
