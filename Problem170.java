//TC: O(n)
//SC: O(1)
class Solution {
    public boolean isRobotBounded(String instructions) {
        // Directions: North, East, South, West
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        // Initial position and direction
        int x = 0, y = 0; // Initial coordinates (0, 0)
        int dir = 0; // Facing north (index 0 in directions array)
        
        for (char instruction : instructions.toCharArray()) {
            if (instruction == 'G') {
                // Move forward in the current direction
                x += directions[dir][0];
                y += directions[dir][1];
            } else if (instruction == 'L') {
                // Turn left (anticlockwise), decrease direction
                dir = (dir + 3) % 4;
            } else if (instruction == 'R') {
                // Turn right (clockwise), increase direction
                dir = (dir + 1) % 4;
            }
        }
        
        // Robot is bounded if it's back at the origin or not facing north
        return (x == 0 && y == 0) || (dir != 0);
    }
}
