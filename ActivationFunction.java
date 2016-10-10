import java.io.Serializable;

public abstract class ActivationFunction implements Serializable{

	abstract public double getOut(double net);
	abstract public double getDerivative(double out);
	
}
