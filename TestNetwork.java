

public class TestNetwork {
		
		public static void main(String[] args){
			
			double[][] patterns={
					{0,0,1,
					 0,0,1,
					 0,0,1,
					 0,0,1,
					 0,0,1
					},
					{	1,1,1,
						0,0,1,
						1,1,1,
						1,0,0,
						1,1,1
					},
					{	1,1,1,
						0,0,1,
						1,1,1,
						0,0,1,
						1,1,1
					},
					{
						1,0,1,
						1,0,1,
						1,1,1,
						0,0,1,
						0,0,1
					},{
						1,1,1,
						1,0,0,
						1,1,1,
						0,0,1,
						1,1,1
					},{
						1,1,1,
						1,0,0,
						1,1,1,
						1,0,1,
						1,1,1,
					},{
						1,1,1,
						0,0,1,
						0,1,0,
						0,1,0,
						1,0,0
					},{
						1,1,1,
						1,0,1,
						1,1,1,
						1,0,1,
						1,1,1
					},{
						1,1,1,
						1,0,1,
						1,1,1,
						0,0,1,
						1,1,1
					}
					
			};
			
			double[][] answer={
					{1,0,0,0,0,0,0,0,0},
					{0,1,0,0,0,0,0,0,0},
					{0,0,1,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0},
					{0,0,0,0,1,0,0,0,0},
					{0,0,0,0,0,1,0,0,0},
					{0,0,0,0,0,0,1,0,0},
					{0,0,0,0,0,0,0,1,0},
					{0,0,0,0,0,0,0,0,1}
			};
			
			int[] n={15,60,9};
			
			Network network=new Network(n, new ThresholdFunction());
			
			long time=System.currentTimeMillis();
			network.training(patterns,answer);
			System.out.println("Время обучения сети: "+(System.currentTimeMillis()-time));
			
			for(int i=0;i<patterns.length;i++){
				System.out.println("Цифра "+(i+1));
				network.printOutput(patterns[i]);
			}
			
	}
	
}
