// https://leetcode.com/problems/design-twitter/
// Solution 1) Use max heap and poll first 10
// Solution 2) Use min heap and restrict size to 10, making heap more faster, and then reverse the list when polled. This min heap will contain 10 most recent tweets.

// solution 1 is more like sorting with n elements, solution 2 is pure advantage of heap data structure.
class Tweet implements Comparable<Tweet> {
    int userId;
    int tweetId;
    int tweetTime;
    
    public Tweet(int userId, int tweetId, int tweetTime) {
        this.userId = userId;
        this.tweetId = tweetId;
        this.tweetTime = tweetTime;
    }
    
    @Override
    public int compareTo(Tweet other) {
        return other.tweetTime - this.tweetTime;
    }
    
    @Override
    public String toString() {
        return "[" + this.tweetId + " " + this.tweetTime + "]" ;
    }
}
class Twitter {
    private static final int FEED_LIMIT = 10;
    private int tweetTime;
    
    private Map<Integer, Set<Integer>> userFollowersMap;
    private Map<Integer, Queue<Tweet>> userTweetsMap;
    
    public Twitter() {
        this.userFollowersMap = new HashMap<>();
        this.userTweetsMap = new HashMap<>();
    }
    
    public void postTweet(int userId, int tweetId) {
        Queue<Tweet> selfTweets = userTweetsMap.computeIfAbsent(userId, x -> new LinkedList<>());
        if (selfTweets.size() == FEED_LIMIT) {
            selfTweets.poll();
        }
        selfTweets.add(new Tweet(userId, tweetId, ++tweetTime));
        follow(userId, userId); // follow yourself for simplicity
    }
    
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> pq = new PriorityQueue<>(); // max-heap
    
        if (!userFollowersMap.containsKey(userId)) {
            return Collections.emptyList();
        }
        
        for (int follower : userFollowersMap.get(userId)) {
            Queue<Tweet> userTweets = userTweetsMap.get(follower);
            if (userTweets != null) {
                // cheap operations, size is 10 at max
                List<Tweet> readOnlyTweets = new ArrayList<>(userTweets);
                for (Tweet t : readOnlyTweets) {
                    pq.add(t);
                }
            }
        }
        
        List<Integer> userBestFeed = new ArrayList<>();
        while (!pq.isEmpty() && userBestFeed.size() < FEED_LIMIT) {
            userBestFeed.add(pq.poll().tweetId);
        }
        return userBestFeed;
    }
    
    public void follow(int followerId, int followeeId) {
        Set<Integer> userFollowers = userFollowersMap.computeIfAbsent(followerId, x -> new HashSet<>());
        userFollowers.add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> userFollowers = userFollowersMap.get(followerId);
        if (userFollowers != null) {
            userFollowers.remove(followeeId);
        }
    }
}


// https://leetcode.com/problems/design-twitter/
class Tweet implements Comparable<Tweet> {
    int userId;
    int tweetId;
    int tweetTime;

    public Tweet(int userId, int tweetId, int tweetTime) {
        this.userId = userId;
        this.tweetId = tweetId;
        this.tweetTime = tweetTime;
    }

    @Override
    public int compareTo(Tweet other) {
        return this.tweetTime - other.tweetTime;
    }

    @Override
    public String toString() {
        return "[" + this.tweetId + " " + this.tweetTime + "]" ;
    }
}
class Twitter {
    private static final int FEED_LIMIT = 10;
    private int tweetTime;

    private Map<Integer, Set<Integer>> userFollowersMap;
    private Map<Integer, Queue<Tweet>> userTweetsMap;

    public Twitter() {
        this.userFollowersMap = new HashMap<>();
        this.userTweetsMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        Queue<Tweet> selfTweets = userTweetsMap.computeIfAbsent(userId, x -> new LinkedList<>());
        if (selfTweets.size() == FEED_LIMIT) {
            selfTweets.poll();
        }
        selfTweets.add(new Tweet(userId, tweetId, ++tweetTime));
        follow(userId, userId); // follow yourself for simplicity
    }

    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> pq = new PriorityQueue<>(); // min-heap
        if (!userFollowersMap.containsKey(userId)) {
            return Collections.emptyList();
        }

        for (int follower : userFollowersMap.get(userId)) {
            Queue<Tweet> userTweets = userTweetsMap.get(follower);
            if (userTweets != null) {
                // cheap operations, size is 10 at max
                List<Tweet> readOnlyTweets = new ArrayList<>(userTweets);
                for (Tweet t : readOnlyTweets) {
                    pq.add(t);
                    if (pq.size() > FEED_LIMIT) {
                        pq.poll(); // remove the 11th tweet which was oldest by time. it will be in the root since we are using min-heap. 
                    }
                }
            }
        }

        List<Integer> userBestFeed = new ArrayList<>();
        
        while (!pq.isEmpty()) {
            userBestFeed.add(pq.poll().tweetId);
        }
        Collections.reverse(userBestFeed);
        return userBestFeed;
    }

    public void follow(int followerId, int followeeId) {
        Set<Integer> userFollowers = userFollowersMap.computeIfAbsent(followerId, x -> new HashSet<>());
        userFollowers.add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        Set<Integer> userFollowers = userFollowersMap.get(followerId);
        if (userFollowers != null) {
            userFollowers.remove(followeeId);
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
