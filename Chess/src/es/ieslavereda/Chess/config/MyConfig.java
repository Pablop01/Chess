package es.ieslavereda.Chess.config;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class MyConfig {

	private static MyConfig instancia = new MyConfig();

	private String defaultFile = "default.properties";
	private String appFile = "app.properties";
	private Properties properties;

	private MyConfig() {

		Properties defaultProperties = new Properties();

		try (FileInputStream fis = new FileInputStream(new File(defaultFile))) {

			defaultProperties.load(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}

		properties = new Properties(defaultProperties);

		try (FileInputStream fis = new FileInputStream(new File(appFile))) {

			properties.load(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static MyConfig getInstance() {
		return instancia;
	}

	public int getWhiteCellColor() {
		return Integer.parseInt(properties.getProperty("color_celda_blanca"));
	}
	
	public void setWhiteCellColor(Color color) {
		properties.setProperty("color_celda_blanca", String.valueOf(color.getRGB()));
		guardar();
	}

	public int getBlackCellColor() {
		return Integer.parseInt(properties.getProperty("color_celda_negra"));
	}
	
	public void setBlackCellColor(Color color) {
		properties.setProperty("color_celda_negra", String.valueOf(color.getRGB()));
		guardar();
	}
	
	public int getYellowBorderColor() {
		return Integer.parseInt(properties.getProperty("color_borde_celda_movimiento"));
	}
	
	public void setBorderColor(Color color) {
		properties.setProperty("color_borde_celda_movimiento", String.valueOf(color.getRGB()));
		guardar();
	}
	
	public int getRedBorderColor() {
		return Integer.parseInt(properties.getProperty("color_borde_celda_matar"));
	}
	
	public void setBorderColorComer(Color color) {
		properties.setProperty("color_borde_celda_matar", String.valueOf(color.getRGB()));
		guardar();
	}
	
	public void guardar() {
		
		try(FileOutputStream fos = new FileOutputStream(new File(appFile))){
			
			properties.store(fos, "UTF-8");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
