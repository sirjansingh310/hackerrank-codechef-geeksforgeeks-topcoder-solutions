//https://leetcode.com/explore/challenge/card/february-leetcoding-challenge-2021/586/week-3-february-15th-february-21st/3647/
class Solution {
    /*
    Clearly, without reframing the problem, we can see that based on the given operations, if X >= Y, then the problem is trivial. Since the only way we have to reduce the number is to decrement the number, it is guaranteed to perform the reduction in the fewest number of operations. Therefore, the answer is just X - Y.
Otherwise, we can proceed by starting at Y and working backwards to get to X. The reason why this approach is optimal is because if we were to go in the opposite direction, we don't know the order to best choose our decrement and multiply operations. However, if we work backwards, the path is clear. First of all, let us make clear that the minimum number of operations to get from X to Y is the minimum number of operations to get from Y to X. This is apparent by just reversing the order of the operations and applying their inverses, which will ensure minimality. Now, let's look at getting from Y to X. Clearly, to invert the operations, we must now either divide by two or increment by one, since we are moving in the opposite direction.
Based on the setup of this "working backwards" process described before, we are left with two cases:

If Y is even and more than X, we want to divide by 2. This gets us the most "bang for our buck" in terms of getting closest to X, since we are decreasing by the furthest amount. Furthermore, as an operation, we want to be dividing as much as possible, since there is
Otherwise, if we are an odd number, then we just increment. This puts us back into even territory so we can continue decrementing.
*/
    public int brokenCalc(int x, int y) {
        if (x >= y) {
            return x - y;
        }
        int count = 0;
        while (x != y) {
            if (y % 2 == 0) {
                y = y / 2;
            } else {
                y++;
            }
            count++;
            if (y < x) {
                count += (x - y);
                break;
            }
        }
        return count;
    }
}
