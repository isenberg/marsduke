/*
 * FXMarsDuke.java
 * 
 * This JavaFX example application loads an image file
 * and displays it as rotating cube.
 * 
 * If the image file is missing, download 2P137011382EFF4000P2563L5M1
 * from https://areo.info/mer/spirit/120 and save it as marsduke.png.
 *
 * Author: Holger Isenberg
 * Date: 2023-06-21
 * No copyright claimed, free for any use.
 * 
 * To run it on JavaFX 17, available for example on https://azul.com/downloads:
 * javac FXMarsDuke.java
 * java FXMarsDuke
 * 
 */

import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class FXMarsDuke extends Application {
	static private String imgfilename = "marsduke.png";
	static private Image texture;
	private PhongMaterial texturedMaterial = new PhongMaterial();
	private PhongMaterial solidMaterial = new PhongMaterial();

	public static void main(String[] args) throws IOException, InterruptedException {
		if(args.length > 0) {
			imgfilename = args[0];
		}
		texture = new Image(new FileInputStream(imgfilename));
		System.out.print("Loading image " + imgfilename + "...");
		while(texture.getWidth() == 0) { 
			System.out.print(".");
			Thread.sleep(1000);
		}
		System.out.println();
		System.out.println("  Mars Exploration Rover Spirit, Pancam, sol 120, May 5, 2004\n"
				+ "  ID 2P137011382EFF4000P2563L5M1\n"
				+ "  https://areo.info/mer/spirit/120\n"
                + "  https://mars.nasa.gov/mer/gallery/all/spirit_p120.html\n");
		System.out.println("Press and move mouse to rotate cube,\n"
				+ "or use keys: " + KeyCode.UP + ", " + KeyCode.DOWN
				+ ", " + KeyCode.LEFT + ", " + KeyCode.RIGHT
				+ ", " + KeyCode.MINUS + ", " + KeyCode.PLUS
				+ "\nPress " + KeyCode.Q + " or " + KeyCode.ESCAPE + " to exit.");
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		stage.setTitle("FXMarsDuke");
		
		solidMaterial.setSpecularColor(Color.ORANGE);
		solidMaterial.setDiffuseColor(Color.RED);
		texturedMaterial.setDiffuseMap(texture);
		
		Box cube = new Box();
		cube.setWidth(texture.getWidth()); 
		cube.setHeight(texture.getWidth());
		cube.setDepth(texture.getWidth());
		cube.setMaterial(texturedMaterial);

		Group rootGroup = new Group();
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		double viewSize = Math.min(screenBounds.getWidth(), screenBounds.getHeight())*2/3;
		Scene scene = new Scene(rootGroup, viewSize, viewSize, true);
		scene.setFill(new RadialGradient(225, 0.85,
				scene.getWidth()/2, scene.getHeight()/2, scene.getHeight()/2, false,
				CycleMethod.NO_CYCLE, new Stop[]{
						new Stop(0f, Color.BLUE),
						new Stop(1f, Color.LIGHTBLUE)
				}));
		PerspectiveCamera camera = new PerspectiveCamera();
		scene.setCamera(camera);
		scene.setOnScroll((ScrollEvent e) -> {
			camera.setTranslateZ(camera.getTranslateZ() + e.getDeltaY());
		});

		cube.getTransforms().add(new Translate(scene.getWidth()/2, scene.getHeight()/2, 1));
		rootGroup.getChildren().addAll(cube);
		
		EventHandler<MouseEvent> mouseHandler =	new EventHandler<MouseEvent>() {
			private double oldX, oldY, olddx, olddy;
	        public void handle(MouseEvent mouseEvent) {
	            if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
	            	oldX = mouseEvent.getSceneX();
	            	oldY = mouseEvent.getSceneY();
	            	olddx = 0;
	            	olddy = 0;
	            }
	            if(mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED) {
	            	double dx, dy;
	            	if (olddx == 0 && olddy == 0) {
	            		dx = mouseEvent.getSceneX() - oldX;
	            		dy = mouseEvent.getSceneY() - oldY;
	            	} else {
	            		dx = mouseEvent.getSceneX() - olddx;
	            		dy = mouseEvent.getSceneY() - olddy;
		            	olddx = dx;
		            	olddy = dy;
	            	}
	            	cube.getTransforms().add(new Rotate(-dx/(scene.getWidth()/2), 0, 0, 0, Rotate.Y_AXIS));
	            	cube.getTransforms().add(new Rotate(dy/(scene.getHeight()/2), 0, 0, 0, Rotate.X_AXIS));
	            }
	        }};
		EventHandler<KeyEvent> keyHandler =	new EventHandler<KeyEvent>() {
		        public void handle(KeyEvent keyEvent) {
	            	if(keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
	            		double dw = scene.getWidth()/100; 
	            		double dz = scene.getHeight()/10;
	            		if(keyEvent.getCode() == KeyCode.PLUS || keyEvent.getCode() == KeyCode.EQUALS) {
	            			cube.getTransforms().add(new Translate(0, 0, -dz));
	            		} else if(keyEvent.getCode() == KeyCode.MINUS) {
	            			cube.getTransforms().add(new Translate(0, 0, dz));
	            		} else if(keyEvent.getCode() == KeyCode.UP) {
	            			cube.getTransforms().add(new Rotate(-dw, 0, 0, 0, Rotate.X_AXIS));
	            		} else if(keyEvent.getCode() == KeyCode.DOWN) {
	            			cube.getTransforms().add(new Rotate(dw, 0, 0, 0, Rotate.X_AXIS));
	            		} else if(keyEvent.getCode() == KeyCode.LEFT) {
	            			cube.getTransforms().add(new Rotate(dw, 0, 0, 0, Rotate.Y_AXIS));
	            		} else if(keyEvent.getCode() == KeyCode.RIGHT) {
	            			cube.getTransforms().add(new Rotate(-dw, 0, 0, 0, Rotate.Y_AXIS));
	            		} else if(keyEvent.getCode() == KeyCode.Q || keyEvent.getCode() == KeyCode.ESCAPE) {
	            			stage.close();
	            		}
	            	}
		        }};
		scene.setOnMousePressed(mouseHandler);
		scene.setOnMouseDragged(mouseHandler);
		scene.setOnKeyPressed(keyHandler);
		scene.setOnKeyReleased(keyHandler);
		stage.setScene(scene);
		stage.show();
	}
}