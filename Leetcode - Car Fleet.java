// https://leetcode.com/problems/car-fleet/
class Solution {
    private class Car implements Comparable<Car> {
        int position;
        int speed;
        
        public Car(int position, int speed) {
            this.position = position;
            this.speed = speed;
        }
        
        @Override
        public int compareTo(Car other) {
            return this.position - other.position;
        }
    }
    
    public int carFleet(int target, int[] position, int[] speed) {
        List<Car> cars = new ArrayList<>();
        
        for (int i = 0; i < position.length; i++) {
            cars.add(new Car(position[i], speed[i]));
        }
        
        Collections.sort(cars);
        Stack<Car> stack = new Stack<>();
        
        for (int i = 0; i < position.length; i++) {
            while (!stack.isEmpty() && willAttachToFleet(stack.peek(), cars.get(i), target)) {
                stack.pop();// cars which will join the fleet to be removed and only current car to be pushed to stack to represent this fleet. Final stack will contain fleet leaders, and its size is our ans
            }
            stack.push(cars.get(i));
        }
        
        return stack.size();
    }
    
    private boolean willAttachToFleet(Car behindCar, Car frontCar, int target) {
        double timeTillTargetBehindCar = (double)(target - behindCar.position) / behindCar.speed;
        double timeTillTargetFrontCar = (double)(target - frontCar.position) / frontCar.speed;
        return timeTillTargetBehindCar <= timeTillTargetFrontCar;
    }
}
