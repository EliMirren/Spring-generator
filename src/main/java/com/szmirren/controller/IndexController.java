package com.szmirren.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.szmirren.Main;
import com.szmirren.common.ConfigUtil;
import com.szmirren.common.Constant;
import com.szmirren.common.ConverterUtil;
import com.szmirren.common.CreateFileUtil;
import com.szmirren.common.DBUtil;
import com.szmirren.common.LanguageKey;
import com.szmirren.common.StrUtil;
import com.szmirren.entity.ControllerContent;
import com.szmirren.entity.CustomContent;
import com.szmirren.entity.CustomPropertyContent;
import com.szmirren.entity.DaoContent;
import com.szmirren.entity.DatabaseContent;
import com.szmirren.entity.EntityContent;
import com.szmirren.entity.GeneratorContent;
import com.szmirren.entity.MapperContent;
import com.szmirren.entity.ServiceContent;
import com.szmirren.entity.ServiceImplContent;
import com.szmirren.entity.SqlAssistContent;
import com.szmirren.entity.TableContent;
import com.szmirren.entity.UnitTestContent;
import com.szmirren.models.TableAttributeEntity;
import com.szmirren.models.TableAttributeKeyValueTemplate;
import com.szmirren.options.ControllerConfig;
import com.szmirren.options.CustomConfig;
import com.szmirren.options.CustomPropertyConfig;
import com.szmirren.options.DaoConfig;
import com.szmirren.options.DatabaseConfig;
import com.szmirren.options.EntityConfig;
import com.szmirren.options.HistoryConfig;
import com.szmirren.options.MapperConfig;
import com.szmirren.options.ServiceConfig;
import com.szmirren.options.ServiceImplConfig;
import com.szmirren.options.SqlAssistConfig;
import com.szmirren.options.UnitTestConfig;
import com.szmirren.view.AlertUtil;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;

/**
 * 首页的控制器
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class IndexController extends BaseController {
	private Logger LOG = Logger.getLogger(this.getClass());
	/** 配置信息的名字 */
	private String historyConfigName;
	/** 程序的配置信息 */
	private HistoryConfig historyConfig;
	/** 模板文件夹中模板现有模板名字 */
	private List<String> templateNameItems;

	/** 存储数据库指定数据库,修改属性时用 */
	private DatabaseConfig selectedDatabaseConfig;
	private DatabaseConfig updateOfDatabaseConfig;

	/** 记录存储的表名,修改属性时用 */
	private String selectedTableName;

	/** 实体类名默认的占位符 */
	private String entityNamePlace;
	/** Service默认占位符 */
	private String serviceNamePlace;
	/** ServiceImpl默认占位符 */
	private String serviceImplNamePlace;
	/** Controller默认占位符 */
	private String routerNamePlace;
	/** Dao默认占位符 */
	private String daoNamePlace;
	/** Mapper默认占位符 */
	private String mapperNamePlace;
	/** 单元测试默认占位符 */
	private String unitTestPlace;

	// ========================fxml控件============================
	/** 数据库连接 */
	@FXML
	private Label lblConnection;
	/** 配置信息 */
	@FXML
	private Label lblConfig;
	/** 使用说明 */
	@FXML
	private Label lblInstructions;
	/** 设置 */
	@FXML
	private Label lblSetting;
	/** 存放目录 */
	@FXML
	private Label lblProjectPath;
	/** 数据库表名 */
	@FXML
	private Label lblTableName;
	/** 实体类包名 */
	@FXML
	private Label lblEntityPackage;
	/** Service包名 */
	@FXML
	private Label lblServicePackage;
	/** ServiceImpl包名 */
	@FXML
	private Label lblServiceImplPackage;
	/** router包名 */
	@FXML
	private Label lblRouterPackage;
	/** SQL包名 */
	@FXML
	private Label lblSqlPackage;
	/** Assist包名 */
	@FXML
	private Label lblAssistPackage;
	/** Mapper包名 */
	@FXML
	private Label lblMapperPackage;
	/** 单元测试的包名 */
	@FXML
	private Label lblUnitTestPackage;

	/** 实体类类名 */
	@FXML
	private Label lblEntityName;
	/** Service类名 */
	@FXML
	private Label lblServiceName;
	/** ServiceImpl类名 */
	@FXML
	private Label lblServiceImplName;
	/** router类名 */
	@FXML
	private Label lblRouterName;
	/** SQL类名 */
	@FXML
	private Label lblSqlName;
	/** Assist类名 */
	@FXML
	private Label lblAssistName;
	/** Mapper的名字 */
	@FXML
	private Label lblMapperName;
	/** 单元测试的类名 */
	@FXML
	private Label lblUnitTestName;

	/** 自定义包名与类 */
	@FXML
	private Label lblSetCustom;
	/** 自定义属性 */
	@FXML
	private Label lblSetCustomProperty;
	/** 生成文件的编码格式 */
	@FXML
	private Label lblCodeFormat;

	/** 提示文字进度条 */
	@FXML
	private Label lblRunCreateAllTips;
	/** 提示文字的默认文字 */
	private String runCreateTipsText = "正在生成";

	/** 数据树列表 */
	@FXML
	private TreeView<String> tvDataBase;
	/** 存放目录 */
	@FXML
	private TextField txtProjectPath;
	/** 数据库表名 */
	@FXML
	private TextField txtTableName;
	/** 实体类包名 */
	@FXML
	private TextField txtEntityPackage;
	/** Service包名 */
	@FXML
	private TextField txtServicePackage;
	/** ServiceImpl包名 */
	@FXML
	private TextField txtServiceImplPackage;
	/** router包名 */
	@FXML
	private TextField txtRouterPackage;
	/** SQL包名 */
	@FXML
	private TextField txtSqlPackage;
	/** Assist包名 */
	@FXML
	private TextField txtAssistPackage;
	/** Mapper包名 */
	@FXML
	private TextField txtMapperPackage;
	/** 单元测试的包名 */
	@FXML
	private TextField txtUnitTestPackage;

	/** 实体类类名 */
	@FXML
	private TextField txtEntityName;
	/** Service类名 */
	@FXML
	private TextField txtServiceName;
	/** ServiceImpl类名 */
	@FXML
	private TextField txtServiceImplName;
	/** router类名 */
	@FXML
	private TextField txtRouterName;
	/** SQL类名 */
	@FXML
	private TextField txtSqlName;
	/** Assist类名 */
	@FXML
	private TextField txtAssistName;
	/** Mapper类名 */
	@FXML
	private TextField txtMapperName;
	/** 单元测试类名 */
	@FXML
	private TextField txtUnitTestName;

	/** 选择根目录按钮 */
	@FXML
	private Button btnSelectFile;
	/** 执行创建 */
	@FXML
	private Button btnRunCreate;
	/** 保存配置文件 */
	@FXML
	private Button btnSaveConfig;
	/** 实体类配置按钮 */
	@FXML
	private Button btnSetEntity;
	/** 到设置按钮 */
	@FXML
	private Button btnSetService;
	/** biz设置按钮 */
	@FXML
	private Button btnSetServiceImpl;
	/** router设置按钮 */
	@FXML
	private Button btnSetRouter;
	/** SQL设置按钮 */
	@FXML
	private Button btnSetSql;
	/** Assist的设置按钮 */
	@FXML
	private Button btnSetAssist;
	/** SqlAndParams的设置按钮 */
	@FXML
	private Button btnSetMapper;
	/** 单元测试的设置按钮 */
	@FXML
	private Button btnSetUnitTest;
	/** 自定义包名类的设置按钮 */
	@FXML
	private Button btnSetCustom;
	/** 自定义包名类属性的设置按钮 */
	@FXML
	private Button btnSetCustomProperty;
	/** 字符编码格式 */
	@FXML
	private ComboBox<String> cboCodeFormat;
	/** 生成进度条 */
	@FXML
	private ProgressBar probCreateAll;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		LOG.debug("初始化首页...");
		final int ml = 20;// 左外边距
		// 初始化图标连接与配置信息
		ImageView lblConnImage = new ImageView("image/computer.png");
		lblConnImage.setFitHeight(40);
		lblConnImage.setFitWidth(40);
		lblConnection.setGraphic(lblConnImage);
		lblConnection.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_CONNECTION));
		lblConnection.setOnMouseClicked(this::onConnection);
		lblConnection.widthProperty().addListener(event -> lblConfig.setLayoutX(ml + lblConnection.getLayoutX() + lblConnection.getWidth()));

		ImageView lblConfImage = new ImageView("image/config.png");
		lblConfImage.setFitHeight(40);
		lblConfImage.setFitWidth(40);
		lblConfig.setGraphic(lblConfImage);
		lblConfig.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_CONFIG));
		lblConfig.setOnMouseClicked(this::onConfig);
		lblConfig.widthProperty().addListener(event -> lblInstructions.setLayoutX(ml + lblConfig.getLayoutX() + lblConfig.getWidth()));

		ImageView lblInstructionsImage = new ImageView("image/instructions.png");
		lblInstructionsImage.setFitHeight(40);
		lblInstructionsImage.setFitWidth(40);
		lblInstructions.setGraphic(lblInstructionsImage);
		lblInstructions.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_INSTRUCTIONS));
		lblInstructions.setOnMouseClicked(this::onInstructions);
		lblInstructions.widthProperty()
				.addListener(event -> lblSetting.setLayoutX(ml + lblInstructions.getLayoutX() + lblInstructions.getWidth()));

		ImageView lblSettingImage = new ImageView("image/setting.png");
		lblSettingImage.setFitHeight(40);
		lblSettingImage.setFitWidth(40);
		lblSetting.setGraphic(lblSettingImage);
		lblSetting.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_SETTING));
		lblSetting.setOnMouseClicked(this::onSetting);

		cboCodeFormat.setEditable(true);
		cboCodeFormat.getItems().addAll("UTF-8", "GBK", "UTF-16", "UTF-32", "GB2312", "GB18030", "ISO-8859-1");
		cboCodeFormat.setValue("UTF-8");
		initLanguage();
		LOG.debug("初始化首页成功!");
		try {
			// 加载左边数据库树
			initTVDataBase();
			loadTVDataBase();
			LOG.debug("加载所有数据库到左侧树集成功!");
		} catch (Exception e1) {
			AlertUtil.showErrorAlert(e1.getMessage());
			LOG.error("加载所有数据库到左侧树集失败!!!" + e1);
		}
		try {
			// 加载首页配置信息
			LOG.debug("执行查询默认配置信息并加载到首页...");
			loadIndexConfigInfo("default");// 查询使用有默认的配置,如果有就加载
			loadPlace();// 设置默认的占位符名字
			loadTemplate();// 获取模板文件夹中所有模板的名字
			LOG.debug("加载配置信息到首页成功!");
		} catch (Exception e) {
			AlertUtil.showErrorAlert("加载配置失败!失败原因:\r\n" + e.getMessage());
			LOG.error("加载配置信息失败!!!" + e);
		}
	}

	// ======================方法区域================================
	/**
	 * 加载首页配置文件
	 * 
	 * @param name
	 * @throws Exception
	 */
	public void loadIndexConfigInfo(String name) throws Exception {
		HistoryConfig config = ConfigUtil.getHistoryConfigByName(name);
		if (config == null) {
			historyConfig = new HistoryConfig();
			return;
		} else {
			historyConfig = config;
		}
		historyConfigName = config.getHistoryConfigName();
		txtProjectPath.setText(config.getProjectPath());
		txtEntityPackage.setText(config.getEntityPackage());
		if (txtEntityName.getText().contains("{c}")) {
			txtEntityName.setText(config.getEntityName());
		}
		txtServicePackage.setText(config.getServicePackage());
		if (txtServiceName.getText().contains("{c}")) {
			txtServiceName.setText(config.getServiceName());
		}
		txtServiceImplPackage.setText(config.getServiceImplPackage());
		if (txtServiceImplName.getText().contains("{c}")) {
			txtServiceImplName.setText(config.getServiceImplName());
		}
		txtRouterPackage.setText(config.getControllerPackage());
		if (txtRouterName.getText().contains("{c}")) {
			txtRouterName.setText(config.getControllerName());
		}
		txtSqlPackage.setText(config.getDaoPackage());
		if (txtSqlName.getText().contains("{c}")) {
			txtSqlName.setText(config.getDaoName());
		}
		txtMapperPackage.setText(config.getMapperPackage());
		if (txtMapperName.getText().contains("{c}")) {
			txtMapperName.setText(config.getMapperName());
		}
		txtAssistPackage.setText(config.getAssistPackage());
		txtUnitTestPackage.setText(config.getUnitTestPackage());
		if (txtUnitTestName.getText().contains("{c}")) {
			txtUnitTestName.setText(config.getUnitTestName());
		}
		cboCodeFormat.setValue(config.getCodeFormat());
	}

	/**
	 * 初始化语言
	 */
	private void initLanguage() {
		lblProjectPath.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_PROJECT_PATH));
		txtProjectPath.promptTextProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TXT_PROJECT_PATH));
		lblTableName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_TABLE_NAME));
		txtTableName.promptTextProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TXT_TABLE_NAME));
		lblEntityPackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_ENTITY_PACKAGE));
		lblEntityName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_ENTITY_NAME));
		lblServicePackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_SERVICE_PACKAGE));
		lblServiceName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_SERVICE_NAME));
		lblServiceImplPackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_SERVICE_IMPL_PACKAGE));
		lblServiceImplName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_SERVICE_IMPL_NAME));
		lblRouterPackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_ROUTER_PACKAGE));
		lblRouterName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_ROUTER_NAME));
		lblSqlPackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_SQL_PACKAGE));
		lblSqlName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_SQL_NAME));
		lblAssistPackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_ASSIST_PACKAGE));
		lblAssistName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_ASSIST_NAME));
		lblMapperPackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_MAPPER_PACKAGE));
		lblMapperName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_MAPPER_NAME));
		lblUnitTestPackage.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_UNIT_TEST_PACKAGE));
		lblUnitTestName.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_UNIT_TEST_NAME));
		lblSetCustom.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_SET_CUSTOM));
		lblSetCustomProperty.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_SET_CUSTOM_PROPERTY));
		lblCodeFormat.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_LBL_CODE_FORMAT));
		btnSelectFile.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_BTN_SELECT_FILE));
		btnSetEntity.textProperty().bind(Main.LANGUAGE.get(LanguageKey.COMMON_BTN_SET));
		btnSetService.textProperty().bind(Main.LANGUAGE.get(LanguageKey.COMMON_BTN_SET));
		btnSetServiceImpl.textProperty().bind(Main.LANGUAGE.get(LanguageKey.COMMON_BTN_SET));
		btnSetRouter.textProperty().bind(Main.LANGUAGE.get(LanguageKey.COMMON_BTN_SET));
		btnSetSql.textProperty().bind(Main.LANGUAGE.get(LanguageKey.COMMON_BTN_SET));
		btnSetAssist.textProperty().bind(Main.LANGUAGE.get(LanguageKey.COMMON_BTN_SET));
		btnSetMapper.textProperty().bind(Main.LANGUAGE.get(LanguageKey.COMMON_BTN_SET));
		btnSetUnitTest.textProperty().bind(Main.LANGUAGE.get(LanguageKey.COMMON_BTN_SET));
		btnSetCustom.textProperty().bind(Main.LANGUAGE.get(LanguageKey.COMMON_BTN_SET));
		btnSetCustomProperty.textProperty().bind(Main.LANGUAGE.get(LanguageKey.COMMON_BTN_SET));
		btnRunCreate.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_BTN_RUN_CREATE));
		btnSaveConfig.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_BTN_SAVE_CONFIG));
	}

	/**
	 * 获得当前页面的信息并实例化为配置信息对象,
	 * 
	 * @param name
	 * @return
	 */
	private HistoryConfig getThisHistoryConfig() {
		String projectPath = txtProjectPath.getText();
		String entityPackage = txtEntityPackage.getText();
		String entityName = txtEntityName.getText();
		String servicePackage = txtServicePackage.getText();
		String serviceName = txtServiceName.getText();
		String serviceImplPackage = txtServiceImplPackage.getText();
		String serviceImplName = txtServiceImplName.getText();
		String controllerPackage = txtRouterPackage.getText();
		String controllerName = txtRouterName.getText();
		String daoPackage = txtSqlPackage.getText();
		String daoName = txtSqlName.getText();
		String assistPackage = txtAssistPackage.getText();
		String mapperPackage = txtMapperPackage.getText();
		String mapperName = txtMapperName.getText();
		String unitTestPackage = txtUnitTestPackage.getText();
		String unitTestName = txtUnitTestName.getText();
		String codeFormat = cboCodeFormat.getValue();
		HistoryConfig config = new HistoryConfig(projectPath, entityPackage, entityName, servicePackage, serviceName, serviceImplPackage,
				serviceImplName, controllerPackage, controllerName, daoPackage, daoName, mapperPackage, mapperName, assistPackage, unitTestPackage,
				unitTestName, codeFormat);
		config.setDbConfig(selectedDatabaseConfig);
		config.setEntityConfig(historyConfig.getEntityConfig());
		config.setServiceConfig(historyConfig.getServiceConfig());
		config.setServiceImplConfig(historyConfig.getServiceImplConfig());
		config.setControllerConfig(historyConfig.getControllerConfig());
		config.setDaoConfig(historyConfig.getDaoConfig());
		config.setMapperConfig(historyConfig.getMapperConfig());
		config.setAssistConfig(historyConfig.getAssistConfig());
		config.setUnitTestConfig(historyConfig.getUnitTestConfig());
		config.setCustomConfig(historyConfig.getCustomConfig());
		config.setCustomPropertyConfig(historyConfig.getCustomPropertyConfig());
		return config;
	}

	/**
	 * 获得当前页面的配置信息,如果某个配置信息没有初始化就实例化并初始化基本属性,
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	private HistoryConfig getThisHistoryConfigAndInit(DatabaseConfig databaseConfig, String selectedTableName) {
		try {
			HistoryConfig config = getThisHistoryConfig();
			if (config.getEntityConfig() == null) {
				EntityConfig entityConfig = Optional.ofNullable(ConfigUtil.getEntityConfig(Constant.DEFAULT)).orElse(new EntityConfig());
				List<TableAttributeEntity> columns = DBUtil.getTableColumns(databaseConfig, selectedTableName);
				if (entityConfig.isFieldCamel()) {
					for (TableAttributeEntity attr : columns) {
						attr.setTdField(StrUtil.unlineToCamel(attr.getTdColumnName()));
					}
				} else {
					for (TableAttributeEntity attr : columns) {
						attr.setTdField(attr.getTdColumnName());
					}
				}
				entityConfig.setTblPropertyValues(FXCollections.observableArrayList(columns));
				String primaryKey = DBUtil.getTablePrimaryKey(databaseConfig, selectedTableName);
				entityConfig.setPrimaryKey(primaryKey);
				config.setEntityConfig(entityConfig);
			}
			if (config.getServiceConfig() == null) {
				config.setServiceConfig(
						Optional.ofNullable(ConfigUtil.getServiceConfig(Constant.DEFAULT)).orElse(new ServiceConfig().initDefaultValue()));
			}
			if (config.getServiceImplConfig() == null) {
				config.setServiceImplConfig(
						Optional.ofNullable(ConfigUtil.getServiceImplConfig(Constant.DEFAULT)).orElse(new ServiceImplConfig().initDefaultValue()));
			}
			if (config.getDaoConfig() == null) {
				config.setDaoConfig(Optional.ofNullable(ConfigUtil.getSQLConfig(Constant.DEFAULT)).orElse(new DaoConfig().initDefaultValue()));
			}
			if (config.getMapperConfig() == null) {
				config.setMapperConfig(
						Optional.ofNullable(ConfigUtil.getSqlAndParamsConfig(Constant.DEFAULT)).orElse(new MapperConfig().initDefaultValue()));
			}
			if (config.getControllerConfig() == null) {
				config.setControllerConfig(
						Optional.ofNullable(ConfigUtil.getRouterConfig(Constant.DEFAULT)).orElse(new ControllerConfig().initDefaultValue()));
			}
			if (config.getUnitTestConfig() == null) {
				config.setUnitTestConfig(
						Optional.ofNullable(ConfigUtil.getUnitTestConfig(Constant.DEFAULT)).orElse(new UnitTestConfig().initDefaultValue()));
			}
			if (config.getAssistConfig() == null) {
				config.setAssistConfig(
						Optional.ofNullable(ConfigUtil.getSqlAssistConfig(Constant.DEFAULT)).orElse(new SqlAssistConfig().initDefaultValue()));
			}
			if (config.getCustomConfig() == null) {
				config.setCustomConfig(
						Optional.ofNullable(ConfigUtil.getCustomConfig(Constant.DEFAULT)).orElse(new CustomConfig().initDefaultValue()));
			}
			if (config.getCustomPropertyConfig() == null) {
				config.setCustomPropertyConfig(Optional.ofNullable(ConfigUtil.getCustomPropertyConfig(Constant.DEFAULT))
						.orElse(new CustomPropertyConfig().initDefaultValue()));
			}
			return config;
		} catch (Exception e) {
			LOG.debug("执行初始化配置信息-->失败:", e);
		}
		return null;
	}

	/**
	 * 加载默认名字
	 */
	private void loadPlace() {
		entityNamePlace = txtEntityName.getText();
		serviceNamePlace = txtServiceName.getText();
		serviceImplNamePlace = txtServiceImplName.getText();
		routerNamePlace = txtRouterName.getText();
		daoNamePlace = txtSqlName.getText();
		mapperNamePlace = txtMapperName.getText();
		unitTestPlace = txtUnitTestName.getText();
	}

	/**
	 * 右边数据库树与事件
	 */
	@SuppressWarnings("unchecked")
	public void initTVDataBase() {
		LOG.debug("加载左侧数据库树与事件....");
		tvDataBase.setShowRoot(false);
		tvDataBase.setRoot(new TreeItem<>());
		Callback<TreeView<String>, TreeCell<String>> defaultCellFactory = TextFieldTreeCell.forTreeView();
		tvDataBase.setCellFactory((TreeView<String> tv) -> {
			TreeCell<String> cell = defaultCellFactory.call(tv);
			cell.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
				int level = tvDataBase.getTreeItemLevel(cell.getTreeItem());
				TreeCell<String> treeCell = (TreeCell<String>) event.getSource();
				TreeItem<String> treeItem = treeCell.getTreeItem();
				if (level == 1) {
					final ContextMenu contextMenu = new ContextMenu();
					MenuItem item0 = new MenuItem("打开连接");
					item0.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TVMI_OPEN_CONNECT));
					item0.setOnAction(event1 -> {
						LOG.debug("执行打开数据库连接....");
						DatabaseConfig selectedConfig = (DatabaseConfig) treeItem.getGraphic().getUserData();
						try {
							List<String> tables = DBUtil.getTableNames(selectedConfig);
							if (tables != null && tables.size() > 0) {
								ObservableList<TreeItem<String>> children = cell.getTreeItem().getChildren();
								children.clear();
								for (String tableName : tables) {
									TreeItem<String> newTreeItem = new TreeItem<>();
									ImageView imageView = new ImageView("image/table.png");
									imageView.setFitHeight(16);
									imageView.setFitWidth(16);
									newTreeItem.setGraphic(imageView);
									newTreeItem.setValue(tableName);
									children.add(newTreeItem);
								}
							}
						} catch (Exception e) {
							AlertUtil.showErrorAlert(e.getMessage());
							LOG.error("打开连接失败!!!" + e);
						}
					});
					MenuItem item1 = new MenuItem("关闭连接");
					item1.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TVMI_CLOSE_CONNECT));
					item1.setOnAction(event1 -> {
						treeItem.getChildren().clear();
					});
					MenuItem item3 = new MenuItem("修改连接");
					item3.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TVMI_UPDATE_CONNECT));
					item3.setOnAction(event1 -> {
						updateOfDatabaseConfig = (DatabaseConfig) treeItem.getGraphic().getUserData();
						if (updateOfDatabaseConfig != null) {
							LOG.debug("打开修改数据库连接窗口...");
							StringProperty property = Main.LANGUAGE.get(LanguageKey.PAGE_UPDATE_CONNECTION);
							String title = property == null ? "修改数据库连接" : property.get();
							UpdateConnection controller = (UpdateConnection) loadFXMLPage(title, FXMLPage.UPDATE_CONNECTION, false);
							controller.setIndexController(this);
							controller.init();
							controller.showDialogStage();
						}
					});
					MenuItem item2 = new MenuItem("删除连接");
					item2.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TVMI_DELETE_CONNECT));
					item2.setOnAction(event1 -> {
						if (!AlertUtil.showConfirmAlert("确定删除该连接吗")) {
							return;
						}
						LOG.debug("执行删除数据库链接...");
						DatabaseConfig selectedConfig = (DatabaseConfig) treeItem.getGraphic().getUserData();
						try {
							ConfigUtil.deleteDatabaseConfig(selectedConfig.getConnName());
							this.loadTVDataBase();
						} catch (Exception e) {
							AlertUtil.showErrorAlert("删除数据库连接失败: " + e.getMessage());
							LOG.error("删除数据库连接失败!!!" + e);
						}
					});

					MenuItem itemCreateAll = new MenuItem("全库生成");
					itemCreateAll.textProperty().bind(Main.LANGUAGE.get(LanguageKey.INDEX_TVMI_CREATE_FULL_DB));
					itemCreateAll.setOnAction(event1 -> {
						if (StrUtil.isNullOrEmpty(txtProjectPath.getText())) {
							StringProperty property = Main.LANGUAGE.get(LanguageKey.TIPS_PATH_CANT_EMPTY);
							String title = property == null ? "生成的路径不能为空" : property.get();
							AlertUtil.showWarnAlert(title);
							return;
						}
						if (!AlertUtil.showConfirmAlert("确定当前数据库里面所有的表都生成吗?")) {
							return;
						}
						LOG.debug("执行全库生成...");
						DatabaseConfig selectedConfig = (DatabaseConfig) treeItem.getGraphic().getUserData();

						createAllTable(selectedConfig);
					});
					contextMenu.getItems().addAll(itemCreateAll, item0, item1, item3, item2);
					cell.setContextMenu(contextMenu);
				}
				// 加载所有表
				if (event.getClickCount() == 2) {
					if (treeItem == null) {
						return;
					}
					treeItem.setExpanded(true);
					if (level == 1) {
						LOG.debug("加载所有表....");
						DatabaseConfig selectedConfig = (DatabaseConfig) treeItem.getGraphic().getUserData();
						try {
							List<String> tables = DBUtil.getTableNames(selectedConfig);
							if (tables != null && tables.size() > 0) {
								ObservableList<TreeItem<String>> children = cell.getTreeItem().getChildren();
								children.clear();
								// 获得树节点
								for (String tableName : tables) {
									TreeItem<String> newTreeItem = new TreeItem<>();
									ImageView imageView = new ImageView("image/table.png");
									imageView.setFitHeight(18);
									imageView.setFitWidth(18);
									newTreeItem.setGraphic(imageView);
									newTreeItem.setValue(tableName);
									children.add(newTreeItem);
								}
							}
							LOG.debug("加载所有表成功!");
						} catch (Exception e) {
							AlertUtil.showErrorAlert(e.getMessage());
							LOG.error("加载所有表失败!!!" + e);
						}
					} else if (level == 2) {
						LOG.debug("将表的数据加载到数据面板...");
						String tableName = treeCell.getTreeItem().getValue();
						selectedDatabaseConfig = (DatabaseConfig) treeItem.getParent().getGraphic().getUserData();
						selectedTableName = tableName;
						txtTableName.setText(tableName);
						String pascalTableName = StrUtil.unlineToPascal(tableName);
						txtEntityName.setText(entityNamePlace.replace("{c}", pascalTableName));
						txtServiceName.setText(serviceNamePlace.replace("{c}", pascalTableName));
						txtServiceImplName.setText(serviceImplNamePlace.replace("{c}", pascalTableName));
						txtRouterName.setText(routerNamePlace.replace("{c}", pascalTableName));
						txtSqlName.setText(daoNamePlace.replace("{c}", pascalTableName));
						txtMapperName.setText(mapperNamePlace.replace("{c}", pascalTableName));
						txtUnitTestName.setText(unitTestPlace.replace("{c}", pascalTableName));
						LOG.debug("将表的数据加载到数据面板成功!");
					}
				}
			});
			return cell;
		});
	}

	/**
	 * 加载数据库到树集
	 * 
	 * @throws Exception
	 */
	public void loadTVDataBase() throws Exception {
		TreeItem<String> rootTreeItem = tvDataBase.getRoot();
		rootTreeItem.getChildren().clear();
		List<DatabaseConfig> item = null;
		item = ConfigUtil.getDatabaseConfig();
		for (DatabaseConfig dbConfig : item) {
			TreeItem<String> treeItem = new TreeItem<String>();
			treeItem.setValue(dbConfig.getConnName());
			ImageView dbImage = new ImageView("image/database.png");
			dbImage.setFitHeight(20);
			dbImage.setFitWidth(20);
			dbImage.setUserData(dbConfig);
			treeItem.setGraphic(dbImage);
			rootTreeItem.getChildren().add(treeItem);
		}
	}

	/**
	 * 加载模板文件夹里面所有模板的名字
	 * 
	 * @throws IOException
	 */
	public void loadTemplate() {
		LOG.debug("执行加载模板文件夹里面所有模板的名字...");
		try {
			this.templateNameItems = Files.list(Paths.get(Constant.TEMPLATE_DIR_NAME)).filter(f -> f.getFileName().toString().endsWith(".ftl"))
					.map(p -> p.getFileName().toString()).collect(Collectors.toList());
			if (this.templateNameItems == null) {
				this.templateNameItems = new ArrayList<>();
			}
			LOG.debug("执行加载模板文件夹里面所有模板的名字-->成功!");
		} catch (IOException e) {
			LOG.error("执行加载模板文件夹里面所有模板的名字-->失败:", e);
			AlertUtil.showErrorAlert(e.toString());
		}
	}

	/**
	 * 获得模板需要的上下文
	 * 
	 * @param databaseConfig
	 *          数据库配置文件
	 * @param tableName
	 *          表的名字,如果表名不为空,将类名设置为默认值占位表名,如果直接使用版面数据输入null
	 * @return
	 * @throws Exception
	 */
	public GeneratorContent getGeneratorContent(DatabaseConfig databaseConfig, String tableName) throws Exception {
		GeneratorContent content = new GeneratorContent();
		HistoryConfig history = getThisHistoryConfigAndInit(databaseConfig, tableName != null ? tableName : selectedTableName);
		// 数据库属性
		DatabaseContent databaseContent = new DatabaseContent();
		ConverterUtil.databaseConfigToContent(databaseConfig, databaseContent);
		content.setDatabase(databaseContent);

		// 数据库表属性
		TableContent tableContent = DBUtil.getTableAttribute(databaseConfig, tableName);
		content.setTable(tableContent);
		// 实体类属性
		EntityConfig ec = getThisHistoryConfigAndInit(databaseConfig, tableName != null ? tableName : selectedTableName).getEntityConfig();

		String className = tableName != null ? entityNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)) : txtEntityName.getText();
		String entityName = tableName != null ? entityNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)) : txtEntityName.getText();
		if (tableName == null) {
			tableName = selectedTableName;
		}
		EntityContent entityContent = new EntityContent(txtEntityPackage.getText(), entityName, tableName);
		ConverterUtil.entityConfigToContent(ec, entityContent);
		content.setEntity(entityContent);
		// Service属性
		ServiceConfig sc = history.getServiceConfig();
		String serviceName = tableName != null ? serviceNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)) : txtServiceName.getText();
		ServiceContent serviceContent = new ServiceContent(txtServicePackage.getText(), serviceName);
		ConverterUtil.serviceConfigToContent(sc, serviceContent, className);
		content.setService(serviceContent);
		// ServiceImpl属性
		ServiceImplConfig sci = history.getServiceImplConfig();
		String serviceNameImplName = tableName != null
				? serviceImplNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName))
				: txtServiceImplName.getText();
		ServiceImplContent serviceImplContent = new ServiceImplContent(txtServiceImplPackage.getText(), serviceNameImplName);
		ConverterUtil.serviceImplConfigToContent(sci, serviceImplContent, className);
		content.setServiceImpl(serviceImplContent);
		// dao属性
		String sqlName = tableName != null ? daoNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)) : txtSqlName.getText();
		DaoConfig sql = history.getDaoConfig();
		DaoContent sqlContent = new DaoContent(txtSqlPackage.getText(), sqlName);
		ConverterUtil.SqlConfigToContent(sql, sqlContent, className);
		content.setDao(sqlContent);
		// Controller属性
		String routerName = tableName != null ? routerNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)) : txtRouterName.getText();
		ControllerConfig router = history.getControllerConfig();
		ControllerContent routerContent = new ControllerContent(txtRouterPackage.getText(), routerName);
		ConverterUtil.routerConfigToContent(router, routerContent, className);
		content.setController(routerContent);
		// 单元测试 属性
		String testName = tableName != null ? unitTestPlace.replace("{c}", StrUtil.unlineToPascal(tableName)) : txtUnitTestName.getText();
		UnitTestConfig unit = history.getUnitTestConfig();
		UnitTestContent unitTestContent = new UnitTestContent(txtUnitTestPackage.getText(), testName);
		ConverterUtil.unitTestConfigToContent(unit, unitTestContent, className);
		content.setUnitTest(unitTestContent);
		// SqlAssist属性
		SqlAssistConfig assistConfig = history.getAssistConfig();
		SqlAssistContent assistContent = new SqlAssistContent(txtAssistPackage.getText(), txtAssistName.getText());
		ConverterUtil.sqlAssistConfigToContent(assistConfig, assistContent, className);
		content.setAssist(assistContent);
		// Mapper属性
		MapperConfig mapperConfig = history.getMapperConfig();
		String mapperName = tableName != null ? mapperNamePlace.replace("{c}", StrUtil.unlineToPascal(tableName)) : txtMapperName.getText();
		MapperContent mapperContent = new MapperContent(txtMapperPackage.getText(), mapperName);
		ConverterUtil.mapperConfigToContent(mapperConfig, mapperContent, className);
		content.setMapper(mapperContent);
		// 自定义包类属性
		CustomConfig customConfig = history.getCustomConfig();
		CustomContent customContent = new CustomContent();
		ConverterUtil.customConfigToContent(customConfig, customContent, className);
		content.setCustom(customContent);
		// 自定义属性
		CustomPropertyConfig propertyConfig = history.getCustomPropertyConfig();
		CustomPropertyContent propertyContent = new CustomPropertyContent();
		ConverterUtil.customPropertyConfigToContent(propertyConfig, propertyContent, className);
		content.setCustomProperty(propertyContent);
		return content;
	}
	/**
	 * 将数据库中所有的表创建
	 * 
	 * @param databaseConfig
	 */
	public void createAllTable(DatabaseConfig databaseConfig) {
		try {
			List<String> tables = DBUtil.getTableNames(databaseConfig);
			if (tables.size() == 0) {
				AlertUtil.showWarnAlert("当前数据库不存在表");
				return;
			}
			double progIndex = 1.0 / tables.size();
			probCreateAll.setVisible(true);
			Task<Void> task = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					for (int i = 0; i < tables.size(); i++) {
						updateProgress(progIndex * (i + 1), 1.0);
						updateMessage(runCreateTipsText + " {t} ...".replace("{t}", tables.get(i)));
						createAllRun(databaseConfig, tables.get(i));
						loadIndexConfigInfo(historyConfigName == null ? "default" : historyConfigName);
					}
					updateMessage("创建成功!");
					LOG.debug("执行全库生成-->成功");
					return null;
				}
			};
			probCreateAll.progressProperty().bind(task.progressProperty());
			lblRunCreateAllTips.textProperty().bind(task.messageProperty());
			new Thread(task).start();
		} catch (Exception e) {
			AlertUtil.showErrorAlert("创建文件失败:" + e);
			LOG.error("执行创建文件-->失败:" + e);
		}
	}
	/**
	 * 全库生成的执行方法
	 * 
	 * @param databaseConfig
	 *          数据库连接信息
	 * @param tableName
	 *          表的名字
	 * @throws Exception
	 */
	public void createAllRun(DatabaseConfig databaseConfig, String tableName) throws Exception {
		HistoryConfig historyConfig = getThisHistoryConfigAndInit(databaseConfig, tableName);
		GeneratorContent content = getGeneratorContent(databaseConfig, tableName);
		// 项目生成的路径
		String projectPath = txtProjectPath.getText();
		String codeFormat = cboCodeFormat.getValue();
		// 实体类的名字
		String entityName = StrUtil.unlineToPascal(tableName);
		// 生成实体类
		try {
			EntityConfig config = historyConfig.getEntityConfig();
			if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
				CreateFileUtil.createFile(content, config.getTemplateName(), projectPath, txtEntityPackage.getText(),
						entityNamePlace.replace("{c}", entityName) + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
			}
			LOG.debug("执行将" + tableName + "生成实体类-->成功!");
		} catch (Exception e) {
			LOG.error("执行将" + tableName + "生成实体类-->失败:", e);
		}
		// 生成Service
		try {
			ServiceConfig config = historyConfig.getServiceConfig();
			if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
				CreateFileUtil.createFile(content, config.getTemplateName(), projectPath, txtServicePackage.getText(),
						serviceNamePlace.replace("{c}", entityName) + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
			}
			LOG.debug("执行将" + tableName + "生成Service-->成功!");
		} catch (Exception e) {
			LOG.error("执行将" + tableName + "生成Service-->失败:", e);
		}
		// 生成ServiceImpl
		try {
			ServiceImplConfig config = historyConfig.getServiceImplConfig();
			if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
				CreateFileUtil.createFile(content, config.getTemplateName(), projectPath, txtServiceImplPackage.getText(),
						serviceImplNamePlace.replace("{c}", entityName) + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
			}
			LOG.debug("执行将" + tableName + "生成ServiceImpl-->成功!");
		} catch (Exception e) {
			LOG.error("执行将" + tableName + "生成ServiceImpl-->失败:", e);
		}
		// 生成dao
		try {
			DaoConfig config = historyConfig.getDaoConfig();
			if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
				CreateFileUtil.createFile(content, config.getTemplateName(), projectPath, txtSqlPackage.getText(),
						daoNamePlace.replace("{c}", entityName) + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
			}
			LOG.debug("执行将" + tableName + "生成DAO-->成功!");
		} catch (Exception e) {
			LOG.error("执行将" + tableName + "生成DAO-->失败:", e);
		}
		// 生成Mapper
		try {
			MapperConfig config = historyConfig.getMapperConfig();
			if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
				if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
					String templateName = config.getTemplateName();
					if (templateName.equals(Constant.TEMPLATE_NAME_MAPPER)) {
						templateName = databaseConfig.getDbType() + Constant.TEMPLATE_NAME_MAPPER_SUFFIX;
					}
					CreateFileUtil.createFile(content, templateName, projectPath, txtMapperPackage.getText(),
							mapperNamePlace.replace("{c}", entityName), codeFormat, config.isOverrideFile());
				}
			}
			LOG.debug("执行生成Mapper-->成功!");
		} catch (Exception e) {
			LOG.error("执行生成Mapper-->失败:", e);
		}
		// 生成Router
		try {
			ControllerConfig config = historyConfig.getControllerConfig();
			if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
				CreateFileUtil.createFile(content, config.getTemplateName(), projectPath, txtRouterPackage.getText(),
						routerNamePlace.replace("{c}", entityName) + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
			}
			LOG.debug("执行将" + tableName + "生成Router-->成功!");
		} catch (Exception e) {
			LOG.error("执行将" + tableName + "生成Router-->失败:", e);
		}
		// 生成单元测试
		try {
			UnitTestConfig config = historyConfig.getUnitTestConfig();
			if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
				CreateFileUtil.createFile(content, config.getTemplateName(), projectPath, txtUnitTestPackage.getText(),
						unitTestPlace.replace("{c}", entityName) + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
			}
			LOG.debug("执行将" + tableName + "生成单元测试-->成功!");
		} catch (Exception e) {
			LOG.error("执行将" + tableName + "生成单元测试-->失败:", e);
		}
		// 生成SqlAssist
		try {
			SqlAssistConfig config = historyConfig.getAssistConfig();
			if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
				CreateFileUtil.createFile(content, config.getTemplateName(), projectPath, txtAssistPackage.getText(),
						txtAssistName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
			}
			LOG.debug("执行将" + tableName + "生成SqlAssist-->成功!");
		} catch (Exception e) {
			LOG.error("执行将" + tableName + "生成SqlAssist-->失败:", e);
		}

		CustomConfig config = historyConfig.getCustomConfig();
		if (config.getTableItem() != null) {
			for (TableAttributeKeyValueTemplate custom : config.getTableItem()) {
				if (!StrUtil.isNullOrEmpty(custom.getTemplateValue())) {
					try {
						String loCase = StrUtil.fristToLoCase(entityName);
						String cpackage = custom.getPackageName().replace("{C}", entityName).replace("{c}", loCase);
						String name = custom.getClassName().replace("{C}", entityName).replace("{c}", loCase);
						CreateFileUtil.createFile(content, custom.getTemplateValue(), projectPath, cpackage, name + custom.getSuffix(), codeFormat,
								config.isOverrideFile());
					} catch (Exception e) {
						LOG.error("执行生成自定义生成包类-->失败:", e);
					}
				}
			}
		}

	}

	// ============================事件区域=================================
	/**
	 * 执行创建
	 * 
	 * @param event
	 */
	public void onCreate(ActionEvent event) {
		LOG.debug("执行创建...");
		try {
			if (StrUtil.isNullOrEmpty(txtProjectPath.getText())) {
				StringProperty property = Main.LANGUAGE.get(LanguageKey.TIPS_PATH_CANT_EMPTY);
				String tips = property == null ? "生成的路径不能为空" : property.get();
				AlertUtil.showWarnAlert(tips);
				return;
			}
			if (StrUtil.isNullOrEmpty(txtTableName.getText())) {
				StringProperty property = Main.LANGUAGE.get(LanguageKey.INDEX_TIPS_CREATE_TABLE);
				String tips = property == null ? "请双击左侧数据选择想要生成的表,或者在左侧右键全库生成!" : property.get();
				AlertUtil.showWarnAlert(tips);
				return;
			}
			probCreateAll.setVisible(true);
			Task<Void> task = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					updateProgress(1, 9);
					// 项目生成的路径
					String projectPath = txtProjectPath.getText();
					String codeFormat = cboCodeFormat.getValue();
					HistoryConfig historyConfig = getThisHistoryConfigAndInit(selectedDatabaseConfig, txtTableName.getText());
					GeneratorContent content = getGeneratorContent(selectedDatabaseConfig, null);
					// 生成实体类
					try {
						EntityConfig config = historyConfig.getEntityConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtEntityName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), projectPath, txtEntityPackage.getText(),
									txtEntityName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成实体类-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成实体类:" + txtEntityName.getText() + "失败:" + e);
						LOG.error("执行生成实体类-->失败:", e);
					}
					// 生成Service
					updateProgress(2, 9);
					try {
						ServiceConfig config = historyConfig.getServiceConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtServiceName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), projectPath, txtServicePackage.getText(),
									txtServiceName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成Service-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成Service:" + txtServiceName.getText() + "失败:" + e);
						LOG.error("执行生成Service-->失败:", e);
					}
					// 生成ServiceImpl
					updateProgress(3, 9);
					try {
						ServiceImplConfig config = historyConfig.getServiceImplConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtServiceImplName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), projectPath, txtServiceImplPackage.getText(),
									txtServiceImplName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成ServiceImpl-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成ServiceImpl:" + txtServiceImplName.getText() + "失败:" + e);
						LOG.error("执行生成ServiceImpl-->失败:", e);
					}
					// 生成SQL
					updateProgress(4, 9);
					try {
						DaoConfig config = historyConfig.getDaoConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtSqlName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), projectPath, txtSqlPackage.getText(),
									txtSqlName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成DAO-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成DAO:" + txtSqlName.getText() + "失败:" + e);
						LOG.error("执行生成DAO-->失败:", e);
					}
					// 生成Router
					updateProgress(5, 9);
					try {
						ControllerConfig config = historyConfig.getControllerConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtRouterName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), projectPath, txtRouterPackage.getText(),
									txtRouterName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成Controller-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成Controller:" + txtRouterName.getText() + "失败:" + e);
						LOG.error("执行生成Controller-->失败:", e);
					}
					// 生成单元测试
					updateProgress(6, 9);
					try {
						UnitTestConfig config = historyConfig.getUnitTestConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtUnitTestName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), projectPath, txtUnitTestPackage.getText(),
									txtUnitTestName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成单元测试-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成单元测试:" + txtUnitTestName.getText() + "失败:" + e);
						LOG.error("执行生成单元测试-->失败:", e);
					}
					// 生成SqlAssist
					updateProgress(6, 9);
					try {
						SqlAssistConfig config = historyConfig.getAssistConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtAssistName.getText() + ""));
							CreateFileUtil.createFile(content, config.getTemplateName(), projectPath, txtAssistPackage.getText(),
									txtAssistName.getText() + Constant.JAVA_SUFFIX, codeFormat, config.isOverrideFile());
						}
						LOG.debug("执行生成SqlAssist-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成SqlAssist:" + txtAssistName.getText() + "失败:" + e);
						LOG.error("执行生成SqlAssist-->失败:", e);
					}
					// 生成Mapper
					updateProgress(8, 9);
					try {
						MapperConfig config = historyConfig.getMapperConfig();
						if (!StrUtil.isNullOrEmpty(config.getTemplateName())) {
							updateMessage(runCreateTipsText + " {t} ...".replace("{t}", txtMapperPackage.getText() + ""));
							String templateName = config.getTemplateName();
							if (templateName.equals(Constant.TEMPLATE_NAME_MAPPER)) {
								templateName = selectedDatabaseConfig.getDbType() + Constant.TEMPLATE_NAME_MAPPER_SUFFIX;
							}
							CreateFileUtil.createFile(content, templateName, projectPath, txtMapperPackage.getText(), txtMapperName.getText(), codeFormat,
									config.isOverrideFile());
						}
						LOG.debug("执行生成Mapper-->成功!");
					} catch (Exception e) {
						updateMessage("执行生成Mapper:" + txtMapperName.getText() + "失败:" + e);
						LOG.error("执行生成Mapper-->失败:", e);
					}

					CustomConfig config = historyConfig.getCustomConfig();
					if (config.getTableItem() != null) {
						for (TableAttributeKeyValueTemplate custom : config.getTableItem()) {
							if (!StrUtil.isNullOrEmpty(custom.getTemplateValue())) {
								try {
									String loCase = StrUtil.fristToLoCase(txtEntityName.getText());
									String cpackage = custom.getPackageName().replace("{C}", txtEntityName.getText()).replace("{c}", loCase);
									String name = custom.getClassName().replace("{C}", txtEntityName.getText()).replace("{c}", loCase);
									updateMessage(runCreateTipsText + " {t} ...".replace("{t}", custom.getKey() + ""));
									CreateFileUtil.createFile(content, custom.getTemplateValue(), projectPath, cpackage, name + custom.getSuffix(),
											codeFormat, config.isOverrideFile());
								} catch (Exception e) {
									updateMessage("执行生成自定义生成包类:" + custom.getKey() + "失败:" + e);
									LOG.error("执行生成自定义生成包类-->失败:", e);
								}
							}
						}
					}
					updateProgress(9, 9);
					loadIndexConfigInfo(historyConfigName == null ? "default" : historyConfigName);
					updateMessage("创建成功!");
					LOG.debug("执行创建-->成功!");
					return null;
				}
			};
			probCreateAll.progressProperty().bind(task.progressProperty());
			lblRunCreateAllTips.textProperty().bind(task.messageProperty());
			new Thread(task).start();
		} catch (Exception e) {
			AlertUtil.showErrorAlert("创建文件失败:" + e);
			LOG.error("执行创建-->失败:", e);
		}
	}

	/**
	 * 保存配置文件
	 * 
	 * @param event
	 */
	public void onSaveConfig(ActionEvent event) {
		LOG.debug("执行保存配置文件...");
		// 检查是否类名是否存在占位符
		boolean indexOf = StrUtil.indexOf("{c}", txtEntityName.getText(), txtServiceName.getText(), txtServiceImplName.getText(),
				txtRouterName.getText(), txtSqlName.getText(), txtUnitTestName.getText());
		if (!indexOf) {
			StringProperty property = Main.LANGUAGE.get(LanguageKey.INDEX_SAVE_CONFIG_NOT_C_TIPS);
			String title = property == null ? "所有类名里面必须包含用于替换表名的占位符: {c}" : property.get();
			AlertUtil.showWarnAlert(title);
			return;
		}
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("保存当前配置");
		StringProperty property = Main.LANGUAGE.get(LanguageKey.INDEX_SAVE_CONFIG_TIPS);
		String title = property == null ? "请输入配置名称:\\r\\n(表名不在保存范围内必须通过数据库加载!!!)" : property.get();
		dialog.setContentText(title);
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			String name = result.map(n -> n).orElse("null");
			try {
				HistoryConfig config = getThisHistoryConfig();
				config.setHistoryConfigName(name);
				ConfigUtil.saveHistoryConfig(config);
				AlertUtil.showInfoAlert("保存配置成功!");
				LOG.debug("保存配置成功!");
			} catch (Exception e) {
				AlertUtil.showErrorAlert("保存配置失败!失败原因:\r\n" + e.getMessage());
				LOG.error("保存配置失败!!!" + e);
			}
		}
	}

	/**
	 * 数据库连接
	 * 
	 * @param event
	 */
	public void onConnection(MouseEvent event) {
		StringProperty property = Main.LANGUAGE.get(LanguageKey.PAGE_CREATE_CONNECTION);
		String title = property == null ? "新建数据库连接" : property.get();
		ConnectionController controller = (ConnectionController) loadFXMLPage(title, FXMLPage.CONNECTION, false);
		controller.setIndexController(this);
		controller.showDialogStage();
	}

	/**
	 * 配置信息
	 * 
	 * @param event
	 */
	public void onConfig(MouseEvent event) {
		HistoryConfigController controller = (HistoryConfigController) loadFXMLPage("配置信息管理", FXMLPage.HISTORY_CONFIG, false);
		controller.setIndexController(this);
		controller.showDialogStage();

	}

	/**
	 * 使用说明
	 * 
	 * @param event
	 */
	public void onInstructions(MouseEvent event) {
		AboutController controller = (AboutController) loadFXMLPage("使用说明", FXMLPage.ABOUT, false, false);
		controller.showDialogStage();
	}

	/**
	 * 打开设置的事件
	 * 
	 * @param event
	 */
	public void onSetting(MouseEvent event) {
		SettingController controller = (SettingController) loadFXMLPage("设置", FXMLPage.SETTING, false, false);
		controller.showDialogStage();
	}

	/**
	 * 选择项目文件
	 * 
	 * @param event
	 */
	public void onSelectProjectPath(ActionEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File file = directoryChooser.showDialog(super.getPrimaryStage());
		if (file != null) {
			txtProjectPath.setText(file.getPath());
			LOG.debug("选择文件项目目录:" + file.getPath());
		}
	}

	/**
	 * 打开设置实体类
	 * 
	 * @param event
	 */
	public void onSetEntity(ActionEvent event) {
		if (selectedTableName == null) {
			StringProperty property = Main.LANGUAGE.get(LanguageKey.INDEX_TIPS_SELECT_TABLE_NAME);
			String tips = property == null ? "请先选择数据库表!打开左侧数据库双击表名便可加载..." : property.get();
			AlertUtil.showWarnAlert(tips);
			return;
		}
		SetEntityAttributeController controller = (SetEntityAttributeController) loadFXMLPage("Entity Attribute Setting",
				FXMLPage.SET_ENTITY_ATTRIBUTE, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	/**
	 * 打开设置Service
	 * 
	 * @param event
	 */
	public void onSetService(ActionEvent event) {
		SetServiceController controller = (SetServiceController) loadFXMLPage("Service Setting", FXMLPage.SET_ROUTER_SERVICE, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	/**
	 * 打开设置Service
	 * 
	 * @param event
	 */
	public void onSetServiceImpl(ActionEvent event) {
		SetServiceImplController controller = (SetServiceImplController) loadFXMLPage("Service implement Setting",
				FXMLPage.SET_ROUTER_SERVICE_IMPL, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	/**
	 * 打开设置Router
	 * 
	 * @param event
	 */
	public void onSetRouter(ActionEvent event) {
		SetRouterController controller = (SetRouterController) loadFXMLPage("Controller Setting", FXMLPage.SET_ROUTER, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	/**
	 * 打开设置SetSQL
	 * 
	 * @param event
	 */
	public void onSetSQL(ActionEvent event) {
		SetSqlController controller = (SetSqlController) loadFXMLPage("DAO Setting", FXMLPage.SET_SQL, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	/**
	 * 打开设置SetSqlAssist
	 * 
	 * @param event
	 */
	public void onSetSqlAssist(ActionEvent event) {
		SetSqlAssistController controller = (SetSqlAssistController) loadFXMLPage("Assist Setting", FXMLPage.SET_ASSIST, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	/**
	 * 打开设置SqlAndParams
	 * 
	 * @param event
	 */
	public void onSetMapper(ActionEvent event) {
		SetMapperController controller = (SetMapperController) loadFXMLPage("Mapper Setting", FXMLPage.SET_MAPPER, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	/**
	 * 打开设置单元测试
	 * 
	 * @param event
	 */
	public void onSetUnitTest(ActionEvent event) {
		SetUnitTestController controller = (SetUnitTestController) loadFXMLPage("UnitTest Setting", FXMLPage.SET_UNIT_TEST, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	/**
	 * 打开设置SetCustom
	 * 
	 * @param event
	 */
	public void onSetCustom(ActionEvent event) {
		SetCustomController controller = (SetCustomController) loadFXMLPage("SetCustom Setting", FXMLPage.SET_CUSTOM, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}

	/**
	 * 打开设置CustomProperty
	 * 
	 * @param event
	 */
	public void onSetCustomProperty(ActionEvent event) {
		SetCustomPropertyController controller = (SetCustomPropertyController) loadFXMLPage("SetCustomProperty Setting",
				FXMLPage.SET_CUSTOM_PROPERTY, false);
		controller.setIndexController(this);
		controller.showDialogStage();
		controller.init();
	}
	// ======================get/set============================
	/**
	 * 获得当前选择数据库的信息
	 * 
	 * @return
	 */
	public DatabaseConfig getSelectedDatabaseConfig() {
		return selectedDatabaseConfig;
	}

	/**
	 * 设置当前选择数据库的信息
	 * 
	 * @param selectedDatabaseConfig
	 */
	public void setSelectedDatabaseConfig(DatabaseConfig selectedDatabaseConfig) {
		this.selectedDatabaseConfig = selectedDatabaseConfig;
	}

	/**
	 * 获得更新数据库选择的配置文件
	 * 
	 * @return
	 */
	public DatabaseConfig getUpdateOfDatabaseConfig() {
		return updateOfDatabaseConfig;
	}

	/**
	 * 设置更新数据库选择的配置文件
	 * 
	 * @param updateOfDatabaseConfig
	 */
	public void setUpdateOfDatabaseConfig(DatabaseConfig updateOfDatabaseConfig) {
		this.updateOfDatabaseConfig = updateOfDatabaseConfig;
	}

	/**
	 * 获得配置信息的名字
	 * 
	 * @return
	 */
	public String getHistoryConfigName() {
		return historyConfigName;
	}

	/**
	 * 设置配置信息的名字
	 * 
	 * @param historyConfigName
	 */
	public void setHistoryConfigName(String historyConfigName) {
		this.historyConfigName = historyConfigName;
	}

	/**
	 * 获得配置信息
	 * 
	 * @return
	 */
	public HistoryConfig getHistoryConfig() {
		return historyConfig;
	}

	/**
	 * 设置配置信息
	 * 
	 * @param historyConfig
	 */
	public void setHistoryConfig(HistoryConfig historyConfig) {
		this.historyConfig = historyConfig;
	}

	/**
	 * 获得当前数据库选择表的名字
	 * 
	 * @return
	 */
	public String getSelectedTableName() {
		return selectedTableName;
	}

	/**
	 * 设置当前数据库选择表的名字
	 * 
	 * @param selectedTableName
	 */
	public void setSelectedTableName(String selectedTableName) {
		this.selectedTableName = selectedTableName;
	}

	/**
	 * 获得模板文件夹现有模板名字
	 * 
	 * @return
	 */
	public List<String> getTemplateNameItems() {
		return templateNameItems;
	}

	/**
	 * 模板文件夹现有模板名字
	 * 
	 * @param templateNameItems
	 */
	public void setTemplateNameItems(List<String> templateNameItems) {
		this.templateNameItems = templateNameItems;
	}

}
