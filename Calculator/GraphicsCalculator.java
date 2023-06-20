import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.KeyEvent;

public class GraphicsCalculator extends Application {
    Tokenizer tok;
    Label l=new Label();
    @Override
    public void start(Stage stage) {
        stage.show();
        stage.setTitle("Calculator");
        stage.setWidth(200);
        stage.setHeight(200);
       	HBox h1=new HBox(b('7'),b('8'),b('9'),b('+'));
    	HBox h2=new HBox(b('4'),b('5'),b('6'),b('-'));
    	HBox h3=new HBox(b('1'),b('2'),b('3'),b('*'));
    	HBox h4=new HBox(b('0'),b('.'),b('C'),b('/'));
    	HBox h5=new HBox(b('('),b(')'),b('='));
        Scene scene = new Scene(new VBox(h0,h1,h2,h3,h4,h5));
        stage.setScene(scene);
        scene.setOnKeyTyped(e      -> handlekey(e));
    }
    public Button b(char c) {
    	Button b = new Button(""+c);
    	b.setMinSize(30,30);
    	b.setMaxSize(30,30);
    	b.setOnAction(value -> update(c));
    	return b;
    }
    void update(char c) {
    	tok.readChar(c);
    	label.setText(label.getText()+Character.toString(c));
    	if (c=='=')
        	label.setText(label.getText()+Double.toString(tok.calc.getResult()));
    	if (c=='C')
    		label.setText("");
    	
    }
    void   handlekey(KeyEvent e) update(e.getCharacter().charAt(0));
    
    public static void main(String[] args) {
        launch(args);
    }
}