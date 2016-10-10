import java.io.Serializable;

public abstract class Layer implements Serializable{

	protected String name;
	protected double input[];
	protected double w[][];
	protected double net[];
	protected double out[];
	protected double err[];
	protected double nextErr[];
	protected double nextW[][];
	protected Layer prevLayer;
	protected Layer nextLayer;
	protected double speed;
	protected ActivationFunction func;
	
	protected double performActivation(double net){
		return func.getOut(net);
	}
	
	protected double getDerivative(double out){
		return func.getDerivative(out);
	}
	
	abstract public void countOut();
	
	abstract public void countErr();
	
	abstract public void countW();
	
	protected String getName(){
		return name;
	}
	
	public void setInput(double[] input){
		this.input=input;
	}
	
	public double[][] getW(){
		return w;
	}
	
	public double[] getErr(){
		return err;
	}
	
	public double[] getOut(){
		return out;
	}
	
	public void setNextErr(double[] err){
		this.nextErr=err;
	}
	
	public void setNextW(double[][] w){
		this.nextW=w;
	}
	
	public void setSpeed(double speed){
		this.speed=speed;
	}
	
	public void init(Layer prevLayer,Layer nextLayer){
		this.prevLayer=prevLayer;
		this.nextLayer=nextLayer;
	}

	public void countGlobalErr(double[] answer) {
		// TODO Auto-generated method stub
		
	}
}
