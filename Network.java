import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Network {
	
	private ArrayList<Layer> layers;
	private Layer inputLayer;
	private Layer outputLayer;

		//число входов
		//функция активации нейронов
		public Network(int num[], ActivationFunction func){
			layers=new ArrayList();
			
			layers.add(new BlankLayer());
			for(int i=0;i<num.length-1;i++){
				
				String name=Integer.toString(i+1);
				Layer l=new FMLayer(name,num[i+1],num[i],func);
				layers.add(l);
				
			}	
			layers.add(new BlankLayer());
			
			
			for(int i=1;i<layers.size()-1;i++){
				//System.out.println(layers.get(i-1)+"   "+layers.get(i+1)+"   "+layers.get(i));
				Layer l=(Layer) layers.get(i);
				l.init(layers.get(i-1), layers.get(i+1));
				
			}
			
			inputLayer=layers.get(1);
			outputLayer=layers.get(layers.size()-2);
		}
		
		
		private void countOutput(double[] enters){
			inputLayer.setInput(enters);
			inputLayer.countOut();		
		}


		public void training(double[][] patterns, double[][] answer,
				int numOfIteration){
			
			double gError;
			for(int i=0;i<numOfIteration;i++){
				gError=0;
				for(int j=0;j<patterns.length;j++){
					
					countOutput(patterns[j]);
					//System.out.println("Цифра "+(j+1));
					outputLayer.countGlobalErr(answer[j]);		
					
					double err[]=outputLayer.getErr();
					for(int i1=0;i1<err.length;i1++){
						gError+=Math.abs(err[i1]);
					}
					
				}
				System.out.println("Глобальнаая ошибка: "+gError);
			}
		}
		
		public void training(double[][] patterns, double[][] answer, 
				int numOfIteration, double speed){
			
			for(int i=1;i<layers.size()-1;i++){
				layers.get(i).setSpeed(speed);
			}
			
			double gError;
			for(int i=0;i<numOfIteration;i++){
				gError=0;
				for(int j=0;j<patterns.length;j++){
					
					countOutput(patterns[j]);
					//System.out.println("Цифра "+(j+1));
					outputLayer.countGlobalErr(answer[j]);		
					
					double err[]=outputLayer.getErr();
					for(int i1=0;i1<err.length;i1++){
						gError+=Math.abs(err[i1]);
					}
					
				}
				System.out.println("Глобальнаая ошибка: "+gError);
			}
		}
		
		public void training(double[][] patterns, double[][] answer){
			
			double gError;
			do{
				gError=0;
				for(int j=0;j<patterns.length;j++){
					
					countOutput(patterns[j]);
					//System.out.println("Цифра "+(j+1));
					outputLayer.countGlobalErr(answer[j]);		
					
					double err[]=outputLayer.getErr();
					for(int i1=0;i1<err.length;i1++){
						gError+=Math.abs(err[i1]);
					}
					
				}
				System.out.println("Глобальнаая ошибка: "+gError);
			}while(gError>0.5);
		}
		
		public void printOutput(double enters[]){
			
			countOutput(enters);
			double[] out=outputLayer.getOut();
				
			for(int j=0;j<out.length;j++){
				System.out.println("нейрон №"+(j+1)+": "+out[j]);
			}
			
		}
		
		public void save(String nameFile){
			try {
				FileOutputStream fos = new FileOutputStream(nameFile);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(layers);
				oos.flush();
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public Network(String nameFile) {
			
			try {
				FileInputStream fis= new FileInputStream(nameFile);
				ObjectInputStream oin = new ObjectInputStream(fis);
				layers=(ArrayList<Layer>) oin.readObject();
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			inputLayer=layers.get(1);
			outputLayer=layers.get(layers.size()-2);
		}
		
		
}
