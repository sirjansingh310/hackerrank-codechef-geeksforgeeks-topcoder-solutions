// https://leetcode.com/problems/shortest-path-to-get-all-keys/description/
// T.C O(N*M*2^K), since our k number of bits number can have 2^k possible states.
// S.C O(N*M*2^K)

class State {
    int keys;
    int x;
    int y;

    public State(int keys, int x, int y) {
        this.keys = keys;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof State)) {
            return false;
        }
        State otherState = (State) other;
        return this.keys == otherState.keys && this.x == otherState.x && this.y == otherState.y;
    }

    @Override
    public int hashCode() {
        return (this.keys * 31 + this.x) * 31 + this.y;
    }
}

class Solution {
    private static final int[] xDir = {1, -1, 0, 0};
    private static final int[] yDir = {0, 0, 1, -1};

    public int shortestPathAllKeys(String[] grid) {
        int totalKeys = 0;
        int startX = 0, startY = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                if (Character.isLowerCase(grid[i].charAt(j))) {
                    totalKeys++;
                } else if (grid[i].charAt(j) == '@') {
                    startX = i;
                    startY = j;
                }
            }
        }

        int finalState = (int) Math.pow(2, totalKeys) - 1;// all 1's

        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>(); // instead of x,y in visited,
        // we have x,y,bitmask in visited. i.e if we don't have a key yet, we can revisit a cell to collect that key. 

        State initState = new State(0, startX, startY);
        queue.add(initState);
        visited.add(initState);
        int dist = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                State polledState = queue.poll();
                if (polledState.keys == finalState) {
                    return dist;
                }
                int x = polledState.x, y = polledState.y, parentKeys = polledState.keys;

                for (int i = 0; i < 4; i++) {
                    int nextX = xDir[i] + x;
                    int nextY = yDir[i] + y;
                    if (nextX >= 0 && nextX < grid.length && nextY >= 0 && nextY < grid[0].length()) {
                        char child = grid[nextX].charAt(nextY);
                        int currentKeys = parentKeys;
                        if (child == '#') {
                            continue;
                        }

                        if (Character.isLowerCase(child)) {
                            int keyNumber = child - 'a';
                            currentKeys = setBit(currentKeys, keyNumber);
                        } else if (Character.isUpperCase(child)) {
                            int keyNumber = Character.toLowerCase(child) - 'a';
                            if (!isBitSet(currentKeys, keyNumber)) {
                                continue;
                            }
                        }
                        State newState = new State(currentKeys, nextX, nextY);
                        
                        if (!visited.contains(newState)) {
                            queue.add(newState);
                            visited.add(newState);
                        }
                    }
                }
            }
            dist++;
        }

        return -1;
    }

    private int setBit(int n, int k) {
        return (n | (1 << k));
    }

    private boolean isBitSet(int n, int k) {
        return (n & (1 << k)) != 0;
    }
}
