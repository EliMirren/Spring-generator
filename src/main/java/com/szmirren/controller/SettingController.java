package com.szmirren.controller;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import com.szmirren.Main;
import com.szmirren.common.LanguageKey;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;

public class SettingController extends BaseController {
	/** 语言 */
	@FXML
	private Label lblLanguage;
	/** 语言选择 */
	@FXML
	private MenuButton mbLanguage;
	/** 英语 */
	@FXML
	private Label lblEnglish;
	/** 中文 */
	@FXML
	private Label lblSimpleChinese;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblLanguage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.SETTING_LBL_LANGUAGE));
		lblLanguage.widthProperty().addListener(ev -> mbLanguage.setLayoutX(10 + lblLanguage.getLayoutX() + lblLanguage.getWidth()));
		// 设置选择框里面的文字跟选择框一样大
		mbLanguage.widthProperty().addListener(ev -> {
			if (mbLanguage.getWidth() > 100) {
				lblEnglish.setPrefWidth(mbLanguage.getWidth() - 15);
				lblSimpleChinese.setPrefWidth(mbLanguage.getWidth() - 15);
			}
		});
		// 给语言添加事件
		lblEnglish.setOnMouseClicked(ev -> {
			Main.loadLanguage(Locale.ENGLISH);
			closeDialogStage();
		});
		lblSimpleChinese.setOnMouseClicked(ev -> {
			Main.loadLanguage(Locale.SIMPLIFIED_CHINESE);
			closeDialogStage();
		});
	}

}
