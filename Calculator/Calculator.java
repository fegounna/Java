
import java.util.Stack;
import java.util.LinkedList;
public class Calculator {
	public Stack<Double> numbers;
	public Stack<Operator> operators;
	LinkedList<Double> results;
	Calculator() {
		numbers = new Stack<Double>();
		operators = new Stack<Operator>();
		results = new LinkedList<Double>();
	}

	@Override
	public String toString() {
		return numbers.toString() + "\n" + operators.toString();
	}

	void pushDouble(double d) {
		numbers.push(d);
	}

	void pushOperator(Operator o) {
		operators.push(o);
	}

	double getResult() {
		if (numbers.empty())
			throw new RuntimeException();
		return numbers.peek();
	}

	void executeBinOperator(Operator op) {
		double n1 = numbers.pop();
		double n2 = numbers.pop();
		switch (op) {
		case PLUS:
			pushDouble(n1 + n2);
			break;
		case MINUS:
			pushDouble(n2 - n1);
			break;
		case MULT:
			pushDouble(n1 * n2);
			break;
		case DIV:
			pushDouble(n2 / n1);
			break;
		default:
			break;
		}
	}

	private static int precedence(Operator op) {
		switch (op) {
		case PLUS:
			return 0;
		case MINUS:
			return 0;
		case MULT:
			return 1;
		case DIV:
			return 1;
		default:
			return -1;
		}
	}

	void commandOperator(Operator op) {

		while (!operators.empty()) {
			if (precedence(operators.peek()) >= precedence(op)) {
				Operator o = operators.pop();
				executeBinOperator(o);
			} 
			else 
				break;
		}
		pushOperator(op);
	}

	void commandDouble(double d) {
		pushDouble(d);
	}

	void commandEqual() {
		while (!operators.empty())
			executeBinOperator(operators.pop());
		results.add(getResult());
	}
	void commandLPar() {
		pushOperator(Operator.OPEN);
	}
	void commandRPar() {
		Operator o=operators.pop();
		while(! o.equals(Operator.OPEN)) {
			executeBinOperator(o);
			o=operators.pop();
		}
	}
	void commandInit() {
		numbers = new Stack<Double>();
		operators = new Stack<Operator>();
	}
	void commandReadMemory(int i) {
		pushDouble(results.get(results.size()-i));
	}


}
