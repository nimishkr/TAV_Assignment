package Assignment_1;

public class ParkCarSystem {
	
	public ParkCarSystem(){
		
		
	}

	public boolean isEmpty(int ultrasonic_1, int ultrasonic_2) {
		// TODO Auto-generated method stub
		boolean empty;	
		if((ultrasonic_1 + ultrasonic_2)/2 <= 5){
			
			empty = true;
		}
		else{
			
			empty = false;
		}		
		
		return empty;
	}

	public int moveForward(int i) {
		// TODO this is to check if the input is positive then it will
				// compel the car to move FORWARD
				int move = 0;
						if(i >0){
							
							move = i;
						}
				
				return move;
			}


	public int moveBackward(int i) {
		// TODO this is to check if the input is negative then it will
		// compel the car to move BACKWARD
		int move = 0;
				if(i <0){
					
					move = i;
				}
		
		return move;
	}
	
	
	public boolean park(int i, int x) {
		// TODO this is check whether the car is idle, if it is then it returns 
		// true 	
		return (moveForward(i)==0 && moveBackward(x) ==0);	
		}
	

	public boolean unPark(int i, int x){		
		
		return (moveForward(i) >= 1 && moveBackward(x) <= 1);	
	}
	
	
}