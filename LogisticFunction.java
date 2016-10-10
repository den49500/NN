import java.io.Serializable;

public class LogisticFunction extends ActivationFunction{

	@Override
	public double getOut(double net) {
		// TODO Auto-generated method stub
		return 1/(1+Math.pow(Math.E, -(0.5*net)));
	}

	@Override
	public double getDerivative(double out) {
		// TODO Auto-generated method stub
		return out*(1-out);
	}

}
