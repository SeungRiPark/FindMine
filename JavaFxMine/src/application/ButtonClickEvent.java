package application;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ButtonClickEvent implements EventHandler<MouseEvent>{

	private int row;
	private int col;
	private char[][] arrMine;
	private Button[][] arrButton_mine;
	private Main main = null;
	int count =0;

	public ButtonClickEvent(int row, int col, char[][] arrMine, Button[][] arrButton_mine,Main main) {
		this.row = row;
		this.col = col;
		this.arrMine = arrMine;
		this.arrButton_mine = arrButton_mine;
		this.main = main;
	}
	@Override
	public void handle(MouseEvent event) {
		if(event.isPrimaryButtonDown()) {
			openEmptyBlock(row,col);
			if(!clickMine()){

				for(int i = 0; i < arrButton_mine.length; i++) {
					for(int j = 0; j < arrButton_mine[i].length;j++) {
						if(arrButton_mine[i][j].isVisible()){
							count++;
						}
					}
				}

				if(count == main.MINE_MAX){
					Stage pop = new Stage();
					GridPane rootAlert = new GridPane();
					Button ok = new Button();
					ok.setText("OK");
					final Label str = new Label();
					str.setText("클리어. \n게임을 초기화 합니다...");
					ok.setOnMousePressed(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {

							main.findMine();
							for(int row = 0 ; row < arrButton_mine.length; row++){
								for(int col = 0; col < arrButton_mine[row].length; col++) {
									arrButton_mine[row][col].setVisible(true);
									arrButton_mine[row][col].setText("");
								}
							}

							Stage sScene = (Stage) str.getScene().getWindow();
							sScene.close();
						}
					});
					ok.setId("ok");
					rootAlert.add(str, 2, 0,1,2);
					rootAlert.add(ok, 2, 4, 2, 1);
					rootAlert.setAlignment(Pos.CENTER);

					Scene scene = new Scene(rootAlert,200,100);

					pop.setScene(scene);

					pop.show();
				} 

				count =0;

			}
		}else if(event.isSecondaryButtonDown()) {
			String flag = this.arrButton_mine[row][col].getText();
			if(flag.equals("")) {
				this.arrButton_mine[row][col].setText("♠");
			}else if(flag.equals("♠")) {
				this.arrButton_mine[row][col].setText("?");
			}else if(flag.equals("?")) {
				this.arrButton_mine[row][col].setText("");
			}
		}

	}





	private boolean clickMine() {
		boolean isMine = false;
		if(arrMine[row][col] == 'M'){
			isMine = true;
			Stage pop = new Stage();
			GridPane rootAlert = new GridPane();
			Button ok = new Button();
			ok.setText("OK");
			final Label str = new Label();
			str.setText("지뢰가 터졌습니다. \n게임을 초기화 합니다...");
			ok.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					count =0;
					main.findMine();
					for(int row = 0 ; row < arrButton_mine.length; row++){
						for(int col = 0; col < arrButton_mine[row].length; col++) {
							arrButton_mine[row][col].setVisible(true);
							arrButton_mine[row][col].setText("");
						}
					}


					Stage sScene = (Stage) str.getScene().getWindow();
					sScene.close();
				}
			});
			ok.setId("ok");
			rootAlert.add(str, 2, 0,1,2);
			rootAlert.add(ok, 2, 4, 2, 1);
			rootAlert.setAlignment(Pos.CENTER);

			Scene scene = new Scene(rootAlert,200,100);

			pop.setScene(scene);

			pop.show();
		}
		return isMine;
	}

	private void openEmptyBlock(int row, int col) {
		arrButton_mine[row][col].setVisible(false);
		
		if(arrMine[row][col] == 0){
			int[] around2 = getAroundMine(row, col, 1);
			if(around2[0] >= 0 && around2[0] < arrMine.length && around2[1] >= 0 && around2[1] < arrMine[0].length && arrButton_mine[around2[0]][around2[1]].isVisible()) {
				openEmptyBlock(around2[0], around2[1]);
			}
		}

		if(arrMine[row][col] == 0){
			int[] around2 = getAroundMine(row, col, 2);
			if(around2[0] >= 0 && around2[0] < arrMine.length && around2[1] >= 0 && around2[1] < arrMine[0].length && arrButton_mine[around2[0]][around2[1]].isVisible()) {
				openEmptyBlock(around2[0], around2[1]);
			}
		}
		
		if(arrMine[row][col] == 0){
			int[] around2 = getAroundMine(row, col, 3);
			if(around2[0] >= 0 && around2[0] < arrMine.length && around2[1] >= 0 && around2[1] < arrMine[0].length && arrButton_mine[around2[0]][around2[1]].isVisible()) {
				openEmptyBlock(around2[0], around2[1]);
			}
		}


		if(arrMine[row][col] == 0){
			int[] around4 = getAroundMine(row, col, 4);
			if(around4[0] >= 0 && around4[0] < arrMine.length && around4[1] >= 0 && around4[1] < arrMine[0].length && arrButton_mine[around4[0]][around4[1]].isVisible()) {
				openEmptyBlock(around4[0], around4[1]);
			}
		}


		if(arrMine[row][col] == 0){
			int[] around5 = getAroundMine(row, col, 5);
			if(around5[0] >= 0 && around5[0] < arrMine.length && around5[1] >= 0 && around5[1] < arrMine[0].length && arrButton_mine[around5[0]][around5[1]].isVisible()) {
				openEmptyBlock(around5[0], around5[1]);
			}
		}
		if(arrMine[row][col] == 0){
			int[] around2 = getAroundMine(row, col, 6);
			if(around2[0] >= 0 && around2[0] < arrMine.length && around2[1] >= 0 && around2[1] < arrMine[0].length && arrButton_mine[around2[0]][around2[1]].isVisible()) {
				openEmptyBlock(around2[0], around2[1]);
			}
		}


		if(arrMine[row][col] == 0){
			int[] around7 = getAroundMine(row, col, 7);
			if(around7[0] >= 0 && around7[0] < arrMine.length && around7[1] >= 0 && around7[1] < arrMine[0].length && arrButton_mine[around7[0]][around7[1]].isVisible()) {
				openEmptyBlock(around7[0], around7[1]);
			}
		}
		
		if(arrMine[row][col] == 0){
			int[] around2 = getAroundMine(row, col, 8);
			if(around2[0] >= 0 && around2[0] < arrMine.length && around2[1] >= 0 && around2[1] < arrMine[0].length && arrButton_mine[around2[0]][around2[1]].isVisible()) {
				openEmptyBlock(around2[0], around2[1]);
			}
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

}
