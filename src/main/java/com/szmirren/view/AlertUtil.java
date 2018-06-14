package com.szmirren.view;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertUtil {
	/**
	 * 信息提示框
	 * 
	 * @param message
	 */
	public static void showInfoAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText(message);
		alert.show();
	}

	/**
	 * 等待信息提示框
	 * 
	 * @param message
	 */
	public static void showAndWaitInfoAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText(message);
		alert.showAndWait();
	}

	/**
	 * 注意提示框
	 * 
	 * @param message
	 */
	public static void showWarnAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setContentText(message);
		alert.show();
	}

	/**
	 * 异常提示框
	 * 
	 * @param message
	 */
	public static void showErrorAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(message);
		alert.show();
	}

	/**
	 * 确定提示框
	 * 
	 * @param message
	 */
	public static boolean showConfirmAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText(message);
		Optional<ButtonType> optional = alert.showAndWait();
		if (ButtonType.OK == optional.get()) {
			return true;
		} else {
			return false;
		}
	}
}
