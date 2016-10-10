
public class ThresholdFunction extends ActivationFunction {

	public ThresholdFunction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getOut(double net) {
		// TODO Auto-generated method stub
		if(net>0.5){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public double getDerivative(double out) {
		// TODO Auto-generated method stub
		return 1;
	}

	

}
