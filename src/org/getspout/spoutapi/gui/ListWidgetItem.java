package org.getspout.spoutapi.gui;

public class ListWidgetItem {
	String title;
	String text;
	GenericListWidget listWidget;
	public ListWidgetItem(String title, String text) {
		this.title = title;
		this.text = text;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setListWidget(GenericListWidget genericListWidget) {
		listWidget = genericListWidget;
	}
}
