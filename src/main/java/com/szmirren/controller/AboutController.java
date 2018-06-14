package com.szmirren.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.szmirren.Main;
import com.szmirren.common.LanguageKey;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * 使用帮助
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class AboutController extends BaseController {
	/** 当前版本 */
	@FXML
	private Label lblVersion;
	/** 使用说明 */
	@FXML
	private Label lblInstructions;
	/** 项目地址 */
	@FXML
	private Label lblProjectPath;
	/** 模板仓库地址 */
	@FXML
	private Label lblTemplatePath;
	/** QQ交流群 */
	@FXML
	private Label lblTalkGroupInQQ;
	/** 作者邮箱 */
	@FXML
	private Label lblAuthorsEmail;
	/** 使用说明 */
	@FXML
	private TextField txtInstructions;
	/** 项目地址 */
	@FXML
	private TextField txtProjectPath;
	/** 模板仓库地址 */
	@FXML
	private TextField txtTemplatePath;
	/** QQ交流群 */
	@FXML
	private TextField txtTalkGroupInQQ;
	/** 作者邮箱 */
	@FXML
	private TextField txtAuthorsEmail;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		final int ml = 10;// 左外边距
		lblVersion.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INSTRUCTION_LBL_Version));
		lblInstructions.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INSTRUCTION_LBL_INSTRUCTIONS));
		lblInstructions.widthProperty().addListener(w -> txtInstructions.setLayoutX(ml + lblInstructions.getLayoutX() + lblInstructions.getWidth()));
		lblProjectPath.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INSTRUCTION_LBL_PROJECT_PATH));
		lblProjectPath.widthProperty().addListener(w -> txtProjectPath.setLayoutX(ml + lblProjectPath.getLayoutX() + lblProjectPath.getWidth()));
		lblTemplatePath.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INSTRUCTION_LBL_TEMPLATE_PATH));
		lblTemplatePath.widthProperty().addListener(w -> txtTemplatePath.setLayoutX(ml + lblTemplatePath.getLayoutX() + lblTemplatePath.getWidth()));
		lblTalkGroupInQQ.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INSTRUCTION_LBL_TALK_GROUP_IN_QQ));
		lblTalkGroupInQQ.widthProperty().addListener(w -> txtTalkGroupInQQ.setLayoutX(ml + lblTalkGroupInQQ.getLayoutX() + lblTalkGroupInQQ.getWidth()));
		lblAuthorsEmail.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INSTRUCTION_LBL_AUTHORS_EMAIL));
		lblAuthorsEmail.widthProperty().addListener(w -> txtAuthorsEmail.setLayoutX(ml + lblAuthorsEmail.getLayoutX() + lblAuthorsEmail.getWidth()));
	}

}
