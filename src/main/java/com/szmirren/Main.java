package com.szmirren;

import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.szmirren.common.ConfigUtil;
import com.szmirren.common.TemplateUtil;
import com.szmirren.controller.IndexController;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * 程序的入口
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class Main extends Application {
	private static Logger LOG = Logger.getLogger(Main.class.getName());
	/** 国际化控件的文字 */
	public static Map<String, StringProperty> LANGUAGE = new HashMap<>();

	@Override
	public void start(Stage primaryStage) throws Exception {
		ConfigUtil.existsConfigDB();// 创建配置文件
		// LanguageUtil.existsTemplate();// 国际化文件夹创建
		TemplateUtil.existsTemplate();// 创建模板
		loadLanguage(Locale.getDefault());// 加载本地语言资源
		// loadLanguage(Locale.ENGLISH);// 加载英语资源

		URL url = Thread.currentThread().getContextClassLoader().getResource("FXML/Index.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(url);
		Parent root = fxmlLoader.load();
		primaryStage.setResizable(true);
		primaryStage.setTitle("Srping-Generator");
		primaryStage.getIcons().add(new Image("image/icon.png"));
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		IndexController controller = fxmlLoader.getController();
		controller.setPrimaryStage(primaryStage);
	}

	public static void main(String[] args) {
		URL logResource = Thread.currentThread().getContextClassLoader().getResource("config/log4j.properties");
		PropertyConfigurator.configure(logResource);
		try {
			LOG.debug("运行Spring-Generator...");
			launch(args);
			LOG.debug("关闭Spring-Generator!!!");
		} catch (Exception e) {
			LOG.error("运行Spring-Generator-->失败:", e);
		}
	}

	/**
	 * 根据Locale加载控件文本
	 * 
	 * @param locale
	 */
	public static void loadLanguage(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("config/language/language", locale);
		Enumeration<String> keys = resourceBundle.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (LANGUAGE.get(key) == null) {
				LANGUAGE.put(key, new SimpleStringProperty(resourceBundle.getString(key)));
			} else {
				LANGUAGE.get(key).set(resourceBundle.getString(key));
			}
		}
	}
}