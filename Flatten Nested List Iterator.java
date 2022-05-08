// https://leetcode.com/problems/flatten-nested-list-iterator/

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    private final List<Integer> flatList;
    private int cursor;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        if (nestedList == null) {
            throw new IllegalArgumentException("Iterator list should not be null");
        }
        flatList = new ArrayList<>();
        createList(nestedList, 0);
    }
    
    
    private void createList(List<NestedInteger> nestedList, int index) {
        if (index >= nestedList.size()) {
            return;
        }
        
        if (nestedList.get(index) == null) {
            flatList.add(null);
            createList(nestedList, index + 1);
            return;
        }
        
        if (nestedList.get(index).isInteger()) {
            flatList.add(nestedList.get(index).getInteger());
            createList(nestedList, index + 1);
        } else {
            createList(nestedList.get(index).getList(), 0);
            createList(nestedList, index + 1);
        }
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            return flatList.get(cursor++);
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return cursor < flatList.size();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
