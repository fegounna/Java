
public class Tokenizer {
	boolean isStart;
	boolean isIntNum;
	double num;
	Calculator calc;
	boolean isNonIntNum;
	int decimalDigits;
	boolean isMinUnary, isNeg;

	Tokenizer() {
		isStart = true;
		isIntNum = false;
		num = 0;
		calc = new Calculator();
		isNonIntNum = false;
		decimalDigits = 0;
		isMinUnary = true;
		isNeg = false;
	}

	void readChar(char c) {
		if (Character.isDigit(c)) {
			isStart = false;
			if (isNeg) {
				if (isNonIntNum) {
					num = num - Math.pow(10, -decimalDigits) * Character.getNumericValue(c);
					decimalDigits++;
				} else {
					if (isIntNum) {
						num = num * 10 - Character.getNumericValue(c);
					} else {
						num = -Character.getNumericValue(c);
						isIntNum = true;
						isMinUnary = false;
					}
				}

			} else {
				if (isNonIntNum) {
					num = num + Math.pow(10, -decimalDigits) * Character.getNumericValue(c);
					decimalDigits++;
				} else {
					if (isIntNum) {
						num = num * 10 + Character.getNumericValue(c);
					} else {
						num = Character.getNumericValue(c);
						isIntNum = true;
						isMinUnary = false;
					}
				}
			}
		} else if (c == '.') {
			isNonIntNum = true;
			isIntNum = true;
			decimalDigits = 1;
			isMinUnary = false;
		}

		else {
			if (isIntNum) {
				isIntNum = false;
				isNonIntNum = false;
				calc.pushDouble(num);
			}
			if (c == '=') {
				isStart = true;
				calc.commandEqual();
				isMinUnary = true;
			}
			if (c == '+') {
				calc.commandOperator(Operator.PLUS);
				isMinUnary = true;
			}
			if (c == '-') {
				
				if (isMinUnary)
					isNeg = true;
					
				else
					calc.commandOperator(Operator.MINUS);
					isMinUnary = true;
			}
			if (c == '*') {
				calc.commandOperator(Operator.MULT);
				isMinUnary = true;
			}
			if (c == '/') {
				calc.commandOperator(Operator.DIV);
				isMinUnary = true;
			}
			if (c == '(') {
				calc.commandLPar();
				isMinUnary = true;
			}
			if (c == ')') {
				calc.commandRPar();
				isMinUnary = false;
			}
			if(c=='C') {
				isStart = true;
				isIntNum = false;
				num = 0;
				calc = new Calculator();
				isNonIntNum = false;
				decimalDigits = 0;
				isMinUnary = true;
			}
		}

	}

	void readString(String s) {
		for (int i = 0; i < s.length(); i++) {
			readChar(s.charAt(i));
		}
	}

	public static void main(String[] args) {
		Tokenizer t = new Tokenizer();
		t.readString("8+-2=");
		System.out.println(t.calc.toString());

	}
}
