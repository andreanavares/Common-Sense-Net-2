package com.commonsensenet.realfarm.model.aggregate;

import java.util.Hashtable;

/**
 * 
 * @author Oscar Bola�os <@oscarbolanos>
 */
public class AggregateItem {

	private int mActionTypeId;
	// selectors in a list?
	private long selector1 = -1;
	private long selector2 = -1;
	private long selector3 = -1;
	private Hashtable<String, String> mAggregateValues;
	
	private int leftBackground = -1;
	private int centerBackground = -1;
	private int rightBackground = -1;
	private String leftText = "";
	private String centerText = "";
	private String rightText = "";
	private String newsText = "";
	private int leftImage = -1;
	private int centerImage = -1;
	private int leftBottomImage = -1;
	

	public AggregateItem(int actionTypeId) {
		mActionTypeId = actionTypeId;
		// initializes the data structure
		mAggregateValues = new Hashtable<String, String>();

	}

	public void addValue(String key, String value) {
		mAggregateValues.put(key, value);
	}

	public int getActionTypeId() {
		return mActionTypeId;
	}
	public String getValue(String key) {
		return mAggregateValues.get(key);
	}

	public void setActionTypeId(int value) {
		mActionTypeId = value;
	}
	
	public void setRightText(String text){
		rightText = text;
	}
	
	public String getRightText(){
		return rightText;
	}
	
	public void setRightBackground(int res){
		rightBackground = res;
	}
	
	public int getRightBackground(){
		return rightBackground;
	}
	
	public void setLeftBackground(int res){
		leftBackground = res;
	}
	
	public int getLeftBackground(){
		return leftBackground;
	}
	
	public void setCenterBackground(int res){
		centerBackground = res;
	}
	
	public int getCenterBackground(){
		return centerBackground;
	}
	
	public void setCenterText(String text){
		centerText = text;
	}
	
	public String getCenterText(){
		return centerText;
	}
	
	public void setLeftText(String text){
		leftText = text;
	}
	
	public String getLeftText(){
		return leftText;
	}
	
	public void setNewsText(String text){
		newsText = text;
	}
	
	public String getNewsText(){
		return newsText;
	}
	
	public void setLeftImage(int img){
		leftImage = img;
	}
	
	public int getLeftImage(){
		return leftImage;
	}	
	
	public void setCenterImage(int img){
		centerImage = img;
	}
	
	public int getCenterImage(){
		return centerImage;
	}	
	
	public void setLeftBottomImage(int img){
		leftBottomImage = img;
	}
	
	public int getLeftBottomImage(){
		return leftBottomImage;
	}	

	public void setSelector1(long sel1) {
		selector1 = sel1;
	}
	
	public long getSelector1() {
		return selector1;
	}	
	
	public void setSelector2(long sel2) {
		selector2 = sel2;
	}	
	
	public long getSelector2() {
		return selector2;
	}	
	
	public void setSelector3(long sel3) {
		selector3 = sel3;
	}	
	
	public long getSelector3() {
		return selector3;
	}
}
