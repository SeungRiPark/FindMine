package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {

	private GridPane gridpain_label = null;
	private GridPane gridpain_button = null;

	private Label[][] arrLabel_mine = new Label[10][10];
	private Button[][] arrButton_mine = new Button[10][10];

	private char[][] arrMine = null;
	final int MINE_MAX = 10;

	@Override
	public void start(Stage primaryStage) {
		try {
			this.initComponent();
			this.setMine();
			this.setEvent();
			Pane root = new Pane();
			root.getChildren().addAll(gridpain_label,gridpain_button);
			Scene scene = new Scene(root,400,400);			

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);

			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	void setMine() {
		arrMine = new char[10][10];

		int mineCount = 0;
		
		
			for(int i = 0 ; i < arrLabel_mine.length; i++){
				for(int j = 0 ; j < arrLabel_mine[i].length; j++){
					arrLabel_mine[i][j].setText("");;
				}
			}
			
		

		while(mineCount < MINE_MAX) {
			// 지뢰의 가로위치
			int row = (int)(Math.random() * 10);
			int col = (int)(Math.random() * 10);

			if(arrMine[row][col] != 'M') {
				arrMine[row][col] = 'M';
				arrLabel_mine[row][col].setText("★");
				mineCount++;
				setMineCount(row,col);
			}
		}
	}

	private void initComponent() {
		gridpain_label = new GridPane();
		gridpain_label.setId("gridpain_label");
		gridpain_label.setMinWidth(400);
		gridpain_label.setMinHeight(400);
		gridpain_label.setMaxWidth(400);
		gridpain_label.setMaxHeight(400);
		gridpain_label.setGridLinesVisible(true);



		gridpain_button = new GridPane();
		gridpain_button.setId("gridpain_button");
		gridpain_button.setMinWidth(400);
		gridpain_button.setMinHeight(400);
		gridpain_button.setMaxWidth(400);
		gridpain_button.setMaxHeight(400);
		gridpain_button.setGridLinesVisible(true);

		for(int row =0 ; row < arrButton_mine.length; row++) {
			for(int col = 0; col < arrButton_mine[row].length; col++){
				arrLabel_mine[row][col] = new Label("");
				arrLabel_mine[row][col].setMinWidth(40);
				arrLabel_mine[row][col].setMinHeight(40);
				arrLabel_mine[row][col].setAlignment(Pos.CENTER);
				gridpain_label.add(arrLabel_mine[row][col], col, row);

				arrButton_mine[row][col] = new Button("");
				arrButton_mine[row][col].setMinWidth(40);
				arrButton_mine[row][col].setMinHeight(40);
				gridpain_button.add(arrButton_mine[row][col], col, row);

			}
		}
	}

	private void setMineCount(int row, int col) {
		int[] around = null;

		for(int aroundNum = 1; aroundNum <= 8;aroundNum++) {
			around = getAroundMine(row, col, aroundNum);
			this.increaseAroundMineCount(around);

		}
	}

	private int[] getAroundMine(int row, int col, int aroundNum){
		int[] result = new int[2];
		switch(aroundNum) {
		case 1:
			result[0] = row - 1;
			result[1] = col - 1;
			break;
		case 2:
			result[0] = row - 1;
			result[1] = col;
			break;
		case 3:
			result[0] = row - 1;
			result[1] = col + 1;
			break;
		case 4:
			result[0] = row;
			result[1] = col - 1;
			break;
		case 5:
			result[0] = row ;
			result[1] = col + 1;
			break;
		case 6:
			result[0] = row + 1;
			result[1] = col - 1;
			break;
		case 7:
			result[0] = row + 1;
			result[1] = col ;
			break;
		case 8:
			result[0] = row + 1;
			result[1] = col + 1;
			break;
		}

		return result;
	}

	private void increaseAroundMineCount(int[] around){
		if(around[0] >= 0 && around[0] < arrMine.length && around[1] >= 0 && around[1] < arrMine[0].length) {
			char c = arrMine[around[0]][around[1]];

			if(c!='M') {
				if(c==0) {
					arrMine[around[0]][around[1]] = '1';
				}else {
					arrMine[around[0]][around[1]]++;
				}
				arrLabel_mine[around[0]][around[1]].setText(arrMine[around[0]][around[1]]+"");
			}
		}
	}

	private void setEvent() {
		for(int row = 0; row < arrButton_mine.length;row++) {
			for(int col = 0;col < arrButton_mine[row].length; col++) {
				ButtonClickEvent event = new ButtonClickEvent(row, col, arrMine, arrButton_mine,this);
				arrButton_mine[row][col].setOnMousePressed(event);

			}
		}
	}
	public void findMine() {
		this.setMine();
		this.setEvent();
	}


	public static void main(String[] args) {
		launch(args);
	}
}
