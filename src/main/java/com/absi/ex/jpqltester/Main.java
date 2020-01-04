package com.absi.ex.jpqltester;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableView;
import javafx.geometry.Insets;
import javafx.scene.layout.Priority;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;

public class Main extends Application
{
	public static void main(String args[])
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
		EntityManager em = emf.createEntityManager();

		BorderPane root = new BorderPane();
		TextArea textArea = new TextArea();
		TableView tableView = new TableView();
		VBox vbox = new VBox(textArea, tableView);
		vbox.setVgrow(textArea, Priority.ALWAYS);
		vbox.setVgrow(tableView, Priority.ALWAYS);
		root.setCenter(vbox);
		root.setMargin(vbox, new Insets(20,20,20,20));
		Scene scene = new Scene(root, 1024,768);
		primaryStage.setMinWidth(1024);
		primaryStage.setMinHeight(768);
		primaryStage.setTitle("JPQL Tester");
		primaryStage.setScene(scene);
		primaryStage.show();


		//temporary
		em.getTransaction().begin();
		textArea.appendText((String)(em.createNativeQuery("select version()").getSingleResult()));
		em.getTransaction().commit();
		em.close();
	}
}	
