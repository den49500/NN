
public class FMLayer extends Layer{
	
	//Имя слоя
	//кол-во нейронов
	//кол-во нейронов на предыдущем слое
	//тип активационной функции
	public FMLayer(String name, int numNeuron, int prevNumNeuron, ActivationFunction func){
		this.name=name;
		net=new double[numNeuron];
		out=new double[net.length];
		err=new double[net.length];
		input=new double[prevNumNeuron];
		w=new double[prevNumNeuron][net.length];
		this.func=func;
		speed=0.01;
		
		for(int i=0;i<w.length;i++){
			for(int j=0;j<w[0].length;j++){
				w[i][j]=Math.random()*0.3+0.1;	
			}
		}
			
	}
	
	public void countOut(){
		//System.out.println("слой "+getName());
		for(int i=0;i<net.length;i++){
			net[i]=0;
			for(int j=0;j<input.length;j++){
				net[i]+=input[j]*w[j][i];
			}
			out[i]=performActivation(net[i]);
			//System.out.println(out[i]);
		}
		
		//System.out.println(nextLayer);
		
		nextLayer.setInput(out);
		nextLayer.countOut();
	}
	
	public void countW(){
		//System.out.println("слой "+getName());
		for(int i=0; i<input.length; i++){
			for(int j=0; j<out.length; j++){
				w[i][j]+=speed*err[j]*input[i]*getDerivative(out[j]);
			}
		}
		
	}

	public void countGlobalErr(double[] answer) {
		
		//System.out.println("слой "+getName());
		
		for(int i=0;i<out.length;i++){
			err[i]=answer[i]-out[i];
			//System.out.println("Ошибка "+err[i]);
		}
		
		prevLayer.setNextErr(err);
		prevLayer.setNextW(w);
		prevLayer.countErr();
		countW();
	}

	@Override
	public void countErr() {
		// TODO Auto-generated method stub
		//System.out.println("слой "+getName());
		for(int i=0;i<err.length;i++){
			err[i]=0;
			for(int j=0;j<nextErr.length;j++){
				err[i]+=nextW[i][j]*nextErr[j];
			}
		}
		
		prevLayer.setNextErr(err);
		prevLayer.setNextW(w);
		prevLayer.countErr();
		countW();
		
	}
}
